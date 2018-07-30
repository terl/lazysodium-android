/*
 * Copyright (c) Terl Tech Ltd • 23/05/18 16:24 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazycode.lazysodium;

import java.nio.charset.Charset;

public class LazySodiumAndroid extends LazySodium {


    private final SodiumAndroid sodium;


    public LazySodiumAndroid(SodiumAndroid sodium) {
        this.sodium = sodium;
    }

    public LazySodiumAndroid(SodiumAndroid sodium, Charset charset) {
        super(charset);
        this.sodium = sodium;
    }

    public SodiumAndroid getSodium() {
        return sodium;
    }


}
