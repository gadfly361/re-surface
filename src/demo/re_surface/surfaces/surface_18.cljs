(ns re-surface.surfaces.surface-18
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "If you click the dropdown button in the header, it should now be
    full-width. Let's go ahead and make the navbar-dropdown full-width too."]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-18-modal)
                           :surface-18
                           :surface-18-modal)))}
    "modal"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-18-modal-fs)
                           :surface-18
                           :surface-18-modal-fs)))}
    "modal-fullscreen"]
   [:br]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-18-sla)
                           :surface-18
                           :surface-18-sla)))}
    "sidebar-left"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [pk]
                         (if (= pk :surface-18-sra)
                           :surface-18
                           :surface-18-sra)))}
    "sidebar-right"]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/17")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/19")}
    "Make navbar-dropdown full-width"]

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
                         (if (= x :surface-18-header-dropdown)
                         :surface-18
                         :surface-18-header-dropdown)))}
    "header-dropdown"]])

(defn header-dropdown [app-state]
  [:div
   [:h3 "header dropdown (full-width)"]
   (for [x (range 10)]
     ^{:key x}
     [:h4 x])])


(defn navbar [app-state]
  [:div
   [:h1 "navbar"]
   [:button
    {:on-click #(swap! app-state update :page-key
                       (fn [x]
                         (if (= x :surface-18-navbar-dropdown)
                         :surface-18
                         :surface-18-navbar-dropdown)))}
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
                (swap! app-state assoc :page-key :surface-18))}])

(defn dimmer-with-modal-on-top [app-state]
  [:div
   {:style    {:height "100%"
               :width  "100%"}
    :on-click (fn []
                (swap! app-state assoc :page-key :surface-18-modal-fs))}])

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
                 (swap! app-state assoc :page-key :surface-18))}
    "Close"]
   [:button
    {:on-click (fn []
                 (swap! app-state assoc :page-key :surface-18-modal-fs-with-modal-on-top))}
    "Open modal on top"]
   (for [x (range 48)]
     ^{:key x}
     [:h3 x])])



(def components
  {:header {:surface-18-header header}
   :header-dropdown {:surface-18-header-dropdown header-dropdown}
   :navbar {:surface-18-navbar navbar}
   :navbar-dropdown {:surface-18-navbar-dropdown navbar-dropdown}
   :body             {:surface-18-body body}
   :dimmer           {:surface-18-dimmer dimmer
                      :surface-18-dimmer-with-modal-on-top dimmer-with-modal-on-top}
   :modal            {:surface-18-modal modal}
   :modal-fullscreen {:surface-18-modal-fs modal-fs}})


(def surface-init
  {:header           {:key    :surface-18-header
                      :height 100
                      :fixed? true}
   :header-dropdown {:active? false
                     :full-width? true}
   :navbar           {:key    :surface-18-navbar
                      :height 100
                      :fixed? true}
   :navbar-dropdown {:active? false
                     :top     -24}
   :body             {:key :surface-18-body}
   :footer           {:key    :default
                      :height 100
                      :fixed? true}
   :sidebar-left     {:key     :default
                      :width   200
                      :active? false}
   :sidebar-right    {:key     :default
                      :width   200
                      :active? false}
   :dimmer           {:key :surface-18-dimmer}
   :modal            {:active? false
                      :width   300
                      :height  500}
   :modal-fullscreen {:active?    false
                      }})

(def surfaces
  {:surface-18 surface-init

   ;; sla --> sidebar-left-active
   :surface-18-sla (-> surface-init
                       (assoc-in [:sidebar-left :active?] true))

   ;; sra --> sidebar-right-active
   :surface-18-sra (-> surface-init
                       (assoc-in [:sidebar-right :active?] true))

   :surface-18-modal (-> surface-init
                         (assoc-in [:modal :key] :surface-18-modal)
                         (assoc-in [:modal :active?] true))

   ;; fs --> fullscreen
   :surface-18-modal-fs (-> surface-init
                            (assoc-in [:modal-fullscreen :key]
                                      :surface-18-modal-fs)
                            (assoc-in [:modal-fullscreen :active?] true))

   :surface-18-modal-fs-with-modal-on-top
   (-> surface-init
       (assoc-in [:modal-fullscreen :key]
                 :surface-18-modal-fs)
       (assoc-in [:modal-fullscreen :active?] true)
       (assoc-in [:dimmer :key] :surface-18-dimmer-with-modal-on-top)
       (assoc-in [:modal :key] :surface-18-modal)
       (assoc-in [:modal :active?] true))

   :surface-18-header-dropdown
   (-> surface-init
       (assoc-in [:header-dropdown :key] :surface-18-header-dropdown)
       (assoc-in [:header-dropdown :active?] true))

   :surface-18-navbar-dropdown
   (-> surface-init
       (assoc-in [:navbar-dropdown :key] :surface-18-navbar-dropdown)
       (assoc-in [:navbar-dropdown :active?] true))
   })
