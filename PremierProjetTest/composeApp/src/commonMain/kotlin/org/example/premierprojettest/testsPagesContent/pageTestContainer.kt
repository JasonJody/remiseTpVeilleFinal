package org.example.premierprojettest.testsPagesContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import androidx.compose.foundation.layout.Arrangement as alignment1

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
val backButtonPadding : Int = 10

@Composable
fun PageTestContainer(navController: NavController, titre: String, contenue: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = alignment1.Center
    ) {
        Box(modifier = Modifier.padding(top = 15.dp, end = 15.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(todaysDate(),
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = ((backButtonPadding * 2) + backButtonSize).dp)
                )
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.padding (horizontal = backButtonPadding.dp)) {
                        TextButton(
                            onClick = { navController.popBackStack() },
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
                    Text(titre, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                }
            }
        }
        Box(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            contenue()
        }
    }
}
