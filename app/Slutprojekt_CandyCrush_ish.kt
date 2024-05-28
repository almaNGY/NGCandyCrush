object Slutprojekt_CandyCrush_ish {

    @JvmStatic
    fun main(args: Array<String>) {
        val model = GameModel()
        val screen = Gamescreen(model)
        val frame = GamescreenFrame(screen, model)
    }

}
