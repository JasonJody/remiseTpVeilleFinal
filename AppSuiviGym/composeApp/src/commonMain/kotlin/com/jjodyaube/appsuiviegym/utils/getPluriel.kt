package com.jjodyaube.appsuiviegym.utils

fun getPluriel(amount: Int, mot: String): String {
    val containAnS = amount > 1
    val trimmedWord = mot.trimEnd()
    return if (containAnS) trimmedWord + "s" else trimmedWord
}