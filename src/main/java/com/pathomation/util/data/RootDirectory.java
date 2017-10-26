package com.pathomation.util.data;

public class RootDirectory {

    private String incorrectPath;
    private String correctPath;
    private String alias;
    private String username;
    private String password;
    private String domainName;
    private boolean visibilityState;

    public RootDirectory() {
    }

    public RootDirectory(String incorrectPath, String correctPath, String alias, String username, String password, String domainName, boolean visibilityState) {
        this.incorrectPath = incorrectPath;
        this.correctPath = correctPath;
        this.alias = alias;
        this.username = username;
        this.password = password;
        this.domainName = domainName;
        this.visibilityState = visibilityState;
    }

    public void setIncorrectPath(String path) {
        this.incorrectPath = path;
    }

    public void setCorrectPath(String path) {
        this.correctPath = path;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public void setVisibilityState(boolean visibilityState) {
        this.visibilityState = visibilityState;
    }

    public String getCorrectPath() {
        return correctPath;
    }

    public String getIncorrectPath() {
        return incorrectPath;
    }

    public String getAlias() {
        return alias;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDomainName() {
        return domainName;
    }

    public boolean getVisibilityState() {
        return visibilityState;
    }
}
