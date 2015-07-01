/*
 * Copyright (C) 2009 The Android Open Source Project
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

#define LOG_TAG "JoffeeJNI"

#include "jni.h"
#include "JNIHelp.h"
#include "android_runtime/AndroidRuntime.h"

#include <utils/misc.h>
#include <utils/Log.h>
#include <hardware/hardware.h>
#include <hardware/joffee.h>

#include <stdio.h>

namespace android
{

struct joffee_device_t *joffee_HAL;

/**
 * JNI Layer init function;
 * Load HAL and its method table for later use;
 * return a pointer to the same structure
 * (not mandatory btw, we can use a global variable)
 */
static jlong init_native(JNIEnv *env, jobject clazz) {
  int err;
  struct hw_module_t *module;
  struct hw_device_t *device;
  ALOGE("Entering %s\n", __func__);

  ALOGE("%d\n", __LINE__);
  /* Load the HAL module using libhardware hw_get_module */
  err = hw_get_module(JOFFEE_HARDWARE_MODULE_ID, (hw_module_t const**)&module);
  ALOGE("%d\n", __LINE__);
  if (err) {
  ALOGE("%d\n", __LINE__);
    //TODO
  }
  ALOGE("%d\n", __LINE__);

  /* Open the HAL object and get the joffee_device_t structure
   * filled with methods pointers
   */
  err = module->methods->open(module, "joffeeJNI", &device);
  if (err) {
  ALOGE("%d\n", __LINE__);
    //TODO
  }
  ALOGE("%d\n", __LINE__);

  joffee_HAL =  (struct joffee_device_t *)device;
  ALOGE("%d\n", __LINE__);
  return (jlong)joffee_HAL;
}

/**
 * Joffee wrapper function;
 * Call the native joffee function from Framework layer
 */
static void joffeeFunction_native(jlong nativePointer) {
  int err;

  ALOGE("Entering %s\n", __func__);
  ALOGE("%d\n", __LINE__);
  err = joffee_HAL->joffee_function();
  if (err) {
    ALOGE("%d\n", __LINE__);
    //TODO
  }

}

static JNINativeMethod method_table[] = {
    { "init_native", "()J", (void*)init_native },
    { "joffeeFunction_native", "(J)V", (void*)joffeeFunction_native },
};
int register_android_server_joffeeService(JNIEnv *env)
{
    return jniRegisterNativeMethods(env, "com/android/server/joffee/JoffeeService",
            method_table, NELEM(method_table));
}
};
