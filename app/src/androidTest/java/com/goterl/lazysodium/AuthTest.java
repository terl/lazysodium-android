/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.interfaces.Auth;
import com.goterl.lazysodium.utils.Key;
import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AuthTest extends BaseTest {


    @Test
    public void auth256KeygenAndVerify() {
        String m = "A simple message.";

        Key k = lazySodium.cryptoAuthHMACShaKeygen(Auth.Type.SHA256);
        String shaResult = lazySodium.cryptoAuthHMACSha(Auth.Type.SHA256, m, k);
        boolean isTrue = lazySodium.cryptoAuthHMACShaVerify(Auth.Type.SHA256, shaResult, m, k);
        assertTrue(isTrue);
    }

    @Test
    public void auth512KeygenAndVerify() {
        String m = "A simple message.";

        Key k = lazySodium.cryptoAuthHMACShaKeygen(Auth.Type.SHA512);
        String shaResult = lazySodium.cryptoAuthHMACSha(Auth.Type.SHA512, m, k);
        boolean isTrue = lazySodium.cryptoAuthHMACShaVerify(Auth.Type.SHA512, shaResult, m, k);
        assertTrue(isTrue);
    }

    @Test
    public void auth512256KeygenAndVerify() {
        String m = "Follow us on twitter @terlacious";

        Key k = lazySodium.cryptoAuthHMACShaKeygen(Auth.Type.SHA512256);
        String shaResult = lazySodium.cryptoAuthHMACSha(Auth.Type.SHA512256, m, k);
        boolean isTrue = lazySodium.cryptoAuthHMACShaVerify(Auth.Type.SHA512256, shaResult, m, k);
        assertTrue(isTrue);
    }

    @Test
    public void auth256StreamKeygenAndVerify() throws SodiumException {
        String m = "Terl is ";
        String m2 = "the best";

        Key k = lazySodium.cryptoAuthHMACShaKeygen(Auth.Type.SHA256);
        Auth.StateHMAC256 state = new Auth.StateHMAC256();


        boolean res = lazySodium.cryptoAuthHMACShaInit(state, k);
        if (!res) {
            TestCase.fail("Could not initialise HMAC Sha.");
            return;
        }

        boolean res2 = lazySodium.cryptoAuthHMACShaUpdate(state, m);
        if (!res2) {
            TestCase.fail("Could not update HMAC Sha.");
            return;
        }

        boolean res3 = lazySodium.cryptoAuthHMACShaUpdate(state, m2);
        if (!res3) {
            TestCase.fail("Could not update HMAC Sha (part 2).");
            return;
        }

        String sha = lazySodium.cryptoAuthHMACShaFinal(state);

        boolean isTrue = lazySodium.cryptoAuthHMACShaVerify(Auth.Type.SHA256, sha, m + m2, k);
        assertTrue(isTrue);
    }


    @Test
    public void auth512StreamKeygenAndVerify() throws SodiumException {
        String m = "Lazysodium makes devs lazy";
        String m2 = " but don't tell your manager that!";

        Key k = lazySodium.cryptoAuthHMACShaKeygen(Auth.Type.SHA512);
        Auth.StateHMAC512 state = new Auth.StateHMAC512();


        boolean res = lazySodium.cryptoAuthHMACShaInit(state, k);
        if (!res) {
            TestCase.fail("Could not initialise HMAC Sha.");
            return;
        }

        boolean res2 = lazySodium.cryptoAuthHMACShaUpdate(state, m);
        if (!res2) {
            TestCase.fail("Could not update HMAC Sha.");
            return;
        }

        boolean res3 = lazySodium.cryptoAuthHMACShaUpdate(state, m2);
        if (!res3) {
            TestCase.fail("Could not update HMAC Sha (part 2).");
            return;
        }

        String sha = lazySodium.cryptoAuthHMACShaFinal(state);

        boolean isTrue = lazySodium.cryptoAuthHMACShaVerify(Auth.Type.SHA512, sha, m + m2, k);
        assertTrue(isTrue);
    }


    @Test
    public void auth512256StreamKeygenAndVerify() throws SodiumException {
        String m = "A string that ";
        String m2 = "is sha512256 sha mac'd ";
        String m3 = "is super secure.";

        Key k = lazySodium.cryptoAuthHMACShaKeygen(Auth.Type.SHA512256);
        Auth.StateHMAC512256 state = new Auth.StateHMAC512256();


        boolean res = lazySodium.cryptoAuthHMACShaInit(state, k);
        boolean res2 = lazySodium.cryptoAuthHMACShaUpdate(state, m);
        boolean res3 = lazySodium.cryptoAuthHMACShaUpdate(state, m2);
        boolean res4 = lazySodium.cryptoAuthHMACShaUpdate(state, m3);

        String sha = lazySodium.cryptoAuthHMACShaFinal(state);

        boolean isTrue = lazySodium.cryptoAuthHMACShaVerify(Auth.Type.SHA512256, sha, m + m2 + m3, k);
        assertTrue(isTrue);
    }
}
