package raju.dev.dagger2;

import android.app.Application;

import raju.dev.dagger2.di.components.DaggerHttpComponent;
import raju.dev.dagger2.di.components.DaggerViewsApiComponent;
import raju.dev.dagger2.di.components.ViewsApiComponent;
import raju.dev.dagger2.di.components.HttpComponent;
import raju.dev.dagger2.di.modules.MyContextModule;
import raju.dev.dagger2.di.modules.MyAppModule;
import raju.dev.dagger2.di.modules.MyHttpModule;
import raju.dev.dagger2.di.modules.ViewsApiModule;


public class MyApp extends Application {

    private HttpComponent mHttpComponent;
    private ViewsApiComponent mViewsApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // specify the full namespace of the component
        // Dagger_xxxx (where xxxx = component name)
        mHttpComponent = DaggerHttpComponent.builder()
                .myAppModule(new MyAppModule(this))
                .myHttpModule(new MyHttpModule("https://api.github.com"))
                .myContextModule(new MyContextModule(this))
                .build();

        mViewsApiComponent = DaggerViewsApiComponent.builder()
                .httpComponent(mHttpComponent)
                .build();
        //TODO NOTE: For Explicitly created Constructor of Module, You must call Module (like  .viewsApiModule(new ViewsApiModule()))
        // For DC, you don't need to specify that module.(E.g. ViewsApiModule). This is one of feature of Dagger2.
    }

    public HttpComponent getNetComponent() {
        return mHttpComponent;
    }

    public ViewsApiComponent getGitHubComponent() {
        return mViewsApiComponent;
    }
}