package com.h2g2.dontpanic.networking.base;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/23
 */
public class HttpClient extends OkHttpClient {
    public HttpClient() {

    }

    @Override
    public Call newCall(Request request) {
        return super.newCall(request);
    }
}
