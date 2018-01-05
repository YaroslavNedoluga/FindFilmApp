package io.esalenko.findfilmapp.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.bumptech.glide.Glide
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.activity.MainActivity
import io.esalenko.findfilmapp.adapters.GenreChipsAdapter
import io.esalenko.findfilmapp.fragments.common.BaseFragment
import io.esalenko.findfilmapp.helper.ApiHelper
import io.esalenko.findfilmapp.model.DetailedFilm
import io.esalenko.findfilmapp.viewmodel.FilmsViewModel
import org.jetbrains.anko.AnkoLogger

class DetailFilmFragment : BaseFragment(), AnkoLogger {

    companion object {
        fun newInstance(bundle: Bundle? = null): DetailFilmFragment {
            val detailFragment = DetailFilmFragment()
            if (bundle != null) {
                detailFragment.arguments = bundle
            }
            return detailFragment
        }
    }

    @BindView(R.id.tv_fragment_detail_film_overview)
    lateinit var movieOverview: TextView

    @BindView(R.id.tv_fragment_detail_film_release_date)
    lateinit var movieReleaseDate: TextView

    @BindView(R.id.tv_fragment_detail_film_title)
    lateinit var movieTitle: TextView

    @BindView(R.id.iv_fragment_detail_film_poster)
    lateinit var moviePoster: ImageView

    @BindView(R.id.tv_fragment_detail_film_average_vote)
    lateinit var movieVoteAvarage: TextView

    @BindView(R.id.tv_fragment_detail_film_tagline)
    lateinit var movieTagline: TextView

    @BindView(R.id.rv_fragment_detail_film_genres_list)
    lateinit var genresRecyclerView: RecyclerView

    private lateinit var genresAdapter: GenreChipsAdapter

    private lateinit var filmViewModel: FilmsViewModel

    override val layoutRes: Int
        get() = R.layout.fragment_detail_film

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmViewModel = ViewModelProviders.of(this).get(FilmsViewModel::class.java)
        genresAdapter = GenreChipsAdapter()
        genresRecyclerView.adapter = genresAdapter
        genresRecyclerView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false)
        filmDetailSubscribe()
    }

    private fun filmDetailSubscribe() {
        val detailFilmObserver = Observer<DetailedFilm> { film: DetailedFilm? ->

            if (film != null) {
                with(film) {
                    val baseImgUrl = ApiHelper.baseImgUrl
                    val backDropUrl = baseImgUrl + posterPath

                    Glide.with(context)
                            .load(backDropUrl)
                            .into(moviePoster)

                    movieTitle.text = title?.toUpperCase()
                    movieOverview.text = overview
                    movieTagline.text = tagline
                    movieReleaseDate.text = releaseDate?.substring(0, 4)
                    movieVoteAvarage.text = voteAverage.toString()
                    genresAdapter.setList(genres!!)

                }
            }
        }
        val filmId: Int = arguments!!.getInt(MainActivity.FILM_ID)
        filmViewModel
                .getFilmByID(filmId, app.getCurrentLocale())
                .observe(this, detailFilmObserver)
    }

}
