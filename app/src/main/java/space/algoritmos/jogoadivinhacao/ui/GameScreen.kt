package space.algoritmos.jogoadivinhacao.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import space.algoritmos.jogoadivinhacao.viewmodel.GameViewModel

@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel()) {
    var input by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }
    var jogoFinalizado by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "🎯 Número Secreto",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Adivinhe um número de 1 a 100", style = MaterialTheme.typography.headlineSmall)

            if (!jogoFinalizado) {
                OutlinedTextField(
                    value = input,
                    onValueChange = { input = it },
                    label = { Text("Seu palpite") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        val palpite = input.toIntOrNull()
                        if (palpite != null && palpite in 1..100) {
                            val resultado = gameViewModel.verificarPalpite(palpite)
                            mensagem = resultado
                            if (resultado.contains("Parabéns")) {
                                jogoFinalizado = true
                            }
                        } else {
                            mensagem = "⚠️ Digite um número válido entre 1 e 100."
                        }
                        input = ""
                        focusManager.clearFocus()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Chutar")
                }
            }

            Text(mensagem, style = MaterialTheme.typography.bodyLarge)

            if (jogoFinalizado) {
                Button(onClick = {
                    gameViewModel.reiniciarJogo()
                    mensagem = ""
                    jogoFinalizado = false
                    input = ""
                }, modifier = Modifier.fillMaxWidth()) {
                    Text("🔁 Jogar Novamente")
                }
            }
        }
    }
}