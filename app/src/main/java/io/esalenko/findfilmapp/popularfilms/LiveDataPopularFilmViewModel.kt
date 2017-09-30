package io.esalenko.findfilmapp.popularfilms

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.service.RestLoader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LiveDataPopularFilmViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var restLoader: RestLoader

    init {
        loadPopularFilms()
    }

    val data: MutableLiveData<List<Film>> = MutableLiveData()

    private var currentPage = 1

    private fun loadPopularFilms() {
        restLoader.getPopularFilms(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { films: List<Film> -> data.value = films }
    }

    fun nextPage() = currentPage++

    fun previousPage() = if (currentPage != 1) currentPage-- else currentPage = 1
}