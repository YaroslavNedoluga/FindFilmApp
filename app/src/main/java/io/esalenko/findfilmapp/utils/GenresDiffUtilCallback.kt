package io.esalenko.findfilmapp.utils

import android.support.v7.util.DiffUtil
import io.esalenko.findfilmapp.model.DetailedFilm


class GenresDiffUtilCallback(oldList: List<DetailedFilm.Genre>, newList: List<DetailedFilm.Genre>) : DiffUtil.Callback() {

    private var oldGenres = ArrayList<DetailedFilm.Genre>()
    private var newGenres = ArrayList<DetailedFilm.Genre>()

    init {
        oldGenres.addAll(oldList)
        newGenres.addAll(newList)
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldGenres[oldItemPosition].id == newGenres[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldGenres.size

    override fun getNewListSize(): Int = newGenres.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldGenres[oldItemPosition] == newGenres[newItemPosition]
    }
}
