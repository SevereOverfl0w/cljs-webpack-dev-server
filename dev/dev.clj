(ns dev
  (:require
    [figwheel.main.api]
    [io.dominic.wedge.dev :refer :all]))

(add-dev-system-fixture!
  :start-once ::figwheel
  #(figwheel.main.api/start
     {:mode :serve
      :open-url false}
     "app:dev"))

(add-dev-system-fixture!
  :stop-once ::figwheel
  #(figwheel.main.api/stop "app:dev"))
