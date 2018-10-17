package com.tapptic.abwe.component

import com.tapptic.abwe.module.FragmentModule
import com.tapptic.abwe.api.NetworkModule
import com.tapptic.abwe.ui.DetailFragment

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(FragmentModule::class, NetworkModule::class))
interface FragmentComponentDetail {
    //abstract fun inject(itemDetailFragment: DetailFragment)

    fun inject(detailFragment: DetailFragment)
}