package com.wanggang.common.other;

import com.vdurmont.emoji.EmojiParser;

/**
 * @program: sxyp_mini_api
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-21 09:43
 **/
public class ImBaseUserInfo {
    private String avatarUrl;//头像	string	@mock=http://qmarket.oss-cn-shanghai.aliyuncs.com/qmarket/d723a881-de30-4101-ac23-4a8fd2af954c.jpg
    private String nickName;//昵称	string	@mock=18310000026
    private String uid;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
//        if (nickName!=null && nickName.length()>0&&nickName.contains(":")) {
//            //处理 emoji 与 mall_api方式相同
//            this.nickName = EmojiParser.parseToAliases(nickName);
//        }
    }

    public String getNickName() {
//        if (nickName!=null && nickName.length()>0&&nickName.contains(":")) {
//            //解析 emoji 与 mall_api方式相同
//            return EmojiParser.parseToUnicode(nickName);
//        }
        return nickName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
