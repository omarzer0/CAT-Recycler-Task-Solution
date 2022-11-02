package az.zero.cat_recyclertask

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import az.zero.cat_recyclertask.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val noteList = mutableListOf<Note>()
    private var toast: Toast? = null

    private val noteAdapter = NoteAdapter(
        noteList = noteList,
        onNoteClick = { handleNoteClick(it) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRv()
        handleClicks()
    }

    private fun setUpRv() {
        binding.rvNoteAdapter.adapter = noteAdapter
    }

    private fun handleClicks() {
        binding.fabAddNote.setOnClickListener {
            val index = noteList.size + 1
            val newNote = Note(index, "Title #$index", "Description #$index", getRandomColorInt())
            noteList.add(newNote)
            noteAdapter.notifyItemInserted(noteList.size - 1)
        }
    }

    /*
    * ARGB -> Alpha Red Green Blue.
    * Any color consists of these colors with Alpha for the opacity.
    * These colors are any number between 0 - 255
    * so we generate random values with these specifications:
    * Alpha = 255 for full opacity
    * Red => 0 - 255 for the red
    * Green => 0 - 255 for the green
    * Blue => 0 - 255 for the blue
    * the result is a random color
    * */
    private fun getRandomColorInt(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    /* To prevent multiple toast messages we use the same object
     * and check if is not null (means maybe showing toast) so we cancel it
     * and start a new one
     */
    private fun handleNoteClick(note: Note) {
        toast?.cancel()
        toast = Toast(this@MainActivity).apply {
            setText(note.title)
            duration = Toast.LENGTH_SHORT
            show()
        }
    }
}