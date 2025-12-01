package com.demo.android.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.android.R
import com.demo.android.databinding.HoldingItemBinding
import com.demo.android.model.UserHolding
import com.demo.android.utils.Constants

class HoldingAdapter(private val context: Context, private val userList: List<UserHolding>) :
    RecyclerView.Adapter<HoldingAdapter.UserViewHolder>() {

    class UserViewHolder(val binding: HoldingItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = HoldingItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]

        holder.binding.tvStockSymbol.text = user.symbol
        holder.binding.tvLTP.text = "₹ " + Constants.formatDoubleAmount(user.ltp)
        holder.binding.tvQuantity.text = "${user.quantity}"
        val profitLoss = (user.close - user.ltp) * user.quantity
        holder.binding.tvPl.text = "₹ ${Constants.formatDoubleAmount(profitLoss)}"

        if (profitLoss > 0) {
            holder.binding.tvPl.setTextColor(context.getColor(R.color.green))
        } else {
            holder.binding.tvPl.setTextColor(context.getColor(R.color.red))
        }
    }

    override fun getItemCount(): Int = userList.size
}