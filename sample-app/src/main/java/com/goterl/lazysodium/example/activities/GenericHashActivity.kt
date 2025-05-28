package com.goterl.lazysodium.example.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import com.goterl.lazysodium.LazySodiumAndroid
import com.goterl.lazysodium.SodiumAndroid
import com.goterl.lazysodium.example.R
import com.goterl.lazysodium.exceptions.SodiumException
import com.goterl.lazysodium.interfaces.GenericHash
import com.goterl.lazysodium.interfaces.PwHash

class GenericHashActivity : BaseActivity(), TextWatcher {
    private lateinit var cipherTv: EditText
    private lateinit var cipherLayout: View
    private lateinit var etMessage: EditText
    private lateinit var gh: GenericHash.Lazy

    @SuppressLint("AndroidLogDetector")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic_hash)
        setupToolbar("Generic hash")

        cipherTv = findViewById(R.id.et_cipher)
        cipherLayout = findViewById(R.id.cipher_layout)
        etMessage = findViewById(R.id.et_message)

        etMessage.addTextChangedListener(this)

        gh = ls as GenericHash.Lazy

        val ls = LazySodiumAndroid(SodiumAndroid())
        val salt = ls.randomBytesBuf(PwHash.SALTBYTES)

        val outputHash = ls.randomBytesBuf(32)
        val outputHashLen = outputHash.size

        Log.e("Test", ls.str(outputHash))
        Log.e("Test", ls.toHexStr(outputHash))

        val password = ls.bytes("123456")
        val passwordLen = password.size

        val res = ls.sodium.crypto_pwhash(
            outputHash,
            outputHashLen.toLong(),
            password,
            passwordLen.toLong(),
            salt,
            PwHash.OPSLIMIT_MIN,
            PwHash.MEMLIMIT_INTERACTIVE,
            PwHash.Alg.getDefault().value
        )
        if (res == 0) {
            Log.e("S", "Successful hashing.")
        }

        Log.e("Test", ls.str(outputHash))
        Log.e("Test", ls.toHexStr(outputHash))
    }


    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun afterTextChanged(editable: Editable) {
        try {
            val cipherText = gh!!.cryptoGenericHash(editable.toString())
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
