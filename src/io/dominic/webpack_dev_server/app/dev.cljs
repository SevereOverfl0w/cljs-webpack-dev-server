(ns ^:figwheel-hooks io.dominic.webpack-dev-server.app.dev
  (:require
    [helix.experimental.refresh :as r]
    [io.dominic.webpack-dev-server.app :as app]))

(defonce init
  (do (r/inject-hook!)
      (app/mount)))

(defn ^:export ^:after-load reload [] (r/refresh!))

(defn ^:export hard-reload []
  (app/mount))
