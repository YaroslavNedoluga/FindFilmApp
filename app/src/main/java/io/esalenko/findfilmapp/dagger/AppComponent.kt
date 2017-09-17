package io.esalenko.findfilmapp.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.esalenko.findfilmapp.App
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class
))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context) : Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

}