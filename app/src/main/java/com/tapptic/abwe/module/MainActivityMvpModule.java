package com.tapptic.abwe.module;

import com.tapptic.abwe.mvp.ViewModule;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityMvpModule {
    private final ViewModule.View mView;


    public MainActivityMvpModule(ViewModule.View mView) {
        this.mView = mView;
    }

    @Provides
    ViewModule.View provideView() {
        return mView;
    }


}
