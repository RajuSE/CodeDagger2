package raju.dev.dagger2.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

@Module
public class MyHttpModule {

    String mBaseUrl;
    ConnectivityManager connectivityManager;

    public MyHttpModule(String baseUrl, ConnectivityManager connectivityManager) {
        this.mBaseUrl = baseUrl;
        this.connectivityManager = connectivityManager;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }


    @Provides  // Dagger will only look for methods annotated with @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new RequestInterceptor(connectivityManager))
                .build();
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public class RequestInterceptor implements Interceptor {

        final ConnectivityManager connectivityManager;

        public RequestInterceptor(ConnectivityManager connectivityManager) {
            this.connectivityManager = connectivityManager;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            if (!isConnected()) {
                throw new NoInternetException("NoInternetException");
            }

            Request.Builder r = chain.request().newBuilder();
            return chain.proceed(r.build());
        }

        protected boolean isConnected() {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }

    }

    class NoInternetException extends IOException{

        String message;
        public NoInternetException(String s) {
            this.message =s;
        }

        String getMessaget(){
            return message;
        }
    }
}
