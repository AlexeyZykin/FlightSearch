package com.alexisdev.airline_tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexisdev.airline_tickets.adapter.RecommendationDelegateAdapter
import com.alexisdev.airline_tickets.adapter.TipDelegateAdapter
import com.alexisdev.airline_tickets.databinding.FragmentSearchBottomBinding
import com.alexisdev.airline_tickets.util.onDrawableEndClick
import com.alexisdev.model.Recommendation
import com.alexisdev.model.Tip
import com.alexisdev.model.TipAction
import com.alexisdev.ui.adapter.DelegateAdapterManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchBottomFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchBottomBinding
    private val viewModel by viewModel<SearchViewModel>()
    private val recommendationAdapter by lazy {
        DelegateAdapterManager(RecommendationDelegateAdapter { item ->
            recommendationItemClick(item)
        })
    }

    private val tipAdapter by lazy {
        DelegateAdapterManager(TipDelegateAdapter { item ->
            tipAction(item)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        subscribeObservers()
        setupSearch()
    }

    private fun setupSearch() {
        binding.inputArrival.onDrawableEndClick {
            binding.inputArrival.setText("")
        }
    }

    private fun initRecyclerView() {
        binding.rvSearchTips.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSearchTips.adapter = tipAdapter

        binding.rvSearchRecommendations.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvSearchRecommendations.adapter = recommendationAdapter
    }

    private fun subscribeObservers() {
        viewModel.tips.observe(viewLifecycleOwner) { tips ->
            tipAdapter.swapData(tips)
        }
        viewModel.recommendations.observe(viewLifecycleOwner) { recommendations ->
            recommendationAdapter.swapData(recommendations)
        }
    }

    private fun tipAction(item: Tip) {
        when (item.tipAction) {
            TipAction.COMPLEX_ROUTE -> findNavController().navigate(R.id.action_searchBottomFragment_to_complexRouteFragment)
            TipAction.ANYWHERE -> binding.inputArrival.setText("Какое-то место (заглушка)")
            TipAction.WEEKEND -> findNavController().navigate(R.id.action_searchBottomFragment_to_weekendFragment)
            TipAction.HOT_TICKETS -> findNavController().navigate(R.id.action_searchBottomFragment_to_hotTicketsFragment)
        }
    }

    private fun recommendationItemClick(item: Recommendation) {
        binding.inputArrival.setText(item.town)
    }
}