package com.helper.data_diagnostics.dataSource

import com.helper.common.Response

interface DiagnosticsDataSource {
    suspend fun getDevInfoResponse(): Response<Int>

    suspend fun saveDiagnostic(id: Int)

    suspend fun switchFreePorts(): Response<Int>

    suspend fun getFreePorts(): Response<Int>
}