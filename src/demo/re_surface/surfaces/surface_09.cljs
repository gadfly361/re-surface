(ns re-surface.surfaces.surface-09
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
      [:div
       [:h1
        "We have a sidebar that we can "
        [:button
         {:on-click #(swap! app-state update :page-key
                            (fn [pk]
                              (if (= pk :surface-09-sla)
                                :surface-09
                                :surface-09-sla)))}
         "toggle open/closed"]
        " but it is missing something. It is missing a dimmer!"]
       [:br]
       [:button
        {:on-click #(util/set-hash! "/surface/8")}
        "Back"]
       [:button
        {:on-click #(util/set-hash! "/surface/10")}
        "Add dimmer"]

       (for [x (range 42)]
         ^{:key x}
         [:h3 x])
       ])

(defn sidebar-left [app-state]
    [:div
     [:h1 "Sidebar-left"]
     [:button
      {:on-click #(swap! app-state assoc :page-key :surface-09)}
      "close"]])

(def components
  {:body         {:surface-09-body body}
   :sidebar-left {:surface-09-sidebar-left sidebar-left}})

(def surface-init
  {:header       {:key    :default
                  :height 100
                  :fixed? true}
   :navbar       {:key    :default
                  :height 80
                  :fixed? true}
   :body         {:key :surface-09-body}
   :footer       {:key    :default
                  :height 100
                  :fixed? true}
   :sidebar-left {:key     :surface-09-sidebar-left
                  :active? false
                  :width   200}})

(def surfaces
  {:surface-09 surface-init

   ;; sla --> sidebar-left-active
   :surface-09-sla (-> surface-init
                       (assoc-in [:sidebar-left :active?] true))})
