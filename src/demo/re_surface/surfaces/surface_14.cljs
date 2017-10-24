(ns re-surface.surfaces.surface-14
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "You should now be able to see a modal-fullscreen. Now, let's get cute and add a modal on top of a modal-fullscreen."]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-14-modal)
                           :surface-14
                           :surface-14-modal)))}
    "modal"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-14-modal-fs)
                           :surface-14
                           :surface-14-modal-fs)))}
    "modal-fullscreen"]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-14-sla)
                           :surface-14
                           :surface-14-sla)))}
    "sidebar-left"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-14-sra)
                           :surface-14
                           :surface-14-sra)))}
    "sidebar-right"]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/13")}
    "Back"]

   [:button
    {:on-click #(util/set-hash! "/surface/15")}
    "Add modal on top of modal-fullscreen"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])


(defn dimmer [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-14))}])

(defn modal [app-state]
  [:div
   {:style {:padding "16px"}}
   [:h1 "modal"]
   (for [x (range 32)]
     ^{:key x}
     [:h3 x])])


(defn modal-fs [app-state]
  [:div
   {:style {:padding "16px"}}
   [:h1 "Modal (fullscreen)"]
   [:button
    {:on-click (fn []
                 (swap! app-state assoc :page-key :surface-14))}
    "Close"]
   (for [x (range 48)]
     ^{:key x}
     [:h3 x])])



(def components
  {:body             {:surface-14-body body}
   :dimmer           {:surface-14-dimmer dimmer}
   :modal            {:surface-14-modal modal}
   :modal-fullscreen {:surface-14-modal-fs modal-fs}})


(def surface-init
  {:header           {:key    :default
                      :height 100
                      :fixed? true}
   :navbar           {:key    :default
                      :height 80
                      :fixed? true}
   :body             {:key :surface-14-body}
   :footer           {:key    :default
                      :height 100
                      :fixed? true}
   :sidebar-left     {:key     :default
                      :width   200
                      :active? false}
   :sidebar-right    {:key     :default
                      :width   200
                      :active? false}
   :dimmer           {:key :surface-14-dimmer}
   :modal            {:key     :surface-14-modal
                      :active? false
                      :width   300
                      :height  500}
   :modal-fullscreen {:key        :surface-14-modal-fs
                      :active?    false
                      }})

(def surfaces
  {:surface-14 surface-init

   ;; sla --> sidebar-left-active
   :surface-14-sla (-> surface-init
                       (assoc-in [:sidebar-left :active?] true))

   ;; sra --> sidebar-right-active
   :surface-14-sra (-> surface-init
                       (assoc-in [:sidebar-right :active?] true))


   :surface-14-modal (-> surface-init
                         (assoc-in [:modal :active?] true))

   ;; fs --> fullscreen
   :surface-14-modal-fs (-> surface-init
                            (assoc-in [:modal-fullscreen :active?] true))})
