package com.denobaba.loginandsignup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.denobaba.loginandsignup.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        firestore = Firebase.firestore

    }



    fun signup(view: View){
        val email = binding.emailhere.text.toString()
        val password = binding.passhere.text.toString()
        if (email.equals("")||password.equals("")){
            Toast.makeText(this,"Please Write Email And Password",Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val intent = Intent(this,LogIn::class.java)
                startActivity(intent)
                finish()

            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

        val postMap = hashMapOf<String,Any>()
        postMap.put("name",binding.namehere.text.toString())
        postMap.put("email",binding.emailhere.text.toString())

        firestore.collection("name").add(postMap).addOnSuccessListener {
            val intent = Intent(this,LogIn::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
        }







    }


}