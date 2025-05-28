package com.goterl.lazysodium.example.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.goterl.lazysodium.example.R
import com.goterl.lazysodium.exceptions.SodiumException
import com.goterl.lazysodium.interfaces.PwHash

class PasswordHashActivity : BaseActivity(), TextWatcher {
    private lateinit var cipherTv: EditText
    private lateinit var cipherLayout: View
    private lateinit var etMessage: EditText
    private lateinit var pwHash: PwHash.Lazy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_hash)
        setupToolbar("Generic hash")

        cipherTv = findViewById(R.id.et_cipher)
        cipherLayout = findViewById(R.id.cipher_layout)
        etMessage = findViewById(R.id.et_message)

        etMessage.addTextChangedListener(this)

        pwHash = ls as PwHash.Lazy
    }


    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun afterTextChanged(editable: Editable) {
        try {
            val cipherText = pwHash!!.cryptoPwHashStrRemoveNulls(
                editable.toString(),
                PwHash.OPSLIMIT_MIN,
                PwHash.MEMLIMIT_MIN
            )
            cipherTv!!.setText(cipherText)
        } catch (e: SodiumException) {
            e.printStackTrace()
        } finally {
            if (editable.toString().length == 0) {
                cipherLayout!!.visibility = View.GONE
            } else {
                cipherLayout!!.visibility = View.VISIBLE
            }
        }
    }
}
