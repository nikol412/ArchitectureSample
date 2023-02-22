package com.helper.data_diagnostics.repository

import com.helper.common.Response
import com.helper.data_diagnostics.dataSource.DiagnosticsDataSource
import com.helper.data_diagnostics.dataSource.DiagnosticsRemoteDataSource
import javax.inject.Inject

class DiagnosticsRepositoryImpl @Inject constructor(private val localDataSource: DiagnosticsDataSource, private val remoteDataSource: DiagnosticsRemoteDataSource) :
    DiagnosticsRepository {
    override suspend fun getDevInfoResponse(id: Int, isForceCall: Boolean): Response<Int> {
        if(isForceCall) {
            val restResponse = remoteDataSource.getDevInfoResponse()
            localDataSource.saveDiagnostic(restResponse.data)
        }
        return localDataSource.getDevInfoResponse()
    }

    override suspend fun switchFreePorts(): Response<Int> = localDataSource.switchFreePorts()

    override suspend fun getFreePorts(): Response<Int> = localDataSource.getFreePorts()

}