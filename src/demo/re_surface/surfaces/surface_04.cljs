(ns re-surface.surfaces.surface-04
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Our footer should now be pushed off the page. However, if you want a fixed footer, then re-surface has you covered."]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/3")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/5")}
    "Make fixed footer"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])

(def components
  {:body {:surface-04-body body}})

(def surfaces
  {:surface-04 {:header {:key    :default
                         :height 100}
                :body   {:key :surface-04-body}
                :footer {:key    :default
                         :height 100}}})
