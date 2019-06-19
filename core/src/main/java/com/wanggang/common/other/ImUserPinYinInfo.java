package com.wanggang.common.other;

import java.util.List;

/**
 * @program: sxyp_service
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-22 14:51
 **/
public class ImUserPinYinInfo {
    private List<String> shortPinYin;

    private List<String> longPinYin;

    public List<String> getShortPinYin() {
        return shortPinYin;
    }

    public void setShortPinYin(List<String> shortPinYin) {
        this.shortPinYin = shortPinYin;
    }

    public List<String> getLongPinYin() {
        return longPinYin;
    }

    public void setLongPinYin(List<String> longPinYin) {
        this.longPinYin = longPinYin;
    }
}
