package com.helper.domain_diagnostics

import com.helper.api.API
import com.helper.common.NetworkResult
import com.helper.common.safeCall
import com.helper.common.safeLet
import com.helper.data_diagnostics.dataSource.DiagnosticsDataSource
import com.helper.data_diagnostics.repository.DiagnosticsRepository
import com.helper.domain_diagnostics.mappers.DiagnosticsModel
import com.helper.domain_diagnostics.mappers.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DiagnosticsUseCase @Inject constructor(private val repository: DiagnosticsRepository) {
    suspend fun getDevInfoResponse(id: Int, isForceCall: Boolean): NetworkResult<DiagnosticsModel> =
        withContext(Dispatchers.IO) {
            val devInfoResponse = safeCall { repository.getDevInfoResponse(id, isForceCall) }
            val additionalRequest = safeCall { repository.switchFreePorts() }

            // лямбда в safeLet выполняется, если оба ответа не завершились ошибкой
            safeLet(devInfoResponse, additionalRequest) { r1, r2 ->
                val uiModel = r1.data.toUi()
                val switchPorts = r2.data.toUi()
                return@withContext NetworkResult.Success(uiModel)
            } ?: kotlin.run { return@withContext NetworkResult.Error(100, "Something went wrong") }
        }

    suspend fun getFreePorts() {
        //todo

    }

    suspend fun saveDiagnostics() {
        //todo
    }
}
