package com.pq.agency.entity;

import java.sql.Timestamp;

public class CouponUsedLog {
    private Integer id;

    private String userId;

    private Integer couponBatchId;

    private Integer couponId;

    private String parentMerchantNo;

    private String childMerchantNo;

    private String channelNo;

    private String channelName;

    private String paymentType;

    private String paymentName;

    private Integer discountAmount;

    private Integer platformCostAmount;

    private Integer parentMerchantCostAmount;

    private Integer childMerchantCostAmount;

    private String payOrderNo;

    private Integer calculationAmount;

    private Timestamp payOrderTime;

    private Integer status;

    private Timestamp createTime;

    private Timestamp updateTime;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getCouponBatchId() {
        return couponBatchId;
    }

    public void setCouponBatchId(Integer couponBatchId) {
        this.couponBatchId = couponBatchId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo == null ? null : parentMerchantNo.trim();
    }

    public String getChildMerchantNo() {
        return childMerchantNo;
    }

    public void setChildMerchantNo(String childMerchantNo) {
        this.childMerchantNo = childMerchantNo == null ? null : childMerchantNo.trim();
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo == null ? null : channelNo.trim();
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType == null ? null : paymentType.trim();
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName == null ? null : paymentName.trim();
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Integer getPlatformCostAmount() {
        return platformCostAmount;
    }

    public void setPlatformCostAmount(Integer platformCostAmount) {
        this.platformCostAmount = platformCostAmount;
    }

    public Integer getParentMerchantCostAmount() {
        return parentMerchantCostAmount;
    }

    public void setParentMerchantCostAmount(Integer parentMerchantCostAmount) {
        this.parentMerchantCostAmount = parentMerchantCostAmount;
    }

    public Integer getChildMerchantCostAmount() {
        return childMerchantCostAmount;
    }

    public void setChildMerchantCostAmount(Integer childMerchantCostAmount) {
        this.childMerchantCostAmount = childMerchantCostAmount;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo == null ? null : payOrderNo.trim();
    }

    public Integer getCalculationAmount() {
        return calculationAmount;
    }

    public void setCalculationAmount(Integer calculationAmount) {
        this.calculationAmount = calculationAmount;
    }

    public Timestamp getPayOrderTime() {
        return payOrderTime;
    }

    public void setPayOrderTime(Timestamp payOrderTime) {
        this.payOrderTime = payOrderTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}