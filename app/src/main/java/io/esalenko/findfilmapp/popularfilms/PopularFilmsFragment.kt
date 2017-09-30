package io.esalenko.findfilmapp.popularfilms


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import io.esalenko.findfilmapp.R
import io.esalenko.findfilmapp.common.BaseFragment


/**
 * A simple [Fragment] subclass.
 */
class PopularFilmsFragment : BaseFragment() {

    @BindView(R.id.rv_popular_films_list)
    lateinit var recyclerView: RecyclerView

    private lateinit var viewModel: LiveDataPopularFilmViewModel

    private lateinit var adapter: PopularFilmAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_popular_films

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LiveDataPopularFilmViewModel::class.java)
        subscribePopularFilms()
        adapter = PopularFilmAdapter()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun subscribePopularFilms() {
        viewModel.data
                .observe(this, Observer { popularFilmsList ->
                    adapter.list = popularFilmsList
                })
    }

}
