package io.esalenko.findfilmapp.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.adapters.viewholder.FilmViewHolder
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.utils.FilmDiffUtilCallback
import java.util.*


class PopularFilmAdapter(private val callback: FilmViewHolder.FilmViewHolderCommander) : RecyclerView.Adapter<FilmViewHolder>() {

    private val list: ArrayList<Film>? = ArrayList<Film>()

    fun setList(list: List<Film>?) {
        if (this.list?.size == 0) {
            this.list.addAll(list!!)
        }
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(FilmDiffUtilCallback(this.list!!, list!!))
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FilmViewHolder {
        val view: View = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_film_card, parent, false)
        return FilmViewHolder(view, callback)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: FilmViewHolder?, position: Int) {
        val film: Film = list!![position]
        holder?.bindView(film)
    }

}