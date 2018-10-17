package com.tapptic.abwe.module;

import com.tapptic.abwe.mvp.ViewModule;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private final ViewModule.View mView;


    public FragmentModule(ViewModule.View mView) {
        this.mView = mView;
    }

    @Provides
    ViewModule.View provideView() {
        return mView;
    }


}
