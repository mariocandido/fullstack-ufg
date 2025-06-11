package br.ufg.inf.listviewcustom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterProducts(
    private val myList: List<Produto>,
    private val onItemClick: (Produto) -> Unit // Alterado para passar o objeto Times
) : RecyclerView.Adapter<AdapterProducts.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = myList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val produto = myList[position]
        holder.textName.text = produto.nome
        holder.textValor.text = produto.valor
        holder.imgCarro.setImageResource(produto.drawable)

        // Configurando o clique no item
        holder.itemView.setOnClickListener {
            onItemClick(produto) // Passa o objeto `Times` para a função de clique
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName: TextView = itemView.findViewById(R.id.txtNome)
        val imgCarro: ImageView = itemView.findViewById(R.id.imgCarro)
        val textValor: TextView = itemView.findViewById(R.id.txtValor)
    }
}