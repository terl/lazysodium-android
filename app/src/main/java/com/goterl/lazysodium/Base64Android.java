/*
 * Copyright (c) Terl Tech Ltd • 18/11/2022, 12:07 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import android.util.Base64;
import com.goterl.lazysodium.utils.Base64Facade;

class Base64Android implements Base64Facade {

    @Override
    public String encode(byte[] cipher) {
        return Base64.encodeToString(cipher, Base64.NO_WRAP);
    }

    @Override
    public byte[] decode(String cipherText) {
        return Base64.decode(cipherText, Base64.NO_WRAP);
    }
}
