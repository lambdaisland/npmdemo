(set-env! :dependencies '[[org.clojure/clojure "1.9.0-beta3"]
                          [org.clojure/clojurescript "1.9.946" :scope "provided"]
                          [adzerk/boot-cljs "2.1.4" :scope "test"]
                          [adzerk/boot-cljs-repl "0.3.3" :scope "test"]
                          [adzerk/boot-reload "0.5.2" :scope "test"]
                          [org.clojure/tools.nrepl "0.2.12" :scope "test"]
                          [com.cemerick/piggieback "0.2.2" :scope "test"]
                          [weasel "0.7.0" :scope "test"]])

(require '[adzerk.boot-cljs :refer [cljs]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[adzerk.boot-reload :refer [reload]])

(merge-env! :source-paths ["src"])

(deftask dev []
  (comp (watch)
        (reload :on-jsload 'npmdemo.core/restart!)
        (cljs-repl)
        (cljs :ids #{"npmdemo"} :source-map true :optimizations :none)
        (target)))

(deftask build []
  (comp
   (cljs :optimizations :simple)
   (target)))
