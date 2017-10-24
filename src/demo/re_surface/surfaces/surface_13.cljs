(ns re-surface.surfaces.surface-13
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Now we should be able to see a modal. Next, let's make a
     fullscreen modal."]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-13-modal)
                           :surface-13
                           :surface-13-modal)))}
    "modal"]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-13-sla)
                           :surface-13
                           :surface-13-sla)))}
    "sidebar-left"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-13-sra)
                           :surface-13
                           :surface-13-sra)))}
    "sidebar-right"]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/12")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/14")}
    "Make modal-fullscreen"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])


(defn dimmer [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-13))}])


(defn modal [app-state]
  [:div
   {:style {:padding "16px"}}
   [:h1 "modal"]
   (for [x (range 32)]
     ^{:key x}
     [:h3 x])])



(def components
  {:body   {:surface-13-body body}
   :dimmer {:surface-13-dimmer dimmer}
   :modal {:surface-13-modal modal}})


(def surface-init
  {:header        {:key    :default
                   :height 100
                   :fixed? true}
   :navbar        {:key    :default
                   :height 80
                   :fixed? true}
   :body          {:key :surface-13-body}
   :footer        {:key    :default
                   :height 100
                   :fixed? true}
   :sidebar-left  {:key     :default
                   :width   200
                   :active? false}
   :sidebar-right {:key     :default
                   :width   200
                   :active? false}
   :dimmer        {:key :surface-13-dimmer}
   :modal         {:key     :surface-13-modal
                   :active? false
                   :width 300
                   :height 500}})

(def surfaces
  {:surface-13 surface-init

   ;; sla --> sidebar-left-active
   :surface-13-sla (-> surface-init
                       (assoc-in [:sidebar-left :active?] true))

   ;; sra --> sidebar-right-active
   :surface-13-sra (-> surface-init
                       (assoc-in [:sidebar-right :active?] true))

   :surface-13-modal (-> surface-init
                         (assoc-in [:modal :active?] true))})
