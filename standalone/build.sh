#!/bin/sh

if ! [ -f cljs.jar ]
then
    echo "downloading cljs.jar ..."
    curl -L https://github.com/clojure/clojurescript/releases/download/r1.9.946/cljs.jar >> cljs.jar
fi

echo "building..."

java -cp cljs.jar:src clojure.main build.clj
