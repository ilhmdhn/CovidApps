package com.idnsoft.covid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_hand_wash.*

class HandWashActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand_wash)

        header.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.header -> {
                val moveIntent = Intent(this@HandWashActivity, MainActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }
}