package io.esalenko.findfilmapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.esalenko.findfilmapp.MainActivity
import io.esalenko.findfilmapp.common.BaseActivity
import io.esalenko.findfilmapp.dagger.scope.ActivityScope

@Module(includes = arrayOf(AndroidSupportInjectionModule::class))
interface AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(BaseActivityModule::class))
    fun baseActivity() : BaseActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    fun mainActivity(): MainActivity

}

