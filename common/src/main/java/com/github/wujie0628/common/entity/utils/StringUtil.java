package com.github.wujie0628.common.entity.utils;

public class StringUtil {

    public static String ReplacementInfo(StringBuilder stringBuilder, String keyword, String before, String rear) {
        //字符第一次出现的位置
        int index = stringBuilder.indexOf(keyword);
        while (index != -1) {
            stringBuilder.insert(index, before);
            stringBuilder.insert(index + before.length() + keyword.length(), rear);
            //下一次出现的位置，
            index = stringBuilder.indexOf(keyword, index + before.length() + keyword.length() + rear.length() - 1);
        }
        return stringBuilder.toString();
    }

}
