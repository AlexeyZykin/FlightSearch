package com.alexisdev.airline_tickets.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexisdev.airline_tickets.R
import com.alexisdev.airline_tickets.SearchDetailsViewModel
import com.alexisdev.airline_tickets.adapter.FlightDelegateAdapter
import com.alexisdev.airline_tickets.databinding.FragmentSearchDetailsBinding
import com.alexisdev.airline_tickets.state.AirlineTicketsUiState
import com.alexisdev.airline_tickets.state.SearchDetailsUiState
import com.alexisdev.airline_tickets.util.DateManager
import com.alexisdev.airline_tickets.util.onDrawableEndClick
import com.alexisdev.ui.adapter.DelegateAdapterManager
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar


class SearchDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSearchDetailsBinding
    private val args by navArgs<SearchDetailsFragmentArgs>()
    private val viewModel by viewModel<SearchDetailsViewModel>()
    private val adapter by lazy {
        DelegateAdapterManager(FlightDelegateAdapter())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearch()
        initRecyclerView()
        subscribeObserver()
        binding.icBack.setOnClickListener { findNavController().navigateUp() }
        binding.btViewAllTickets.setOnClickListener { navigateToAllTickets() }
        binding.chipReturnDate.setOnClickListener { showDatePicker(it as Chip) }
        binding.chipDepartureDate.text = DateManager.currentDate()
        binding.chipDepartureDate.setOnClickListener { showDatePicker(it as Chip) }
    }

    private fun navigateToAllTickets() {
        val pathway = "${binding.inputDeparture.text}-${binding.inputArrival.text}"
        val flightDetails = "${binding.chipDepartureDate.text};   ${binding.chipTicketOptions.text}"
        viewModel.saveFlightData(pathway, flightDetails)
        findNavController().navigate(R.id.action_searchDetailsFragment_to_allTicketsFragment)
    }

    private fun initRecyclerView() {
        binding.rvFlights.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvFlights.adapter = adapter
    }

    private fun subscribeObserver() {
        viewModel.ticketOffers.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchDetailsUiState.Loading -> {}

                is SearchDetailsUiState.Success -> if (state.data != null) {
                    adapter.swapData(state.data)
                }

                is SearchDetailsUiState.Error ->
                    Toast.makeText(requireActivity(), state.msg, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.departurePoint.observe(viewLifecycleOwner) {
            binding.inputDeparture.setText(it)
        }
    }

    private fun setupSearch() {
        binding.inputArrival.setText(args.arrivalLocation)
        binding.inputArrival.onDrawableEndClick {
            binding.inputArrival.setText("")
            findNavController().navigateUp()
        }
        binding.inputDeparture.onDrawableEndClick {
            val arrival = binding.inputArrival
            val departure = binding.inputDeparture
            arrival.text = departure.text.also {
                departure.text = arrival.text
            }
            viewModel.saveDeparturePoint(departure.text.toString())
        }
    }

    private fun showDatePicker(chip: Chip) {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.addOnPositiveButtonClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            chip.text = DateManager.convertDateToString(calendar.time)
        }
        datePicker.show(childFragmentManager, "TAG")
    }

}