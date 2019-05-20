package br.jsv.junio.comanda

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class ComandaActivity : AppCompatActivity(), ComandaInterface{
    private var comandaFragments: ArrayList<ComandaFragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comanda_activity)
    }

    override fun changeComandaFragment(fragment: ComandaFragment) {
        supportFragmentManager.beginTransaction().replace(R.id.comanda_fragment, fragment).commit()
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
