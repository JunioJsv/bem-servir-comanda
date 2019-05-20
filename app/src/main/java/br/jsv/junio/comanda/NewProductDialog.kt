package br.jsv.junio.comanda

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.new_product_dialog.*

class NewProductDialog(context: Context, private val products: ArrayList<Product>, private val productAdapter: ProductAdapter) : Dialog(context) {

    init {
        setContentView(R.layout.new_product_dialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        this.add_buttom.setOnClickListener {
            if (this.amount.text.toString() != "" && this.product.text.toString() != "" && this.price.text.toString() != "") {
                products.add(Product(this.amount.text.toString().toInt(), this.product.text.toString(), this.price.text.toString().toDouble()))
                productAdapter.notifyDataSetChanged()
                this.dismiss()
            } else Toast.makeText(context, "Todos campos são necessarios", Toast.LENGTH_SHORT).show()
        }
    }
}