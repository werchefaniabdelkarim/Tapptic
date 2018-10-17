package com.tapptic.abwe.component;

import com.tapptic.abwe.TappticApplication;
import com.tapptic.abwe.module.ContextModule;
import com.tapptic.abwe.api.NetworkModule;
import com.tapptic.abwe.api.NetworkTappticService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, NetworkModule.class})
public interface ApplicationComponent {


    NetworkTappticService getApiInterface();


    /*@Provides
    @ApplicationContext
    Context getContext();*/

    void injectApplication(TappticApplication tappticApplication);
}
