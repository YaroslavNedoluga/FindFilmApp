package io.esalenko.findfilmapp.helper

import android.content.Context
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.service.RestService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper () {

    private lateinit var retrofit: Retrofit
    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()

    fun getRetrofitInstance(context: Context): RestService? {

        retrofitBuilder
                .baseUrl(context.resources.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

        retrofit = retrofitBuilder.build()

        return retrofit.create(RestService::class.java)
    }

}