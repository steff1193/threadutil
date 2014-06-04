#!/bin/bash

CURRENT_PATH=`pwd`
cd `dirname $0`

cd ../../target
javah -classpath classes -d native dk.designware.threadutil.schedule.Util
cd native
cp ../../src/native/* .
JAVA_HOME=$(readlink -f /usr/bin/javac | sed "s:bin/javac::")
gcc -I${JAVA_HOME}/include -fPIC -shared dk_designware_threadutil_schedule_Util.c -o libDesignWareThreadUtilScheduleUtil.so

cd $CURRENT_PATH