package rajusugale.dev.dagger2;

import android.app.Application;

import rajusugale.dev.dagger2.di.components.DaggerViewsApiComponent;
import rajusugale.dev.dagger2.di.components.DaggerHttpComponent;
import rajusugale.dev.dagger2.di.components.ViewsApiComponent;
import rajusugale.dev.dagger2.di.components.HttpComponent;
import rajusugale.dev.dagger2.di.modules.MyAppModule;
import rajusugale.dev.dagger2.di.modules.MyHttpModule;
import rajusugale.dev.dagger2.di.modules.ViewsApiModule;


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