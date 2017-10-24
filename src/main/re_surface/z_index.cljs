(ns re-surface.z-index)


(def indicies
  {:dimmer          -1
   :modal           -1
   :modal-fullscreen -1

   :surface 0
   :main    0
   :body    0

   :header 1
   :navbar 1
   :footer 1

   ;; TODO: notifications 2

   :dimmer-active 3

   :sidebar-left  4
   :sidebar-right 4

   :modal-fullscreen-active 5

   :dimmer-active-with-modal 6

   :modal-active 7
   })
