package io.esalenko.findfilmapp.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import butterknife.BindView
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.activity.common.BaseActivity
import io.esalenko.findfilmapp.fragments.DetailFilmFragment
import io.esalenko.findfilmapp.fragments.PopularFilmsFragment
import io.esalenko.findfilmapp.fragments.RandomFilmFragment

class MainActivity : BaseActivity(),
        PopularFilmsFragment.PopularFilmCommander {

    companion object {
        val FILM_ID: String = "film"
    }

    @BindView(R.id.bnv_activity_main_nav)
    lateinit var bnv: BottomNavigationView

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            replaceFragment(PopularFilmsFragment())
        }
        bnv.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_pop -> {
                    replaceFragment(PopularFilmsFragment())
                    true
                }
                R.id.nav_random -> {
                    replaceFragment(RandomFilmFragment())
                    true
                }
                else -> false
            }
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
