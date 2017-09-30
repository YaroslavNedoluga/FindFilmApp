package io.esalenko.findfilmapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.esalenko.findfilmapp.MainActivity
import io.esalenko.findfilmapp.dagger.scope.ActivityScope

@Module
interface AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    fun mainActivity(): MainActivity

}

