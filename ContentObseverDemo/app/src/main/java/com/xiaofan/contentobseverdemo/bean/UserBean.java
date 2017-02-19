package com.xiaofan.contentobseverdemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * @author: 范建海
 * @createTime: 2017/2/19 11:23
 * @className:  UserBean
 * @description: 用户实体Bean
 * @changed by:
 */
@Entity
public class UserBean implements Serializable {
    @Id
    private Long id;
    @NotNull
    private String userName;
    @Property
    private String age;
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 330542831)
    public UserBean(Long id, @NotNull String userName, String age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
