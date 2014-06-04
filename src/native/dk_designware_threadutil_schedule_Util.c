#include <jni.h>
#include <syscall.h>
#include <sched.h>
#include "dk_designware_threadutil_schedule_Util.h"

JNIEXPORT jint JNICALL
Java_dk_designware_threadutil_schedule_Util_getNativeThreadId(JNIEnv *env, jclass clz) {
    jint tid = syscall(__NR_gettid);
    return tid;
}

JNIEXPORT jint JNICALL
Java_dk_designware_threadutil_schedule_Util_getNativeProcessId(JNIEnv *env, jclass clz) {
    jint pid = getpid();
    return pid;
}

JNIEXPORT jint JNICALL
Java_dk_designware_threadutil_schedule_Util_setScheduler(JNIEnv *env, jclass clz, jint tid, jint policy, jobject jsp) {
	struct sched_param sp;
	jclass jsp_clz = (*env)->GetObjectClass(env, jsp);
	jfieldID jsp_schedPriority_field = (*env)->GetFieldID(env, jsp_clz, "schedPriority", "I");
	sp.sched_priority = (*env)->GetIntField(env, jsp, jsp_schedPriority_field);
	jint res = sched_setscheduler(tid, policy, &sp);
	return res;
}
