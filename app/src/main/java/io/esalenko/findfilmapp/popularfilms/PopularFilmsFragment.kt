package io.esalenko.findfilmapp.popularfilms


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.common.BaseFragment
import io.esalenko.findfilmapp.model.Film
import kotlinx.android.synthetic.main.fragment_popular_films.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class PopularFilmsFragment : BaseFragment(), PopularFilmView {

    @Inject
    lateinit var presenter: PopularIPresenter

    @Inject
    lateinit var adapter: PopularFilmsAdapter

    lateinit var layout_manager: RecyclerView.LayoutManager

    override fun getLayoutRes(): Int = R.layout.fragment_popular_films

    override fun onStart() {
        super.onStart()
        presenter.load()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout_manager = LinearLayoutManager(context)
        popular_films_list.layoutManager = this.layout_manager
        popular_films_list.adapter = this.adapter

    }

    override fun showPopularFilms(films: List<Film>?) {
        adapter.films = films!!
    }
}
