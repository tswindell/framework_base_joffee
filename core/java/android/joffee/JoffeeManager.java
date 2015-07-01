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

package android.joffee;

import android.joffee.IJoffeeService;

import android.util.AndroidException;
import android.util.Log;
import android.util.Slog;
import android.content.Context;
import android.os.RemoteException;

/**
 * The Manager class;
 * The public methods of this class will be part of the new system API
 * 
 * Have a look at how decorators are used (eg. hide) to see how mwthods are
 * exported or not in the SDK;
 */
public class JoffeeManager {
    private final Context mContext;
    private final IJoffeeService mService;
    
    /**
     * Initialize the remote service and execution context
     * ContextImpl will build this manager object and provide the
     * remote service stub as parameter
     * 
     * @param ctx
     * @param service
     */
    public JoffeeManager(Context ctx, IJoffeeService service) {
        mContext = ctx;
        mService = service;
    }
    
    /**
     * Perform a call to the remote service;
     * We have only one method, call it!
     */
    public void callJoffeeMethod() {
        try{
            mService.callJoffeeMethod();
        } catch (RemoteException ex){
            Slog.e("JoffeManager", "Unable to contact the remote Joffee Service");
        }
    }
    
    /**
     * This method (which does nothing)
     * is hidden from system API
     * Have a look at 'reflection' to see how you can
     * access hidden class members
     * 
     * @hide
     */
    public void hiddenMethod(){
        Slog.d("JoffeeManager", "Hello I'm hidden");
    }
}