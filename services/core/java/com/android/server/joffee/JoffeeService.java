/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.server.joffee;

import android.util.Log;
import android.util.Slog;

import android.os.IBinder;
import android.content.Context;

import android.joffee.IJoffeeService;
import com.android.server.SystemService;

public class JoffeeService extends SystemService {

    private final Context mContext;
    private static final String TAG = "JoffeeService";
    private static final boolean DEBUG = true;

    public JoffeeService(Context context) {
        super(context);
        if (DEBUG){
            Slog.d(TAG, "Build service");
        }
        mContext = context;
        publishBinderService(context.JOFFEE_SERVICE, mService);

    }
    
    /**
     * Called when service is started by the main system service
     */
    @Override
    public void onStart() {
        if (DEBUG){
            Slog.d(TAG, "Start service");
        }
        mNativePointer = init_native();
    }
    
    /**
     * Implementation of AIDL service interface
     */
    private final IBinder mService = new IJoffeeService.Stub() {
        /**
         * Implementation of the methods described in AIDL interface
         */
        @Override
        public void callJoffeeMethod() {
            if (DEBUG){
                Slog.d(TAG, "Call native service");
            }
            /*
             * We do not really need the nativePointer here;
             * Just to show how arguments are passed to JNI from Java
             */
            joffeeFunction_native(mNativePointer);
        }
    };
    
    /* Native functions declarations */
    private long mNativePointer;
    private static native long init_native();
    private static native void joffeeFunction_native(long nativePointer);

}