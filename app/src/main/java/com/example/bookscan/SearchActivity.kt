package com.example.bookscan

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bookscan.databinding.ActivitySearchBinding
import com.example.bookscan.db.SmLog
import com.hjq.toast.ToastParams
import com.hjq.toast.Toaster
import com.hjq.toast.style.CustomToastStyle
import com.rscja.barcode.BarcodeDecoder
import com.rscja.barcode.BarcodeFactory
import kotlinx.coroutines.launch


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private var smLog = SmLog()
    private var isCjm = true
    var kfid = 0
    var name = ""
    var barcodeDecoder = BarcodeFactory.getInstance().barcodeDecoder
    private val smLogAdapter = SmLogAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        kfid = intent.getIntExtra("kfid", 0)

        smLog.kfid = kfid
        barcodeDecoder.open(this@SearchActivity)
        barcodeDecoder.setDecodeCallback { barcodeEntity ->
            if (barcodeEntity.resultCode == BarcodeDecoder.DECODE_SUCCESS) {
                if (isCjm) {
                    smLog.cjm = barcodeEntity.barcodeData
                    switchToastStyleToWarn("切换层架码：${smLog.cjm}")
                } else {
                    switchToastStyleToSuccess("扫描盒号成功")
                    smLog.hh = barcodeEntity.barcodeData
                    smLog.smsj = System.currentTimeMillis().toString()
                    lifecycleScope.launch {
                        val app = application as App
                        app.db.getSmLogDao().insertSmLog(smLog)
                        smLogAdapter.logList.add(0, smLog)
                        smLogAdapter.notifyDataSetChanged()
                        val dalxid = smLog.dalxid
                        val dalxmc = smLog.dalxmc
                        val cjm = smLog.cjm
                        smLog = SmLog()
                        smLog.kfid = kfid
                        smLog.cjm = cjm
                        smLog.dalxid = dalxid
                        smLog.dalxmc = dalxmc
                        smLog.kfmc = name
                    }
                }
            } else {
                switchToastStyleToError("扫码出错，请重新扫码")
            }
        }
        lifecycleScope.launch {
            val app = application as App
            val kuFangDao = app.db.getKuFangDao()
            val kufang = kuFangDao.getKuFangById(kfid)
            val kuFangLogs = app.db.getSmLogDao().getKuFangList(kfid) as ArrayList<SmLog>
            smLogAdapter.logList = kuFangLogs
            name = kufang.name ?: ""
            smLog.kfmc = kufang.name
            val dalxList = app.db.getDalxDao().getAllDalx()

            binding.tvTab.setOnClickListener {
                AlertDialog.Builder(this@SearchActivity).setTitle("请选择档案类型")
                    .setItems(dalxList.map { it.dalx }.toTypedArray()) { _, which ->
                        val dalx = dalxList[which]
                        binding.tvTab.text = dalx.dalx
                        if (dalx.modelid == smLog.dalxid) {
                            return@setItems
                        }
                        smLog.cjm = ""
                        smLog.hh = ""
                        smLog.dalxid = dalx.modelid
                        smLog.dalxmc = dalx.dalx ?: ""
                    }.show()
            }
        }

        binding.recyclerView.adapter = smLogAdapter


        binding.tvOne.setOnClickListener {
            if (smLog.dalxid == 0) {
                switchToastStyleToError("请先选择档案类型")
                return@setOnClickListener
            }
            isCjm = true
            barcodeDecoder.startScan()
        }
        binding.tvTwo.setOnClickListener {
            if (smLog.dalxid == 0) {
                switchToastStyleToError("请先选择档案类型")
                return@setOnClickListener
            }
            if (smLog.cjm.isNullOrEmpty()) {
                switchToastStyleToError("请先扫描层架码")
                return@setOnClickListener
            }
            isCjm = false
            barcodeDecoder.startScan()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == 293 && event.action == KeyEvent.ACTION_DOWN) {
            if (isCjm) {
                binding.tvTwo.performClick()
            } else {
                binding.tvOne.performClick()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        barcodeDecoder.close()
    }


    fun switchToastStyleToWarn(mgs: String) {
        val params = ToastParams()
        params.text = mgs
        params.style = CustomToastStyle(R.layout.toast_warn)
        Toaster.show(params)
    }

    fun switchToastStyleToSuccess(mgs: String) {
        val params = ToastParams()
        params.text = mgs
        params.style = CustomToastStyle(R.layout.toast_success)
        Toaster.show(params)
    }

    fun switchToastStyleToError(mgs: String) {
        val params = ToastParams()
        params.text = mgs
        params.style = CustomToastStyle(R.layout.toast_error)
        Toaster.show(params)
    }
}
