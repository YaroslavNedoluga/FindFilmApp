package io.esalenko.findfilmapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import io.esalenko.findfilmapp.livedata.FilmLiveData
import io.esalenko.findfilmapp.livedata.ListFilmLiveData
import io.esalenko.findfilmapp.model.DetailedFilm
import io.esalenko.findfilmapp.model.Film
import org.jetbrains.anko.AnkoLogger

class FilmsViewModel : ViewModel(), AnkoLogger {

    private val listFilmLiveData = ListFilmLiveData()
    private val filmLiveData = FilmLiveData()

    fun loadPopularFilms(page: Int, locale: String): LiveData<List<Film>> {
        listFilmLiveData.getFilmsList(page, locale)
        return listFilmLiveData
    }

    fun getFilmByID(id: Int, locale: String): LiveData<DetailedFilm> {
        filmLiveData.getFilmByID(id, locale)
        return filmLiveData
    }

}