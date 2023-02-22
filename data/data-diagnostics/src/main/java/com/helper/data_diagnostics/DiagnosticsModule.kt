package com.helper.data_diagnostics

import com.helper.data_diagnostics.dataSource.DiagnosticsDataSource
import com.helper.data_diagnostics.dataSource.RoomDiagnosticsDataSource
import com.helper.data_diagnostics.repository.DiagnosticsRepository
import com.helper.data_diagnostics.repository.DiagnosticsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

//@Module
//@InstallIn(ActivityComponent::class)
//interface DomainDiagnosticsModule

@Module
@InstallIn(ViewModelComponent::class)
abstract class DiagnosticsModule {

    @Binds
    abstract fun bindDiagnosticsRepository(
        diagnosticsDataSource: RoomDiagnosticsDataSource
    ): DiagnosticsDataSource

    @Binds
    abstract fun bindRepository(
        impl: DiagnosticsRepositoryImpl
    ): DiagnosticsRepository

}