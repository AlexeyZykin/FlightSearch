package com.alexisdev.airline_tickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexisdev.airline_tickets.databinding.OfferItemBinding
import com.alexisdev.airline_tickets.util.Mapper
import com.alexisdev.airline_tickets.util.imgRes
import com.alexisdev.model.Offer
import com.bumptech.glide.Glide

class OffersAdapter : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>(), Mapper<List<Offer>> {

    private val list = mutableListOf<Offer>()

    class OfferViewHolder(private val binding: OfferItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: Offer) {
            binding.tvOfferTitle.text = offer.title
            binding.tvOfferTown.text = offer.town
            binding.tvOfferPrice.text = "от ${offer.price.value} р"
            val imgRes = offer.imgRes(offer.id)
            Glide
                .with(binding.root)
                .load(imgRes)
                .centerCrop()
                .into(binding.imgOffer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = OfferItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun map(source: List<Offer>) {
        val diff = DiffUtil(list, source)
        val result = androidx.recyclerview.widget.DiffUtil.calculateDiff(diff)
        list.clear()
        list.addAll(source)
        result.dispatchUpdatesTo(this)
    }

    class DiffUtil(
        private val oldList: List<Offer>,
        private val newList: List<Offer>
    ) : androidx.recyclerview.widget.DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}
