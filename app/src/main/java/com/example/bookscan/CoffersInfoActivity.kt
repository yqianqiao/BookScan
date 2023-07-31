package com.example.bookscan

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.bookscan.databinding.ActivityCoffersInfoBinding
import com.example.bookscan.db.KuFang
import kotlinx.coroutines.launch

class CoffersInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoffersInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoffersInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            val app = application as App
            val kuFangs = app.db.getKuFangDao().getAllKuFangEntity()
            binding.recyclerView.adapter = CoffersInfoAdapter(this@CoffersInfoActivity, kuFangs)
        }
    }
}

class CoffersInfoAdapter(private val context: Context, private val kufangs: List<KuFang>) :
    RecyclerView.Adapter<CoffersInfoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: AppCompatTextView = view.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_coffers_info, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return kufangs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = kufangs[position].name

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, SearchActivity::class.java).putExtra("kfid", kufangs[position].kfid))
        }
    }
}