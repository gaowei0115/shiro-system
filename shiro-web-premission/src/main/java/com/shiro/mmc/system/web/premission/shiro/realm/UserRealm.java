package com.shiro.mmc.system.web.premission.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @packageName：com.shiro.mmc.system.web.premission.shiro.realm
 * @desrciption: UserRealm
 * @author: gaowei
 * @date： 2017/12/4 21:40
 * @history: (version) author date desc
 */
public class UserRealm extends AuthenticatingRealm{

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.debug("进入 userRealm {}", token);
        // 1. 把AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 2. 从UsernamePasswordToken 中获取userName
        String userName = upToken.getUsername();
        log.debug("登录用户信息UserName:{}, password:{}", userName, upToken.getPassword());
        // 3. 调用数据库的方法，从数据库中查询username 对应的记录
        log.debug("从数据库中获取UserName：{} 对应的用户信息.", userName);
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
            credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06";
        } else if ("user".equals(userName)){
            credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718";
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
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("realm2", credentials, credentialsSalt, realmName);

        return authenticationInfo;
    }

    public static void main(String[] args) {
        // 模拟计算用户存储的密码加密后的值
        String hashAlgorithmName = "SHA-1";
        String credentials =  "123456";
        String userName = "admin";
        Object salt = ByteSource.Util.bytes(userName);
        int hashIterations = 1024;

        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }
}
