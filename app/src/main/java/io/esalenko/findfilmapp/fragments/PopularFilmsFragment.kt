package io.esalenko.findfilmapp.fragments


import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.common.BaseFragment
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.adapters.PopularFilmAdapter
import io.esalenko.findfilmapp.presenter.PopularFilmsPresenter
import io.esalenko.findfilmapp.view.PopularFilmsView
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class PopularFilmsFragment : BaseFragment()
        , PopularFilmsView
        , PopularFilmAdapter.PopularFilmOnCLickListener
        , AnkoLogger {

    private lateinit var commander: PopularFilmCommander

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity is PopularFilmCommander) {
            commander = activity as PopularFilmCommander
        } else if (parentFragment is PopularFilmCommander) {
            commander = parentFragment as PopularFilmCommander
        }
    }

    @BindView(R.id.rv_popular_films_list)
    lateinit var recyclerView: RecyclerView

    private var page: Int = 1

    private lateinit var adapter: PopularFilmAdapter

    private lateinit var presenter: PopularFilmsPresenter
    override fun getLayoutRes(): Int = R.layout.fragment_popular_films

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info("onViewCreated()")
        presenter = PopularFilmsPresenter(this, context)
        presenter.loadPopularFilms(page)
        adapter = PopularFilmAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = this.adapter
        recyclerView.setHasFixedSize(true)
    }

    override fun showFilmsList(popular_films: List<Film>) {
        adapter.setList(popular_films)
    }

    override fun onPopularFilmClicked(film: Film) {
        commander.showFilmDetails(film)
    }

    interface PopularFilmCommander {
        fun showFilmDetails(film: Film)
    }
}
