package io.esalenko.findfilmapp.utils

import android.support.v7.util.DiffUtil
import io.esalenko.findfilmapp.model.Film


class FilmDiffUtilCallback(oldFilms: List<Film>, newFilms: List<Film>) : DiffUtil.Callback() {

    private val oldFilms = java.util.ArrayList<Film>()
    private val newFilms = java.util.ArrayList<Film>()

    init {
        this.oldFilms.addAll(oldFilms)
        this.newFilms.addAll(newFilms)
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFilms[oldItemPosition].id == newFilms[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldFilms.size

    override fun getNewListSize(): Int = newFilms.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFilms[oldItemPosition] == newFilms[newItemPosition]
    }

}