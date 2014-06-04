#!/bin/bash

CURRENT_PATH=`pwd`
cd `dirname $0`

./java_build.sh

./native_build.sh

cd $CURRENT_PATH