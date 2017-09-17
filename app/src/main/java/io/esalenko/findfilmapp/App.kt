package io.esalenko.findfilmapp

import android.app.Activity
import android.support.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.esalenko.findfilmapp.dagger.DaggerAppComponent
import javax.inject.Inject


class App : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = injector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
                .builder()
                .context(this)
                .build()
                .inject(this)
    }

}