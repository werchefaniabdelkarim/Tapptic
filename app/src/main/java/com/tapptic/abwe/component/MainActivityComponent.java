package com.tapptic.abwe.component;

import com.tapptic.abwe.module.MainActivityContextModule;
import com.tapptic.abwe.module.MainActivityMvpModule;
import com.tapptic.abwe.ui.MainActivity;

import dagger.Component;


@Component(modules = { MainActivityMvpModule.class,MainActivityContextModule.class}, dependencies = ApplicationComponent.class)
@PerActivityScope
public interface MainActivityComponent {


    void injectMainActivity(MainActivity mainActivity);
}
