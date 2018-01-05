package io.esalenko.findfilmapp.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.fragments.common.BaseFragment
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.viewmodel.FilmsViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class RandomFilmFragment : BaseFragment(), AnkoLogger {

    override val layoutRes: Int
        get() = R.layout.fragment_random_film

    private lateinit var filmViewModel: FilmsViewModel
    private lateinit var randomFilmObserver: Observer<Film>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filmViewModel = ViewModelProviders.of(this).get(FilmsViewModel::class.java)
        subscribe()
    }

    private fun subscribe() {
        randomFilmObserver = Observer { film ->
            info { "${film?.id}" }
        }

        filmViewModel.getRandomFilm(app.getCurrentLocale()).observe(this, randomFilmObserver)
    }
}