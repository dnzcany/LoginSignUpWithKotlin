package com.denobaba.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.denobaba.loginandsignup.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_log_in)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firestore = Firebase.firestore
        auth = Firebase.auth
        binding.signuptext.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun loginn(view: View){
        val email = binding.emailhere.text.toString()
        val password = binding.passhere.text.toString()
        if(email.equals("")||password.equals("")){
            Toast.makeText(this,"Please write password and email",Toast.LENGTH_LONG).show()

        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()

            }
        }

    }




}