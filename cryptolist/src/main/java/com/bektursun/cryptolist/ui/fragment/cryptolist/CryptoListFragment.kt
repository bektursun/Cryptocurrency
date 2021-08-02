package com.bektursun.cryptolist.ui.fragment.cryptolist

import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.bektursun.core.ui.fragment.CoreFragment
import com.bektursun.core.utils.DebouncingTextWatcher
import com.bektursun.core.utils.ResultWrapper
import com.bektursun.cryptolist.R
import com.bektursun.cryptolist.databinding.FragmentCryptoListBinding
import com.bektursun.cryptolist.ui.fragment.cryptolist.adapter.CryptoListAdapter

class CryptoListFragment :
    CoreFragment<FragmentCryptoListBinding, CryptoListVM>(CryptoListVM::class) {

    private val adapter: CryptoListAdapter = CryptoListAdapter()

    override fun setupViews() {
        setupSearch()
        setupRV()
    }

    private fun setupRV() {
        vb.rvCurrency.adapter = adapter
    }

    private fun setupSearch() {
        vb.etSearch.addTextChangedListener(DebouncingTextWatcher(lifecycle, lifecycleScope)
        { query: String? ->
            vm.searchCurrencies(query)
        })
    }

    override fun subscribeToLiveData() {
        subscribeToCurrencies()
        subscribeToCurrencyState()
    }

    private fun subscribeToCurrencies() {
        vm.currenciesTicker.observe(viewLifecycleOwner, { currencies ->
            currencies?.let {
                adapter.submitList(it)
            }
        })
    }

    private fun subscribeToCurrencyState() {
        vm.currenciesTickerState.observe(viewLifecycleOwner, { queryState ->
            vb.progressHorizontal.isVisible = queryState is ResultWrapper.Loading
            vb.tvMessage.isVisible = queryState is ResultWrapper.Error
            when (queryState) {
                is ResultWrapper.Error -> {
                    vb.tvMessage.text = getString(R.string.error_request_message)
                }
                is ResultWrapper.Empty -> {
                    vb.tvMessage.isVisible = true
                    vb.tvMessage.text = getString(R.string.empty_request_message, queryState.data)
                }
                else -> return@observe
            }
        })
    }

    override fun createViewBinding(inflater: LayoutInflater): FragmentCryptoListBinding =
        FragmentCryptoListBinding.inflate(inflater)

    companion object {
        fun newInstance(): CryptoListFragment = CryptoListFragment()
    }
}