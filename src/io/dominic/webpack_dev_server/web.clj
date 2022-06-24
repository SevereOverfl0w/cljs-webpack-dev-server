(ns io.dominic.webpack-dev-server.web
  (:require
    [co.deps.ring-etag-middleware :as etag]
    [ring.middleware.not-modified :as not-modified]
    [ring.middleware.resource :as resource]))

(defn- wrap-no-cache
  [handler]
  (fn [req]
    (some->
      (handler req)
      (update :headers assoc
              "Cache-Control" "no-cache"))))

(defn wrap-serve-build
  [handler]
  (-> handler
      (resource/wrap-resource "public/")
      wrap-no-cache
      etag/wrap-file-etag
      not-modified/wrap-not-modified))

(defn message
  "Returns a handler which returns HTML for serving ClojureScript on /, or
  message otherwise."
  [{:keys [message]}]
  (fn [req]
    (if (= "/" (:uri req))
      {:status 200
       :headers {"Access-Control-Allow-Origin" "*"}
       :body
       "<html><body><div id=app>Loading</div><script src=\"http://localhost:9777/main_bundle.js\"></script></body></html>"}
      {:status 404
       :body message})))
