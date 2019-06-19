package com.wanggang.common.other;

import com.ctrip.framework.apollo.core.utils.StringUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vdurmont.emoji.EmojiParser;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-22 11:37
 **/
public class PinYinUtils {


    private static Logger log = LoggerFactory.getLogger(PinYinUtils.class);

    /**
     * 获取汉字首字母的方法。如： 张长三a --> [ZCSA, ZZSA]
     *
     * @param HanZi 汉字字符串
     * @return 大写汉字首字母; 如果都转换失败,那么返回null
     */
    public static List<String> getHanZiShortPinYin(String HanZi) {
        if (StringUtils.isEmpty(HanZi))
            return Lists.newArrayList();

        return getHanZiPinYin(HanZi, PinYinUtils::toShortPinyinStringArray);
    }

    /**
     * 获取汉字拼音的方法。如： 张长三a --> [ZHANGCHANGSANA, ZHANGZHANGSANA]
     *
     * @param HanZi 汉字字符串
     * @return 汉字拼音; 如果都转换失败,那么返回null
     */
    public static List<String> getHanZiLongPinYin(String HanZi) {
        if (StringUtils.isEmpty(HanZi))
            return Lists.newArrayList();

        return getHanZiPinYin(HanZi, PinYinUtils::toLongPinyinStringArray);
    }

    private static List<String> getHanZiPinYin(String HanZi, Function<Character, Set<String>> sth) {
        char[] charArray = HanZi.toCharArray();

        List<Set<String>> sets = Lists.newArrayList();

        for (char ch : charArray) {
            if (!isChineseChar(ch)) {
                Set<String> characters = Sets.newHashSet(String.valueOf(ch).toUpperCase());
                sets.add(characters);
                continue;
            }

            Set<String> strings = sth.apply(ch);
            if (strings.size() > 0)
                sets.add(strings);
        }

        List<String> strings = cartesianProduct(sets);

        return strings;
    }

    private static Set<String> toLongPinyinStringArray(char ch) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        try {
            String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch, format);

