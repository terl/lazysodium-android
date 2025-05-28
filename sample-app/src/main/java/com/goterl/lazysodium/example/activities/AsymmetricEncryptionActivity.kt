package com.goterl.lazysodium.example.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.goterl.lazysodium.example.R
import com.goterl.lazysodium.exceptions.SodiumException
import com.goterl.lazysodium.interfaces.Box
import com.goterl.lazysodium.interfaces.SecretBox
import com.goterl.lazysodium.utils.KeyPair

class AsymmetricEncryptionActivity : BaseActivity(), TextWatcher {
    private var cipherView: EditText? = null
    private var etMessage: EditText? = null
    private var cipherLayout: View? = null
    private var box: Box.Lazy? = null
    private var bobKeyPair: KeyPair? = null
    private var keyPair: KeyPair? = null
    private var finalNote: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asymmetric_enc)
        setupToolbar("Asymmetric")
        val yourPrivate = findViewById<TextView>(R.id.tv_your_private_key)
        val yourPublic = findViewById<TextView>(R.id.tv_your_public_key)

        val bobPrivate = findViewById<TextView>(R.id.tv_bob_private_key)
        val bobPublic = findViewById<TextView>(R.id.tv_bob_public_key)

        cipherView = findViewById(R.id.et_cipher)
        cipherLayout = findViewById(R.id.cipher_layout)
        finalNote = findViewById(R.id.final_note)
        etMessage = findViewById(R.id.et_message)

        etMessage?.addTextChangedListener(this)


        // Cast to Box.Lazy to make it easier for us to
        // focus on asymmetric encryption.
        box = ls as Box.Lazy

        try {
            // This is our keypair.
            keyPair = box!!.cryptoBoxKeypair()
            bobKeyPair = box!!.cryptoBoxKeypair()

            yourPrivate.text = keyPair?.secretKey?.asHexString
            yourPublic.text = keyPair?.publicKey?.asHexString

            bobPrivate.text = bobKeyPair?.secretKey?.asHexString
            bobPublic.text = bobKeyPair?.publicKey?.asHexString
        } catch (e: SodiumException) {
            e.printStackTrace()
        }
    }


    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

    override fun afterTextChanged(editable: Editable) {
        val nonce = ls.randomBytesBuf(SecretBox.NONCEBYTES)

        // We're sending a message to Bob so
        // let's encrypt using his public key and our private key.
        val encryptionKeyPair = KeyPair(
            bobKeyPair!!.publicKey, keyPair!!.secretKey
        )

        try {
            val cipherText = box!!.cryptoBoxEasy(
                editable.toString(),
                nonce,
                encryptionKeyPair
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
