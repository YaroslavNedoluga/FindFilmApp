package io.esalenko.findfilmapp.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.adapters.viewholder.GenreChipsViewHolder
import io.esalenko.findfilmapp.model.DetailedFilm
import io.esalenko.findfilmapp.utils.GenresDiffUtilCallback
import java.util.*

class GenreChipsAdapter : RecyclerView.Adapter<GenreChipsViewHolder>() {

    private var list = ArrayList<DetailedFilm.Genre>()

    fun setList(list: List<DetailedFilm.Genre>) {
        val genreDiffUtil = GenresDiffUtilCallback(this.list, list)
        val diffResult = DiffUtil.calculateDiff(genreDiffUtil)
        this.list = list as ArrayList<DetailedFilm.Genre>
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: GenreChipsViewHolder?, position: Int) {
        holder?.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GenreChipsViewHolder {
        return GenreChipsViewHolder(LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_chips_genre, parent, false))
    }
}