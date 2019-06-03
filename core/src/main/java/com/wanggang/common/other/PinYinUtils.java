package com.wanggang.common.other;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.logging.log4j.util.Strings;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-22 11:37
 **/
public class PinYinUtils {


    /**
     * 获取汉字首字母的方法。如： 张长三a --> [ZCSA, ZZSA]
     *
     * @param hanZi 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static List<String> getHanziShortPinYin(String hanZi) {
        return getHanziPinYin(hanZi, PinYinUtils::toShortHanyuPinyinStringArray);
    }

    /**
     * 获取汉字拼音的方法。如： 张长三a --> [ZHANGCHANGSANA, ZHANGZHANGSANA]
     *
     * @param hanZi 汉子字符串
     * @return 汉字拼音; 如果都转换失败,那么返回null
     */
    public static List<String> getHanziLongPinYin(String hanZi) {
        return getHanziPinYin(hanZi, PinYinUtils::toLongHanyuPinyinStringArray);
    }

    private static List<String> getHanziPinYin(String hanZi, Function<Character, Set<String>> sth) {
        char[] charArray = hanZi.toCharArray();

        List<Set<String>> sets = Lists.newArrayList();

        for (char ch : charArray) {
            if (!isChineseChar(ch)) {
                Set<String> characters = Sets.newHashSet(String.valueOf(ch).toUpperCase());
                sets.add(characters);
                continue;
            }

            Set<String> strings = sth.apply(ch);
            sets.add(strings);
        }

        List<String> strings = cartesianProduct(sets);

        return strings;
    }

    private static Set<String> toLongHanyuPinyinStringArray(char ch) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        try {
            String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch, format);

            if (stringArray != null)
                return Sets.newConcurrentHashSet(Arrays.asList(stringArray));

        } catch (Exception ex) {
        }

        return Sets.newHashSet();
    }

    private static Set<String> toShortHanyuPinyinStringArray(char ch) {
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

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        System.out.println(PinYinUtils.getHanziLongPinYin("张长三a"));
        System.out.println(PinYinUtils.getHanziShortPinYin("张长三a"));
        System.out.println(PinYinUtils.getHanziLongPinYin("长长aa"));
        System.out.println(PinYinUtils.getHanziShortPinYin("长长aa"));

        //[ZHANGCHANGSANA, ZHANGZHANGSANA]
        //[ZCSA, ZZSA]
        //[CHANGCHANGAA, CHANGZHANGAA, ZHANGCHANGAA, ZHANGZHANGAA]
        //[CCAA, CZAA, ZCAA, ZZAA]
    }
}
