package com.shiro.mmc.system.spring.common.utils;

import com.shiro.mmc.system.spring.entity.UserEntity;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @packageName：com.shiro.mmc.system.spring.common.utils
 * @desrciption: 用户加密工具类
 * @author: gaowei
 * @date： 2017-12-08 17:20
 * @history: (version) author date desc
 */
public class PasswordHelper {

    /**
     * logger
     */
    private static final Logger log = LoggerFactory.getLogger(PasswordHelper.class);

    /**
     * 加密salt（盐值）
     */
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    /**
     * 加密算法
     */
    private String algorithmName = "MD5";
    /**
     * 加密次数
     */
    private int hashIterations = 1024;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }


    /**
     * 加密
     * @param user
     */
    public void encryptPassword(UserEntity user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();
        log.debug("加密后密码 {}", newPassword);
        user.setPassword(newPassword);
    }
}
