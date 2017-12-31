package io.esalenko.findfilmapp.adapters.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.model.DetailedFilm


class GenreChipsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.tv_item_chip_genre)
    lateinit var chipView: TextView

    fun bindView(genre: DetailedFilm.Genre) {
        chipView.text = genre.name
    }

    init {
        ButterKnife.bind(this, itemView!!)
    }

}