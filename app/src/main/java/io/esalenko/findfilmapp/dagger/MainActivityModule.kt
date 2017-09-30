package io.esalenko.findfilmapp.dagger

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.esalenko.findfilmapp.dagger.scope.FragmentScope
import io.esalenko.findfilmapp.popularfilms.PopularFilmsFragment

@Module
interface MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(PopularFragmentModule::class))
    fun popularFragment(): PopularFilmsFragment
}