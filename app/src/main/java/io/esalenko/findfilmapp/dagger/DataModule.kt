package io.esalenko.findfilmapp.dagger

import dagger.Module
import dagger.Provides
import io.esalenko.findfilmapp.dagger.scope.ApplicationScope
import io.esalenko.findfilmapp.hepler.ApiHelper
import io.esalenko.findfilmapp.service.RestLoader
import io.esalenko.findfilmapp.service.RestService

@Module
class DataModule {

    @ApplicationScope
    @Provides
    fun provideRestLoader(rest: RestService, helper: ApiHelper): RestLoader = RestLoader(rest, helper)

}