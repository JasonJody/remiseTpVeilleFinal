package com.jjodyaube.appsuiviegym.composants

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

class Action(
    private var action: () -> Unit,
    private var icon: ImageVector,
    private var description: String
) {
    fun getAction(): () -> Unit {
        return action
    }
    fun getIcon(): ImageVector {
        return icon
    }

    fun getDescription(): String {
        return description
    }
}

class ButtonActions {
    private var actions: MutableList<Action> = mutableListOf()

    fun action(action: Action): ButtonActions {
        actions.add(action)
        return this
    }

    @Composable
    fun build() {
        require(actions.isNotEmpty())

        return MultipleChoiceButton(actions)
    }
}

@Composable
private fun MultipleChoiceButton(actions: MutableList<Action>) {

    val completeRound = CircleShape

    val leftRound = RoundedCornerShape(
        topStart = 50.dp,
        topEnd = 0.dp,
        bottomStart = 50.dp,
        bottomEnd = 0.dp
    )
    val rightRound = RoundedCornerShape(
        topStart = 0.dp,
        topEnd = 50.dp,
        bottomStart = 0.dp,
        bottomEnd = 50.dp
    )

    val notRound = RectangleShape

    fun getShape(index: Int): Shape {
        if (actions.size == 1) {
            return completeRound
        }

        val lastIndex = actions.lastIndex

        return when(index) {
            0 -> leftRound
            lastIndex -> rightRound
            else -> notRound
        }
    }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {

        val buttonSize = 60

        for ((index, action) in actions.withIndex()) {
            if (index > 0) {
                Column {
                    val blackSpot = @Composable {
                        Spacer(
                            modifier = Modifier
                                .width(1.dp)
                                .height((buttonSize * 0.1).dp)
                                .background(Color.Black)
                        )
                    }
                    blackSpot()
                    Spacer(
                        modifier = Modifier
                            .width(1.dp)
                            .height((buttonSize*0.8).dp)
                            .background(Color.LightGray)
                    )
                    blackSpot()
                }
            }

            FloatingActionButton(
                onClick = action.getAction(),
                contentColor = Color.White,
                backgroundColor = Color.Black,
                shape = getShape(index),
                modifier = Modifier
                    .size(buttonSize.dp)
            ) {
                Icon(
                    action.getIcon(),
                    contentDescription = action.getDescription()
                )
            }
        }
    }


}