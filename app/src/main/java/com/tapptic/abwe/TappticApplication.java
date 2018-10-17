package com.tapptic.abwe;

import android.app.Activity;
import android.app.Application;

import com.tapptic.abwe.component.ApplicationComponent;
import com.tapptic.abwe.component.DaggerApplicationComponent;
import com.tapptic.abwe.module.ContextModule;


public class TappticApplication extends Application {

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

       applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);


    }

    public static TappticApplication get(Activity activity){
        return (TappticApplication) activity.getApplication();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

