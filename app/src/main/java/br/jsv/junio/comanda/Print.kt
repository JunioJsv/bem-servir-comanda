package br.jsv.junio.comanda

import android.content.Context
import android.content.Intent
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class Print(client: String, products: ArrayList<Product>, context: Context) {
    private var total: Double = 0.0

    init {
        val note = StringBuilder("<CENTER><BIG>$client<BR>" +
                "DATA: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())}<BR>"
        ).apply {
            products.forEach {
                append("${it.amount} - ${it.name} - ${String.format("R$%.2f", it.total)}<BR>")
                total += it.total
            }
            append("<MEDIUM2>TOTAL: ${String.format("R$%.2f", total)}<BR><BR>")
        }

        try {
            Intent("pe.diegoveloper.printing").apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, note.toString())
                context.startActivity(this)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "É preciso ter o app Quick Pinter", Toast.LENGTH_LONG).show()
        }
    }
}