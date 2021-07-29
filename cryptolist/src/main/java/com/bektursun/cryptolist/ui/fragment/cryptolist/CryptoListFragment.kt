package com.bektursun.cryptolist.ui.fragment.cryptolist

import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.bektursun.core.ui.fragment.CoreFragment
import com.bektursun.core.utils.DebouncingTextWatcher
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
        vm.currenciesTicker.observe(viewLifecycleOwner, { currencies ->
            currencies?.let {
                adapter.submitList(it)
            }
        })
    }

    override fun createViewBinding(inflater: LayoutInflater): FragmentCryptoListBinding =
        FragmentCryptoListBinding.inflate(inflater)

    companion object {
        fun newInstance(): CryptoListFragment = CryptoListFragment()
    }
}