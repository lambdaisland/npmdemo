(ns npmdemo.core
  (:require ["express"]))

(enable-console-print!)

(defn start-server []
  (println "Starting server")
  (let [app (express)]
    (.get app "/" (fn [req res] (.send res "Hello, world")))
    (.listen app 3000 (fn [] (println "Example app listening on port 3000!")))))


(set! *main-cli-fn* start-server)
