package com.loya.util;

import com.alibaba.fastjson.JSONObject;
import com.loya.appclient.LogCreateData;


import java.io.UnsupportedEncodingException;
import java.util.Random;

public class PackEventJson {
    private static Random rand = new Random();

    /**
     * 为各个事件类型的公共字段（时间、事件类型、Json数据）拼接
     */

    public static JSONObject packEventJson(String eventName, JSONObject jsonObject) {

        int s_uid = LogCreateData.s_uid ;
        JSONObject eventJson = new JSONObject();

        eventJson.put("ett", (System.currentTimeMillis() - rand.nextInt(99999999)) + "");
        eventJson.put("en", eventName);
        eventJson.put("uid",s_uid);
        eventJson.put("kv", jsonObject);

        return eventJson;
    }
    /**
     * 获取随机字母组合
     *
     * @param length 字符串长度
     */
    public static String getRandomChar(Integer length) {

       StringBuffer str = new StringBuffer();

        for (int i = 0; i < length; i++) {
            str.append((char) (65 + rand.nextInt(26)));
        }

        return str.toString();
    }

    /**
     *  获取随机字母数据组合
     * @param length 字符串长度
     * @return
     */
    public static String getRandomCharAndNumr(Integer length) {

        StringBuilder str = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {

            boolean b = random.nextBoolean();

            if (b) { // 字符串
                // int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
                str.append((char) (65 + random.nextInt(26)));// 取得大写字母
            } else { // 数字
                str.append(String.valueOf(random.nextInt(10)));
            }
        }

        return str.toString();
    }
    /**
     * 生成单个汉字
     */
    public static char getRandomChar() {

        String str = "";
        int hightPos; //
        int lowPos;

        Random random = new Random();

        //随机生成汉子的两个字节
        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("错误");
        }

        return str.charAt(0);
    }

    /**
     * 拼接成多个汉字
     */
    public static String getCONTENT() {

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < rand.nextInt(100); i++) {
            str.append(getRandomChar());
        }

        return str.toString();
    }

}
