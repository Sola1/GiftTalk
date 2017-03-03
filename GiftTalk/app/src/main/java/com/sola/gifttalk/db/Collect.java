package com.sola.gifttalk.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 17/3/3.
 */
@Entity
public class Collect {
    @Id
    private Long id;
    private String url;
    @Generated(hash = 1717528835)
    public Collect(Long id, String url) {
        this.id = id;
        this.url = url;
    }
    @Generated(hash = 1726975718)
    public Collect() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
