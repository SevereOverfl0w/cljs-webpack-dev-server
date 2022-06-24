(ns io.dominic.webpack-dev-server.app.main
  (:require
    [io.dominic.webpack-dev-server.app :as app]))

(defonce init (app/mount))
