package com.denobaba.loginandsignup

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.denobaba.loginandsignup.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firestore = Firebase.firestore
        auth = Firebase.auth
        getData()

    }

    fun getData(){
        firestore.collection("name").addSnapshotListener { value, error ->
            if(error !=null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()

            }else{
                if(value !=null){
                    if(!value.isEmpty){
                        val documents = value.documents
                        for (i in documents){
                            val name = i.get("name") as String
                            val email = i.get("email") as String
                            if(email== auth.currentUser?.email){
                                binding.textView.setText("Welcome $name")
                            }
                        }
                    }
                }
            }

        }




    }
}