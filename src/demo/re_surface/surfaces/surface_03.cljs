(ns re-surface.surfaces.surface-03
  (:require
   [re-surface.util-demo :as util]))


(defn body [app-state]
  [:div
   [:h1
    "And now we have a footer. As you can see, even though we
        don't have enough content in our body, the footer is at the
        bottom of the page (where it should be). Let's add content to
        the body and see what happens to the footer."]
   [:br]
   [:button
    {:on-click #(util/set-hash! "/surface/2")}
    "Back"]
   [:button
    {:on-click #(util/set-hash! "/surface/4")}
    "Add content to body"]
   ])

(def components
  {:body {:surface-03-body body}})

(def surfaces
  {:surface-03 {:header {:key    :default
                         :height 100}
                :body   {:key :surface-03-body}
                :footer {:key :default
                         :height 100}}})
