package io.esalenko.findfilmapp.popularfilms

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.model.Film
import javax.inject.Inject


class PopularFilmsAdapter @Inject constructor(var films: List<Film>, val context: Context) : RecyclerView.Adapter<PopularFilmsAdapter.PopularFilmsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PopularFilmsViewHolder {
        val v: View = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_popular_film, parent, false)

        return PopularFilmsViewHolder(v)
    }

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(holder: PopularFilmsViewHolder?, position: Int) {
        val film: Film = films.get(position)
        holder?.bindView(film, context)
    }

    class PopularFilmsViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        var poster: ImageView? = itemView?.findViewById(R.id.film_img)

        var title: TextView? = itemView?.findViewById(R.id.film_title)

        var release_date: TextView? = itemView?.findViewById(R.id.film_release_year)

        fun bindView(film: Film, context: Context) {
            val url = context.getString(R.string.base_url_for_img) + film.posterPath
            Glide.with(context)
                    .load(url)
                    .into(poster)

            title?.text = film.title
            release_date?.text = film.releaseDate
        }
    }

}