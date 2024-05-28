import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue

class GameModel : Model {
    private val views = mutableListOf<View>()
    private var level: Int by mutableStateOf(1)
    private var points: Int by mutableStateOf(0)
    private var board: GameBoard = GameBoard(level)
    private var onOf: Boolean = false
    val levelPoints: MutableState<TextFieldValue> = mutableStateOf(TextFieldValue(""))

    init {
        board.remove().also { addPoints(it) }
    }

    override fun registerView(v: View) {
        views.add(v)
    }

    override fun updateViews() {
        views.forEach { it.update() }
    }

    fun getBoard(): GameBoard {
        return board
    }

    fun getLevel(): Int {
        return level
    }

    fun getPoints(): Int {
        return points
    }

    fun redoGameBoard() {
        println("redoes")
        board = GameBoard(level)
        points = 0
        updateViews()
        addPoints(board.remove())
    }

    fun addPoints(numberOfBricks: Int) {
        println("Adds points")
        points += numberOfBricks * 5
        if (points >= 15 + level * 15) {
            level++
            redoGameBoard()
        }

        updateViews()
    }
}
