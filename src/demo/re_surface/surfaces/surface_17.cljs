(ns re-surface.surfaces.surface-17
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "You should see a button in the navbar that will toggle the
    navbar-dropdown."]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-17-modal)
                           :surface-17
                           :surface-17-modal)))}
    "modal"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-17-modal-fs)
                           :surface-17
                           :surface-17-modal-fs)))}
    "modal-fullscreen"]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-17-sla)
                           :surface-17
                           :surface-17-sla)))}
    "sidebar-left"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-17-sra)
                           :surface-17
                           :surface-17-sra)))}
    "sidebar-right"]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/16")}
    "Back"]

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
                         (if (= x :surface-17-header-dropdown)
                         :surface-17
                         :surface-17-header-dropdown)))}
    "header-dropdown"]])

(defn header-dropdown [app-state]
  [:div
   [:h3 "header dropdown"]
   (for [x (range 10)]
     ^{:key x}
     [:h4 x])])


(defn navbar [app-state]
  [:div
   [:h1 "navbar"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [x]
                         (if (= x :surface-17-navbar-dropdown)
                         :surface-17
                         :surface-17-navbar-dropdown)))}
    "navbar-dropdown"]])

(defn navbar-dropdown [app-state]
  [:div
   [:h3 "navbar dropdown"]
   (for [x (range 10)]
     ^{:key x}
     [:h4 x])])



(defn dimmer [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-17))}])

(defn dimmer-with-modal-on-top [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-17-modal-fs))}])

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
                 (swap! app-state assoc :page-key :surface-17))}
    "Close"]
   [:button
    {:on-click (fn []
                 (swap! app-state assoc :page-key :surface-17-modal-fs-with-modal-on-top))}
    "Open modal on top"]
   (for [x (range 48)]
     ^{:key x}
     [:h3 x])])



(def components
  {:header {:surface-17-header header}
   :header-dropdown {:surface-17-header-dropdown header-dropdown}
   :navbar {:surface-17-navbar navbar}
   :navbar-dropdown {:surface-17-navbar-dropdown navbar-dropdown}
   :body             {:surface-17-body body}
   :dimmer           {:surface-17-dimmer dimmer
                      :surface-17-dimmer-with-modal-on-top dimmer-with-modal-on-top}
   :modal            {:surface-17-modal modal}
   :modal-fullscreen {:surface-17-modal-fs modal-fs}})


(def surface-init
  {:header           {:key    :surface-17-header
                      :height 100
                      :fixed? true}
   :header-dropdown {:key :surface-17-header-dropdown
                     :active? false
                     :top -24}
   :navbar           {:key    :surface-17-navbar
                      :height 100
                      :fixed? true}
   :navbar-dropdown {:key     :surface-17-navbar-dropdown
                     :active? false
                     :top     -24}
   :body             {:key :surface-17-body}
   :footer           {:key    :default
                      :height 100
                      :fixed? true}
   :sidebar-left     {:key     :default
                      :width   200
                      :active? false}
   :sidebar-right    {:key     :default
                      :width   200
                      :active? false}
   :dimmer           {:key :surface-17-dimmer}
   :modal            {:key     :surface-17-modal
                      :active? false
                      :width   300
                      :height  500}
   :modal-fullscreen {:key        :surface-17-modal-fs
                      :active?    false
                      }})

(def surfaces
  {:surface-17 surface-init

   ;; sla --> sidebar-left-active
   :surface-17-sla (-> surface-init
                       (assoc-in [:sidebar-left :active?] true))

   ;; sra --> sidebar-right-active
   :surface-17-sra (-> surface-init
                       (assoc-in [:sidebar-right :active?] true))


   :surface-17-modal (-> surface-init
                         (assoc-in [:modal :active?] true))

   ;; fs --> fullscreen
   :surface-17-modal-fs (-> surface-init
                            (assoc-in [:modal-fullscreen :active?] true))

   :surface-17-modal-fs-with-modal-on-top
   (-> surface-init
       (assoc-in [:modal-fullscreen :active?] true)
       (assoc-in [:dimmer :key] :surface-17-dimmer-with-modal-on-top)
       (assoc-in [:modal :active?] true))

   :surface-17-header-dropdown
   (-> surface-init
       (assoc-in [:header-dropdown :active?] true))

   :surface-17-navbar-dropdown
   (-> surface-init
       (assoc-in [:navbar-dropdown :active?] true))
   })
