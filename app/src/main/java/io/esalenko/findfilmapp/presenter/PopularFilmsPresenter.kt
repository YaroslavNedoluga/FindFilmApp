package io.esalenko.findfilmapp.presenter

import android.content.Context
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.view.PopularFilmsView
import io.esalenko.findfilmapp.service.RestLoader
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class PopularFilmsPresenter(private val view: PopularFilmsView, context: Context) : AnkoLogger {

    private val restLoader: RestLoader = RestLoader(context)

    fun loadPopularFilms(page: Int) {
        restLoader.getPopularFilms(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { films: List<Film> -> view.showFilmsList(films) },
                        { throwable: Throwable -> throwable.printStackTrace() },
                        {info { "onComplete() = getPopularFilms()" } })
    }


}