package com.tapptic.abwe.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tapptic.abwe.module.ApplicationContext;
import com.tapptic.abwe.mvp.PresenterImpl;
import com.tapptic.abwe.mvp.ViewModule;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkModule {

    public NetworkModule() {
    }

    @Provides
    @ApplicationContext
    NetworkTappticService getApiInterface(Retrofit retroFit) {
        return retroFit.create(NetworkTappticService.class);
    }

    @Provides
    @Singleton
    Retrofit provideCall() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        // Customize the request
                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();
                        // Customize or return the response
                        return response;
                    }
                })

                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(NetworkTappticService.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public NetworkTappticService providesNetworkService(
             Retrofit retrofit) {
        return retrofit.create(NetworkTappticService.class);
    }
    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public PresenterImpl providesService(
            NetworkTappticService networkService, ViewModule.View viewModule) {
        return new PresenterImpl(networkService,viewModule);
    }

}
