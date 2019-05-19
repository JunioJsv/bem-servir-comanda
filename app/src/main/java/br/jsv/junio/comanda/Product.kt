package br.jsv.junio.comanda

class Product(var amount: Int, var name: String, var price:Double) {
    var total: Double = amount * price
}