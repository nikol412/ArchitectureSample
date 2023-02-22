package com.helper.domain_diagnostics.mappers

fun Int.toUi() = DiagnosticsModel(this)
fun List<Int>.toUi() = this.map { DiagnosticsModel(it) }

data class DiagnosticsModel(val id: Int)