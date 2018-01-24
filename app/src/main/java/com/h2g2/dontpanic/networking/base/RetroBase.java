package com.h2g2.dontpanic.networking.base;

import android.support.v7.app.AppCompatActivity;

import com.h2g2.dontpanic.networking.constants.Urls;
import com.h2g2.dontpanic.networking.utils.NetworkValidator;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by C. Serrano (cserrano@teravisiontech.com)
 * Teravision Technologies
 * Date: 2018/01/23
 */
public class RetroBase implements Urls {

    public Retrofit retrofit;
    private AppCompatActivity mActivity;

    public RetroBase(AppCompatActivity activity){
        mActivity= activity;
        Interceptor interceptor = new Interceptor();
        //client.interceptors().add(interceptor);
        retrofit = new Retrofit.Builder().client(new OkHttpClient.Builder()
                .addInterceptor(interceptor).build()).addConverterFactory(ScalarsConverterFactory
                .create().create()).baseUrl(Urls.baseUrl).build();
    }
    protected class Interceptor implements okhttp3.Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            if (NetworkValidator.isNetworkAvailable(mActivity)==false){
                throw new IOException();
                // SnackBar.makeMsg(getString(R.string.error_network), loginBinding.container );
                // Toast.makeText(mActivity.getApplicationContext(),mActivity.getString(R.string.error_network),Toast.LENGTH_LONG).canAnimate();
                //SnackBar.makeMsg(mActivity.getString(R.string.error_network),new View(mActivity));
            }
            Request request = chain.request();
            return chain.proceed(request);
        }
    }
}
