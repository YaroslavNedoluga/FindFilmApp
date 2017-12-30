package io.esalenko.findfilmapp.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.common.BaseActivity
import io.esalenko.findfilmapp.fragments.DetailFilmFragment
import io.esalenko.findfilmapp.fragments.PopularFilmsFragment

class MainActivity : BaseActivity(),
        PopularFilmsFragment.PopularFilmCommander {

    companion object {
        val FILM_ID: String = "film"
    }

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            replaceFragment(PopularFilmsFragment())
        }
    }

    override fun showFilmDetails(id: Int) {
        val bundle = Bundle()
        bundle.putInt(FILM_ID, id)
        val detailFragment = DetailFilmFragment.newInstance(bundle)
        replaceFragment(detailFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
    }

}
