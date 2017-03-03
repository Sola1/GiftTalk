package com.sola.gifttalk.db;

import com.sola.gifttalk.bean.HotSearchBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 17/3/2.
 */
@Entity
public class Search {
    @Id
    private Long id;
    private String words;
    private String things;
    @Generated(hash = 90252692)
    public Search(Long id, String words, String things) {
        this.id = id;
        this.words = words;
        this.things = things;
    }
    @Generated(hash = 1644193961)
    public Search() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getWords() {
        return this.words;
    }
    public void setWords(String words) {
        this.words = words;
    }
    public String getThings() {
        return this.things;
    }
    public void setThings(String things) {
        this.things = things;
    }
}
