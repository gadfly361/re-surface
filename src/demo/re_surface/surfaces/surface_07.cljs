(ns re-surface.surfaces.surface-07
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Awesome, we have our navbar. For fun, let's make it fixed too."]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/6")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/8")}
    "Make navbar fixed"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])

(def components
  {:body {:surface-07-body body}})

(def surfaces
  {:surface-07 {:header {:key    :default
                         :height 100
                         :fixed? true}
                :navbar {:key    :default
                         :height 80}
                :body   {:key :surface-07-body}
                :footer {:key    :default
                         :height 100
                         :fixed? true}}})
