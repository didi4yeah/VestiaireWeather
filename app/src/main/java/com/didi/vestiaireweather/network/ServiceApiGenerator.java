package com.didi.vestiaireweather.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by didi on 11/12/2016.
 * API service generator for any kind
 */

public class ServiceApiGenerator {

    //To avoid instantiation
    private ServiceApiGenerator() {}

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    //Debugging (in chrome) interceptor
    private static StethoInterceptor stethoInterceptor = new StethoInterceptor();

    /**
     * Create an API specified service
     * @param serviceClass concerned service
     * @param baseUrl base url for this service
     * @param <S> class type service
     * @return Service newly created
     */
    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                //Add jackson converter to de/serialization
                .addConverterFactory(JacksonConverterFactory.create())
                //To use RxJava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        //Chrome plugin tool enabling
        if(!httpClient.networkInterceptors().contains(stethoInterceptor)) {
            httpClient.addNetworkInterceptor(stethoInterceptor);
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
