(ns re-surface.surfaces.surface-01
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1 "This is pretty boring, all we have so far is a "
    [:strong "body"] " component. Let's add a header."]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/2")}
    "Add header"]
   ])

(def components
  {:body {:surface-01-body body}})

(def surfaces
  {:surface-01 {:body {:key :surface-01-body}}})
