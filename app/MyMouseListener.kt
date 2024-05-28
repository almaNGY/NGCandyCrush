import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class MyMouseListener(private val screen: Gamescreen, private val model: GameModel) : MouseAdapter() {

    private var lastBrick: Brick? = null

    override fun mousePressed(e: MouseEvent) {
        val mousePoint = screen.mousePosition
        println(screen.mousePosition)
        println("Mouse pressed outside bounds")
        if (mousePoint != null) {
            println("Mouse pressed")
            screen.model.board.pick(mousePoint.x, mousePoint.y)
            model.updateViews()
        }
    }

    override fun mouseDragged(e: MouseEvent) {
        val mousePoint = screen.mousePosition
        if (lastBrick != null && lastBrick != model.board.pickedBricks[0] && lastBrick != model.board.pickedBricks[1]) {
            lastBrick?.setPickedFalse()
        }
        model.board.searchByCoord(mousePoint.x, mousePoint.y)?.let {
            it.setPickedTrue()
            lastBrick = it
        }
        model.updateViews()
    }

    override fun mouseReleased(e: MouseEvent) {
        val mousePoint = screen.mousePosition
        if (mousePoint != null) {
            println("Mouse released")
            model.board.pick(mousePoint.x, mousePoint.y)
            model.board.change()
            model.addPoints(model.board.remove())
            model.updateViews()
        }
    }
}
