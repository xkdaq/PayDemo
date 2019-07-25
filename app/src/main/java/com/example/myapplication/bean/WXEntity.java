package com.example.myapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kekex on 2018/10/9.
 * 微信
 */

public class WXEntity implements Serializable {

    /**
     * package : Sign=WXPay
     * paySign : E6B2455019E91F17322A584B0F6AF2AA
     * appid : wxeacd5c31a7cfcb2c
     * partnerid : 1528319871
     * prepayid : wx21095943863522a757c021d93034573495
     * noncestr : eee031657c3c4ac0a0715d7d48862c32
     * timestamp : 1553133584
     */

    @SerializedName("package")
    private String packageX;
    private String paySign;
    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    @Override
    public String toString() {
        return "WXEntity{" +
                "packageX='" + packageX + '\'' +
                ", paySign='" + paySign + '\'' +
                ", appid='" + appid + '\'' +
                ", partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
