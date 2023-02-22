package com.helper.architecturesample

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.helper.common.NetworkResult
import com.helper.domain_diagnostics.DiagnosticsUseCase
import com.helper.domain_diagnostics.mappers.DiagnosticsModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<VM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.toString()
    }
}

@HiltViewModel
class VM @Inject constructor(private val useCase: DiagnosticsUseCase) : ViewModel() {
    init {
        viewModelScope.launch {
            val response = useCase.getDevInfoResponse(1, true)
            when (response) {
                is NetworkResult.Success -> {
                    Log.d("DIAGNIOSTICS", response.data.toString())
                }
                is NetworkResult.Error -> {
                    Log.d("DIAGNIOSTICS", response.message ?: "")

                }
                else -> {}
            }
        }
    }
}


object Screens {

    fun TicketDetails(ticketId: Int) {
       // FragmentScreen { TicketDetailsFragment.newInstance(ticketId) }
    }
}

/*
class TicketDetailsFragment():Fragment() {
    companion object {
        private const val TICKET_ID_KEY = "123"
    }
    fun newInstance(ticketId: Int) {
        return TicketDetailsFragment().apply {
            arguments = bundleOf(TICKET_ID_KEY to ticketId)
        }
    }
}
 */