package br.jsv.junio.comanda

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.edit_product_dialog.*

class EditProductDialog(context: Context, val editProduct: Product, private val productAdapter: ProductAdapter) : Dialog(context) {

    init {
        setContentView(R.layout.edit_product_dialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        this.product.hint = editProduct.name
        this.amount.hint = editProduct.amount.toString()
        this.price.hint = editProduct.price.toString()

        this.edit_buttom.setOnClickListener {
            if (this.product.text.toString() != "") {
                editProduct.name = this.product.text.toString()
            }
            if (this.amount.text.toString() != "") {
                editProduct.amount = this.amount.text.toString().toInt()
            }
            if (this.price.text.toString() != "") {
                editProduct.price = this.price.text.toString().toDouble()
            }

            editProduct.notifyTotalSetChanged()
            productAdapter.notifyDataSetChanged()
            this.dismiss()
        }

    }
}