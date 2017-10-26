(ns re-surface.surfaces.surface-01
  (:require
   [re-surface.util-screenshots :as util]))

(def test-number 1)
(def test-label "A :body by itself should take up full page")


(def test-id (str "test-" test-number))

(defn ->comp-id
  ([comp-type]
   (->comp-id comp-type nil))
  ([comp-type suffix]
   (keyword (str test-id "-" comp-type suffix))))


(defn h1-test-description []
  [:h1 {:id test-id}
   test-label])

(defn body [app-state]
  [:div
   [h1-test-description]

   ])

(def components
  {:body {(->comp-id "body") body}})

(def surfaces
  {:surface-01 {:body {:key (->comp-id "body")}}})
