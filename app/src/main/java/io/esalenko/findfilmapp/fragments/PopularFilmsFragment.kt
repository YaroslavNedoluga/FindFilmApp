package io.esalenko.findfilmapp.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.adapters.PopularFilmAdapter
import io.esalenko.findfilmapp.common.BaseFragment
import io.esalenko.findfilmapp.model.Film
import io.esalenko.findfilmapp.viewmodel.PopularFilmsViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class PopularFilmsFragment : BaseFragment()
        , PopularFilmAdapter.PopularFilmOnCLickListener
        , AnkoLogger {

    private lateinit var commander: PopularFilmCommander

    @BindView(R.id.rv_popular_films_list)
    lateinit var recyclerView: RecyclerView

    private var page: Int = 1

    private lateinit var adapter: PopularFilmAdapter

    private lateinit var viewModel: PopularFilmsViewModel


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (parentFragment is PopularFilmCommander) {
            commander = parentFragment as PopularFilmCommander
        } else if (activity is PopularFilmCommander) {
            commander = activity as PopularFilmCommander
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_popular_films

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info("onViewCreated()")

        adapter = PopularFilmAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
        viewModel = ViewModelProviders.of(this).get(PopularFilmsViewModel::class.java)
        subscribe()
    }

    private fun subscribe() {
        viewModel.loadPopularFilms(page, getApp().getCurrentLocale())
                .observe(this, Observer { films ->
                    adapter.setList(films)
                    adapter.notifyDataSetChanged()
                    info { "adapter.setList() called" }
                })
    }

    override fun onPopularFilmClicked(film: Film) {
        commander.showFilmDetails(film)
    }


    interface PopularFilmCommander {
        fun showFilmDetails(film: Film)
    }
}
