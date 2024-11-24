package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jjodyaube.appsuiviegym.Popup
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ExtendedMenuItem(private val titre: String, private val action: () -> Unit) {
    fun getTitre() = titre
    fun getAction() = action
}

class AppBar(private var navController: NavController) {
    private var titre: String = ""
    private var backButton: Boolean = false
    private var popup: Popup? = null
    private var showPopupCondition: () -> Boolean = { true }
    private var extendedMenuItem: MutableList<ExtendedMenuItem> = mutableListOf()
    private var extendedMenuOffset: Int = 0

    fun backButton(backButton: Boolean): AppBar {
        this.backButton = backButton
        return this
    }

    fun titre(titre: String): AppBar {
        this.titre = titre
        return this
    }

    fun popup(popup: Popup): AppBar {
        this.popup = popup
        return this
    }

    fun showPopupCondition(showPopupCondition: () -> Boolean): AppBar {
        this.showPopupCondition = showPopupCondition
        return this
    }

    fun addExtendedMenuItem(item: ExtendedMenuItem): AppBar {
        extendedMenuItem.add(item)
        return this
    }

    fun extendedMenuOffset(extendedMenuOffset: Int): AppBar {
        this.extendedMenuOffset = extendedMenuOffset
        return this
    }

    @Composable
    fun build() {
        return AppBar(
            navController,
            titre,
            backButton,
            popup,
            showPopupCondition,
            extendedMenuItem,
            extendedMenuOffset
        )
    }
}

val mois: Array<String> = arrayOf(
    "janvier",
    "février",
    "mars",
    "avril",
    "mai",
    "juin",
    "juillet",
    "août",
    "septembre",
    "octobre",
    "novembre",
    "décembre"
)

fun todaysDate(): String {

    val now = Clock.System.now()
    val zone = TimeZone.currentSystemDefault()
    val localDateTime = now.toLocalDateTime(zone)

    val dateFormatee = StringBuilder()
    dateFormatee
        .append(localDateTime.dayOfMonth)
        .append(" ")
        .append(mois[localDateTime.monthNumber])
        .append(" ")
        .append(localDateTime.year)

    return dateFormatee.toString()
}

val backButtonSize: Int = 40
val backButtonPadding : Int = 5

@Composable
private fun AppBar(
    navController: NavController,
    titre: String,
    backButton: Boolean,
    popup: Popup?,
    showPopupCondition: () -> Boolean,
    extendedMenuItem: MutableList<ExtendedMenuItem>,
    ExtendedMenuOffset: Int
) {
    var isExpanded by remember { mutableStateOf(false) }

    if(
        popup != null &&
        popup.showPopup() &&
        popup.getPopup() != null
    ) {
        (popup.getPopup()!!)()
    }

    fun getPaddingDate(): Int {
        if (!backButton) {
            return 0
        }
        return (backButtonPadding * 2) + backButtonSize
    }

    Box(modifier = Modifier.padding(vertical = 15.dp, horizontal = (if (backButton) 0 else 15).dp)) {
        Column(
            verticalArrangement = Arrangement.spacedBy((-10).dp)
        ) {
            Text(
                todaysDate(),
                color = Color.Gray,
                fontSize = 10.sp,
                modifier = Modifier.padding(horizontal = (getPaddingDate().dp))
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (backButton) {
                        Box(
                            modifier = Modifier.padding(horizontal = backButtonPadding.dp)
                        ) {
                            TextButton(
                                onClick = {
                                    if (popup == null || !showPopupCondition()) {
                                        navController.popBackStack()
                                    } else {
                                        popup.showPopup(true)
                                    }
                                },
                                shape = CircleShape,
                                modifier = Modifier
                                    .size(backButtonSize.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.Black
                                )
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Retour icon"
                                )
                            }
                        }
                    }
                    Text(
                        titre,
                        modifier = Modifier.weight(1f, fill = false),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-1).sp,
                        lineHeight = 35.sp
                    )
                }
                if (extendedMenuItem.isNotEmpty()) {
                    Box {
                        Box(
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            IconButton(
                                onClick = { isExpanded = true },
                                size = backButtonSize,
                                icon = Icons.Default.MoreVert,
                                description = "Extended menu button"
                            )
                        }
                        DropdownMenu(
                            expanded = isExpanded,
                            onDismissRequest = { isExpanded = false },
                            offset = DpOffset((ExtendedMenuOffset).dp, 0.dp)
                        ) {
                            for (item in extendedMenuItem) {
                                DropdownMenuItem(
                                    onClick = {
                                        item.getAction()()
                                        isExpanded = false
                                    }
                                ) {
                                    Text(item.getTitre())
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}