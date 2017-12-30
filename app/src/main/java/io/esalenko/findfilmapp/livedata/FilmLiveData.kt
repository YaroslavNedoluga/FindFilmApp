package io.esalenko.findfilmapp.livedata

import android.arch.lifecycle.LiveData
import io.esalenko.findfilmapp.model.DetailedFilm
import io.esalenko.findfilmapp.service.RestLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info


class FilmLiveData : LiveData<DetailedFilm>(), AnkoLogger {

    private val restLoader: RestLoader = RestLoader()

    fun getFilmByID(id: Int, locale: String) {
        restLoader
                .getFilmByID(id, locale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { film: DetailedFilm? ->
                            value = film
                        },
                        { throwable: Throwable ->
                            error { throwable.printStackTrace() }
                        },
                        {
                            info { "onComplete() = getFilmByID($id)" }
                        })
    }
}