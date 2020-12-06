package com.github.tangmonkmeat.vo;

import com.sun.istack.internal.NotNull;

/**
 * 微信用户信息
 *
 * @author zwl
 * @date 2020/12/5 22:12
 */
public class WxUserVO {

    /**
     * 传入参数：临时登录凭证
     */
    @NotNull
    private String code;

    /**
     * 传入参数: 用户非敏感信息
     */
    @NotNull
    private String rawData;

    /**
     * 传入参数: 签名
     */
    @NotNull
    private String signature;

    /**
     * 传入参数: 用户敏感信息
     */
    @NotNull
    private String encryptedData;

    /**
     * 传入参数: 解密算法的向量
     */
    @NotNull
    private String iv;


    private String avatarUrl;

    private String city;

    private String country;

    private Byte gender;

    private String nickName;

    private String province;

    @Override
    public String toString() {
        return "WxUserVo{" +
                "code='" + code + '\'' +
                ", rawData='" + rawData + '\'' +
                ", signature='" + signature + '\'' +
                ", encryptedData='" + encryptedData + '\'' +
                ", iv='" + iv + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", gender=" + gender +
                ", nickName='" + nickName + '\'' +
                ", province='" + province + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRawData() {
        return rawData;
    }

    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
