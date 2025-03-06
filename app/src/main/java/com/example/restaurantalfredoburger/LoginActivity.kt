package com.example.restaurantalfredoburger

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantalfredoburger.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    // Credenciales fijas para la aplicación
    private val validEmail = "admin@gmail.com"
    private val validPassword = "admin1234"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Configurar botón de inicio de sesión con las validaciones
        binding.loginbutton.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            // Validación A: Comprobar que ambos campos estén completos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa correo y contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validación B y C: Comprobar que las credenciales sean correctas
            if (email == validEmail && password == validPassword) {
                // Credenciales correctas, redirigir al menú principal
                val intent = Intent(this, ChooseLocationActivity::class.java)
                startActivity(intent)
            } else {
                // Credenciales incorrectas, mostrar mensaje de error
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        // Redirigir a crear una nueva cuenta
        binding.donthavebutton.setOnClickListener {
            val intent = Intent(this, SignActivity::class.java)
            startActivity(intent)
        }

        // Deshabilitar temporalmente los botones de Google y Facebook
        binding.button.isEnabled = false  // Facebook
        binding.button2.isEnabled = false // Google
    }
}