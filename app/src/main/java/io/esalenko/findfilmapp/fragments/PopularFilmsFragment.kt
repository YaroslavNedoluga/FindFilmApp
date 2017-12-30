package io.esalenko.findfilmapp.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import butterknife.BindView
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.adapters.PopularFilmAdapter
import io.esalenko.findfilmapp.adapters.viewholder.FilmViewHolder
import io.esalenko.findfilmapp.common.BaseFragment
import io.esalenko.findfilmapp.viewmodel.FilmsViewModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class PopularFilmsFragment : BaseFragment()
        , FilmViewHolder.FilmViewHolderCommander
        , AnkoLogger {

    companion object {
        const val LAST_PAGE: String = "last_page"
    }

    private lateinit var commander: PopularFilmCommander
    private lateinit var adapter: PopularFilmAdapter
    private lateinit var viewModel: FilmsViewModel
    private var page: Int = 1

    @BindView(R.id.rv_popular_films_list)
    lateinit var recyclerView: RecyclerView

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (parentFragment is PopularFilmCommander) {
            commander = parentFragment as PopularFilmCommander
        } else if (activity is PopularFilmCommander) {
            commander = activity as PopularFilmCommander
        }
    }

    override val layoutRes: Int
        get() = R.layout.fragment_popular_films

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info("onViewCreated()")

        adapter = PopularFilmAdapter(this)
        if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else if (activity?.resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = this.adapter
        viewModel = ViewModelProviders.of(this).get(FilmsViewModel::class.java)
        subscribe()
    }

    private fun subscribe() {
        viewModel.loadPopularFilms(page, app.getCurrentLocale())
                .observe(this, Observer { films ->
                    adapter.setList(films)
                    adapter.notifyDataSetChanged()
                    info { "adapter.setList() called" }
                })
    }

    override fun onFilmViewHolderMore(id: Int) {
        commander.showFilmDetails(id)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(LAST_PAGE, page)
    }

    interface PopularFilmCommander {
        fun showFilmDetails(id: Int)
    }
}