            if (stringArray != null)
                return Sets.newHashSet(Arrays.asList(stringArray));

        } catch (Exception ex) {
            log.error("汉字转拼音", ex);
        }

        return Sets.newHashSet();
    }

    private static Set<String> toShortPinyinStringArray(char ch) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        try {
            String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch, format);

            Set<String> sets = Sets.newHashSet();
            for (String one : stringArray) {
                sets.add(String.valueOf(one.charAt(0)));
            }
            return sets;
        } catch (Exception ex) {
            log.error("汉字转拼音", ex);
        }

        return Sets.newHashSet();
    }

    private static List<String> cartesianProduct(List<Set<String>> s) {
        Set<List<String>> lists = Sets.cartesianProduct(s);

        return lists.stream().map(i -> Joiner.on("").join(i)).collect(toList());
    }

    private static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination, InterruptedException {
//        System.out.println(PinYinUtils.getHanziLongPinYin("张长三a"));
//        System.out.println(PinYinUtils.getHanziShortPinYin("张长三a"));
//        System.out.println(PinYinUtils.getHanziLongPinYin("长长aa"));
//        System.out.println(PinYinUtils.getHanziShortPinYin("长长aa"));

        //[ZHANGCHANGSANA, ZHANGZHANGSANA]
        //[ZCSA, ZZSA]
        //[CHANGCHANGAA, CHANGZHANGAA, ZHANGCHANGAA, ZHANGZHANGAA]
        //[CCAA, CZAA, ZCAA, ZZAA]

        List<String> hanZiLongPinYin = PinYinUtils.getHanZiLongPinYin("凊亾兯");
        List<String> 凊亾兯 = PinYinUtils.getHanZiShortPinYin("凊亾兯");


        String s = EmojiParser.parseToAliases("李想:smirk:");

        String s1 = EmojiParser.parseToUnicode("李想:smirk:");

        String s2 = EmojiParser.parseToAliases(s1);

        System.out.println(s + "-----------------" + s1 + "-----------------" + s2);

        String msg = "如果上用字都要付款面:two_直接用几个样本对比就可以看出来区别hearts:实收一把上面那些字体放大一点费是对的不然谁费心费力的还设计新的字体？知识产权必须要尊重。现在谁都可以设计字体个人也可以你不愿意交费你可以自己设计自己用啊而且别人用你的字体也可以给你付费啊这很合那些字返回信息体放网易七鱼的宣传文案客服编号大一点看就看字上上面那些字体放大一点看就看字上面那个点你就能看面那个点你就能看上面那些字体放大一点看就看字上面那个点你就能看登录]上面那些字体放大一点看就看 字 上面那个点你就能看根据用户id获取用户信息 返回下一个伪随机数，它是取自此随机数生成器序列的均匀轮播图，商品图片视频url查如下表分它是取自此随机数生成器序列的、在0.0和1.0之间均匀分布布的 long 值sxyp_member计算返利业务";
        for (int i = 0; i < 10; i++)
            msg += msg;
        Set<String> sets = new HashSet<>();
        List<ImMyFriend> list = Lists.newArrayList();
        for (long i = 0; i < 2500; i++) {
            ImMyFriend myFriend = new ImMyFriend();
            myFriend.setAvatarUrl("");
            Random ran = new Random(i);
            String xxx = "";
            while (true) {
//                TimeUnit.MILLISECONDS.sleep(1);
                int i1 = ran.nextInt(msg.length());
                if (i1 <= 10)
                    i1 = 10;
                xxx = msg.substring(i1 - 10, i1);
                if (sets.add(xxx)) {
                    break;
                }
            }

            if (sets.size() > 300)
                break;


            myFriend.setNickName(xxx);
            myFriend.setUid(String.valueOf(i));
            list.add(myFriend);
        }

        long l = System.currentTimeMillis();
        list = list.stream().map(i -> {
            FormatImUserPinYin(i);
            return i;
        }).sorted((a, b) -> {
            if (a.getPinYinInfo() == null
                    || a.getPinYinInfo().getLongPinYin().size() == 0)
                return Integer.MAX_VALUE;

            if (b.getPinYinInfo() == null
                    || b.getPinYinInfo().getLongPinYin().size() == 0)
                return 0;

            char aChar = a.getPinYinInfo().getLongPinYin().get(0).charAt(0);
            char bChar = b.getPinYinInfo().getLongPinYin().get(0).charAt(0);


            if (aChar == bChar)
                return 0;

            if (!String.valueOf(aChar).matches("[A-Z]")
                    && !String.valueOf(bChar).matches("[A-Z]")) {
                return String.valueOf(aChar).compareTo(String.valueOf(bChar));
            }

            if (!String.valueOf(aChar).matches("[A-Z]")) {
                return Integer.MAX_VALUE;
            }

            if (!String.valueOf(bChar).matches("[A-Z]")) {
                return Integer.MIN_VALUE;
            }

            return String.valueOf(aChar).compareTo(String.valueOf(bChar));
        }).collect(Collectors.toList());

        long l2 = System.currentTimeMillis();


        System.out.println(list);
        list.forEach(t -> {
            System.out.println(t.getPinYinInfo().getLongPinYin().get(0) + "-" + t.getNickName());
        });
        System.out.println(l2 - l);
    }

    private static void FormatImUserPinYin(ImMyFriend user) {
        if (user == null || StringUtils.isEmpty(user.getNickName()))
            return;

        ImUserPinYinInfo pinYinInfo = new ImUserPinYinInfo();

        List<String> hanZiShortPinYin = PinYinUtils.getHanZiShortPinYin(user.getNickName());
        List<String> hanZiLongPinYin = PinYinUtils.getHanZiLongPinYin(user.getNickName());
        pinYinInfo.setLongPinYin(hanZiLongPinYin);
        pinYinInfo.setShortPinYin(hanZiShortPinYin);

        user.setPinYinInfo(pinYinInfo);
    }
}
