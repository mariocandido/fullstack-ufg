package br.ufg.inf.listviewcustom

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.ufg.inf.listviewcustom.databinding.ActivityMainBinding

data class Produto(val nome: String, val drawable: Int, val marca: String, val descricao: String, val valor: String)

fun preencheDados(): List<Produto> {
    return listOf(
        Produto("Chevrolet Onix", R.drawable.onix, "Chevrolet", "O Chevrolet Onix é um modelo compacto de grande sucesso no Brasil, oferecendo motor 1.0 turbo com excelente desempenho e eficiência de consumo de combustível. Ideal para quem busca um carro prático, com design moderno e baixo custo de manutenção, perfeito para o dia a dia nas cidades.", "R$ 69.990"),
        Produto("Volkswagen Gol", R.drawable.gol, "Volkswagen", "O Volkswagen Gol é um dos carros mais tradicionais do mercado brasileiro, com motor 1.0, design simples e excelente custo-benefício. É uma opção prática e econômica para quem precisa de um carro para o uso diário, com baixo custo de manutenção e alta durabilidade.", "R$ 54.990"),
        Produto("Fiat Argo", R.drawable.argo, "Fiat", "O Fiat Argo é um compacto com design moderno e motor 1.3, que proporciona bom desempenho e conforto. Com características que agradam tanto no visual quanto no desempenho, ele é uma excelente escolha para quem busca versatilidade, bom consumo de combustível e tecnologias de conectividade.", "R$ 72.490"),
        Produto("Honda Civic", R.drawable.civic, "Honda", "O Honda Civic é um sedã de luxo que se destaca pela combinação de motor 2.0, design sofisticado e tecnologias avançadas. Ideal para quem busca um carro de alto desempenho, conforto excepcional e uma condução refinada, sendo uma das opções mais confiáveis do mercado.", "R$ 139.990"),
        Produto("Hyundai HB20", R.drawable.hb20, "Hyundai", "O Hyundai HB20 é um carro compacto com motor 1.6, proporcionando boa performance tanto em cidades quanto em rodovias. Seu design moderno e recursos tecnológicos, como sistema de conectividade e câmbio automático, tornam-no uma escolha popular entre os consumidores que buscam praticidade e estilo.", "R$ 71.990"),
        Produto("Jeep Compass", R.drawable.compass, "Jeep", "O Jeep Compass é um SUV que oferece motor 2.0 turbo e um excelente nível de conforto, perfeito para quem gosta de viajar e explorar novos destinos com sofisticação. Seu sistema de tração integral e tecnologias de segurança avançadas garantem uma condução segura e agradável em diversos tipos de terreno.", "R$ 155.000"),
        Produto("Ford Fiesta", R.drawable.fiesta, "Ford", "O Ford Fiesta é um hatch compacto, com motor 1.6 que garante um bom desempenho urbano, ideal para quem precisa de um carro ágil e econômico para o dia a dia. Seu design moderno e recursos de conectividade oferecem uma experiência de condução prática e divertida.", "R$ 63.990"),
        Produto("Renault Kwid", R.drawable.kwid, "Renault", "O Renault Kwid é um SUV compacto com motor 1.0 e grande economia de combustível. Seu tamanho reduzido e design inovador o tornam uma opção excelente para quem busca um carro funcional e acessível, ideal para quem precisa de agilidade no trânsito urbano e baixo custo de manutenção.", "R$ 47.990"),
        Produto("Toyota Corolla", R.drawable.corolla, "Toyota", "O Toyota Corolla é um sedã premium que se destaca pela confiabilidade, com motor 2.0 e alto nível de conforto e segurança. Ideal para quem busca um veículo durável, com tecnologias avançadas, baixo custo de manutenção e ótimo desempenho em qualquer situação.", "R$ 149.990"),
        Produto("Nissan Kicks", R.drawable.kicks, "Nissan", "O Nissan Kicks é um SUV com motor 1.6 e tecnologias de segurança avançadas, como alerta de colisão frontal e monitoramento de pontos cegos. Seu design arrojado e amplo espaço interno tornam-no uma opção excelente para quem busca um veículo confortável, seguro e com bom consumo de combustível.", "R$ 110.000"))
}
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        iniciaRecyclerView()
    }

    private fun iniciaRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)

        val produtcsList = preencheDados()

        // Configurando o adapter com evento de clique
        val adapter = AdapterProducts(produtcsList) { productSelected ->
            // Navegando para a ProductActivity
            val intent = Intent(this, ProductActivity::class.java)
            intent.putExtra("imagem", productSelected.drawable)
            intent.putExtra("nome", productSelected.nome)
            intent.putExtra("marca", productSelected.marca)
            intent.putExtra("descricao", productSelected.descricao)
            intent.putExtra("valor", productSelected.valor)
            startActivity(intent)
        }

        binding.recyclerView.adapter = adapter
    }
}