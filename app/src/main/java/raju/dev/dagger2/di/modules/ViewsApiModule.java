package raju.dev.dagger2.di.modules;


import dagger.Module;
import dagger.Provides;
import raju.dev.dagger2.ViewsApiEnd;
import raju.dev.dagger2.di.scopes.UserScope;
import retrofit2.Retrofit;

@Module
public class ViewsApiModule {

    @Provides
    @UserScope
//TODO Uncommenting UserScope creates different objects of ViewsApiEnd (Nice Article-http://frogermcs.github.io/dependency-injection-with-dagger-2-custom-scopes/)
    //UserScope - custom scope for user(User login to logout)
    //Singletone - Application scope
    //ActivityScope - custom scope for activity
    public ViewsApiEnd providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(ViewsApiEnd.class);
    }
}
