(ns re-surface.core-demo
  (:require-macros [secretary.core :refer [defroute]])
  (:import goog.History)
  (:require
   [secretary.core :as secretary]
   [goog.events :as events]
   [goog.history.EventType :as EventType]
   [clojure.string :as string]
   [reagent.core :as reagent]
   [re-frisk.core :as rf]
   [re-surface.core :as rs]
   [re-surface.util-demo :as util]
   [re-surface.registries-demo :as registries]
   ))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(defonce app-state
  (reagent/atom {}))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Routes

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defn app-routes [app-state]
  (secretary/set-config! :prefix "#")

  (defroute "/surface/:id" [id]
    (util/set-page-key! app-state
                        (let [under-ten? (< id 10)]
                          (->> id
                               (str "surface-" (when under-ten? "0"))
                               keyword))))

  (defroute "*" []
    (util/set-hash! "/surface/1"))

  (hook-browser-navigation!))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Page

(defn page [app-state]
  (let [page-key (get @app-state :page-key)]
    [:div

     [:style
      "h1, h2, h3, h4, h5 { margin:0 }"]

     [rs/surface {:app-state app-state
                  :surface-key  page-key
                  :surface-registry   registries/surface-registry
                  :component-registry registries/component-registry
                  :surface-config     {:debug? true}}]]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    (rf/enable-frisk!)
    (rf/add-data :app-state app-state)
    ))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app")))

(defn ^:export main []
  (dev-setup)
  (app-routes app-state)
  (reload))
