data class CoordinateRange(
    val minX: Int,
    val maxX: Int,
    val minY: Int,
    val maxY: Int
) {
    fun inRange(xValue: Int, yValue: Int): Boolean {
        return (xValue >= this.minX && xValue <= this.maxX) && (yValue >= this.minY && yValue <= this.maxY)
    }
}
