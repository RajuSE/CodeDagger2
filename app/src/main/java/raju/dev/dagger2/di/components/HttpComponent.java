package raju.dev.dagger2.di.components;


import android.content.Context;
import android.content.SharedPreferences;


import okhttp3.OkHttpClient;

import javax.inject.Singleton;

import dagger.Component;
import raju.dev.dagger2.di.modules.MyAppModule;
import raju.dev.dagger2.di.modules.MyHttpModule;
import retrofit2.Retrofit;

@Singleton
@Component(modules={MyAppModule.class, MyHttpModule.class})
public interface HttpComponent {
    // downstream components need these exposed
    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}