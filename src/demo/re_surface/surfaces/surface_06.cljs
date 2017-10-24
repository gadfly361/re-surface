(ns re-surface.surfaces.surface-06
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Now that we have a fixed header, let's add a navbar."]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/5")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/7")}
    "Add navbar"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])

(def components
  {:body {:surface-06-body body}})

(def surfaces
  {:surface-06 {:header {:key    :default
                         :height 100
                         :fixed? true}
                :body   {:key :surface-06-body}
                :footer {:key    :default
                         :height 100
                         :fixed? true}}})
