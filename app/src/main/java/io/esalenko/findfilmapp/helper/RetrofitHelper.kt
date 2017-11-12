package io.esalenko.findfilmapp.helper

import io.esalenko.findfilmapp.service.RestService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    private lateinit var retrofit: Retrofit
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()

    private val baseUrl: String = "https://api.themoviedb.org/3/"

    fun getRetrofitInstance(): RestService? {

        retrofitBuilder
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        retrofit = retrofitBuilder.build()

        return retrofit.create(RestService::class.java)
    }

}