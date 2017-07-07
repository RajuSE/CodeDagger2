package raju.dev.dagger2.di.components;


import dagger.Component;
import raju.dev.dagger2.MainActivity;
import raju.dev.dagger2.SecondActivity;
import raju.dev.dagger2.di.modules.ViewsApiModule;

@Component(dependencies = HttpComponent.class, modules = ViewsApiModule.class)
public interface ViewsApiComponent {
    void inject(MainActivity activity);//TODO Note: Putting Activity willbe Failed because Dagger2 needs to know the concrete classes that will be injected at COMPILE time, not runtime (like Dagger 1 could do).

    void inject(SecondActivity activity2);//TODO Hence this way mutliple activities uses Component
}
