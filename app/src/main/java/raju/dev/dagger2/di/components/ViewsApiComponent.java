package raju.dev.dagger2.di.components;


import dagger.Component;
import raju.dev.dagger2.MainActivity;
import raju.dev.dagger2.di.modules.MyViewsApiModule;
import raju.dev.dagger2.di.scopes.UserScope;

@UserScope
@Component(dependencies = HttpComponent.class, modules = MyViewsApiModule.class)
public interface ViewsApiComponent {
    void inject(MainActivity activity);

}
