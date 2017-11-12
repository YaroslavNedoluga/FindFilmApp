package io.esalenko.findfilmapp.adapters

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
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*


class PopularFilmAdapter(private val callback : PopularFilmOnCLickListener) : RecyclerView.Adapter<PopularFilmAdapter.PopularFilmViewHolder>() {

    private var list: ArrayList<Film>? = ArrayList()

    fun setList(list: List<Film>?) {
        this.list?.addAll(list!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PopularFilmViewHolder {
        val view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_popular_film, parent, false)
        return PopularFilmViewHolder(view, callback)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: PopularFilmViewHolder?, position: Int) {
        val film: Film = list!![position]
        holder?.bindView(film)
    }

    class PopularFilmViewHolder(itemView: View?, private val callback: PopularFilmOnCLickListener) :
            RecyclerView.ViewHolder(itemView),
            AnkoLogger,
            View.OnClickListener {

        @BindView(R.id.film_img)
        lateinit var posterImage: ImageView

        @BindView(R.id.film_title)
        lateinit var title: TextView

        @BindView(R.id.film_overview)
        lateinit var overview: TextView

        @BindView(R.id.film_release_year)
        lateinit var releaseYear: TextView

        private lateinit var film: Film

        init {
            ButterKnife.bind(this, itemView!!)
            itemView.setOnClickListener(this)
        }

        fun bindView(film: Film) {
            this.film = film

            val baseImageUrl = itemView.context.resources.getString(R.string.base_url_for_img)
            val url = baseImageUrl + film.backdropPath

            if (url != null) {
                Glide.with(itemView)
                        .load(url)
                        .into(posterImage)
            } else {
                posterImage.setImageResource(R.drawable.no_image_available)
            }
            title.text = film.title?.toUpperCase() ?: itemView.context.getString(R.string.error_title)

            overview.text = film.overview ?: itemView.context.getString(R.string.error_overview)

            val yearLength = 4
            releaseYear.text = film.releaseDate?.substring(0, yearLength) ?: "- - - -"
        }

        override fun onClick(v: View?) {
            info { "onClick()" }
            callback.onPopularFilmClicked(film)
        }

    }

    interface PopularFilmOnCLickListener {
        fun onPopularFilmClicked(film : Film)
    }
}