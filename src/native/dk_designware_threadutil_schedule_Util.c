#include <jni.h>
#include <syscall.h>
#include "dk_designware_threadutil_schedule_Util.h"

JNIEXPORT jint JNICALL
Java_dk_designware_threadutil_schedule_Util_getNativeThreadId(JNIEnv *env, jobject obj) {
    jint tid = syscall(__NR_gettid);
    return tid;
}