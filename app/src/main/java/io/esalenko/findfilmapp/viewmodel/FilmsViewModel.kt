package io.esalenko.findfilmapp.viewmodel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.esalenko.findfilmapp.model.DetailedFilm
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.viewmodel.common.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info

class FilmsViewModel(app: Application) : BaseViewModel(app), AnkoLogger {

    private val listFilmLiveData = MutableLiveData<List<Film>>()
    private val filmLiveData = MutableLiveData<DetailedFilm>()
    private val randomFilmLiveData = MutableLiveData<Film>()

    fun loadPopularFilms(page: Int, locale: String): LiveData<List<Film>> {
        loadFilmsList(page, locale)
        return listFilmLiveData
    }

    fun getFilmByID(id: Int, locale: String): LiveData<DetailedFilm> {
        loadFilmByID(id, locale)
        return filmLiveData
    }

    fun getRandomFilm(locale: String): LiveData<Film> {
        loadRandomFilm(locale)
        return randomFilmLiveData
    }

    private fun loadFilmsList(page: Int = 1, locale: String) {
        restManager.getPopularFilms(page, locale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { films: List<Film> ->
                            listFilmLiveData.postValue(films)
                        },
                        { throwable: Throwable ->
                            kotlin.error { throwable.printStackTrace() }
                        },
                        {
                            info { "onComplete() = getPopularFilms()" }
                        })
    }

    private fun loadRandomFilm(locale: String) {
        restManager
                .getRandomFilm(locale)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { film: Film? -> randomFilmLiveData.postValue(film) },
                        { throwable: Throwable -> throwable.printStackTrace() },
                        {
                            info { "onComplete() = loadRandomFilm" }
                        })
    }

    private fun loadFilmByID(id: Int, locale: String) {
        restManager
                .getFilmByID(id, locale)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { film: DetailedFilm? ->
                            filmLiveData.postValue(film)
                        },
                        { throwable: Throwable ->
                            error { throwable.printStackTrace() }
                        },
                        {
                            info { "onComplete() = getFilmByID($id)" }
                        })
    }

}