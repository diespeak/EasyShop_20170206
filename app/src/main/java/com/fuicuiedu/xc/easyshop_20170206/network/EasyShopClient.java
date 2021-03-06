package com.fuicuiedu.xc.easyshop_20170206.network;

import com.fuicuiedu.xc.easyshop_20170206.model.User;
import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 整个项目的网络模块客户端
 */

public class EasyShopClient {
    private static EasyShopClient easyShopClient;

    private OkHttpClient okHttpClient;
    private Gson gson;

    private EasyShopClient(){
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        gson = new Gson();
    }

    public static EasyShopClient getInstance(){
        if (easyShopClient == null){
            easyShopClient = new EasyShopClient();
        }
        return easyShopClient;
    }

    /**
     * 注册
     * post
     *
     * @param username 用户名
     * @param password 密码
     * */
    public Call register(String username,String password){
        RequestBody requestBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.REGISTER)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }

    /**
     * 登录
     * post
     *
     * @param username 用户名
     * @param password 密码
     * */
    public Call login(String username,String password){
        RequestBody requestBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.LOGIN)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }

    //修改昵称
    public Call uploadUser(User user){
        //构架请求体（多部分形式）
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                //传一个用户实体类，转换为json字符串（Gson）
                .addFormDataPart("user",gson.toJson(user))
                .build();

        //构建请求
        Request request = new Request.Builder()
                .url(EasyShopApi.BASE_URL + EasyShopApi.UPDATA)
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
    }
}
