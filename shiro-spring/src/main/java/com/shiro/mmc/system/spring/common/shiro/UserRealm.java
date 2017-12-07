package com.shiro.mmc.system.spring.common.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * @packageName：com.shiro.mmc.system.spring.common.shiro
 * @desrciption: 自定义认证领域
 * @author: gaowei
 * @date： 2017-12-07 17:23
 * @history: (version) author date desc
 */
public class UserRealm extends AuthorizingRealm{

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 授权认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if (principalCollection == null) {
            throw new AuthorizationException(
                    "PrincipalCollection method argument cannot be null.");
        }

        String userName = (String) getAvailablePrincipal(principalCollection);
        // 模拟用户角色
        Set<String> roles = new HashSet<>();
        if ("user".equals(userName)) {
            roles.add("user");
        } else if ("admin".equals(userName)) {
            roles.add("admin");
        } else {
            roles.add("user");
            roles.add("admin");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        return info;
    }

    /**
     * 用户信息认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("用户登录认证....");
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        // 登录用户信息
        String userName = upToken.getUsername();
        log.debug("用户登录认证 userName:{}", userName);

        // 从数据库中取出用户信息
        // 4. 若用于不存在， 则可以抛出UnknownAccountException 异常
        if ("unknown".equals(userName)) {
            throw new UnknownAccountException("用户信息不存在！");
        }
        // 5. 根据用户信息的情况，决定是否需要抛出其它的AuthenticationException 异常
        if ("other".equals(userName)) {
            throw new LockedAccountException("用户被锁定！");
        }
        // 6. 根据用户情况，来构建AuthenticationInfo 对象并返回, 通常使用的实现类SimpleAuthenticationInfo
        /**
         * 创建SimpleAuthenticationInfo入参分析，一般采用三个参数入参创建对象
         * SimpleAuthenticationInfo(Object principal, Object credentials, String realmName)
         * principal：认证信息，可以是username，也可以是数据表对应的用户实体对象.
         * credentials：密码.
         * credentialsSalt：当前realm对象的name，调用父类的getName()方法即可.
         */
        Object principal = userName;
        // 密码采用加密后的值
//        Object credentials = "123456";
        Object credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";
        if ("admin".equals(userName)) {
            credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
        } else if ("user".equals(userName)){
            credentials = "098d2c478e9c11555ce2823231e02ec1";
        }
        String realmName = getName();

        /**
         * 登录用户名密码是交给Shiro完成，只需要将登录realm和从数据库中获取的密码
         */
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, realmName);
        /**
         * 保证相同密码加密之后的值不同，采用盐值（salt）保证
         * 在创建SimpleAuthenticationInfo对象返回值的时候，需要使用带有入参盐值（salt）创建
         * ByteSource credentialsSalt
         * 可以使用salt区分某个用户的密码加密之后的唯一性，确定salt取值问题，可以使用登录用户作为盐值，登录用户名是唯一的
         */
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);

        return authenticationInfo;
    }
}
