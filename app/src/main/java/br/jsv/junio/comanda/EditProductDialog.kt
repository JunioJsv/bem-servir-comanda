package br.jsv.junio.comanda

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.edit_product_dialog.*

class EditProductDialog(
    context: Context,
    private val targetProduct: Product,
    private val productAdapter: ProductAdapter
) : Dialog(context) {

    init {
        setContentView(R.layout.edit_product_dialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.apply {
            window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
            product.hint = targetProduct.name
            amount.hint = targetProduct.amount.toString()
            price.hint = targetProduct.price.toString()

            edit_buttom.setOnClickListener {
                if (this.product.text.toString() != "") {
                    targetProduct.name = this.product.text.toString()
                }

                if (this.amount.text.toString() != "") {
                    targetProduct.amount = this.amount.text.toString().toInt()
                }

                if (this.price.text.toString() != "") {
                    targetProduct.price = this.price.text.toString().toDouble()
                }

                targetProduct.notifyTotalSetChanged()
                productAdapter.notifyDataSetChanged()
                dismiss()
            }
        }

    }
}