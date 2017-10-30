(ns npmdemo.core
  (:require express))

(enable-console-print!)

(defonce server (atom nil))

(defn add-routes [app]
  (.get app "/" (fn [req res] (.send res "Hello, world"))))

(defn start-server []
  (println "Starting server")
  (let [app (express)]
    (add-routes app)
    (.listen app 3000 (fn [] (println "Example app listening on port 3000!")))))

(defn start! []
  (reset! server (start-server)))

(defn reset-app! []
  (.close @server start!))

(set! *main-cli-fn* start!)
