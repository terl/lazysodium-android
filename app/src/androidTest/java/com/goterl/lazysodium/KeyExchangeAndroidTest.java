/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.KeyExchange;
import com.goterl.lazysodium.utils.KeyPair;
import com.goterl.lazysodium.utils.SessionPair;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class KeyExchangeAndroidTest extends BaseTest {


    @Test
    public void generateKeyPair() {
        KeyPair keys = lazySodium.cryptoKxKeypair();
        assertNotNull(keys);
    }

    @Test
    public void generateDeterministicPublicKeyPair() {
        byte[] seed = new byte[KeyExchange.SEEDBYTES];
        KeyPair keys = lazySodium.cryptoKxKeypair(seed);
        KeyPair keys2 = lazySodium.cryptoKxKeypair(seed);

        TestCase.assertEquals(keys.getPublicKey().getAsHexString(), keys2.getPublicKey().getAsHexString());
    }

    @Test
    public void generateDeterministicSecretKeyPair() {
        byte[] seed = new byte[KeyExchange.SEEDBYTES];
        KeyPair keys = lazySodium.cryptoKxKeypair(seed);
        KeyPair keys2 = lazySodium.cryptoKxKeypair(seed);

        TestCase.assertEquals(keys.getSecretKey().getAsHexString(), keys2.getSecretKey().getAsHexString());
    }


    @Test
    public void generateSessionPair() throws SodiumException {
        // Generate the client's keypair
        KeyPair clientKeys = lazySodium.cryptoKxKeypair();

        // Generate the server keypair
        KeyPair serverKeys = lazySodium.cryptoKxKeypair();

        SessionPair clientSession = lazySodium.cryptoKxClientSessionKeys(clientKeys, serverKeys);
        SessionPair serverSession = lazySodium.cryptoKxServerSessionKeys(serverKeys, clientKeys);

        // You can now use the secret and public keys of the client and the server
        // to encrypt and decrypt messages to one another.
        // lazySodium.cryptoSecretBoxEasy( ... );

        // The Rx of the client should equal the Tx of the server
        TestCase.assertEquals(clientSession.getRxString(), serverSession.getTxString());
    }

}
