package com.ic.cinefile.screens

//import com.ic.cinefile.activities.contentGeneroActivity
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ic.cinefile.activities.GeneroActivity
import com.ic.cinefile.ui.theme.black
import com.ic.cinefile.ui.theme.dark_blue
import com.ic.cinefile.ui.theme.white
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrearPerfil() {

    val context = LocalContext.current
    val activity = context as Activity

    // Obtener los datos de la actividad anterior
    val correo = activity.intent.getStringExtra("correo") ?: ""
    val contrasena = activity.intent.getStringExtra("contrasena") ?: ""


    //val calendar = Calendar.getInstance() // Inicializar el calendario correctamente
    // Obtener el año actual
    //val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    // Obtener el mes actual
    //val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1 // Los meses en Calendar son 0-based

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Crea tu perfil",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                color = white,
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.height(70.dp))

        val usuario: MutableState<String> = remember { mutableStateOf("") }

        TextField(
            modifier = Modifier.width(300.dp), // Ajustar el ancho para que coincida con el botón de selección de fecha
            value = usuario.value,
            maxLines = 1, // Limitar a una línea para evitar saltos de línea
            onValueChange = {
                if (it.length <= 15) { // Limitar a 15 caracteres
                    usuario.value = it
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = black,
                focusedContainerColor = black,
                unfocusedLabelColor = white,
                focusedLabelColor = white,
                focusedIndicatorColor = white,
                cursorColor = white,
                focusedTextColor = white
            ),
            placeholder = {
                Text(
                    text = "Nombre de usuario (máximo 15 caracteres)",
                    style = TextStyle(
                        color = white,
                        fontSize = 15.sp,
                        letterSpacing = 0.1.em,
                        fontWeight = FontWeight.Normal,
                    )
                )
            },
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Fecha de nacimiento",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp),
            style = TextStyle(
                color = white,
                fontSize = 15.sp,
                letterSpacing = 0.1.em,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Normal,
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        val hidden: MutableState<Boolean> = remember { mutableStateOf(false) }
        val dateResult = remember { mutableStateOf("DD/MM/YYYY") }
        val datePickerState = rememberDatePickerState(initialSelectedDateMillis = 1578096000000)
        Button(
            onClick = { hidden.value = !hidden.value },
            modifier = Modifier
                .width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = white,
                contentColor = black
            ),
        ) {
            Row {
                Text(text = dateResult.value, fontSize = 15.sp)
                Spacer(modifier = Modifier.width(130.dp))
                Icon(Icons.Filled.DateRange, contentDescription = "")
            }
        }
        if (hidden.value) {
            DatePickerDialog(
                onDismissRequest = {
                    hidden.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            hidden.value = false
                            var date = "no selection"
                            if (datePickerState.selectedDateMillis != null) {
                                // Utilizamos la zona horaria UTC para evitar problemas de ajuste de días
                                val calendar =
                                    Calendar.getInstance(TimeZone.getTimeZone("UTC")).apply {
                                        timeInMillis = datePickerState.selectedDateMillis!!
                                    }
                                val day = calendar.get(Calendar.DAY_OF_MONTH) - 1
                                val month = calendar.get(Calendar.MONTH) + 1
                                val year = calendar.get(Calendar.YEAR)

                                // Validar el día según el mes
                                val maxDay = when (month) {
                                    4, 6, 9, 11 -> 30
                                    2 -> if (calendar.get(Calendar.YEAR) % 4 == 0) 29 else 28
                                    else -> 31
                                }
                                if (day > maxDay) {
                                    calendar.set(Calendar.DAY_OF_MONTH, maxDay)
                                }

                                // Validar el mes
                                if (month > 12) {
                                    calendar.set(Calendar.MONTH, 11)
                                }

                                // Sumar 1 al día (si el día es menor que el máximo)
                                if (day < maxDay) {
                                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                                }

                                // Validar el año con el año actual
                                val currentYearValidation =
                                    Calendar.getInstance().get(Calendar.YEAR)
                                if (year > currentYearValidation) {
                                    calendar.set(Calendar.YEAR, currentYearValidation)
                                }

                                date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(
                                    calendar.time
                                )
                            }
                            dateResult.value = date
                        },

                        ) {
                        Text(
                            "Confirmar",
                            color = black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                },

                ) {

                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        //año actual
                        currentYearContentColor = dark_blue,
                        //año seleccionado contenedor
                        selectedYearContainerColor = dark_blue,
                        //contenedor del a
                        selectedDayContainerColor = dark_blue,
                        todayContentColor = dark_blue,
                        todayDateBorderColor = dark_blue,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = {
                val username = usuario.value
                val birthday = dateResult.value

                // Validación: verificar que el nombre de usuario y la fecha de nacimiento no estén vacíos
                if (username.isNotEmpty() && birthday != "DD/MM/YYYY") {
                    // Parsear la fecha de nacimiento
                    val birthDate =
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(birthday)
                    birthDate?.let {
                        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                        calendar.time = birthDate

                        val birthYear = calendar.get(Calendar.YEAR)
                        val birthMonth =
                            calendar.get(Calendar.MONTH) + 1 // Los meses en Calendar son 0-based
                        val birthDay = calendar.get(Calendar.DAY_OF_MONTH)

                        // Obtener la fecha actual
                        val currentCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                        val currentYear = currentCalendar.get(Calendar.YEAR)
                        val currentMonth = currentCalendar.get(Calendar.MONTH) + 1
                        val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)

                        // Calcular la edad
                        var age = currentYear - birthYear
                        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) {
                            age--
                        }

                        // Validar la edad
                        if (age >= 12) {
                            val intent = Intent(context, GeneroActivity::class.java).apply {
                                putExtra("correo", correo)
                                putExtra("contrasena", contrasena)
                                putExtra("username", username)
                                putExtra("birthday", birthday)
                            }
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(
                                context,
                                "Debes ser mayor de 12 años para usar la aplicación",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Por favor, Completa todos los campos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier
                .width(300.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = white,
                contentColor = black
            ),
        ) {
            Text(
                text = "Siguiente",
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CrearPerfilPreview() {
    //val navController = rememberNavController()
    CrearPerfil()
}
