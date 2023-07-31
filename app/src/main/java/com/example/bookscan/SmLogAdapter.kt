package com.example.bookscan

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookscan.db.SmLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class SmLogAdapter(val context: Context) :
    RecyclerView.Adapter<SmLogAdapter.ViewHolder>() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    var logList = arrayListOf<SmLog>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tv_title)
        val cjm = view.findViewById<TextView>(R.id.cjm)
        val ren = view.findViewById<TextView>(R.id.tv_ren)
        val time = view.findViewById<TextView>(R.id.tv_time)
        val state = view.findViewById<TextView>(R.id.tv_state)
        val delete = view.findViewById<AppCompatImageView>(R.id.tv_delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_smlog, parent, false))
    }

    override fun getItemCount(): Int {
        return logList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val smLog = logList[position]
        holder.title.text = smLog.kfmc
        holder.cjm.text = smLog.cjm
        holder.ren.text = smLog.hh
        holder.time.text = formatTimestamp(smLog.smsj?.toLong() ?: 0)
        holder.state.text = if (smLog.zbzt == 0) "未同步" else "同步"
        holder.delete.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("确定要删除该条扫描记录吗？")
                .setPositiveButton("确定") { _, _ ->
                    coroutineScope.launch {
                        (context.applicationContext as App).db.getSmLogDao().deleteSmLog(smLog)
                    }
                    logList.remove(smLog)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("取消") { _, _ -> }
                .show()

        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        coroutineScope.coroutineContext.cancelChildren()
    }

    fun formatTimestamp(timestamp: Long): String {
        val format = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss")

        return format.format(timestamp)
    }
}

