package com.example.demo.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "ofuser")
public class Ofuser {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "storedkey")
    private String storedkey;

    @Column(name = "serverkey")
    private String serverkey;

    @Column(name = "salt")
    private String salt;

    @Column(name = "iterations")
    private int iterations;

    @Column(name = "plainpassword")
    private String plainpassword;

    @Column(name = "encryptedpassword")
    private String encryptedpassword;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

//    @Column(name = "creationdate", updatable = false)
//    @CreationTimestamp
    @Column(name = "creationdate")
    private String createTime;

//    @Column(name = "modificationdate", insertable = false)
//    @UpdateTimestamp
    @Column(name = "modificationdate")
    private String updateTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStoredkey() {
        return storedkey;
    }

    public void setStoredkey(String storedkey) {
        this.storedkey = storedkey;
    }

    public String getServerkey() {
        return serverkey;
    }

    public void setServerkey(String serverkey) {
        this.serverkey = serverkey;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public String getPlainpassword() {
        return plainpassword;
    }

    public void setPlainpassword(String plainpassword) {
        this.plainpassword = plainpassword;
    }

    public String getEncryptedpassword() {
        return encryptedpassword;
    }

    public void setEncryptedpassword(String encryptedpassword) {
        this.encryptedpassword = encryptedpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
