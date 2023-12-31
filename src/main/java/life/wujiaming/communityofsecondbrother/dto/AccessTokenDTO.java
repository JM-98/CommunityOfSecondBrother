package life.wujiaming.communityofsecondbrother.dto;

/**
 * @author Wu JiaMing, head of National Aeronautics and Space Administration
 * @date 20/07/2023 17:19
 */
public class AccessTokenDTO {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String state;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
