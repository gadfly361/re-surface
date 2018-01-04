(ns re-surface.surfaces.surface-15
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "Now, if you open the modal-fullscreen, you should see a button to
    open a modal on top of that. If you want, you can go directly to
    that state by clicking on "
    [:button
     {:on-click #(swap! app-state update :page-key
                        (fn [pk]
                          (if (= pk :surface-15-modal-fs-with-modal-on-top)
                            :surface-15
                            :surface-15-modal-fs-with-modal-on-top)))}
     "modal-fullscreen with modal on top"]
    ". Next, let's add a header dropdown."]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-15-modal)
                           :surface-15
                           :surface-15-modal)))}
    "modal"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-15-modal-fs)
                           :surface-15
                           :surface-15-modal-fs)))}
    "modal-fullscreen"]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-15-sla)
                           :surface-15
                           :surface-15-sla)))}
    "sidebar-left"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-15-sra)
                           :surface-15
                           :surface-15-sra)))}
    "sidebar-right"]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/14")}
    "Back"]

   [:button
    {:on-click #(util/set-hash! "/surface/16")}
    "Add header dropdown"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])


(defn dimmer [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-15))}])

(defn dimmer-with-modal-on-top [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-15-modal-fs))}])

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
                 (swap! app-state assoc :page-key :surface-15))}
    "Close"]
   [:button
    {:on-click (fn []
                 (swap! app-state assoc :page-key :surface-15-modal-fs-with-modal-on-top))}
    "Open modal on top"]
   (for [x (range 48)]
     ^{:key x}
     [:h3 x])])



(def components
  {:body             {:surface-15-body body}
   :dimmer           {:surface-15-dimmer dimmer
                      :surface-15-dimmer-with-modal-on-top dimmer-with-modal-on-top}
   :modal            {:surface-15-modal modal}
   :modal-fullscreen {:surface-15-modal-fs modal-fs}})


(def surface-init
  {:header           {:key    :default
                      :height 100
                      :fixed? true}
   :navbar           {:key    :default
                      :height 80
                      :fixed? true}
   :body             {:key :surface-15-body}
   :footer           {:key    :default
                      :height 100
                      :fixed? true}
   :sidebar-left     {:key     :default
                      :width   200
                      :active? false}
   :sidebar-right    {:key     :default
                      :width   200
                      :active? false}
   :dimmer           {:key :surface-15-dimmer}
   :modal            {:active? false
                      :width   300
                      :height  500}
   :modal-fullscreen {:active?    false
                      }})

(def surfaces
  {:surface-15 surface-init

   ;; sla --> sidebar-left-active
   :surface-15-sla (-> surface-init
                       (assoc-in [:sidebar-left :active?] true))

   ;; sra --> sidebar-right-active
   :surface-15-sra (-> surface-init
                       (assoc-in [:sidebar-right :active?] true))


   :surface-15-modal (-> surface-init
                         (assoc-in [:modal :key] :surface-15-modal)
                         (assoc-in [:modal :active?] true))

   ;; fs --> fullscreen
   :surface-15-modal-fs (-> surface-init
                            (assoc-in [:modal-fullscreen :key]
                                      :surface-15-modal-fs)
                            (assoc-in [:modal-fullscreen :active?] true))

   :surface-15-modal-fs-with-modal-on-top
   (-> surface-init
       (assoc-in [:modal-fullscreen :key]
                 :surface-15-modal-fs)
       (assoc-in [:modal-fullscreen :active?] true)
       (assoc-in [:dimmer :key] :surface-15-dimmer-with-modal-on-top)
       (assoc-in [:modal :key] :surface-15-modal)
       (assoc-in [:modal :active?] true))
   })
