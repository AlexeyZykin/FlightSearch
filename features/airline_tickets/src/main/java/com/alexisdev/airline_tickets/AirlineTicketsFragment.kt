package com.alexisdev.airline_tickets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexisdev.airline_tickets.adapter.OffersDelegateAdapter
import com.alexisdev.ui.adapter.DelegateAdapterManager
import com.alexisdev.airline_tickets.databinding.FragmentAirlineTicketsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class AirlineTicketsFragment : Fragment() {
    private val viewModel by viewModel<AirlineTicketsViewModel>()
    private lateinit var binding: FragmentAirlineTicketsBinding
    private val adapter by lazy {
        DelegateAdapterManager(
            OffersDelegateAdapter()
        )
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
        binding.inputArrival.setOnClickListener {
            val action = AirlineTicketsFragmentDirections.actionAirlineTicketsFragmentToSearchBottomFragment()
            findNavController().navigate(action)
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
    }
}