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
                        :closure-defines {goog.DEBUG false}
                        :pretty-print false})
