package br.jsv.junio.comanda

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.comanda_activity.*
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener as BaseOnTabSelectedListener1
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener as OnTabSelectedListener1

class ComandaActivity : AppCompatActivity(), ComandaInterface{
    private var comandaFragments: ArrayList<ComandaFragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comanda_activity)
        comandas_tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (comandaFragments.size > 1) {
                    changeComandaFragment(comandaFragments[tab!!.position], false)
                }
            }

        })
    }

    override fun changeComandaFragment(fragment: ComandaFragment, new: Boolean) {
        supportFragmentManager.beginTransaction().replace(R.id.comanda_fragment, fragment).commit()
        if (new) {
            comandaFragments.add(fragment)
            comandas_tabs.addTab(comandas_tabs.newTab().setText(fragment.client), true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.comanda_shortcut, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.new_comanda -> {
                NewComandaDialog(this, this).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}
