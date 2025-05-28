package com.goterl.lazysodium.example.activities

import android.os.Bundle
import android.widget.EditText
import com.goterl.lazysodium.example.R
import com.goterl.lazysodium.exceptions.SodiumException
import com.goterl.lazysodium.interfaces.KeyDerivation
import com.goterl.lazysodium.utils.Key

class KeyDerivationActivity : BaseActivity() {
    private lateinit var kd: KeyDerivation.Lazy
    private lateinit var masterKey: EditText
    private lateinit var subkey1: EditText
    private lateinit var subkey2: EditText
    private lateinit var subkey3: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_derivation)
        setupToolbar("Key derivation")

        masterKey = findViewById(R.id.master_key)
        subkey1 = findViewById(R.id.subkey_1)
        subkey2 = findViewById(R.id.subkey_2)
        subkey3 = findViewById(R.id.subkey_3)

        kd = ls as KeyDerivation.Lazy

        val masterKeyTrue = kd!!.cryptoKdfKeygen()
        masterKey.setText(masterKeyTrue.asHexString)

        subkey1.setText(generateSubKey(1L, masterKeyTrue))
        subkey2.setText(generateSubKey(2L, masterKeyTrue))
        subkey3.setText(generateSubKey(3L, masterKeyTrue))
    }

    private fun generateSubKey(subKeyNumber: Long, masterKey: Key): String? {
        try {
            // The context param is an 8 byte string that
            // allows you to specify application unique
            // keys.
            return kd
                .cryptoKdfDeriveFromKey(
                    KeyDerivation.BYTES_MAX,
                    subKeyNumber,
                    "context_",
                    masterKey
                )
                .asHexString
        } catch (e: SodiumException) {
            e.printStackTrace()
        }
        return null
    }
}
