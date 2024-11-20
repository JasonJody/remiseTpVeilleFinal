package com.jjodyaube.appsuiviegym

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

class Popup(private var popup: @Composable (() -> Unit)?, private val showPopup: MutableState<Boolean>) {
    fun getPopup() = popup
    fun showPopup(value: Boolean) { showPopup.value = value }
    fun showPopup(): Boolean = showPopup.value
}
