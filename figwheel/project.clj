(def shared-compiler-settings '{:main npmdemo.core
                                :npm-deps {:express "4.16.2"
                                           :ws "3.2.0"}
                                :install-deps true
                                :infer-externs true
                                :target :nodejs})

(defproject npmdemo "0.1.0-SNAPSHOT"
  :description ""
  :url "http://github.com/lambdaisland/npmdemo"
  :license {:name "Mozilla Public License 2.0"
            :url "https://www.mozilla.org/en-US/MPL/2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0-beta3"]
                 [org.clojure/clojurescript "1.9.946" :scope "provided"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :min-lein-version "2.6.1"

  :clean-targets ^{:protect false} [:target-path :compile-path "dev-out" "out"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]

                :figwheel {:on-jsload "npmdemo.core/reset-app!"}

                :compiler ~(merge
                            {:output-to "npmdemo-dev.js"
                             :output-dir "dev-out"}
                            shared-compiler-settings)}

               {:id "prod"
                :source-paths ["src"]
                :compiler ~(merge
                            {:output-to "npmdemo.js"
                             :output-dir "out"
                             :optimizations :simple
                             :closure-defines '{goog.DEBUG false}
                             :pretty-print false}
                            shared-compiler-settings)}]}

  :figwheel {:server-logfile "log/figwheel.log"
             :server-port 3450}

  :profiles {:dev
             {:dependencies [[figwheel "0.5.15-SNAPSHOT"]
                             [figwheel-sidecar "0.5.15-SNAPSHOT"]
                             [com.cemerick/piggieback "0.2.2"]]

              :plugins [[lein-figwheel "0.5.15-SNAPSHOT"]]

              :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
