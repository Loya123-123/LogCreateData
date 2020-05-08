package com.loya.appclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.loya.Factory.Factory;
import com.loya.bean.AppBase;
import com.loya.server.IEventsLog;
import com.loya.server.impl.EventsLog;
import com.loya.util.PackEventJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;



public class LogCreateData {
    private final static Logger logger = LoggerFactory.getLogger(LogCreateData.class);
    private static Random rand = new Random();

    // 设备id
    public static int s_mid = 0;

    // 用户id
    public static int s_uid = 0;

    // 商品id
    private static int s_goodsid = 0;

    public static void main(String[] args) {
        // 参数一：控制发送每条的延时时间，默认是0
        Long delay = args.length > 0 ? Long.parseLong(args[0]) : 0L;

        // 参数二：循环遍历次数
        int loop_len = args.length > 1 ? Integer.parseInt(args[1]) : 1000;

        generateLog(delay,loop_len);


    }

    /**
     *  json 框架搭建出来 {"ap":"app","cm":{},"et":[]}
     * @param delay 每条时间间隔 默认 0
     * @param loop_len 条数 默认 1000
     */
    public static void generateLog(Long delay, int loop_len) {
        for (int i = 0; i < loop_len; i++) {

            JSONObject json = new JSONObject();
            json.put("ap","app");

            json.put("cm",generateComFields());

            json.put("et",eventslog());

            long millis = System.currentTimeMillis();

            logger.info(millis+"|"+json.toJSONString());
            // 延迟
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONArray eventslog() {
        IEventsLog eventsLog = Factory.getService(EventsLog.class,IEventsLog.class);

        JSONArray eventsArray = new JSONArray();

        eventsArray.add(eventsLog.generateStart());
        // 事件日志
        // 商品曝光
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generateDisplay(s_goodsid));
        }

        // 商品详情页
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generateNewsDetail(s_goodsid));
        }

        // 商品列表页
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generateNewList());
        }

        // 购物车
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generateCart(s_mid));
        }

        // 广告
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generateAd());
        }

        // 消息通知
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generateNotification());
        }

        //故障日志
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generateError());
        }

        // 用户评论
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generateComment());
        }

        // 用户收藏
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generateFavorites());
        }

        // 用户点赞
        if (rand.nextBoolean()) {
            eventsArray.add(eventsLog.generatePraise());
        }

        return eventsArray;
    }

    /**
     * 公共字段设置
     */
    private static JSONObject generateComFields() {


        AppBase appBase = new AppBase();

        //设备id
        appBase.setMid(s_mid + "");
        s_mid++;

        // 用户id
        appBase.setUid(s_uid + "");
        s_uid++;

        // 程序版本号 5,6等
        appBase.setVc("" + rand.nextInt(20));

        //程序版本名 v1.1.1
        appBase.setVn("1." + rand.nextInt(4) + "." + rand.nextInt(10));

        // 安卓系统版本
        appBase.setOs("8." + rand.nextInt(3) + "." + rand.nextInt(10));

        // 语言  es,en,pt
        int flag = rand.nextInt(3);
        switch (flag) {
            case (0):
                appBase.setL("es");
                break;
            case (1):
                appBase.setL("en");
                break;
            case (2):
                appBase.setL("pt");
                break;
        }

        // 渠道号   从哪个渠道来的
        appBase.setSr(PackEventJson.getRandomChar(1));

        // 区域
        flag = rand.nextInt(2);
        switch (flag) {
            case 0:
                appBase.setAr("BR");
            case 1:
                appBase.setAr("MX");
        }

        // 手机品牌 ba ,手机型号 md，就取2位数字了
        flag = rand.nextInt(3);
        switch (flag) {
            case 0:
                appBase.setBa("Sumsung");
                appBase.setMd("sumsung-" + rand.nextInt(20));
                break;
            case 1:
                appBase.setBa("Huawei");
                appBase.setMd("Huawei-" + rand.nextInt(20));
                break;
            case 2:
                appBase.setBa("HTC");
                appBase.setMd("HTC-" + rand.nextInt(20));
                break;
        }

        // 嵌入sdk的版本
        appBase.setSv("V2." + rand.nextInt(10) + "." + rand.nextInt(10));
        // gmail
        appBase.setG(PackEventJson.getRandomCharAndNumr(8) + "@gmail.com");

        // 屏幕宽高 hw
        flag = rand.nextInt(4);
        switch (flag) {
            case 0:
                appBase.setHw("640*960");
                break;
            case 1:
                appBase.setHw("640*1136");
                break;
            case 2:
                appBase.setHw("750*1134");
                break;
            case 3:
                appBase.setHw("1080*1920");
                break;
        }

        // 客户端产生日志时间
        long millis = System.currentTimeMillis();
        appBase.setT("" + (millis - rand.nextInt(99999999)));

        // 手机网络模式 3G,4G,WIFI
        flag = rand.nextInt(3);
        switch (flag) {
            case 0:
                appBase.setNw("3G");
                break;
            case 1:
                appBase.setNw("4G");
                break;
            case 2:
                appBase.setNw("WIFI");
                break;
        }

        // 拉丁美洲 西经34°46′至西经117°09；北纬32°42′至南纬53°54′
        // 经度
        appBase.setLn((-34 - rand.nextInt(83) - rand.nextInt(60) / 10.0) + "");
        // 纬度
        appBase.setLa((32 - rand.nextInt(85) - rand.nextInt(60) / 10.0) + "");

        return (JSONObject) JSON.toJSON(appBase);
    }

}
