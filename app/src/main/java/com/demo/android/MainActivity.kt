package com.demo.android

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.demo.android.databinding.ActivityMainBinding
import com.demo.android.model.UserHolding
import com.demo.android.ui.fragment.ViewPagerAdapter
import com.demo.android.utils.Constants
import com.demo.android.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var apiResponse: List<UserHolding>? = null
    private var flag: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Holdings"
                1 -> tab.text = "Positions"
            }
        }.attach()

        viewModel.uiState.observe(this) { state ->
            if (state.isLoading) {
            }

            state.data?.let {
                apiResponse = state.data
                var currentValue = 0.0
                var totalInvestment = 0.0
                var totalPln = 0.0
                for (i in 0 until it.size) {
                    currentValue += it[i].quantity * it[i].ltp
                    totalInvestment += it[i].quantity * it[i].avgPrice
                    totalPln += (it[i].close - it[i].ltp) * it[i].quantity
                }

                binding.tvCurrentValue.text = "₹ ${Constants.formatDoubleAmount(currentValue)}"
                binding.tvInvestment.text = "₹ ${Constants.formatDoubleAmount(totalInvestment)}"
                binding.tvTodayProfitLoss.text =
                    "₹ ${Constants.formatDoubleAmount(currentValue - totalInvestment)}"
                binding.tvTotalProfit.text = "₹ ${Constants.formatDoubleAmount(totalPln)}"

                if ((currentValue - totalInvestment) > 0) {
                    binding.tvTodayProfitLoss.setTextColor(getColor(R.color.green))
                } else {
                    binding.tvTodayProfitLoss.setTextColor(getColor(R.color.red))
                }

                if ((totalPln) > 0) {
                    binding.tvTotalProfit.setTextColor(getColor(R.color.green))
                } else {
                    binding.tvTotalProfit.setTextColor(getColor(R.color.red))
                }
            }

            state.error?.let {
                binding.rlProfitLoss.visibility = View.GONE
            }
        }

        binding.rlProfitLoss.setOnClickListener {
            flag = !flag
            if (flag) {
                binding.llProfitLoss.visibility = View.VISIBLE
            } else {
                binding.llProfitLoss.visibility = View.GONE
            }
        }
    }
}