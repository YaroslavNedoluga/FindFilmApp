package io.esalenko.findfilmapp.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.esalenko.findfilmapp.hepler.ApiHelper
import javax.inject.Singleton


@Module
class ApiHelperModule {

    @Singleton
    @Provides
    fun apiHelper(context: Context): ApiHelper = ApiHelper(context)

}