package com.goodsure.frameworkdemo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/6/5.
 */

@Entity
public class Shop {
    @Id(autoincrement = true)
    private long id;

    @Generated(hash = 1321762033)
    public Shop(long id) {
        this.id = id;
    }

    @Generated(hash = 633476670)
    public Shop() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
