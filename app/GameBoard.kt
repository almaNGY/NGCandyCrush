import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class Brick(var color: Color, var position: Offset, var picked: Boolean = false)

class GameBoard(level: Int) {
    private val columns: MutableList<MutableList<Brick>> = mutableListOf()
    private val squareWidth = 500 / (level + 3).coerceAtLeast(5)
    private val pickedBricks = arrayOfNulls<Brick>(2)
    private var pickedPlace = 0

    init {
        createColumns(level)
        fillBricks()
    }

    private fun createColumns(level: Int) {
        val columnCount = when {
            level < 4 -> 5
            level > 21 -> 24
            else -> level + 3
        }
        for (i in 0 until columnCount step 2) {
            columns.add(mutableListOf())
        }
    }

    private fun fillBricks() {
        for (i in columns.indices) {
            for (j in columns.indices) {
                fillBrick(i, j)
            }
        }
    }

    private fun fillBrick(i: Int, j: Int) {
        val colors = listOf(Color.Blue, Color.Green, Color.Red, Color.Yellow, Color.Magenta)
        val color = colors[Random.nextInt(colors.size)]
        val startX = i * squareWidth
        val startY = j * squareWidth
        val position = Offset(startX.toFloat(), startY.toFloat())
        columns[i].add(Brick(color, position))
    }

    fun draw(canvas: androidx.compose.ui.graphics.Canvas) {
        for (column in columns) {
            for (brick in column) {
                canvas.drawIntoCanvas { it.drawCircle(brick.color, 20f, brick.position) }
            }
        }
    }

    fun change() {
        val first = pickedBricks[0] ?: return
        val second = pickedBricks[1] ?: return

        val tempPos = first.position
        first.position = second.position
        second.position = tempPos

        first.picked = false
        second.picked = false
    }

    fun pick(xValue: Int, yValue: Int) {
        val brick = searchByCoord(xValue, yValue)
        brick?.picked = true
        pickedBricks[pickedPlace % 2] = brick
        pickedPlace++
    }

    private fun searchByCoord(xValue: Int, yValue: Int): Brick? {
        for (column in columns) {
            for (brick in column) {
                if (brick.position.x.toInt() == xValue && brick.position.y.toInt() == yValue) {
                    return brick
                }
            }
        }
        return null
    }
}

@Composable
fun GameBoardView(gameBoard: GameBoard) {
    Canvas(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        gameBoard.draw(drawContext.canvas)
    }
}

@Preview
@Composable
fun PreviewGameBoard() {
    val gameBoard = GameBoard(level = 5)
    GameBoardView(gameBoard)
}
