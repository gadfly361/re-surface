(ns re-surface.surfaces.surface-02
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1 "Woohoo, we have a header. Now for a footer."]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/1")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/3")}
    "Add footer"]
   ])

(def components
  {:body {:surface-02-body body}})

(def surfaces
  {:surface-02 {:header {:key    :default
                         :height 100
                         }
                :body   {:key :surface-02-body}}})
