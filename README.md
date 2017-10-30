This came up when trying to show someone how to write a simple Node app with ClojureScript.

There's a single source file

``` clojure
(ns npmdemo.core
  (:require express))

(enable-console-print!)

(defn add-routes [app]
  (.get app "/" (fn [req res] (.send res "Hello, world"))))

(defn start-server []
  (println "Starting server")
  (let [app (express)]
    (add-routes app)
    (.listen app 3000 (fn [] (println "Example app listening on port 3000!")))))

(set! *main-cli-fn* start-server)
```

and a standalone build script

``` clojure
(require 'cljs.build.api)

(cljs.build.api/build "src"
                      '{:main npmdemo.core
                        :npm-deps {:express "4.16.2"}
                        :install-deps true
                        :infer-externs true
                        :target :nodejs
                        :output-to "npmdemo.js"
                        :output-dir "out"
                        :optimizations :advanced
                        :pretty-print false})
```

When compiling you get these warnings:

```
$  java -cp cljs.jar:src clojure.main build.clj

Oct 30, 2017 1:46:36 PM com.google.javascript.jscomp.LoggerErrorManager println
WARNING: out/inferred_externs.js:3: WARNING - name goog is not defined in the externs.
goog.isArrayLike;
^^^^

Oct 30, 2017 1:46:36 PM com.google.javascript.jscomp.LoggerErrorManager println
WARNING: out/inferred_externs.js:4: WARNING - name goog is not defined in the externs.
goog.global;
^^^^

Oct 30, 2017 1:46:36 PM com.google.javascript.jscomp.LoggerErrorManager printSummary
WARNING: 0 error(s), 2 warning(s)
```

And when running it turns out some symbols have been renamed in error.

```
$ node npmdemo.js
Starting server
/home/arne/clj-projects/npmdemo/npmdemo.js:212
va=function(){function a(a){var c=null;if(0<arguments.length){c=0;for(var e=Array(arguments.length-0);c<e.length;)e[c]=arguments[c+0],++c;c=new J(e,0,null)}return b.call(this,c)}function b(a){return console.error.apply(console,Ja(a))}a.T=0;a.ba=function(a){a=I(a);return b(a)};a.A=b;return a}();function ee(a){a.get("/",function(a,c){return c.send("Hello, world")})}Ga=function(){be($b(["Starting server"]));var a=de.B?de.B():de.call(null);ee(a);return a.ac(3E3,function(){return function(){return be($b(["Example app listening on port 3000!"]))}}(a))};var fe=Ga;("function"==n(fe)||(null!=fe?q===fe.Nb||(fe.Zb?0:t(La,fe)):t(La,fe)))&&Mc(Ga,Sc());


TypeError: a.ac is not a function
    at Function.Ga (/home/arne/clj-projects/npmdemo/npmdemo.js:212:456)
    at Mc (/home/arne/clj-projects/npmdemo/npmdemo.js:109:113)
    at Object.<anonymous> (/home/arne/clj-projects/npmdemo/npmdemo.js:212:636)
    at Module._compile (module.js:570:32)
    at Object.Module._extensions..js (module.js:579:10)
    at Module.load (module.js:487:32)
    at tryModuleLoad (module.js:446:12)
    at Function.Module._load (module.js:438:3)
    at Module.runMain (module.js:604:10)
    at run (bootstrap_node.js:393:7)
```
