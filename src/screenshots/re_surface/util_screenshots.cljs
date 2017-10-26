(ns re-surface.util-screenshots)


(defn- scroll-to-top []
  (.scroll js/window 0 0))

(defn set-page-key! [app-state page-key]
  (swap! app-state assoc :page-key page-key)
  (scroll-to-top))


(defn set-hash! [url]
  (set! (.-hash (.-location js/window))
        (str "#" url)))
