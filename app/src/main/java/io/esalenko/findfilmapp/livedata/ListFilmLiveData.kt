package io.esalenko.findfilmapp.livedata

import android.arch.lifecycle.LiveData
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.service.RestLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class ListFilmLiveData : LiveData<List<Film>>(), AnkoLogger {

    private val restLoader: RestLoader = RestLoader()

    fun getFilmsList(page: Int = 1, locale: String) {
        restLoader.getPopularFilms(page, locale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { films: List<Film> ->
                            value = films
                            for (film in films) {
                                info { "$film" }
                            }
                        },
                        { throwable: Throwable ->
                            error { throwable.printStackTrace() }
                        },
                        {
                            info { "onComplete() = getPopularFilms()" }
                        })
    }

}