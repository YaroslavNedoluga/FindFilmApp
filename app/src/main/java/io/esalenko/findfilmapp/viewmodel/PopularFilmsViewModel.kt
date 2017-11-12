package io.esalenko.findfilmapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.service.RestLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class PopularFilmsViewModel : ViewModel(), AnkoLogger {

    private val liveData: MutableLiveData<List<Film>> = MutableLiveData()

    private val restLoader: RestLoader = RestLoader()


    fun loadPopularFilms(page: Int, locale: String): MutableLiveData<List<Film>> {
        restLoader.getPopularFilms(page, locale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { films: List<Film> ->
                            liveData.value = films
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

        return liveData
    }


}