package br.jsv.junio.comanda

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.new_comanda_dialog.*

class NewComandaDialog(context: Context, private val comandaInterface: ComandaInterface) : Dialog(context) {

    init {
        setContentView(R.layout.new_comanda_dialog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.apply {
            window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)

            add_buttom.setOnClickListener {
                if (client.text.toString() != "") {
                    comandaInterface.changeComandaFragment(ComandaFragment(client.text.toString()), true)
                    dismiss()
                } else Toast.makeText(context, "O campo estabelecimento é obrigatorio", Toast.LENGTH_SHORT).show()
            }
        }

    }
}