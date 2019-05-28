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
import kotlinx.android.synthetic.main.header_product_view.view.*

class ComandaFragment(val client: String) : Fragment() {
    private var products: ArrayList<Product> = ArrayList()
    private var longClickedProduct: Product? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.comanda_fragment, container, false)
        val productAdapter = context?.let { ProductAdapter(products, it) }
        val productMenu: PowerMenu = PowerMenu.Builder(context)
            .setHeaderView(R.layout.header_product_view)
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

        fragmentView.fragment_list_view.adapter = productAdapter
        fragmentView.fragment_list_view.onItemLongClickListener =
            AdapterView.OnItemLongClickListener { parent, view, position, id ->
                productMenu.headerView.header_title.text = products[position].name
                longClickedProduct = products[position]
                productMenu.showAtLocation(view, Gravity.BOTTOM, 0, 0)
                true
            }
        fragmentView.add_product.setOnClickListener {
            context?.let { it1 -> NewProductDialog(it1, products, productAdapter!!).show() }
        }
        fragmentView.print.setOnClickListener {
            context?.let { it1 -> Print(client, products, it1) }
        }
        return fragmentView
    }

}
