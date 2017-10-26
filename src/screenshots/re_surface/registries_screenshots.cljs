(ns re-surface.registries-screenshots
  (:require
   [re-surface.surfaces.surface-01 :as surface-01]
   ))



(def surface-registry
  (merge
   surface-01/surfaces
   ))


(def component-registry
  (reduce (fn [registry {:keys [header
                                header-dropdown
                                navbar
                                navbar-dropdown
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
                (update :navbar-dropdown #(merge % navbar-dropdown))
                (update :body #(merge % body))
                (update :footer #(merge % footer))
                (update :dimmer #(merge % dimmer))
                (update :sidebar-left #(merge % sidebar-left))
                (update :sidebar-right #(merge % sidebar-right))
                (update :modal #(merge % modal))
                (update :modal-fullscreen #(merge % modal-fullscreen))))
          {}
          [surface-01/components
           ]))
