package com.shiro.mmc.system.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * @packageName：com.shiro.mmc.system.session
 * @desrciption:
 * @author: gaowei
 * @date： 2017-12-05 16:48
 * @history: (version) author date desc
 */
public class AutoSessionDao extends CachingSessionDAO {

    private JdbcTemplate jdbcTemplate = JDBCTemplateUtils.jdbcTemplate();

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        String sql = "insert into T_SESSION(id, session) values(?,?)";
        jdbcTemplate.update(sql, sessionId, SerializableUtils.serialize(session));
        return session.getId();
    }
    @Override
    protected void doUpdate(Session session) {
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
        String sql = "update T_SESSION set session=? where id=?";
        jdbcTemplate.update(sql, SerializableUtils.serialize(session), session.getId());
    }
    @Override
    protected void doDelete(Session session) {
        String sql = "delete from T_SESSION where id=?";
        jdbcTemplate.update(sql, session.getId());
    }
    @Override
    protected Session doReadSession(Serializable sessionId) {
        String sql = "select session from T_SESSION where id=?";
        List<String> sessionStrList = jdbcTemplate.queryForList(sql, String.class, sessionId);
        if(sessionStrList.size() == 0) {
            return null;
        }
        return SerializableUtils.deserialize(sessionStrList.get(0));
    }
}
