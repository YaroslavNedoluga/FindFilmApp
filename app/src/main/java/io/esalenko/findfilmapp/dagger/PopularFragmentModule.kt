package io.esalenko.findfilmapp.dagger

import dagger.Binds
import dagger.Module
import io.esalenko.findfilmapp.IPresenter
import io.esalenko.findfilmapp.popularfilms.PopularFilmView
import io.esalenko.findfilmapp.popularfilms.PopularFilmsFragment
import io.esalenko.findfilmapp.popularfilms.PopularIPresenter
import javax.inject.Singleton


@Module
interface PopularFragmentModule {

    @Binds
    fun popularPresenter(popularPresenter: PopularIPresenter) : IPresenter

    @Binds
    fun popularFilmView(fragment : PopularFilmsFragment) : PopularFilmView

}