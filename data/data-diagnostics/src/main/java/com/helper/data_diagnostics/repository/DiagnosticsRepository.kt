package com.helper.data_diagnostics.repository

import com.helper.common.Response

interface DiagnosticsRepository {
    suspend fun getDevInfoResponse(id: Int, isForceCall: Boolean = true): Response<Int>

    suspend fun switchFreePorts(): Response<Int>

    suspend fun getFreePorts(): Response<Int>
}