(ns re-surface.surfaces.surface-08
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Now that our navbar is fixed, let's do something new. Let's
        add a sidebar to the left side."]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/7")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/9")}
    "Add sidebar-left"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])

(def components
  {:body {:surface-08-body body}})

(def surfaces
  {:surface-08 {:header {:key    :default
                         :height 100
                         :fixed? true}
                :navbar {:key    :default
                         :height 80
                         :fixed? true}
                :body   {:key :surface-08-body}
                :footer {:key    :default
                         :height 100
                         :fixed? true}}})
