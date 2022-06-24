(ns io.dominic.webpack-dev-server.app.yumyum
  (:require
    [clojure.set :as set]
    [cljs.env]))

(defmacro yummy
  []
  (let [all-node-deps (:node-module-index @cljs.env/*compiler*)]
    (.println
      System/out
      (pr-str "Uses a node module:"
              (map :name
                   (filter
                     (fn [ns]
                       (-> ns :requires vals set (->> (set/intersection all-node-deps)) seq))
                     (vals (:cljs.analyzer/namespaces @cljs.env/*compiler*))))))))
