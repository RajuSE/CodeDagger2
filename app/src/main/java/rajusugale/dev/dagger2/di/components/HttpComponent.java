package rajusugale.dev.dagger2.di.components;


import android.content.SharedPreferences;


import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Component;
import rajusugale.dev.dagger2.di.modules.MyAppModule;
import rajusugale.dev.dagger2.di.modules.MyHttpModule;
import retrofit.Retrofit;

@Singleton
@Component(modules={MyAppModule.class, MyHttpModule.class})
public interface HttpComponent {
    // downstream components need these exposed
    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}