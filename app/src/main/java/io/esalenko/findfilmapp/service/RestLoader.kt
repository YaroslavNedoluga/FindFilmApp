package io.esalenko.findfilmapp.service

import io.esalenko.findfilmapp.helper.ApiHelper
import io.esalenko.findfilmapp.helper.RetrofitHelper
import io.esalenko.findfilmapp.model.Film
import io.reactivex.Observable


class RestLoader {

    private val restService: RestService = RetrofitHelper().getRetrofitInstance()!!
    private val apiHelper: ApiHelper = ApiHelper()

    fun getPopularFilms(page: Int, locale: String): Observable<List<Film>> {
        return restService
                .getPopularFilms(
                        apiHelper.getApiKey(),
                        locale,
                        page,
                        apiHelper.region)
                .map { popularFilms -> popularFilms.results!! }
    }
}