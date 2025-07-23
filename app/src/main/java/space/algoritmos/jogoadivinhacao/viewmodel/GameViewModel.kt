package space.algoritmos.jogoadivinhacao.viewmodel

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GameViewModel : ViewModel() {
    var numeroSecreto = Random.nextInt(1, 101)
        private set
    var tentativas = 0
        private set

    fun reiniciarJogo() {
        numeroSecreto = Random.nextInt(1, 101)
        tentativas = 0
    }

    fun verificarPalpite(palpite: Int): String {
        tentativas++
        return when {
            palpite < numeroSecreto -> "🔼 Tente um número maior."
            palpite > numeroSecreto -> "🔽 Tente um número menor."
            else -> "🎉 Parabéns! Você acertou o número $numeroSecreto em $tentativas tentativas!"
        }
    }
}
