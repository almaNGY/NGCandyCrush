import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

class Gamescreen(private val model: GameModel) : View {

    init {
        model.registerView(this)
    }

    @Composable
    fun DrawBoard() {
        // Implementera ritningen av spelbrädet här
        // Använd Painter eller ImageVector för att rita brädet
    }

    override fun update() {
        // Uppdatera vyn här
    }

    fun getModel(): GameModel {
        return model
    }
}
