/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanntk.registration;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationErr implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String confirmNotMatch;
    private String fullNameLengthErr;

    private String usernameIsExitsted;

    public RegistrationErr() {
    }

    public RegistrationErr(String usernameLengthErr, String passwordLengthErr, String confirmNotMatch, String fullNameLengthErr, String usernameIsExitsted) {
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.confirmNotMatch = confirmNotMatch;
        this.fullNameLengthErr = fullNameLengthErr;
        this.usernameIsExitsted = usernameIsExitsted;
    }



    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr= fullNameLengthErr;
    }

    public String getUsernameIsExitsted() {
        return usernameIsExitsted;
    }

    public void setUsernameIsExitsted(String usernameIsExitsted) {
        this.usernameIsExitsted = usernameIsExitsted;
    }

    
    
}
