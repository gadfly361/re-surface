(ns re-surface.surfaces.surface-11
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Now our sidebar-left is fixed. Let's put the sidebar-left back to normal, as well as add a sidebar-right"]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/10")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/12")}
    "Add sidebar-right"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])


(defn dimmer [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-11))}])



(def components
  {:body   {:surface-11-body body}
   :dimmer {:surface-11-dimmer dimmer}})

(def surfaces
  {:surface-11 {:header       {:key    :default
                               :height 100
                               :fixed? true}
                :navbar       {:key    :default
                               :height 80
                               :fixed? true}
                :body         {:key :surface-11-body}
                :footer       {:key    :default
                               :height 100
                               :fixed? true}
                :sidebar-left {:key    :default
                               :width  200
                               :fixed? true}}})
