package com.nhn.web.ongame.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Table(name = "poker_profile")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ongameId;
    private String nickName;
    private String regDate;
    private String lastDate;
    private String ip;
    private String name;
    private String url;

    public PokerProfile() {
    }

    public PokerProfile(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public PokerProfile(long id, String ongameId, String nickName, String regDate, String lastDate, String ip, String name, String url) {
        this.id = id;
        this.ongameId = ongameId;
        this.nickName = nickName;
        this.regDate = regDate;
        this.lastDate = lastDate;
        this.ip = ip;
        this.name = name;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOngameId() {
        return ongameId;
    }

    public void setOngameId(String ongameId) {
        this.ongameId = ongameId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
