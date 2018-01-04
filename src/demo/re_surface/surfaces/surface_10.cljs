(ns re-surface.surfaces.surface-10
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Now if we "
    [:button
     {:on-click #(swap! app-state update :page-key
                        (fn [pk]
                          (if (= pk :surface-10-sla)
                            :surface-10
                            :surface-10-sla)))}
     "toggle open/closed"] " our sidebar, it looks more
         normal. However, we don't have to have our sidebar slide in
         and out, we can have it be fixed and always open."]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/9")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/11")}
    "Make sidebar-left fixed"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])


(defn dimmer [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-10))}])



(def components
  {:body   {:surface-10-body body}
   :dimmer {:surface-10-dimmer dimmer}})


(def surface-init
  {:header       {:key    :default
                  :height 100
                  :fixed? true}
   :navbar       {:key    :default
                  :height 80
                  :fixed? true}
   :body         {:key :surface-10-body}
   :footer       {:key    :default
                  :height 100
                  :fixed? true}
   :sidebar-left {:key     :default
                  :width   200
                  :active? false}
   })

(def surfaces
  {:surface-10 surface-init

   ;; sla --> sidebar-left-active
   :surface-10-sla (-> surface-init
                       (assoc-in [:dimmer :key] :surface-10-dimmer)
                       (assoc-in [:sidebar-left :active?] true))})
