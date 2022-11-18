/*
 * Copyright (c) Terl Tech Ltd • 04/04/2020, 00:05 • goterl.com
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v2.0. If a copy of the MPL was not distributed with this
 * file, you can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.goterl.lazysodium;

import com.sun.jna.Native;

public class SodiumAndroid extends com.goterl.lazysodium.Sodium {

    public SodiumAndroid() {
        this("sodium");
    }

    /**
     * If on the Android platform, then this is the intended
     * route to load a libsodium native library. Please note loading for Android is not
     * the same as loading for Java.
     * The path must be to a directory with all the Android ABIs, which each
     * contain the libsodium.so. For example, given {@code /path/to/ABI1/}, {@code /path/to/THE_ABI2/}
     * and {@code /path/to/THE_ABI3/}, the param {@code path} should be {@code /path/to/}.
     * ABI1, ABI2 and ABI3 must all contain libsodium.so/libsodium.dylib/libsodium.dll built for that specific ABI.
     *
     * @param path Absolute path to the parent directory of all the ABI directories.
     */
    public SodiumAndroid(String path) {
        // Load the libsodium native files from the "src/main/libs" folder.
        // This folder should have folders with ABI names
        // such as x86 or x86_64 etc.
        Native.register(Sodium.class, path);
        Native.register(SodiumAndroid.class, path);
        base64Facade = new Base64Android();
        onRegistered();
    }

}
