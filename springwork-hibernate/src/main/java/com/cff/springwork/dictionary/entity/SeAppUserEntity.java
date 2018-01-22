package com.cff.springwork.dictionary.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "se_app_user", schema = "cff")
public class SeAppUserEntity {
    private String uuid;
    private String userName;
    private String passwd;
    private String userType;
    private String userNo;

    @Basic
    @Column(name = "uuid", nullable = false, length = 32)
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 40)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "passwd", nullable = true, length = 32)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "user_type", nullable = true, length = 4)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Id
    @Column(name = "user_no", nullable = false, length = 16)
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeAppUserEntity that = (SeAppUserEntity) o;
        return Objects.equals(uuid, that.uuid) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(passwd, that.passwd) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(userNo, that.userNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid, userName, passwd, userType, userNo);
    }
}
