package br.jsv.junio.comanda

import androidx.appcompat.app.ActionBar

interface ComandaInterface {

    fun changeComandaFragment(fragment: ComandaFragment)

    fun mainActionBar(): ActionBar?
}