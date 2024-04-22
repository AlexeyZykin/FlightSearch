package com.alexisdev.airline_tickets.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexisdev.airline_tickets.AirlineTicketsViewModel
import com.alexisdev.airline_tickets.adapter.OffersDelegateAdapter
import com.alexisdev.ui.adapter.DelegateAdapterManager
import com.alexisdev.airline_tickets.databinding.FragmentAirlineTicketsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AirlineTicketsFragment : Fragment() {
    private val viewModel by viewModel<AirlineTicketsViewModel>()
    private lateinit var binding: FragmentAirlineTicketsBinding
    private val adapter by lazy {
        DelegateAdapterManager(OffersDelegateAdapter())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAirlineTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeObserver()
        binding.svArrival.setOnClickListener { navigateToSearchBottomSheet() }
        departurePointListener()
    }

    private fun departurePointListener() {
        binding.svDeparture.setOnEditorActionListener { query, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.saveDeparturePoint(query.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvAirlineTicketsOffers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvAirlineTicketsOffers.adapter = adapter
    }

    private fun subscribeObserver() {
        viewModel.offers.observe(viewLifecycleOwner) { offers ->
            adapter.swapData(offers)
        }
        viewModel.departurePoint.observe(viewLifecycleOwner) { cachedDeparturePoint ->
            binding.svDeparture.setText(cachedDeparturePoint)
        }
    }

    private fun navigateToSearchBottomSheet() {
        viewModel.saveDeparturePoint(binding.svDeparture.text.toString())
        val action =
            AirlineTicketsFragmentDirections.actionAirlineTicketsFragmentToSearchBottomFragment()
        findNavController().navigate(action)
    }
}