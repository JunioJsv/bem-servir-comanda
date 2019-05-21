package br.jsv.junio.comanda

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.product_view.view.*

class ProductAdapter(private val listView: ListView, private val products: ArrayList<Product>, private val context: Context) : BaseAdapter() {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val productView = inflate(context, R.layout.product_view, null)

        if (products[position].amount > 1) {
            productView.amount_card.visibility = View.VISIBLE
            productView.amount.text = products[position].amount.toString()
        }

        productView.initial.text = products[position].name.capitalize()[0].toString()
        productView.name.text = products[position].name
        productView.price.text = "R$${String.format("%.2f", products[position].price)}"
        productView.total.text = "R$${String.format("%.2f", products[position].total)}"

        return productView
    }

    override fun getItem(position: Int): Any {
       return products[position]
    }

    override fun getItemId(position: Int): Long {
        return (products.size - position).toLong()
    }

    override fun getCount(): Int {
        return products.size
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        listView.adapter = this
    }
}