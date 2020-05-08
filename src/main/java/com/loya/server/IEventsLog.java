package com.loya.server;

import com.alibaba.fastjson.JSONObject;

public interface IEventsLog {
    public JSONObject generateStart();

    public JSONObject generateDisplay(int s_goodsid);

    public JSONObject generateNewsDetail(int s_goodsid);

    public JSONObject generateNewList();

    public JSONObject generateCart(int s_mid);

    public JSONObject generateAd();

    public JSONObject generateNotification();

    public JSONObject generateError();

    public JSONObject generateComment();

    public JSONObject generateFavorites();

    public JSONObject generatePraise();

}
