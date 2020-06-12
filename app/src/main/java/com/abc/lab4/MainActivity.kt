package com.abc.lab4

import Mark
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val marksRef = FirebaseDatabase.getInstance().getReference("marks")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        save_button.setOnClickListener { saveMarkFromForm() }

        marksRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(databaseError: DatabaseError) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildChanged(dataSnapshot: DataSnapshot, previousName: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {
                val mark = dataSnapshot.getValue(Mark::class.java)
                if (mark != null) writeMark(mark)
            }
        })
    }

    private fun saveMarkFromForm() {
        val mark = Mark(
                name_editText.text.toString(),
                subject_editText.text.toString(),
                mark_editText.text.toString().toDouble()
        )
        marksRef.push().setValue(mark)
    }

    private fun writeMark(mark: Mark) {
        val text = list_textView.text.toString() + mark.toString() + "\n"
        list_textView.text = text
    }

    //test
}