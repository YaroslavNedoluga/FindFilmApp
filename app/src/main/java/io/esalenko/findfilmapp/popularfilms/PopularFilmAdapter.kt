package io.esalenko.findfilmapp.popularfilms

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.model.Film
import javax.inject.Inject


class PopularFilmAdapter @Inject constructor() : RecyclerView.Adapter<PopularFilmAdapter.PopularFilmViewHolder>() {

    var list: List<Film>? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PopularFilmViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_popular_film, parent, false)
        return PopularFilmViewHolder(view)
    }

    override fun getItemCount(): Int = list!!.size

    override fun onBindViewHolder(holder: PopularFilmViewHolder?, position: Int) {
        val film: Film = list!![position]
        holder?.bindView(film)
    }

    class PopularFilmViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.film_img)
        lateinit var posterImage: ImageView

        @BindView(R.id.film_title)
        lateinit var title: TextView

        @BindView(R.id.film_overview)
        lateinit var overview: TextView

        @BindView(R.id.film_release_year)
        lateinit var releaseYear: TextView

        fun bindView(film: Film) {
            val url = film.posterPath
            Glide.with(itemView)
                    .load(url)
                    .into(posterImage)

            title.text = film.title
            overview.text = film.overview
            releaseYear.text = film.releaseDate
        }

        init {
            ButterKnife.bind(this, itemView!!)
        }

    }
}