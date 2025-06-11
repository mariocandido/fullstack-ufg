package br.ufg.inf.atividade1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PainelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_painel)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.painel)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val back : ImageView = findViewById(R.id.back)
        val i_back = Intent(applicationContext, MainActivity::class.java)

        back.setOnClickListener{
            startActivity(i_back)
        }

        val banner : ImageView = findViewById(R.id.banner)
        val i_detail = Intent(applicationContext, DetailActivity::class.java)

        banner.setOnClickListener{
            startActivity(i_detail)
        }

    }
}