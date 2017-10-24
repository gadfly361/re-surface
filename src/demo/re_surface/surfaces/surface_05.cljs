(ns re-surface.surfaces.surface-05
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Our footer should now be stuck to the bottom of the
        page. However, you'll notice that our header still scrolls off
        the page. Let's go ahead and make that a fixed header."]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/4")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/6")}
    "Make fixed header"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])

(def components
  {:body {:surface-05-body body}})

(def surfaces
  {:surface-05 {:header {:key    :default
                         :height 100}
                :body   {:key :surface-05-body}
                :footer {:key    :default
                         :height 100
                         :fixed? true}}})
