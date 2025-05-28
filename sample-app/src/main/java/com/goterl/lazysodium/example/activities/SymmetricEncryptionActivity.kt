package com.goterl.lazysodium.example.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.annotation.CallSuper
import com.goterl.lazysodium.example.R
import com.goterl.lazysodium.exceptions.SodiumException
import com.goterl.lazysodium.interfaces.SecretBox
import com.goterl.lazysodium.utils.Key

class SymmetricEncryptionActivity : BaseActivity(), TextWatcher {
    private lateinit var keyView: EditText
    private lateinit var messageView: EditText
    private lateinit var cipherView: EditText
    private lateinit var finalNote: View
    private lateinit var cipherLayout: View

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symmetric_enc)
        setupToolbar("Symmetric")

        keyView = findViewById(R.id.et_key)
        messageView = findViewById(R.id.et_message)
        cipherView = findViewById(R.id.et_cipher)
        cipherLayout = findViewById(R.id.cipher_layout)
        finalNote = findViewById(R.id.final_note)

        val key = ls!!.cryptoSecretBoxKeygen()
        keyView.setText(key.asHexString)

        messageView.addTextChangedListener(this)
    }


    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun afterTextChanged(editable: Editable) {
        val nonce = ls!!.randomBytesBuf(SecretBox.NONCEBYTES)
        try {
            val cipherText = ls!!.cryptoSecretBoxEasy(
                editable.toString(),
                nonce,
                Key.fromHexString(keyView!!.text.toString())
            )
            cipherView!!.setText(cipherText)
        } catch (e: SodiumException) {
            e.printStackTrace()
        } finally {
            if (editable.toString().length == 0) {
                finalNote!!.visibility = View.GONE
                cipherLayout!!.visibility = View.GONE
            } else {
                finalNote!!.visibility = View.VISIBLE
                cipherLayout!!.visibility = View.VISIBLE
            }
        }
    }
}
