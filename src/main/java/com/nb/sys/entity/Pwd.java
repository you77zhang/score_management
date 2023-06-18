package com.nb.sys.entity;

public class Pwd {
    /**
     * 用户名
     */
    private String username;
    /**
     * 旧密码
     */
    private String prePassword;
    /**
     * 新密码
     */
    private String newPassword;


    public Pwd() {
    }

    public Pwd(String username, String prePassword, String newPassword) {
        this.username = username;
        this.prePassword = prePassword;
        this.newPassword = newPassword;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return prePassword
     */
    public String getPrePassword() {
        return prePassword;
    }

    /**
     * 设置
     * @param prePassword
     */
    public void setPrePassword(String prePassword) {
        this.prePassword = prePassword;
    }

    /**
     * 获取
     * @return newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * 设置
     * @param newPassword
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String toString() {
        return "Pwd{username = " + username + ", prePassword = " + prePassword + ", newPassword = " + newPassword + "}";
    }
}
