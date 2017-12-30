package io.esalenko.findfilmapp.service

import io.esalenko.findfilmapp.model.DetailedFilm
import io.esalenko.findfilmapp.model.PopularFilm
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RestService {

    @GET("movie/popular")
    fun getPopularFilms(@Query("api_key") key: String,
                        @Query("language") locale: String,
                        @Query("page") page: Int,
                        @Query("region") region: String): Observable<PopularFilm>

    @GET("movie/{movie_id}")
    fun getFilmByID(@Path("movie_id") id: Int,
                    @Query("api_key") api_key: String,
                    @Query("language") locale: String): Observable<DetailedFilm>
}