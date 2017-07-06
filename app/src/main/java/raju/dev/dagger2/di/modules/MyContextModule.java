package raju.dev.dagger2.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by RajuSE on 7/6/2017.
 */

@Module
public class MyContextModule {

    Context context;

    public MyContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context providesContext() {
        return context;
    }
}
