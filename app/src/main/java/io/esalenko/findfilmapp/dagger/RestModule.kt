package io.esalenko.findfilmapp.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.dagger.scope.ApplicationScope
import io.esalenko.findfilmapp.service.RestService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
abstract class RestModule {

    @ApplicationScope
    @Provides
    fun retrofit(context: Context): Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @ApplicationScope
    @Provides
    fun restService(retrofit: Retrofit): RestService = retrofit.create(RestService::class.java)

}

