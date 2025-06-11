package br.ufg.inf.listviewcustom

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nome : TextView = findViewById(R.id.nome)
        val marca : TextView = findViewById(R.id.marca)
        val descricao : TextView = findViewById(R.id.descricao)
        val valor : TextView = findViewById(R.id.valor)
        val imagem : ImageView = findViewById(R.id.imagemCarro)

        val nomeExtra = intent.getStringExtra("nome")
        val marcaExtra = intent.getStringExtra("marca")
        val imagemExtra = intent.getIntExtra("imagem", 0)
        val descricaoExtra = intent.getStringExtra("descricao")
        val valorExtra = intent.getStringExtra("valor")

        nome.setText(nomeExtra);
        marca.setText(marcaExtra);
        descricao.setText(descricaoExtra);
        valor.setText(valorExtra);
        imagem.setImageResource(imagemExtra);

        // Recupera o botão pelo ID
        val btnVoltar: Button = findViewById(R.id.btnVoltar)

        // Define um listener para o botão
        btnVoltar.setOnClickListener {
            // Cria o Intent para a nova Activity
            val intent = Intent(this, MainActivity::class.java)
            // Inicia a nova Activity
            startActivity(intent)
        }

    }
}