package webservice_data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String emailAddress;
    private String password;
    private String installationType;
    private String token;
    private String userId;

    public User(){}
    
    public User(String email, String password) {
        this.emailAddress = email;
        this.password = password;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String email) {
        this.emailAddress = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getInstallationType() {
        return installationType;
    }
    public void setInstallationType(String installationType) {
        this.installationType = installationType;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}