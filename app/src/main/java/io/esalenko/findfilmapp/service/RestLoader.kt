package io.esalenko.findfilmapp.service

import io.esalenko.findfilmapp.hepler.ApiHelper
import io.esalenko.findfilmapp.model.Film
import io.reactivex.Observable


class RestLoader(private val restService: RestService,
                 private var apiHelper: ApiHelper) {

    fun getPopularFilms(page: Int): Observable<List<Film>> {
        return restService
                .getPopularFilms(
                        apiHelper.getApiKey(),
                        apiHelper.getLocale(),
                        page,
                        apiHelper.region)
                .map { popularFilms -> popularFilms.results!! }
    }
}