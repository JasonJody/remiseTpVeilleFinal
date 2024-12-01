package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputsAvecTitre(
    titre: String? = null,
    value: String,
    onChange: (String) -> Unit,
    placeholder: String? = null,
    isError: Boolean = false,
    isEnable: Boolean = true
) {
    val focusManager: FocusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    val customTextSelectionColors = TextSelectionColors(
        handleColor = Color.Black,
        backgroundColor = Color.LightGray
    )

    if (titre != null) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(titre)
        }
    }

    CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(1.dp,   if(isEnable) if (isError) Color.Red else Color.LightGray else Color.Gray),
                        shape = RoundedCornerShape(3.dp)
                    )
                    .background(if (isEnable) Color.White else Color.LightGray, shape = RoundedCornerShape(3.dp))
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = onChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp, horizontal = 10.dp)
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        },
                    singleLine = true,
                    textStyle = TextStyle.Default.copy(color = Color.Black),
                    cursorBrush = SolidColor(if (value.isEmpty()) Color.LightGray else Color.Black),
                    enabled = isEnable,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        },
                    ),
                    decorationBox = { innerTextField ->
                        Box {
                            if (titre != null && placeholder != null && value.isEmpty() ||
                                titre == null && !isFocused && placeholder != null && value.isEmpty()
                            ) {
                                Text(
                                    text = placeholder,
                                    style = TextStyle.Default.copy(color = if(isEnable) if (isError) Color.Red else Color.LightGray else Color.Gray)
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }

            if (titre == null && placeholder != null && (isFocused || value.isNotEmpty())) {
                val labelTextColor = if (isError) Color.Red else Color.Gray
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .offset(y = (-7).dp)
                    ) {
                        Box(
                            modifier = Modifier.background(Color.White)
                                .height(1.dp)
                                .align(
                                    BiasAlignment(0f, 0.2f)
                                )
                        ) {
                            // Texte invisible pour set la longueur de la bar
                            // Technique de merde!
                            Text(
                                text = placeholder,
                                style = TextStyle.Default.copy(
                                    fontSize = 12.sp,
                                    color = Color.Transparent
                                ),
                                modifier = Modifier.padding(horizontal = 2.dp)
                            )
                        }
                        Text(
                            text = placeholder,
                            style = TextStyle.Default.copy(
                                fontSize = 12.sp,
                                color = labelTextColor
                            ),
                            modifier = Modifier.padding(horizontal = 2.dp)
                        )
                    }
            }
        }
    }
}