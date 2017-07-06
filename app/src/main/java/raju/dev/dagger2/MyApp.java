package raju.dev.dagger2;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import raju.dev.dagger2.di.components.DaggerViewsApiComponent;
import raju.dev.dagger2.di.components.DaggerHttpComponent;
import raju.dev.dagger2.di.components.ViewsApiComponent;
import raju.dev.dagger2.di.components.HttpComponent;
import raju.dev.dagger2.di.modules.MyAppModule;
import raju.dev.dagger2.di.modules.MyHttpModule;
import raju.dev.dagger2.di.modules.ViewsApiModule;


public class MyApp extends Application {

    private HttpComponent mHttpComponent;
    private ViewsApiComponent mViewsApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // specify the full namespace of the component
        // Dagger_xxxx (where xxxx = component name)
        mHttpComponent = DaggerHttpComponent.builder()
                .myAppModule(new MyAppModule(this))
                .myHttpModule(new MyHttpModule("https://api.github.com",cm))
                .build();

        mViewsApiComponent = DaggerViewsApiComponent.builder()
                .httpComponent(mHttpComponent)
                .viewsApiModule(new ViewsApiModule())
                .build();

    }

    public HttpComponent getNetComponent() {
        return mHttpComponent;
    }

    public ViewsApiComponent getGitHubComponent() {
        return mViewsApiComponent;
    }
}