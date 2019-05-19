package br.jsv.junio.comanda

import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class Print(establishment: String, products: ArrayList<Product>, context: Context) {
    private var total: Double = 0.0

    init {
        val note = StringBuilder("<CENTER><BIG>$establishment<BR>" +
                "DATA: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())}<BR>")

        for (product in products) {
            note.append("${product.amount} - ${product.name} - ${String.format("R$%.2f", product.total)}<BR>")
            total += product.total
        }

        note.append("<MEDIUM2>TOTAL: ${String.format("R$%.2f", total)}<BR><BR>")

        try {
            val printNote: Intent = Intent("pe.diegoveloper.printing")
            printNote.type = "text/plain"
            printNote.putExtra(Intent.EXTRA_TEXT, note.toString())
            context.startActivity(printNote)
        } catch (e: Exception) {
            Toast.makeText(context, "É preciso ter o app Quick Pinter", Toast.LENGTH_LONG).show()
        }
    }
}