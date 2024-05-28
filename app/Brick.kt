import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import java.awt.Point


class Brick(
    var color: Color,
    var coordinates: CoordinateRange,
    var pos: Point
) {
    var picked: Boolean = false

    fun setPickedTrue() {
        this.picked = true
    }

    fun setPickedFalse() {
        this.picked = false
    }

    fun updateCoordinates(squareWidth: Int) {
        val startX = pos.x * squareWidth
        val startY = pos.y * squareWidth
        this.coordinates = CoordinateRange(startX, startX + squareWidth, startY, startY + squareWidth)
    }

    @Composable
    fun DrawBrick() {
        Canvas(modifier = Modifier.size((coordinates.maxX - coordinates.minX).dp, (coordinates.maxY - coordinates.minY).dp)) {
            drawCircle(
                color = color,
                radius = (size.minDimension / 2),
                center = Offset((coordinates.maxX - coordinates.minX) / 2f, (coordinates.maxY - coordinates.minY) / 2f)
            )
            drawContext.canvas.nativeCanvas.apply {
                val fontSize = (coordinates.maxX - coordinates.minX).toFloat()
                val paint = Paint().asFrameworkPaint().apply {
                    isAntiAlias = true
                    textSize = fontSize
                    color = android.graphics.Color.BLACK
                }
                drawText(
                    "n",
                    (coordinates.maxX - coordinates.minX) / 4f,
                    (coordinates.maxY - coordinates.minY) - (coordinates.maxY - coordinates.minY) / 4f,
                    paint
                )
            }
            if (picked) {
                drawCircle(
                    color = Color.Black,
                    radius = (size.minDimension / 2),
                    center = Offset((coordinates.maxX - coordinates.minX) / 2f, (coordinates.maxY - coordinates.minY) / 2f),
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }
    }
}