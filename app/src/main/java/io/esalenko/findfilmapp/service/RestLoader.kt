package io.esalenko.findfilmapp.service

import android.content.Context
import io.esalenko.findfilmapp.helper.ApiHelper
import io.esalenko.findfilmapp.helper.RetrofitHelper
import io.esalenko.findfilmapp.model.Film
import io.reactivex.Observable


class RestLoader(context: Context) {

    private val restService: RestService = RetrofitHelper().getRetrofitInstance(context)!!
    private val apiHelper: ApiHelper = ApiHelper(context)

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