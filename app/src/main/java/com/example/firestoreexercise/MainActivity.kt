package com.example.firestoreexercise

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private val AUTHOR_KEY = "author"
    private val QUOTE_KEY = "quote"

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveQuote(view: View){
        val quoteView = findViewById(R.id.edtQuote) as EditText
        val authorView = findViewById(R.id.edtAuthor) as EditText

        var quoteText: String = quoteView.text.toString()
        var authorText: String = authorView.text.toString()

        if(quoteText.isEmpty() || authorText.isEmpty()) return
        val dataToSave = HashMap<String, Any>()
        dataToSave[QUOTE_KEY] = quoteText
        dataToSave[AUTHOR_KEY] = authorText

        db.collection("exercise")
            .add(dataToSave)
            .addOnSuccessListener { documentReference ->
            Log.d("MainActivity", "Document was Saved")
            }
            .addOnFailureListener { e ->
                Log.w("MainActivity", e)
            }
    }
}
