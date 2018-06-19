package com.robotcms.api.config;

/**
 * <pre>
 * </pre>
 * 
 *
 */
public class JWTConfig {
    private String userPrimaryKey;
    /**
     * jwt过期时间，单位为毫秒
     */
    private Long expireTime;

    public String getUserPrimaryKey() {
        return userPrimaryKey;
    }

    public void setUserPrimaryKey(String userPrimaryKey) {
        this.userPrimaryKey = userPrimaryKey;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "JWTConfig [userPrimaryKey=" + userPrimaryKey + ", expireTime=" + expireTime + "]";
    }

}
