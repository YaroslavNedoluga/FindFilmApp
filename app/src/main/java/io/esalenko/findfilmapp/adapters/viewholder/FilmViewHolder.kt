package io.esalenko.findfilmapp.adapters.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import es.dmoral.toasty.Toasty
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.helper.ApiHelper
import io.esalenko.findfilmapp.model.Film
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class FilmViewHolder(itemView: View?, private val callback: FilmViewHolderCommander) :
        RecyclerView.ViewHolder(itemView),
        AnkoLogger {

    @BindView(R.id.iv_item_film_card_poster)
    lateinit var posterImage: ImageView

    @BindView(R.id.tv_item_film_card_title)
    lateinit var filmTitle: TextView

    @BindView(R.id.tv_item_film_card_description)
    lateinit var filmOverview: TextView

    @BindView(R.id.tv_item_film_card_release_year)
    lateinit var releaseYear: TextView

    @BindView(R.id.tv_item_film_card_average_vote)
    lateinit var averageVote: TextView

    private lateinit var film: Film

    init {
        ButterKnife.bind(this, itemView!!)
    }

    fun bindView(film: Film) {
        this.film = film


        with(this.film) {
            val url = ApiHelper.baseImgUrl + film.backdropPath
            Glide.with(itemView)
                    .load(url)
                    .into(posterImage)

            filmTitle.text = title ?: itemView.context.getString(R.string.error_title)

            filmOverview.text = overview ?: itemView.context.getString(R.string.error_overview)

            val yearLength = 4
            releaseYear.text = releaseDate?.substring(0, yearLength) ?: "- - - -"

            averageVote.text = voteAverage.toString()
        }
    }

    @OnClick(R.id.iv_item_film_card_more)
    fun onMoreClicked() {
        info { "onClick()" }
        callback.onFilmViewHolderMore(film.id!!)
    }

    @OnClick(R.id.iv_item_film_card_share)
    fun onShareClicked() {
        // TODO :: Implemet share film logic
        Toasty.success(itemView.context, "Film shared").show()
    }

    @OnClick(R.id.iv_item_film_card_favorites)
    fun onFavoriteClicked() {
        // TODO :: Implemt adding to favorite
        Toasty.success(itemView.context, "Added to favorite").show()
    }

    interface FilmViewHolderCommander {
        fun onFilmViewHolderMore(id: Int)
    }
}