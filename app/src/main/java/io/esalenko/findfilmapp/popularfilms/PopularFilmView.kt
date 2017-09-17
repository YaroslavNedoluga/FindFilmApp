package io.esalenko.findfilmapp.popularfilms

import io.esalenko.findfilmapp.IView
import io.esalenko.findfilmapp.model.Film


interface PopularFilmView : IView {

    fun showPopularFilms(films: List<Film>?)

}