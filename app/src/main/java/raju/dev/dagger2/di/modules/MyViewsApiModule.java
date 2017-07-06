package raju.dev.dagger2.di.modules;


import dagger.Module;
import dagger.Provides;
import raju.dev.dagger2.ViewsApiEnd;
import raju.dev.dagger2.di.scopes.UserScope;
import retrofit2.Retrofit;

@Module
public class MyViewsApiModule {

    @Provides
    @UserScope
    public ViewsApiEnd providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(ViewsApiEnd.class);
    }
}
