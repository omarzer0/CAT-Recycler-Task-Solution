package az.zero.cat_recyclertask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.zero.cat_recyclertask.databinding.ItemNoteBinding

class NoteAdapter(
    private val noteList: List<Note>,
    private val onNoteClick: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.bind(note)
    }

    override fun getItemCount() = noteList.size

    inner class NoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*
        * The init block will be called only when the view holders are created
        * which is limited number and we add listeners to the view holders themself
        * and using what ever data on them to limit the number of listener we use
        * */

        init {
            binding.root.setOnClickListener {
                onNoteClick(noteList[adapterPosition])
            }
        }

        fun bind(note: Note) {
            binding.apply {
                tvTitle.text = note.title
                tvDescription.text = note.description
                viewColor.setBackgroundColor(note.color)
            }
        }
    }
}