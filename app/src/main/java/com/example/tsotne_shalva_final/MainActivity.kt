package com.example.tsotne_shalva_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginButton.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        if(userNameEditText.text.toString().isEmpty()){
            userNameEditText.error = "Enter username"
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(userNameEditText.text.toString()).matches()){
            userNameEditText.error = "Enter valid email"
            userNameEditText.requestFocus()
            return
        }

        if(passwordEditText.text.toString().isEmpty()){
            passwordEditText.error = "Enter password"
            passwordEditText.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(userNameEditText.text.toString(), passwordEditText.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser : FirebaseUser?){

        if(currentUser != null){
            val intent = Intent(this, WeatherActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(
                baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
