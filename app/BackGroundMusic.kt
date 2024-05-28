import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.io.File
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.LineUnavailableException
import javax.sound.sampled.UnsupportedAudioFileException

class BackGroundMusic {
    private var clip: Clip? = null

    fun playMusic() {
        try {
            val musicPath = File("/assets/music.wav")

            if (musicPath.exists()) {
                val audioInput = AudioSystem.getAudioInputStream(musicPath)
                clip = AudioSystem.getClip()
                clip?.open(audioInput)
                clip?.start()
                clip?.loop(Clip.LOOP_CONTINUOUSLY)  // För att spela musik i en loop
            } else {
                println("Kan inte hitta ljudfilen.")
            }
        } catch (e: UnsupportedAudioFileException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: LineUnavailableException) {
            e.printStackTrace()
        }
    }

    fun stopMusic() {
        clip?.let {
            if (it.isRunning) {
                it.stop()
            }
        }
    }

    fun restartMusic() {
        clip?.let {
            it.framePosition = 0  // Går till början av ljudklippet
            it.start()
            it.loop(Clip.LOOP_CONTINUOUSLY)  // Startar musiken och sätter den i loop
        }
    }
}
