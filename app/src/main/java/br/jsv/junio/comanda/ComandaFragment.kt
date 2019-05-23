package br.jsv.junio.comanda


import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baoyz.swipemenulistview.SwipeMenu
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuListView
import kotlinx.android.synthetic.main.comanda_fragment.view.*

class ComandaFragment(val client: String) : Fragment() {
    var products: ArrayList<Product> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.comanda_fragment, container, false)
        val productAdapter = context?.let { ProductAdapter(fragmentView.fragment_list_view, products, it) }
        val swipeMenuCreator = object: SwipeMenuCreator {
            override fun create(menu: SwipeMenu?) {
                val deleteSwipe = SwipeMenuItem(context)
                deleteSwipe.background = ColorDrawable(resources.getColor(R.color.colorAccent))
                deleteSwipe.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64f, resources.displayMetrics).toInt()
                deleteSwipe.setIcon(R.drawable.ic_delete_white)
                menu!!.addMenuItem(deleteSwipe)

                val editSwipe = SwipeMenuItem(context)
                editSwipe.background = ColorDrawable(resources.getColor(R.color.colorPrimary))
                editSwipe.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 64f, resources.displayMetrics).toInt()
                editSwipe.setIcon(R.drawable.ic_edit_white)
                menu.addMenuItem(editSwipe)
            }
        }

        fragmentView.fragment_list_view.setMenuCreator(swipeMenuCreator)
        fragmentView.fragment_list_view.setOnMenuItemClickListener(object: SwipeMenuListView.OnMenuItemClickListener {
            override fun onMenuItemClick(position: Int, menu: SwipeMenu?, index: Int): Boolean {
                Log.d("Swipe", "Pos:$position Item:${products[position].name}")
                when (index) {
                    0 -> {
                        products.remove(products[position])
                        productAdapter?.notifyDataSetChanged()
                    }
                    1 -> {
                        context?.let { EditProductDialog(it, products[position], productAdapter!!).show() }
                    }
                }
                return true
            }
        })
        fragmentView.fragment_list_view.adapter = productAdapter
        fragmentView.add_product.setOnClickListener {
            context?.let { it1 -> NewProductDialog(it1, products, productAdapter!!).show() }
        }
        fragmentView.print.setOnClickListener {
            context?.let { it1 -> Print(client, products, it1) }
        }
        return fragmentView
    }

}
