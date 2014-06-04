#!/bin/bash

CURRENT_PATH=`pwd`
cd `dirname $0`

cd ../..
mvn compile
mvn test-compile

cd $CURRENT_PATH