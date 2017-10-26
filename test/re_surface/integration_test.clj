(ns re-surface.integration-test
  (:require [clojure.test :refer :all]
            [etaoin.api :refer :all]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Setup

(def ^:dynamic
  *driver*)

(defn fixture-driver
  [f]
  (with-headless {} driver
    (binding [*driver* driver]
      (f))))

(use-fixtures
  :each ;; start and stop driver for each test
  fixture-driver)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(def url "http://localhost:3449/screenshots.html")

(def screenshot-failure-path "screenshots/failure")
(def screenshot-success-path "screenshots/success")

(def desktop-env
  {:label  "desktop"
   :width  1920
   :height 1080})

(def mobile-env
  {:label  "mobile"
           :width  337
           :height 667})


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Util

(defn ->screenshot [driver env name]
  (screenshot driver
              (str screenshot-success-path
                   "/"
                   (get env :label)
                   "_"
                   name
                   ".png")))


(defn px [int]
  (when int
    (str int "px")))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Test

(defn check-size [driver q
                  description
                  label
                  width
                  height]
  (println " ")
  (println label)
  (println (get-element-attrs driver q "class") "check size")
  (println (get-element-csss driver q "height" "width"))
  (let [[d-width d-height] (get-element-csss driver q "width" "height")]
    (testing description
      (is (= (px width) d-width))
      (is (= (px height) d-height))))
  driver)

(defn ->test1
  [env]
  (testing "body by itself should take up full page"
    (with-postmortem *driver* {:dir screenshot-failure-path}
      (doto *driver*
        (set-window-size (get env :width)
                         (get env :height))
        (go url)
        ;; (wait-visible [{:id "test-1"}])
        (wait-visible {:class :surf-surface})
        (->screenshot env "test-01")

        (check-size [{:class :surf-surface}
                     {:class :surf-body}]
                    "body should be full page width"
                    (get env :label)
                    (get env :width)
                    (get env :height))))))


(deftest ^:integration
  desktop-1920x1080
  (->test1 desktop-env))


(deftest ^:integration
  mobile-337x667
  (->test1 mobile-env))
