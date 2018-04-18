package com.tingfeng.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


/**
 * 机能概要:用于传递给CAS服务器验证数据
 */
public class SysUser {

    @JsonProperty("id")
    private String id;

    @NotNull
    private String username;

    @JsonProperty("@class")
    //需要返回实现org.apereo.cas.authentication.principal.Principal的类名接口
    private String clazz = "org.apereo.cas.authentication.principal.SimplePrincipal";

    @JsonProperty("attributes")
    private Map<String, Object> attributes = new HashMap<>();

    @JsonIgnore
    private String password;

    @JsonIgnore
    //用户是否不可用
    private boolean disabled = false;

    @JsonIgnore
    //用户是否过期
    private boolean expired = false;

    @JsonIgnore
    //是否锁定
    private boolean locked = false;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
