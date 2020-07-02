package com.example.tsotne_shalva_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth

        signInButton.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        registerButton.setOnClickListener{
            onRegister()
        }
    }

    private fun onRegister(){

        if(registerUserNameEditText.text.toString().isEmpty()){
            registerUserNameEditText.error = "Please Enter Email"
            registerUserNameEditText.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(registerUserNameEditText.text.toString()).matches()){
            registerUserNameEditText.error = "Please Enter Valid Email"
            registerUserNameEditText.requestFocus()
            return
        }
        if(registerPasswordEditText.text.toString().isEmpty()){
            registerPasswordEditText.error = "Please Enter Password"
            registerPasswordEditText.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(registerUserNameEditText.text.toString(),registerPasswordEditText.text.toString())
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(baseContext,"Failed To Register, Try Again",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}