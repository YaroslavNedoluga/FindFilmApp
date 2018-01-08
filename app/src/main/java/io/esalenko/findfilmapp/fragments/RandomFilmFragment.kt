package io.esalenko.findfilmapp.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.fragments.common.BaseFragment
import io.esalenko.findfilmapp.helper.ApiHelper
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.viewmodel.FilmsViewModel
import org.jetbrains.anko.AnkoLogger


class RandomFilmFragment : BaseFragment(), AnkoLogger {

    override val layoutRes: Int = R.layout.fragment_random_film

    private lateinit var filmViewModel: FilmsViewModel
    private lateinit var randomFilmObserver: Observer<Film>

    @BindView(R.id.iv_item_random_film_card_poster)
    lateinit var posterImageView: ImageView
    @BindView(R.id.tv_item_random_film_card_title)
    lateinit var titleTextView: TextView
    @BindView(R.id.tv_item_random_film_card_release_year)
    lateinit var releaseYearTextView: TextView
    @BindView(R.id.tv_item_random_film_card_average_vote)
    lateinit var averageVoteTextView: TextView
    @BindView(R.id.tv_item_random_film_card_description)
    lateinit var overviewTextView: TextView
    @BindView(R.id.srl_fragment_random_film)
    lateinit var swipeRefreshlayout: SwipeRefreshLayout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmViewModel = ViewModelProviders.of(this).get(FilmsViewModel::class.java)

        subscribeLoadRandomFilm()

        swipeRefreshlayout.setOnRefreshListener {
            swipeRefreshlayout.isRefreshing = true
            subscribeLoadRandomFilm()
            swipeRefreshlayout.isRefreshing = false
        }
    }

    private fun subscribeLoadRandomFilm() {
        randomFilmObserver = Observer { film ->
            if (film != null) {
                with(film) {

                    val url = ApiHelper.baseImgUrl + backdropPath
                    Glide.with(context)
                            .load(url)
                            .into(posterImageView)

                    if (title.isNullOrEmpty()) {
                        titleTextView.visibility = View.GONE
                    } else {
                        titleTextView.text = title
                    }
                    if (releaseDate.isNullOrEmpty()) {
                        releaseYearTextView.visibility = View.GONE
                    } else {
                        releaseYearTextView.text = releaseDate?.substring(0, 4)
                    }
                    if ((voteAverage == 0.0) or (voteAverage == null)) {
                        averageVoteTextView.visibility = View.GONE
                    } else {
                        averageVoteTextView.text = voteAverage.toString()
                    }
                    if ((overview != null) or (!overview?.isEmpty()!!)) {
                        overviewTextView.text = overview
                    } else {
                        overviewTextView.gravity = Gravity.CENTER
                        overviewTextView.text = getString(R.string.error_overview_not_found)
                        overviewTextView
                                .setCompoundDrawablesWithIntrinsicBounds(
                                        0,
                                        R.drawable.ic_sentiment_dissatisfied_black_24px,
                                        0,
                                        0)
                    }

                }
            }
        }

        filmViewModel.getRandomFilm(app.getCurrentLocale()).observe(this, randomFilmObserver)
    }
}