(ns re-surface.surfaces.surface-12
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Now we can toggle either sidebar-left or sidebar-right. Next, let's add a modal."]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-12-sla)
                           :surface-12
                           :surface-12-sla)))}
    "sidebar-left"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-12-sra)
                           :surface-12
                           :surface-12-sra)))}
    "sidebar-right"]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/11")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/13")}
    "Add modal"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])


(defn dimmer [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-12))}])



(def components
  {:body   {:surface-12-body body}
   :dimmer {:surface-12-dimmer dimmer}})


(def surface-init
  {:header        {:key    :default
                   :height 100
                   :fixed? true}
   :navbar        {:key    :default
                   :height 80
                   :fixed? true}
   :body          {:key :surface-12-body}
   :footer        {:key    :default
                   :height 100
                   :fixed? true}
   :sidebar-left  {:key     :default
                   :width   200
                   :active? false}
   :sidebar-right {:key     :default
                   :width   200
                   :active? false}
   :dimmer        {:key :surface-12-dimmer}})

(def surfaces
  {:surface-12 surface-init

   ;; sla --> sidebar-left-active
   :surface-12-sla (-> surface-init
                       (assoc-in [:sidebar-left :active?] true))

   ;; sra --> sidebar-right-active
   :surface-12-sra (-> surface-init
                       (assoc-in [:sidebar-right :active?] true))})
