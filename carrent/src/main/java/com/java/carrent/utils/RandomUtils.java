package com.java.carrent.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 随机工具类
 */
public class RandomUtils {

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss_SSS");
    private static Random random = new Random();

    /**
     * 获取当前时间
     * @return
     */
    public static String getCurrentDateForString() {
        return sdf1.format(new Date());
    }

    /**
     * 生成文件名使用时间+4位随机数+临时后缀
     * @param fileName 文件名称
     * @param suffix   临时文件后缀
     */
    public static String createFileNameUseTime(String fileName, String suffix) {
//        获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
//        获取当前时间，并格式化
        String date = sdf2.format(new Date());
//        获取4个随机数，最大是9999
        int num = random.nextInt(9000) + 1000;

        return date + num + fileSuffix + suffix;
    }

    /**
     * 生成文件名使用时间+4位随机数
     * @param fileName
     * @return
     */
    public static String createFileNameUseTime(String fileName) {
        //        获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
//        获取当前时间，并格式化
        String date = sdf2.format(new Date());
//        获取4个随机数，最大是9999
        int num = random.nextInt(9000) + 1000;

        return date + num + fileSuffix;
    }

    /**
     * 根据给出的前缀 + 当前时间 + 5位随机数生成的字符串
     * @param prefix
     * @return
     */
    public static String createRandomStringUseTime(String prefix) {
        String separator = "_";
        return prefix + separator + sdf3.format(new Date()) + separator + (random.nextInt(90000) + 10000);
    }
}
