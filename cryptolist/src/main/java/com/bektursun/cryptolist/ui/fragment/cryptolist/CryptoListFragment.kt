package com.bektursun.cryptolist.ui.fragment.cryptolist

import android.view.LayoutInflater
import androidx.core.widget.doAfterTextChanged
import com.bektursun.core.ui.fragment.CoreFragment
import com.bektursun.cryptolist.databinding.FragmentCryptoListBinding

class CryptoListFragment :
    CoreFragment<FragmentCryptoListBinding, CryptoListVM>(CryptoListVM::class) {

    override fun setupViews() {
        vb.etSearch.doAfterTextChanged {
            vm.searchCurrencies(it.toString())
        }
    }

    override fun subscribeToLiveData() {

    }

    override fun createViewBinding(inflater: LayoutInflater): FragmentCryptoListBinding =
        FragmentCryptoListBinding.inflate(inflater)

    companion object {
        fun newInstance(): CryptoListFragment = CryptoListFragment()
    }
}