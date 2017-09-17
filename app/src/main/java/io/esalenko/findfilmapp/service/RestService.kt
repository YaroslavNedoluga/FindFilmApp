package io.esalenko.findfilmapp.service

import io.esalenko.findfilmapp.model.PopularFilms
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface RestService {

    @GET("movie/popular")
    fun getPopularFilms(@Query("api_key") key : String,
                        @Query("language") language : String,
                        @Query("page") page : Int,
                        @Query("region") region : String) : Observable<PopularFilms>

}