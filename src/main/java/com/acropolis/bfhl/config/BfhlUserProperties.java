package com.acropolis.bfhl.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bfhl.user")
public class BfhlUserProperties {

    private String fullName = "sayansh_pal";
    private String dobDdmmyyyy = "30102005";
    private String email = "sayanshpal@gmail.com";
    private String rollNumber = "0827CI231121";

    public String getUserId() {
        return fullName + "_" + dobDdmmyyyy;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDobDdmmyyyy() {
        return dobDdmmyyyy;
    }

    public void setDobDdmmyyyy(String dobDdmmyyyy) {
        this.dobDdmmyyyy = dobDdmmyyyy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
}
