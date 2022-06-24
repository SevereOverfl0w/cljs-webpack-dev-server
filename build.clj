(ns build
  (:require
    [clojure.tools.build.api :as b]
    [figwheel.main.api :as fig]
    [figwheel.main :as fig.main]
    [juxt.pack.api :as pack]))

(def app 'io.dominic/webpack-dev-server)
(def basis (delay (b/create-basis {:project "deps.edn"
                                   :extra {:aliases {::extra {:extra-paths [:build-paths]
                                                              :main-opts ["-m" "io.dominic.wedge.main"]}}}
                                   :aliases [::extra]})))

(defn clean [_]
  (b/delete {:path "target"}))

(defn cljs [{:keys [build-name]
             :or {build-name "app:min"}}]
  (fig/start {:validate-config false
              ::fig.main/build-once true}
             build-name)
  (b/copy-file {:src "target/cljs-out/app/main_bundle.js"
                :target "target/public/cljs-out/app/main_bundle.js"}))

(defn docker [params]
  (clean nil)
  (cljs params)
  (pack/docker
    (merge
      {:basis @basis
       :image-name (str app)}

      {:image-type :tar
       :tar-file (format "target/%s.tar.gz" (name app))}
      params)))
