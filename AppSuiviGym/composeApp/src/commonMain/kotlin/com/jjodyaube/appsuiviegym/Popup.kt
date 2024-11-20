package com.jjodyaube.appsuiviegym

import androidx.compose.runtime.Composable

class Popup(private var popup: @Composable (() -> Unit)?, private var setShowPopup: (value: Boolean) -> Unit, private var getShowPopup: () -> Boolean) {
    fun getPopup() = popup
    fun showPopup(value: Boolean) { this.setShowPopup(value) }
    fun showPopup(): Boolean = this.getShowPopup()
}
