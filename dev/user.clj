(ns user
  (:require
    [io.dominic.wedge.user :refer :all]
    figwheel.main.logging))

(figwheel.main.logging/remove-handlers figwheel.main.logging/*logger*)
(.setUseParentHandlers figwheel.main.logging/*logger* true)
