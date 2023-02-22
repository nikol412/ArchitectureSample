package com.helper.api

import com.helper.common.MetaData
import com.helper.common.Response
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class API @Inject constructor()  {
    suspend fun getA() = Response(1, MetaData(0, null))
    suspend fun getB(): Int = 2
    suspend fun getC(): Int = 3
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkApiModule {
    @Provides
    fun provideApi(): API {
        return API()
    }
}

