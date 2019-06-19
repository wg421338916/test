package com.wanggang.common.other;

/**
 * @program: sxyp_mini_api
 * @description: 我的好友
 * @author: Mr.WG
 * @create: 2019-05-21 10:19
 **/
public class ImMyFriend extends ImBaseUserInfo {
    private String imUserId;

    private ImUserPinYinInfo pinYinInfo;

    public ImUserPinYinInfo getPinYinInfo() {
        return pinYinInfo;
    }

    public void setPinYinInfo(ImUserPinYinInfo pinYinInfo) {
        this.pinYinInfo = pinYinInfo;
    }

    /**
     * 标签:1.粉丝;2.邀请人
     */
    private Integer flag;

    public String getImUserId() {
        return imUserId;
    }

    public void setImUserId(String imUserId) {
        this.imUserId = imUserId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
