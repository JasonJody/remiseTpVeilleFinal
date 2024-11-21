package com.jjodyaube.appsuiviegym.utils

fun capitalize(word: String) : String{
    return word.lowercase().replaceFirstChar { firstChar -> firstChar.uppercase() }
}