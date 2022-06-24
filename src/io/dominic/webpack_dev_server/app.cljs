(ns io.dominic.webpack-dev-server.app
  (:require
    [io.dominic.helix-sev.core :refer [defnc defnc-]]
    [io.dominic.helix-sev.dom :refer [$d]]
    [helix.hooks :as hooks]
    ["react-dom" :as react.dom]
    ["coolfoo" :refer [foo]])
  (:require-macros
    [io.dominic.webpack-dev-server.app.yumyum :refer [yummy]]))

(yummy)

(enable-console-print!)

(defnc- greeting
  "A component which greets a user."
  [{:keys [name]}]
  ($d "p.greeting" "Hello, " ($d "em" name) "!"))

(defnc app
  []
  (let [[state set-state] (hooks/use-state {:name "Helix usEr"})]
    ($d "div"
     ($d "h1" (str "Welcome!" coolfoo/foo))
      (greeting {:name (:name state)})
      ($d "input" {:value (:name state)
                   :on-change #(set-state assoc :name (.. % -target -value))}))))

(defn mount
  []
  (js/console.log "mcoolfoois" foo)
  (react.dom/render
    (app)
    (js/document.getElementById "app")))
