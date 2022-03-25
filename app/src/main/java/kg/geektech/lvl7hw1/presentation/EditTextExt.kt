package kg.geektech.lvl7hw1.presentation

import android.widget.EditText

fun EditText.print(): Int{
    return this.text.toString().toInt()
}