#!/bin/bash

CURRENT_PATH=`pwd`
cd `dirname $0`

./build.sh

cd ../../target
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:./native/
java -classpath classes:test-classes dk.designware.threadutil.schedule.UtilTest 60000

cd $CURRENT_PATH