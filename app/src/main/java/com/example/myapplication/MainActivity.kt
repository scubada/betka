package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.models.Items
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.lang.ref.Reference

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private var database: FirebaseDatabase = Firebase.database
    private var reference = database.getReference("data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reference.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<Items>()

                if (value != null) {
                    binding.button.text = value.name
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })

        binding.button.setOnClickListener{
            Toast.makeText(this, "HI Man!!", Toast.LENGTH_SHORT).show()
        }
    }
}