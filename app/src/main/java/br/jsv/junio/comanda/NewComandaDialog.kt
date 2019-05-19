package br.jsv.junio.comanda

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.new_comanda_dialog.*

class NewComandaDialog(context: Context, val comandaInterface: ComandaInterface) : Dialog(context) {

    init {
        setContentView(R.layout.new_comanda_dialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        this.add_buttom.setOnClickListener {
            if (this.establishment.text.toString() != "") {
                comandaInterface.changeComandaFragment(ComandaFragment(this.establishment.text.toString(), comandaInterface))
                this.dismiss()
            } else Toast.makeText(context, "O campo estabelecimento é obrigatorio", Toast.LENGTH_SHORT).show()
        }
    }
}