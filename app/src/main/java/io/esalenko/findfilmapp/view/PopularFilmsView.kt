package io.esalenko.findfilmapp.view

import io.esalenko.findfilmapp.model.Film

interface PopularFilmsView {

    fun showFilmsList(popular_films: List<Film>)
}