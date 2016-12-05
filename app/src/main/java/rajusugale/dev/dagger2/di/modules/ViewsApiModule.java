package rajusugale.dev.dagger2.di.modules;


import dagger.Module;
import dagger.Provides;
import rajusugale.dev.dagger2.ViewsApiEnd;
import rajusugale.dev.dagger2.di.scopes.UserScope;
import retrofit.Retrofit;

@Module
public class ViewsApiModule {

    @Provides
    @UserScope
    public ViewsApiEnd providesGitHubInterface(Retrofit retrofit) {
        return retrofit.create(ViewsApiEnd.class);
    }
}
