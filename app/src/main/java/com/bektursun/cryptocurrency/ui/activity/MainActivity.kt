package com.bektursun.cryptocurrency.ui.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog
import com.bektursun.core.ui.activity.SimpleActivity
import com.bektursun.core.utils.NetworkManager
import com.bektursun.core.utils.addFragmentExt
import com.bektursun.cryptocurrency.R
import com.bektursun.cryptocurrency.databinding.ActivityMainBinding
import com.bektursun.cryptolist.ui.fragment.cryptolist.CryptoListFragment

class MainActivity : SimpleActivity<ActivityMainBinding>(),
    NetworkManager.ConnectivityReceiverListener {

    private val networkReceiver = NetworkManager()

    override fun setupViews() {
        addFragmentExt<CryptoListFragment>(R.id.main_container)
        registerNetworkReceiver()
    }

    override fun onResume() {
        super.onResume()
        networkReceiver.connectivityReceiverListener = this
    }

    private fun registerNetworkReceiver() {
        registerReceiver(networkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            networkAlertDialog()
        }
    }

    private fun networkAlertDialog() {
        val builder = AlertDialog.Builder(this)
        with(builder) {
            setTitle(R.string.network_lose_title)
            setMessage(R.string.network_lose_message)
            setPositiveButton(R.string.network_positive_btn_text, null)
            setIcon(R.drawable.ic_wifi_off)
        }
        builder.show()
    }

    override fun getBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

}