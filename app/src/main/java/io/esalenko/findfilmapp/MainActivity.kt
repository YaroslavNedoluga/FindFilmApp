package io.esalenko.findfilmapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import io.esalenko.findfilmapp.common.BaseActivity
import io.esalenko.findfilmapp.popularfilms.PopularFilmsFragment

class MainActivity : BaseActivity() {

    private enum class State {
        POPULAR
    }

    private lateinit var state : State

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fm : FragmentManager = supportFragmentManager
        var fragment : Fragment? = fm.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
            state = State.POPULAR
            fragment = PopularFilmsFragment()
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit()
        }
    }
}
