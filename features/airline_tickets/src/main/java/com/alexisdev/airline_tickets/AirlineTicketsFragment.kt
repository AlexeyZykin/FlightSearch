package com.alexisdev.airline_tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.alexisdev.airline_tickets.adapter.OffersAdapter
import com.alexisdev.airline_tickets.databinding.FragmentAirlineTicketsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AirlineTicketsFragment : Fragment() {
    private val viewModel by viewModel<AirlineTicketsViewModel>()
    private lateinit var binding: FragmentAirlineTicketsBinding
    private val adapter by lazy {
        OffersAdapter()
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
    }

    private fun initRecyclerView() {
        binding.rvAirlineTicketsOffers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvAirlineTicketsOffers.adapter = adapter
    }

    private fun subscribeObserver() {
        viewModel.offers.observe(viewLifecycleOwner) { offers ->
            adapter.map(offers)
        }
    }
}