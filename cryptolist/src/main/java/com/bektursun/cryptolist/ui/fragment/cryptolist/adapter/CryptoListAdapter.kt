package com.bektursun.cryptolist.ui.fragment.cryptolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bektursun.cryptolist.R
import com.bektursun.cryptolist.databinding.ItemCryptoCurrencyTickerBinding
import com.bektursun.data.currencyTicker.CurrencyTicker

class CryptoListAdapter : RecyclerView.Adapter<CryptoListAdapter.CryptoListVH>() {

    private var currencies: List<CurrencyTicker> = emptyList()

    fun submitList(items: List<CurrencyTicker>) {
        currencies = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoListVH =
        CryptoListVH.from(parent)

    override fun onBindViewHolder(holder: CryptoListVH, position: Int) {
        holder.bind(currencies[position])
    }

    override fun getItemCount(): Int = currencies.size

    class CryptoListVH(private val vb: ItemCryptoCurrencyTickerBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(currency: CurrencyTicker) {
            val context: Context = vb.root.context
            with(vb) {
                tvCurrency.text = context.getString(R.string.currency_label, currency.currency)
                tvName.text = context.getString(R.string.currency_name_label, currency.name)
                tvPrice.text =
                    context.getString(R.string.currency_price_label, currency.price.toString())
            }
        }

        companion object {
            fun from(parent: ViewGroup): CryptoListVH {
                val inflater = LayoutInflater.from(parent.context)
                val vb = ItemCryptoCurrencyTickerBinding.inflate(inflater)
                return CryptoListVH(vb)
            }
        }

    }
}