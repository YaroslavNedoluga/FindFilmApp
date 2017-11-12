package io.esalenko.findfilmapp

import android.os.Bundle
import android.support.v4.app.Fragment
import io.esalenko.findfilmapp.common.BaseActivity
import io.esalenko.findfilmapp.fragments.DetailFilmFragment
import io.esalenko.findfilmapp.fragments.PopularFilmsFragment
import io.esalenko.findfilmapp.model.Film

class MainActivity : BaseActivity(),
        PopularFilmsFragment.PopularFilmCommander {

    private enum class State {
        POPULAR_MOVIES_LIST,
        MOVIE_DETAIL
    }

    companion object {
        val FILM: String = "film"
    }

    private lateinit var state: State

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        state = State.POPULAR_MOVIES_LIST
        supportFragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .add(R.id.fragment_container, PopularFilmsFragment())
                .commitNowAllowingStateLoss()
    }

    override fun showFilmDetails(film: Film) {
        showMovieDetailScreen(film)
    }

    override fun onBackPressed() {
        when (state) {
            State.MOVIE_DETAIL -> showPopularMoviesScreen()
            else -> super.onBackPressed()
        }
    }

    private fun showMovieDetailScreen(film: Film) {
        state = State.MOVIE_DETAIL
        val detailFragment = DetailFilmFragment()
        val bundle = Bundle()
        bundle.putParcelable(FILM, film)
        detailFragment.arguments = bundle

        replaceFragment(detailFragment)
    }

    private fun showPopularMoviesScreen() {
        state = State.POPULAR_MOVIES_LIST
        replaceFragment(PopularFilmsFragment())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment)
                .commitNowAllowingStateLoss()
    }

}
