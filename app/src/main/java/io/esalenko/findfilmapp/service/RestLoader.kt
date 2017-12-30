package io.esalenko.findfilmapp.service

import io.esalenko.findfilmapp.helper.ApiHelper
import io.esalenko.findfilmapp.helper.RetrofitHelper
import io.esalenko.findfilmapp.model.DetailedFilm
import io.esalenko.findfilmapp.model.Film
import io.reactivex.Observable


class RestLoader {

    private val restService: RestService = RetrofitHelper().getRetrofitInstance()!!

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
}