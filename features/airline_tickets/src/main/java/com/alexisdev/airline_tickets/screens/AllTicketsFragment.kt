package com.alexisdev.airline_tickets.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexisdev.airline_tickets.AllTicketsViewModel
import com.alexisdev.airline_tickets.adapter.TicketDelegateAdapter
import com.alexisdev.airline_tickets.databinding.FragmentAllTicketsBinding
import com.alexisdev.ui.adapter.DelegateAdapterManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllTicketsFragment : Fragment() {
    private lateinit var binding: FragmentAllTicketsBinding
    private val viewModel by viewModel<AllTicketsViewModel>()
    private val adapter by lazy {
        DelegateAdapterManager(TicketDelegateAdapter())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeObservers()
        binding.icBack.setOnClickListener { findNavController().navigateUp() }
    }

    private fun initRecyclerView() {
        binding.rvTickets.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvTickets.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.tickets.observe(viewLifecycleOwner) { tickets ->
            adapter.swapData(tickets)
        }
        viewModel.pathway.observe(viewLifecycleOwner) {
            binding.tvPathway.text = it
        }
        viewModel.flightDetails.observe(viewLifecycleOwner) {
            binding.tvFlightInfo.text = it
        }
    }
}