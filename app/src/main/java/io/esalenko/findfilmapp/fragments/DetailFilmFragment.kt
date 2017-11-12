package io.esalenko.findfilmapp.fragments

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import io.esalenko.findfilmapp.MainActivity
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.common.BaseFragment
import io.esalenko.findfilmapp.model.Film
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class DetailFilmFragment : BaseFragment(), AnkoLogger {

    @BindView(R.id.tv_fragment_detail_film_overview)
    lateinit var movieOverview: TextView

    @BindView(R.id.tv_fragment_detail_film_release_date)
    lateinit var movieReleaseDate: TextView

    @BindView(R.id.tv_fragment_detail_film_title)
    lateinit var movieTitle: TextView

    @BindView(R.id.iv_fragment_detail_film_backdrop)
    lateinit var movieBackdrop: ImageView

    @BindView(R.id.tv_fragment_detail_film_vote_count)
    lateinit var movieVoteCount: TextView

    @BindView(R.id.tv_fragment_detail_film_average_vote)
    lateinit var movieVoteAvarage: TextView

    override fun getLayoutRes(): Int = R.layout.fragment_detail_film

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFilmDetails()
    }

    private fun showFilmDetails() {
        Handler().post({

            val film: Film = arguments!![MainActivity.FILM] as Film
            val baseImgUrl = context?.resources?.getString(R.string.base_url_for_img)

            val backDropUrl = baseImgUrl + film.posterPath
            Glide.with(context)
                    .load(backDropUrl)
                    .into(movieBackdrop)

            info { "Film title: " + film.title }
            val releaseDate = film.releaseDate?.substring(0, 4)
            movieTitle.text = film.title?.toUpperCase() + " ($releaseDate)"
            info { "Film overview: " + film.overview }
            movieOverview.text = film.overview
            info { "Film release date : " + film.releaseDate }
            movieReleaseDate.text = film.releaseDate?.substring(0, 4)
            info { "Vote count: " + film.voteCount }
            movieVoteCount.text = getString(R.string.vote_count, film.voteCount)
            info { "Vote average:" + film.voteAverage }
            movieVoteAvarage.text = getString(R.string.average_vote, film.voteAverage)
        })
    }

}