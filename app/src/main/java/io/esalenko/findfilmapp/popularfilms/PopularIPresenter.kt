package io.esalenko.findfilmapp.popularfilms

import android.content.Context
import io.esalenko.findfilmapp.IPresenter
import io.esalenko.findfilmapp.hepler.ApiHelper
import io.esalenko.findfilmapp.service.RestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PopularIPresenter @Inject constructor(private val view: PopularFilmView, private val service: RestService, val context: Context) : IPresenter {

    private var page = 1

    override fun load() {

        service.getPopularFilms(ApiHelper.getApiKey(context),
                ApiHelper.getLocale(context),
                page,
                ApiHelper.region)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy { popularFilms ->
                    view.showPopularFilms(popularFilms.results)
                }

    }
}