(set-env! :dependencies '[[org.clojure/clojure "1.9.0-beta3"]
                          [org.clojure/clojurescript "1.9.946" :scope "provided"]
                          [adzerk/boot-cljs "2.1.4" :scope "test"]])

(require '[adzerk.boot-cljs :refer [cljs]])

(merge-env! :source-paths ["src"])

(deftask build []
  (comp
   (cljs :ids #{"npmdemo"})
   (target)))
