/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.goterl.lazysodium.exceptions.SodiumException;
import com.goterl.lazysodium.utils.Key;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShortHashAndroidTest extends BaseTest {


    @Test
    public void hash() throws SodiumException {
        String hashThis = "This should get hashed";

        Key key = lazySodium.cryptoShortHashKeygen();
        String hash = lazySodium.cryptoShortHash(hashThis, key);

        assertNotNull(hash);
    }



}
