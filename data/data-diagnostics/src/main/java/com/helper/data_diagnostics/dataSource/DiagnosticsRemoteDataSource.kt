package com.helper.data_diagnostics.dataSource

import com.helper.common.MetaData
import com.helper.common.Response
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

class DiagnosticsRemoteDataSource @Inject constructor() : DiagnosticsDataSource {
    override suspend fun getDevInfoResponse(): Response<Int> {
        delay(500L)
        return Response(Random.nextInt(0..10), metaData = MetaData(0, null))

    }

    override suspend fun saveDiagnostic(id: Int) {
        //todo save data
    }

    override suspend fun switchFreePorts(): Response<Int> {
        delay(500L)
        return Response(Random.nextInt(0..10), metaData = MetaData(0, null))
    }

    override suspend fun getFreePorts(): Response<Int> {
        delay(500L)
        return Response(Random.nextInt(0..10), metaData = MetaData(0, null))
    }
}