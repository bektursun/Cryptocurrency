package com.bektursun.cryptocurrency.ui.activity

import com.bektursun.core.ui.activity.SimpleActivity
import com.bektursun.core.utils.addFragmentExt
import com.bektursun.cryptocurrency.R
import com.bektursun.cryptocurrency.databinding.ActivityMainBinding
import com.bektursun.cryptolist.ui.fragment.cryptolist.CryptoListFragment

class MainActivity : SimpleActivity<ActivityMainBinding>() {

    override fun setupViews() {
        addFragmentExt<CryptoListFragment>(R.id.main_container)
    }

    override fun getBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

}