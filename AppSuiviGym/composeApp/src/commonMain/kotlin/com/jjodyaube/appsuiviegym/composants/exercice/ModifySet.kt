package com.jjodyaube.appsuiviegym.composants.exercice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jjodyaube.appsuiviegym.WorkoutSet
import com.jjodyaube.appsuiviegym.composants.OutlinedButton
import com.jjodyaube.appsuiviegym.utils.capitalize

@Composable
fun ModifySet(
    numeroDuSet: Int,
    set: WorkoutSet,
    horizontalPadding: Int,
    aSetIsBeingModified: MutableState<WorkoutSet?>,
    aSetGotModified: MutableState<Boolean>
) {

    var poids by remember { mutableStateOf(set.getPoids()) }
    var nombreRepetition by remember { mutableStateOf(set.getNombreRepetition()) }
    var typeEquipement by remember { mutableStateOf(set.getTypeEquipement()) }
    var uniteDeMesure by remember { mutableStateOf(set.getUniteDeMesure()) }

    val temporaryWorkoutSet = WorkoutSet(
        poids,
        nombreRepetition,
        typeEquipement,
        uniteDeMesure,
    )


    fun saveSet() {
        set.setPoids(poids)
        set.setNombreRepetition(nombreRepetition)
        set.setTypeEquipement(typeEquipement)
        set.setUniteDeMesure(uniteDeMesure)
        set.setIsDone(true)
        aSetGotModified.value = true
    }

    fun changeSetRep(valueToAdd: Int) {
        temporaryWorkoutSet.addRep(valueToAdd)
        nombreRepetition = temporaryWorkoutSet.getNombreRepetition()
    }

    fun addPoids() {
        temporaryWorkoutSet.addPoids()
        poids = temporaryWorkoutSet.getPoids()
    }

    fun reducePoids() {
        temporaryWorkoutSet.reducePoids()
        poids = temporaryWorkoutSet.getPoids()
    }

    fun skipSet() {
        set.skip()
        aSetGotModified.value = true
    }

    fun setMutablesAfterSwtich() {
        poids = temporaryWorkoutSet.getPoids()
        typeEquipement = temporaryWorkoutSet.getTypeEquipement()
        uniteDeMesure = temporaryWorkoutSet.getUniteDeMesure()
    }

    fun switchTypeEquipement() {
        temporaryWorkoutSet.switchTypeEquipement()
        setMutablesAfterSwtich()
    }

    fun switchUniteDeMesure() {
        temporaryWorkoutSet.switchUniteDeMesure()
        setMutablesAfterSwtich()
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Set $numeroDuSet",
            fontSize = 18.sp,
            color = Color.Gray,
            letterSpacing = (-0.5).sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = horizontalPadding.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AddRemoveButton("-", { changeSetRep(-1) })

            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                AfficherNombreRep(temporaryWorkoutSet.getNombreRepetition(), 1.5f)
            }

            AddRemoveButton("+", { changeSetRep(1) })
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = horizontalPadding.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddRemoveButton("-", { reducePoids() })

                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AfficherPoids(temporaryWorkoutSet, 1.5f)
                }

                AddRemoveButton("+", { addPoids() })
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(0.6f)
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedButton(
                        text = capitalize(uniteDeMesure.toString().lowercase()),
                        action = { switchUniteDeMesure() },
                        height = 30,
                        icon = Icons.Filled.Refresh,
                        iconDescription = "Changer unité de mesure"
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedButton(
                        text = capitalize(typeEquipement.toString().lowercase()),
                        action = { switchTypeEquipement() },
                        height = 30,
                        icon = Icons.Filled.Refresh,
                        iconDescription = "Changer type d'équipement"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier.padding(horizontal = horizontalPadding.dp)
        ) {
            TextButton(
                onClick = { saveSet() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
                    .height(35.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text("Finir")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedButton(
                        text = "Skip",
                        action = { skipSet() },
                        height = 35
                    )
                }
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedButton(
                        text = "Fermer",
                        action = { aSetIsBeingModified.value = null },
                        height = 35,
                    )
                }
            }
        }
    }
}