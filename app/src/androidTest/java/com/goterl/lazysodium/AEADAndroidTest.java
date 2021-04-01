/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.goterl.lazysodium.interfaces.AEAD;
import com.goterl.lazysodium.utils.DetachedDecrypt;
import com.goterl.lazysodium.utils.DetachedEncrypt;
import com.goterl.lazysodium.utils.Key;
import junit.framework.TestCase;
import org.junit.Test;

import javax.crypto.AEADBadTagException;

public class AEADAndroidTest extends BaseTest {

    private final String PASSWORD = "superSecurePassword";

    @Test
    public void encryptChacha() throws AEADBadTagException {

        Key key = lazySodium.keygen(AEAD.Method.CHACHA20_POLY1305);

        byte[] nPub = lazySodium.nonce(AEAD.CHACHA20POLY1305_NPUBBYTES);

        String cipher = lazySodium.encrypt(PASSWORD, null, nPub, key, AEAD.Method.CHACHA20_POLY1305);
        String decrypted = lazySodium.decrypt(cipher, null, nPub, key, AEAD.Method.CHACHA20_POLY1305);

        TestCase.assertEquals(decrypted, PASSWORD);
    }

    @Test
    public void encryptChachaIetf() throws AEADBadTagException {

        Key key = lazySodium.keygen(AEAD.Method.CHACHA20_POLY1305_IETF);

        byte[] nPub = lazySodium.nonce(AEAD.CHACHA20POLY1305_IETF_NPUBBYTES);

        String cipher = lazySodium.encrypt(PASSWORD, null, nPub, key, AEAD.Method.CHACHA20_POLY1305_IETF);
        String decrypted = lazySodium.decrypt(cipher, null, nPub, key, AEAD.Method.CHACHA20_POLY1305_IETF);

        TestCase.assertEquals(decrypted, PASSWORD);
    }

    @Test
    public void encryptXChacha() throws AEADBadTagException {

        Key key = lazySodium.keygen(AEAD.Method.XCHACHA20_POLY1305_IETF);

        byte[] nPub = lazySodium.nonce(AEAD.XCHACHA20POLY1305_IETF_NPUBBYTES);

        String cipher = lazySodium.encrypt(PASSWORD, null, nPub, key, AEAD.Method.XCHACHA20_POLY1305_IETF);
        String decrypted = lazySodium.decrypt(cipher, null, nPub, key, AEAD.Method.XCHACHA20_POLY1305_IETF);

        TestCase.assertEquals(decrypted, PASSWORD);
    }


    @Test
    public void encryptChachaDetached() throws AEADBadTagException {

        Key key = lazySodium.keygen(AEAD.Method.CHACHA20_POLY1305);

        byte[] nPub = lazySodium.nonce(AEAD.CHACHA20POLY1305_NPUBBYTES);

        DetachedEncrypt detachedEncrypt
                = lazySodium.encryptDetached(PASSWORD, null, null, nPub, key, AEAD.Method.CHACHA20_POLY1305);

        DetachedDecrypt detachedDecrypt = lazySodium.decryptDetached(detachedEncrypt, null, null, nPub, key, AEAD.Method.CHACHA20_POLY1305);

        TestCase.assertEquals(detachedDecrypt.getMessageString(), PASSWORD);
    }


    @Test
    public void encryptChachaIetfDetached() throws AEADBadTagException {

        Key key = lazySodium.keygen(AEAD.Method.CHACHA20_POLY1305_IETF);

        byte[] nPub = lazySodium.nonce(AEAD.CHACHA20POLY1305_IETF_NPUBBYTES);

        DetachedEncrypt detachedEncrypt
                = lazySodium.encryptDetached(PASSWORD, null, null, nPub, key, AEAD.Method.CHACHA20_POLY1305_IETF);

        DetachedDecrypt detachedDecrypt = lazySodium.decryptDetached(detachedEncrypt, null, null, nPub, key, AEAD.Method.CHACHA20_POLY1305_IETF);

        TestCase.assertEquals(detachedDecrypt.getMessageString(), PASSWORD);
    }

    @Test
    public void encryptXChachaDetached() throws AEADBadTagException {

        Key key = lazySodium.keygen(AEAD.Method.XCHACHA20_POLY1305_IETF);

        byte[] nPub = lazySodium.nonce(AEAD.XCHACHA20POLY1305_IETF_NPUBBYTES);

        DetachedEncrypt detachedEncrypt
                = lazySodium.encryptDetached(PASSWORD, null, null, nPub, key, AEAD.Method.XCHACHA20_POLY1305_IETF);

        DetachedDecrypt detachedDecrypt = lazySodium.decryptDetached(detachedEncrypt, null, null, nPub, key, AEAD.Method.XCHACHA20_POLY1305_IETF);

        TestCase.assertEquals(detachedDecrypt.getMessageString(), PASSWORD);
    }


    @Test
    public void encryptAES() throws AEADBadTagException {
        if (lazySodium.cryptoAeadAES256GCMIsAvailable()) {
            Key key = lazySodium.keygen(AEAD.Method.AES256GCM);

            byte[] nPub = lazySodium.nonce(AEAD.AES256GCM_NPUBBYTES);

            String cipher = lazySodium.encrypt(PASSWORD, null, nPub, key, AEAD.Method.AES256GCM);
            String decrypted = lazySodium.decrypt(cipher, null, nPub, key, AEAD.Method.AES256GCM);

            TestCase.assertEquals(decrypted, PASSWORD);
        }
    }

    @Test
    public void encryptAESDetached() throws AEADBadTagException {
        if (lazySodium.cryptoAeadAES256GCMIsAvailable()) {
            Key key = lazySodium.keygen(AEAD.Method.AES256GCM);
            byte[] nPub = lazySodium.nonce(AEAD.AES256GCM_NPUBBYTES);

            DetachedEncrypt detachedEncrypt
                    = lazySodium.encryptDetached(PASSWORD, null, null, nPub, key, AEAD.Method.AES256GCM);

            DetachedDecrypt detachedDecrypt = lazySodium.decryptDetached(detachedEncrypt, null, null, nPub, key, AEAD.Method.AES256GCM);

            TestCase.assertEquals(detachedDecrypt.getMessageString(), PASSWORD);
        }
    }

}
