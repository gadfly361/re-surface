(ns re-surface.surfaces.surface-16
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "You should see a button in the header that will toggle the
    header-dropdown. Next, let's add a navbar dropdown."]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-16-modal)
                           :surface-16
                           :surface-16-modal)))}
    "modal"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-16-modal-fs)
                           :surface-16
                           :surface-16-modal-fs)))}
    "modal-fullscreen"]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-16-sla)
                           :surface-16
                           :surface-16-sla)))}
    "sidebar-left"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-16-sra)
                           :surface-16
                           :surface-16-sra)))}
    "sidebar-right"]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/15")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/17")}
    "Add navbar dropdown"]

   (for [x (range 42)]
     ^{:key x}
     [:h3 x])
   ])


(defn header [app-state]
  [:div
   [:h1 "header"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [x]
                         (if (= x :surface-16-header-dropdown)
                         :surface-16
                         :surface-16-header-dropdown)))}
    "header-dropdown"]])

(defn header-dropdown [app-state]
  [:div
   [:h3 "header dropdown"]
   (for [x (range 10)]
     ^{:key x}
     [:h4 x])])


(defn dimmer [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-16))}])

(defn dimmer-with-modal-on-top [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-16-modal-fs))}])

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
                 (swap! app-state assoc :page-key :surface-16))}
    "Close"]
   [:button
    {:on-click (fn []
                 (swap! app-state assoc :page-key :surface-16-modal-fs-with-modal-on-top))}
    "Open modal on top"]
   (for [x (range 48)]
     ^{:key x}
     [:h3 x])])



(def components
  {:header {:surface-16-header header}
   :header-dropdown {:surface-16-header-dropdown header-dropdown}
   :body             {:surface-16-body body}
   :dimmer           {:surface-16-dimmer dimmer
                      :surface-16-dimmer-with-modal-on-top dimmer-with-modal-on-top}
   :modal            {:surface-16-modal modal}
   :modal-fullscreen {:surface-16-modal-fs modal-fs}})


(def surface-init
  {:header           {:key :surface-16-header
                      :height 100
                      :fixed? true}
   :header-dropdown {:active? false
                     :top -24}
   :navbar           {:key    :default
                      :height 80
                      :fixed? true}
   :body             {:key :surface-16-body}
   :footer           {:key    :default
                      :height 100
                      :fixed? true}
   :sidebar-left     {:key     :default
                      :width   200
                      :active? false}
   :sidebar-right    {:key     :default
                      :width   200
                      :active? false}
   :dimmer           {:key :surface-16-dimmer}
   :modal            {:active? false
                      :width   300
                      :height  500}
   :modal-fullscreen {:active? false}})

(def surfaces
  {:surface-16 surface-init

   ;; sla --> sidebar-left-active
   :surface-16-sla (-> surface-init
                       (assoc-in [:sidebar-left :active?] true))

   ;; sra --> sidebar-right-active
   :surface-16-sra (-> surface-init
                       (assoc-in [:sidebar-right :active?] true))

   :surface-16-modal (-> surface-init
                         (assoc-in [:modal :key] :surface-16-modal)
                         (assoc-in [:modal :active?] true))

   ;; fs --> fullscreen
   :surface-16-modal-fs (-> surface-init
                            (assoc-in [:modal-fullscreen :key]
                                      :surface-16-modal-fs)
                            (assoc-in [:modal-fullscreen :active?] true))

   :surface-16-modal-fs-with-modal-on-top
   (-> surface-init
       (assoc-in [:modal-fullscreen :key]
                 :surface-16-modal-fs)
       (assoc-in [:modal-fullscreen :active?] true)
       (assoc-in [:dimmer :key] :surface-16-dimmer-with-modal-on-top)
       (assoc-in [:modal :key] :surface-16-modal)
       (assoc-in [:modal :active?] true))

   :surface-16-header-dropdown
   (-> surface-init
       (assoc-in [:header-dropdown :key] :surface-16-header-dropdown)
       (assoc-in [:header-dropdown :active?] true))
   })
