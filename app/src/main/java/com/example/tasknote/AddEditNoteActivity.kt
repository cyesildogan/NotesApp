package com.example.tasknote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Date

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var noteTitleEdit: EditText
    lateinit var noteDescription: EditText
    lateinit var addUpdateBtn: Button
    lateinit var viewModel: NoteViewModel
    var noteID = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteTitleEdit = findViewById(R.id.edtxt_note_title)
        noteDescription = findViewById(R.id.edtxt_note_description)
        addUpdateBtn = findViewById(R.id.btn_add_update)
        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")){
            val noteTitle = intent.getStringExtra("noteType")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID",-1)
            addUpdateBtn.setText("Update Note")
            noteTitleEdit.setText(noteTitle)
            noteDescription.setText(noteDesc)
        }else{
            addUpdateBtn.setText("Save Note")
        }

        addUpdateBtn.setOnClickListener {
            val noteTitle = noteTitleEdit.text.toString()
            val noteDesc = noteDescription.text.toString()
            if (noteType.equals("Edit ")){
                if (noteTitle.isNotEmpty()&& noteDesc.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM , yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = Note(noteTitle,noteDesc,currentDate)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this,"Note Updated",Toast.LENGTH_LONG).show()
                }
            }else{
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM , yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle,noteDesc,currentDate))
                    Toast.makeText(this,"Note Added",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }

    }
}