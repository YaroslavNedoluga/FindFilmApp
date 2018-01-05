package io.esalenko.findfilmapp.service

import io.esalenko.findfilmapp.helper.ApiHelper
import io.esalenko.findfilmapp.helper.RetrofitHelper
import io.esalenko.findfilmapp.model.DetailedFilm
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.model.PopularFilm
import io.reactivex.Observable
import java.util.*


class RestManager {

    private val restService: RestService = RetrofitHelper().getRetrofitInstance()

    fun getPopularFilms(page: Int, locale: String): Observable<List<Film>> {
        return restService
                .getPopularFilms(
                        ApiHelper.apiKey,
                        locale,
                        page,
                        locale)
                .map { popularFilms -> popularFilms.results!! }
    }

    fun getFilmByID(id: Int, locale: String): Observable<DetailedFilm> {
        return restService
                .getFilmByID(
                        id,
                        ApiHelper.apiKey,
                        locale)
    }


    // TODO :: Unite two response to one Observable (Concat)
    fun getRandomFilm(locale: String): Observable<Film> {
        return restService
                .getPopularFilms(ApiHelper.apiKey, locale, 1, ApiHelper.region)
                .map { films: PopularFilm -> films.totalPages }
                .map { totalPages: Int -> Random().nextInt(totalPages - 1) + 1 }
                .concatMap { page: Int -> restService.getPopularFilms(ApiHelper.apiKey, locale, page, ApiHelper.region) }
                .map { popularFilm: PopularFilm -> popularFilm.results }
                .map { films: List<Film> -> films.first() }
    }

}