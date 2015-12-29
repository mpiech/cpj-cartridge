(ns cpjapp.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults 
             :refer [wrap-defaults site-defaults]]
            [ring.adapter.jetty :refer [run-jetty]]))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& args]
  (let [ip (get (System/getenv) 
                "OPENSHIFT_CLOJURE_HTTP_IP" "0.0.0.0")
        port (Integer/parseInt 
              (get (System/getenv) 
                   "OPENSHIFT_CLOJURE_HTTP_PORT" "8080"))]
    (run-jetty app {:host ip :port port})))
