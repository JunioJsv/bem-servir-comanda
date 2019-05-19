package br.jsv.junio.comanda


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.comanda_fragment.view.*

class ComandaFragment(private val establishment: String, private val comandaInterface: ComandaInterface) : Fragment() {
    var products: ArrayList<Product> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.comanda_fragment, container, false)
        val productAdapter = context?.let { ProductAdapter(products, it) }
        fragmentView.fragment_list_view.adapter = productAdapter
        fragmentView.add_product.setOnClickListener {
            context?.let { it1 -> NewProductDialog(it1, products, productAdapter!!).show() }
        }
        fragmentView.print.setOnClickListener {
            context?.let { it1 -> Print(establishment, products, it1) }
        }
        return fragmentView
    }

    override fun onResume() {
        super.onResume()
        if (comandaInterface.mainActionBar()!!.title != establishment) comandaInterface.mainActionBar()!!.title = establishment
    }
}
