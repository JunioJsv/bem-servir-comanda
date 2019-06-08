package br.jsv.junio.comanda

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.product_view.view.*

class ProductAdapter(private val products: ArrayList<Product>, private val context: Context) : BaseAdapter() {

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val productView = inflate(context, R.layout.product_view, null)

        if (products[position].amount > 1) {
            productView.apply {
                amount_card.visibility = View.VISIBLE
                amount.text = products[position].amount.toString()
            }
        }

        productView.apply {
            initial.text = products[position].name.capitalize()[0].toString()
            name.text = products[position].name
            price.text = "R$${String.format("%.2f", products[position].price)}"
            total.text = "R$${String.format("%.2f", products[position].total)}"
        }

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

}