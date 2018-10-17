package com.tapptic.abwe.module;

import android.content.Context;

import com.tapptic.abwe.component.PerActivityScope;
import com.tapptic.abwe.ui.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityContextModule {
    private MainActivity mainActivity;

    public Context context;

    public MainActivityContextModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
    }

    @Provides
    @PerActivityScope
    public MainActivity providesMainActivity() {
        return mainActivity;
    }

    @Provides
    @PerActivityScope
    public Context provideContext() {
        return context;
    }

}
