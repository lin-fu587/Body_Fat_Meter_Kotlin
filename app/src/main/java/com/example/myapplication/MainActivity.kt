package com.example.myapplication


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_calculate.setOnClickListener(fun(it: View) {
            if (edit_height.length() < 1)
                Toast.makeText(this@MainActivity, "請輸入身高", Toast.LENGTH_SHORT).show()
            else if (ed_weight.length() < 1)
                Toast.makeText(this@MainActivity, "請輸入體重", Toast.LENGTH_SHORT).show()
            else
                runAsyncTask()
        })
    }
/*
    @SuppressLint("StaticFieldLeak")
    private fun runAsyncTask(){
        object : AsyncTask<Void, Int, Boolean>() {
            override fun onPreExecute() {
                super.onPreExecute()
                tv_weight.text = "標準體重\n無"
                tv_bmi.text = "體脂肪\n無"
                progressBar2.progress = 0
                tv_progress.text = "0%"
                ll_progress.visibility = View.VISIBLE
            }

            override fun doInBackground(vararg voids: Void): Boolean? {
                var progress = 0
                while (progress < 100)
                    try {
                        Thread.sleep(50)
                        publishProgress(progress)
                        progress++
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                return true
            }

            override fun onProgressUpdate(vararg values: Int?) {
                super.onProgressUpdate(*values)
                progressBar2.progress = values[0]!!
                tv_progress.text = values[0].toString() + "%"
            }


            override fun onPostExecute(status: Boolean?) {
                ll_progress.visibility = View.GONE

                val h = edit_height.text.toString().toInt()
                val w = ed_weight.text.toString().toInt()
                val standweight: Double
                val bodyfat: Double
                if (btn_boy.isChecked) {
                    standweight = (h - 80) * 0.7
                    bodyfat = (w - 0.88 * standweight) / w * 100
                }
                else {
                    standweight = (h - 70) * 0.6
                    bodyfat = (w - 0.82 * standweight) / w * 100
                }
                tv_weight.text = String.format("標準體重 \n%.2f", standweight)
                tv_bmi.text = String.format("體脂肪 \n%.2f", bodyfat)
            }
        }.execute()
    }*/

    private fun runAsyncTask(){
        GlobalScope.launch(context = Dispatchers.Main){
            tv_weight.text = "標準體重\n無"
            tv_bmi.text = "體脂肪\n無"
            progressBar2.progress = 0
            tv_progress.text = "0%"
            ll_progress.visibility = View.VISIBLE
            delay(5800)
            ll_progress.visibility = View.GONE

            val h = edit_height.text.toString().toInt()
            val w = ed_weight.text.toString().toInt()
            val standweight: Double
            val bodyfat: Double
            if (btn_boy.isChecked) {
                standweight = (h - 80) * 0.7
                bodyfat = (w - 0.88 * standweight) / w * 100
            }
            else {
                standweight = (h - 70) * 0.6
                bodyfat = (w - 0.82 * standweight) / w * 100
            }
            tv_weight.text = String.format("標準體重 \n%.2f", standweight)
            tv_bmi.text = String.format("體脂肪 \n%.2f", bodyfat)
        }
        GlobalScope.launch(context = Dispatchers.Main)
        {
            var progress = 0
            while (progress < 100) {
                try {
                    delay(50)
                    progressBar2.progress = progress
                    tv_progress.text = progress.toString() + "%"
                    progress++
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

}
