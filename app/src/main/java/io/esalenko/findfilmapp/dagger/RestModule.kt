package io.esalenko.findfilmapp.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.service.RestService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class RestModule {

    @Singleton
    @Provides
    fun retrofit(context: Context): Retrofit {
        return Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun restService(retrofit: Retrofit): RestService = retrofit.create(RestService::class.java)

}

