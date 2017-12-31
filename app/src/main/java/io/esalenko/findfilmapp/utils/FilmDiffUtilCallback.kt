package io.esalenko.findfilmapp.utils

import android.support.v7.util.DiffUtil
import io.esalenko.findfilmapp.model.Film


class FilmDiffUtilCallback(private var oldFilms: List<Film>, private var newFilms: List<Film>) : DiffUtil.Callback() {

    init {
        oldFilms = ArrayList()
        newFilms = ArrayList()
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFilms[oldItemPosition].id == newFilms[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldFilms.size

    override fun getNewListSize(): Int = newFilms.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldFilms[oldItemPosition] == newFilms[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}