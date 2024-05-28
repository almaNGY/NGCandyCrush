import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

class GamescreenFrame(private val panel: Gamescreen, private val model: GameModel) : JFrame(), View {

    private val redoBtn: JButton
    private val musicBtn: JButton
    private val bgm: BackGroundMusic

    init {
        model.registerView(this)
        bgm = BackGroundMusic()
        bgm.playMusic()

        initComponents()

        size = 750 to 700
        isVisible = true
        defaultCloseOperation = EXIT_ON_CLOSE
        pack()
    }

    override fun update() {
        model.levelPoints.text = "${model.points} / ${15 + model.level * 15} : ${model.level}"
    }

    private fun initComponents() {
        redoBtn = JButton("Gör om").apply {
            addActionListener {
                model.redoGameBoard()
            }
        }
        musicBtn = JButton("Music").apply {
            var onOf = true
            addActionListener {
                if (onOf) {
                    bgm.stopMusic()
                    onOf = false
                } else {
                    bgm.restartMusic()
                    onOf = true
                }
            }
        }

        model.levelPoints.isEditable = false
        model.levelPoints.text = "${model.points} / ${15 + model.level * 15} : ${model.level}"

        val info = JTextArea().apply {
            isVisible = true
            isEditable = false
            text = "Flytta brickor som ligger bredvid varandra för att få tre eller fler av samma färg i rad. \n" +
                    "Du får 5 poäng för varje bricka. \n" +
                    "För varje nivå krävs mer poäng för att levla upp. \n" +
                    "Om du inte hittar några fler drag kan du trycka på \"börja om\" för att börja om nivån. \n" +
                    "Se upp för falska rader. De försvinner inte och ger inga poäng!"
        }

        layout = BorderLayout()
        add(panel, BorderLayout.CENTER)
        add(redoBtn, BorderLayout.WEST)
        add(model.levelPoints, BorderLayout.NORTH)
        add(info, BorderLayout.SOUTH)
        add(musicBtn, BorderLayout.EAST)
    }
}
