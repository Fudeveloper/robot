package com.robotcms.api.pojo.vo;

/**
 * <pre>
 * </pre>
 * 
 *
 */
public class TokenVO {

    private String token;
    private String refleshToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefleshToken() {
        return refleshToken;
    }

    public void setRefleshToken(String refleshToken) {
        this.refleshToken = refleshToken;
    }

    @Override
    public String toString() {
        return "TokenVO [token=" + token + ", refleshToken=" + refleshToken + "]";
    }
}
