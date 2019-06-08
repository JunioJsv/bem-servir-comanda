package br.jsv.junio.comanda


import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import kotlinx.android.synthetic.main.comanda_fragment.view.*
import kotlinx.android.synthetic.main.header_dialog.view.*

class ComandaFragment(val client: String) : Fragment() {
    private var products: ArrayList<Product> = ArrayList()
    private var longClickedProduct: Product? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.comanda_fragment, container, false)
        val productAdapter = context?.let { ProductAdapter(products, it) }
        val productMenu: PowerMenu = PowerMenu.Builder(context)
            .setHeaderView(R.layout.header_dialog)
            .setMenuRadius(10f)
            .setMenuShadow(10f)
            .setAnimation(MenuAnimation.SHOW_UP_CENTER)
            .setTextGravity(Gravity.CENTER)
            .addItem(PowerMenuItem("Editar"))
            .addItem(PowerMenuItem("Apagar"))
            .build()

        productMenu.setOnMenuItemClickListener { position, item ->
            when (position) {
                1 -> {
                    EditProductDialog(context!!, longClickedProduct!!, productAdapter!!).show()
                }
                2 -> {
                    products.remove(longClickedProduct)
                    productAdapter!!.notifyDataSetChanged()
                }
            }
            productMenu.dismiss()
        }

        fragmentView.apply {
            fragment_list_view.apply {
                adapter = productAdapter
                onItemLongClickListener = AdapterView.OnItemLongClickListener { parent, view, position, id ->
                    productMenu.headerView.header_title.text = products[position].name
                    longClickedProduct = products[position]
                    productMenu.showAtLocation(view, Gravity.BOTTOM, 0, 0)
                    true
                }
            }

            add_product.setOnClickListener {
                context?.let { context -> NewProductDialog(context, products, productAdapter!!).show() }
            }

            print.setOnClickListener {
                context?.let { context -> Print(client, products, context) }
            }
        }

        return fragmentView
    }

}
