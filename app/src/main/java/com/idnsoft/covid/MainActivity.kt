package com.idnsoft.covid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layoutcuciTangan.setOnClickListener{
            moveActivity(HandWashActivity::class.java)
        }
        layoutMasker.setOnClickListener{
            moveActivity(MaskActivity::class.java)
        }

        data
    }

    fun Context.moveActivity(target: Class<*>){
        startActivity(Intent(this, target))
    }

    private val data: Unit
        private get() {
           AndroidNetworking.get("https://data.covid19.go.id/public/api/update.json")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            tvTglUpdate.text=("Data diperbarui pada "+ response.getJSONObject("update").getJSONObject("penambahan").getString("created"))
                            tvCasePlus!!.text =("Positif bertambah "+ response.getJSONObject("update").getJSONObject("penambahan").getString("jumlah_positif"))
                            tvCasePlusDeath!!.text =("Meninggal bertambah "+ response.getJSONObject("update").getJSONObject("penambahan").getString("jumlah_meninggal"))
                            tvCasePlusSembuh!!.text =("Sembuh bertambah "+ response.getJSONObject("update").getJSONObject("penambahan").getString("jumlah_sembuh"))
                            tvCasePlusDirawat!!.text = ("Dirawat Bertambah" + response.getJSONObject("update").getJSONObject("penambahan").getString("jumlah_dirawat"))
                            tvCaseOdp!!.text =("Odp " + response.getJSONObject("data").getString("jumlah_odp"))
                            tvCaseSpesimen!!.text =("Spesimen " + response.getJSONObject("data").getString("total_spesimen"))
                            tvCaseSpesimenNegatif!!.text = ("Spesimen negatif " + response.getJSONObject("data").getString("total_spesimen_negatif"))
                            tvCasePositif!!.text = ("Total Positif " + response.getJSONObject("update").getJSONObject("total").getString("jumlah_positif"))
                            tvCaseMeninggal!!.text = ("Total Meninggal " + response.getJSONObject("update").getJSONObject("total").getString("jumlah_meninggal"))
                            tvCaseDirawat!!.text = ("Spesimen dirawat " + response.getJSONObject("update").getJSONObject("total").getString("jumlah_dirawat"))
                            tvCaseSembuh!!.text = ("Total Sembuh " + response.getJSONObject("update").getJSONObject("total").getString("jumlah_sembuh"))
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    override fun onError(error: ANError) {

                    }
                })
        }
}
