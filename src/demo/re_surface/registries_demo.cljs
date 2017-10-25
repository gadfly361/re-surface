(ns re-surface.registries-demo
  (:require
   [re-surface.surfaces.surface-01 :as surface-01]
   [re-surface.surfaces.surface-02 :as surface-02]
   [re-surface.surfaces.surface-03 :as surface-03]
   [re-surface.surfaces.surface-04 :as surface-04]
   [re-surface.surfaces.surface-05 :as surface-05]
   [re-surface.surfaces.surface-06 :as surface-06]
   [re-surface.surfaces.surface-07 :as surface-07]
   [re-surface.surfaces.surface-08 :as surface-08]
   [re-surface.surfaces.surface-09 :as surface-09]
   [re-surface.surfaces.surface-10 :as surface-10]
   [re-surface.surfaces.surface-11 :as surface-11]
   [re-surface.surfaces.surface-12 :as surface-12]
   [re-surface.surfaces.surface-13 :as surface-13]
   [re-surface.surfaces.surface-14 :as surface-14]
   [re-surface.surfaces.surface-15 :as surface-15]
   [re-surface.surfaces.surface-16 :as surface-16]
   ))



(def surface-registry
  (merge
   surface-01/surfaces
   surface-02/surfaces
   surface-03/surfaces
   surface-04/surfaces
   surface-05/surfaces
   surface-06/surfaces
   surface-07/surfaces
   surface-08/surfaces
   surface-09/surfaces
   surface-10/surfaces
   surface-11/surfaces
   surface-12/surfaces
   surface-13/surfaces
   surface-14/surfaces
   surface-15/surfaces
   surface-16/surfaces
   ))


(def basic-components
  {:header {:default (fn [app-state]
                       [:h1 "header"])}

   :navbar {:default (fn [app-state]
                       [:h1 "navbar"])}

   :footer {:default (fn [app-state]
                       [:h1 "footer"])}

   :sidebar-left {:default (fn [app-state]
                             [:h1 "Sidebar-left"])}

   :sidebar-right {:default (fn [app-state]
                              [:h1 "Sidebar-right"])}})


(def component-registry
  (reduce (fn [registry {:keys [header
                                header-dropdown
                                navbar
                                body
                                footer
                                dimmer
                                sidebar-left
                                sidebar-right
                                modal
                                modal-fullscreen]}]
            (-> registry
                (update :header #(merge % header))
                (update :header-dropdown #(merge % header-dropdown))
                (update :navbar #(merge % navbar))
                (update :body #(merge % body))
                (update :footer #(merge % footer))
                (update :dimmer #(merge % dimmer))
                (update :sidebar-left #(merge % sidebar-left))
                (update :sidebar-right #(merge % sidebar-right))
                (update :modal #(merge % modal))
                (update :modal-fullscreen #(merge % modal-fullscreen))))
          basic-components
          [surface-01/components
           surface-02/components
           surface-03/components
           surface-04/components
           surface-05/components
           surface-06/components
           surface-07/components
           surface-08/components
           surface-09/components
           surface-10/components
           surface-11/components
           surface-12/components
           surface-13/components
           surface-14/components
           surface-15/components
           surface-16/components
           ]))
