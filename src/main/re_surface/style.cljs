(ns re-surface.style
  (:require
   [garden.core :refer [css]]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Util

(defn- px [int]
  (when int
    (str int "px")))

(defn- ->surface-map [opts]
  (let [{:keys [surface-key
                surface-registry]} opts]
    (get surface-registry surface-key)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Dimmer

(defn- ->dimmer [opts]
  (let [surface-config (get opts :surface-config)]
    [:.surf-dimmer
     {:position         "fixed"
      :top              0
      :bottom           0
      :left             0
      :right            0
      :height           "100%"
      :width            "100%"
      :background-color "black"
      :opacity          0
      :transition       "z-index 0.3s step-end, opacity 0.3s linear"
      :z-index          (get-in surface-config [:z-indicies :dimmer])}]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sidebar Left

(defn- ->sidebar-left [opts]
  (let [surface-map      (->surface-map opts)
        surface-config   (get opts :surface-config)
        width            (get-in surface-map [:sidebar-left :width])
        background-color (get-in surface-map [:sidebar-left :background-color] "white")]
    [:.surf-sidebar-left
     {:position         "fixed"
      :background-color background-color
      :top              0
      :bottom           0
      :height           "100%"
      :width            (px width)
      :left             (px (- width))
      :transition       "left 0.3s linear"
      :overflow         "auto"
      :z-index          (get-in surface-config [:z-indicies :sidebar-left])}]))


(defn- ->sidebar-left-active [opts]
  (let [surface-config (get opts :surface-config)]
    [:&.surf-surface-sidebar-left-active
     [:.surf-sidebar-left
      {:left       (px 0)
       :transition "left 0.3s linear"}]
     [:.surf-dimmer
      {:opacity    0.5
       :transition "opacity 0.3s linear"
       :z-index    (get-in surface-config [:z-indicies :dimmer-active])}]]))


(defn- ->sidebar-left-fixed [opts]
  (let [surface-map (->surface-map opts)
        width       (get-in surface-map [:sidebar-left :width])]
    [:&.surf-surface-sidebar-left-fixed
     [:.surf-sidebar-left
      {:left       (px 0)
       :transition "left 0.3s linear"}]

     [:.surf-main
      {:left       (px width)
       :width      (str "calc(100% - " width "px)")
       :transition "left 0.3s linear"}]]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sidebar Right

(defn- ->sidebar-right [opts]
  (let [surface-config   (get opts :surface-config)
        surface-map      (->surface-map opts)
        width            (get-in surface-map [:sidebar-right :width])
        background-color (get-in surface-map [:sidebar-right :background-color] "white")]
    [:.surf-sidebar-right
     {:position         "fixed"
      :background-color background-color
      :top              0
      :bottom           0
      :height           "100%"
      :width            (px width)
      :right            (px (- width))
      :transition       "right 0.3s linear"
      :overflow         "auto"
      :z-index          (get-in surface-config [:z-indicies :sidebar-right])}]))

(defn- ->sidebar-right-active [opts]
  (let [surface-config (get opts :surface-config)]
    [:&.surf-surface-sidebar-right-active
     [:.surf-sidebar-right
      {:right      (px 0)
       :transition "right 0.3s linear"}]

     [:.surf-dimmer
      {:opacity    0.5
       :transition "opacity 0.3s linear"
       :z-index    (get-in surface-config [:z-indicies :dimmer-active])}]]))


(defn- ->sidebar-right-fixed [opts]
  (let [surface-map (->surface-map opts)
        width       (get-in surface-map [:sidebar-right :width])]
    [:&.surf-surface-sidebar-right-fixed
     [:.surf-sidebar-right
      {:right      (px 0)
       :transition "right 0.3s linear"}]

     [:.surf-main
      {:right      (px width)
       :width      (str "calc(100% - " width "px)")
       :transition "right 0.3s linear"}]]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Sidebars

(defn- ->sidebars-active [opts]
  (let [surface-map (->surface-map opts)
        left-width  (get-in surface-map [:sidebar-left :width])
        right-width (get-in surface-map [:sidebar-right :width])]
    [:&.surf-surface-sidebar-left-fixed
     [:&.surf-surface-sidebar-right-fixed

      [:.surf-main
       {:right      (px right-width)
        :left       (px left-width)
        :width      (str "calc(100% - "
                         (+ right-width left-width)
                         "px)")
        :transition "right 0.3s linear"}]
      ]]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Modal

(defn- ->modal [opts]
  (let [surface-config   (get opts :surface-config)
        surface-map      (->surface-map opts)
        height           (get-in surface-map [:modal :height])
        width            (get-in surface-map [:modal :width])
        half-width       (/ width 2)
        background-color (get-in surface-map [:modal :background-color] "white")]
    [:.surf-modal
     {:position         "fixed"
      :background-color background-color
      :overflow-y       "auto"
      :top              "-20px" ;; arbitrary
      :left             (str "calc(50% - " (or half-width 0) "px)")
      :height           (px height)
      :width            (px width)
      :opacity          0
      :transition       "z-index 0.3s step-end, opacity 0.3s linear, top 0.3s linear"
      :z-index          (get-in surface-config [:z-indicies :modal])}]
    ))

(defn- ->modal-active [opts]
  (let [surface-config (get opts :surface-config)
        surface-map    (->surface-map opts)
        width          (get-in surface-map [:modal :width])
        height         (get-in surface-map [:modal :height])
        top            (get-in surface-map [:modal :top] 40)]
    [:&.surf-surface-modal-active

     [:.surf-modal
      {:opacity    1
       :top        (str top "px")
       :z-index    (get-in surface-config [:z-indicies :modal-active])
       :transition "opacity 0.3s linear, top 0.3s linear"}]

     [:.surf-dimmer
      {:opacity    0.5
       :transition "opacity 0.3s linear"
       :z-index    (get-in surface-config [:z-indicies :dimmer-active-with-modal])}]
     ]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Modal-fullscreen

(defn- ->modal-fullscreen [opts]
  (let [surface-config   (get opts :surface-config)
        surface-map      (->surface-map opts)
        background-color (get-in surface-map [:modal-fullscreen :background-color] "white")]
    [:.surf-modal-fullscreen
     {:position         "fixed"
      :top              "0px"
      :left             "0px"
      :bottom           "0px"
      :right            "0px"
      :height           "100%"
      :width            "100%"
      :background-color background-color
      :opacity          0
      :transition       "z-index 0.15s step-end, opacity 0.15s linear"
      :z-index          (get-in surface-config [:z-indicies :modal-fullscreen])}]))


(defn- ->modal-fullscreen-active [opts]
  (let [surface-config (get opts :surface-config)]
    [:&.surf-surface-modal-fullscreen-active
     ;; remove scrollbar from page underneath
     {:overflow "hidden"}
     [:.surf-modal-fullscreen
      {;; add scrollbar for modal-fullscreen
       :overflow "auto"

       :opacity    1
       :z-index    (get-in surface-config [:z-indicies :modal-fullscreen-active])
       :transition "opacity 0.15s linear"}]]))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Main

(defn- ->main [opts]
  (let [surface-config (get opts :surface-config)]
    [:.surf-main
     {:position "absolute"
      :left     0
      :width    "100%"
      :height   "100%"
      :z-index  (get-in surface-config [:z-indicies :main])}]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Header

(defn- ->header [opts]
  (let [surface-config   (get opts :surface-config)
        surface-map      (->surface-map opts)
        {:keys [header]} surface-map
        height           (get header :height)
        background-color (get-in surface-map [:header :background-color] "white")]
    [:.surf-header
     {:background-color background-color
      :height           (px height)
      :width            "100%"
      :z-index          (get-in surface-config [:z-indicies :header])}]))

(defn- ->header-fixed [opts]
  (let [surface-map      (->surface-map opts)
        {:keys [header
                navbar]} surface-map
        header-height    (get header :height)
        navbar-height    (get navbar :height)
        navbar-fixed?    (get navbar :fixed?)]
    [:&.surf-surface-header-fixed
     [:.surf-header
      {:position "fixed"
       :top      (px 0)}]

     (cond
       (and navbar
            navbar-fixed?)
       [:.surf-body
        {:margin-top (px (+ header-height
                            navbar-height))}]

       navbar
       [:.surf-navbar
        {:margin-top (px header-height)}]

       :else
       [:.surf-body
        {:margin-top (px header-height)}])

     ]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Header dropdown

(defn ->header-dropdown [opts]
  (let [surface-config                     (get opts :surface-config)
        surface-map                        (->surface-map opts)
        {:keys [header-dropdown
                header]}                   surface-map
        {:keys [height
                width
                top
                right
                bottom
                left
                active?
                background-color]
         :or   {background-color "white"}} header-dropdown
        header-height                      (get header :height)]
    [
     [:.surf-header-dropdown
      {:position         "absolute"
       :z-index          (get-in surface-config [:z-indicies :header-dropdown])
       :height           (px height)
       :width            (px width)
       :top              (px (+ top
                                header-height))
       :right            (px right)
       :bottom           (px bottom)
       :left             (px left)
       :background-color "white"
       :opacity          0
       :transition       "z-index 0.15s step-end, opacity 0.15s"
       :overflow         "auto"
       }]

     [:&.surf-surface-header-fixed
      [:.surf-header-dropdown
       {:position "fixed"}]]

     [:&.surf-surface-header-dropdown-full-width
      [:.surf-header-dropdown
       {:height     "0px"
        :width      "100%"
        :transition "z-index 0.3s step-end, opacity 0.3s step-end,  height 0.3s linear"}]]
     ]))

(defn ->header-dropdown-active [opts]
  (let [surface-config            (get opts :surface-config)
        surface-map               (->surface-map opts)
        {:keys [header-dropdown]} surface-map
        height                    (get header-dropdown :height)]
    [
     [:&.surf-surface-header-dropdown-active
      [:.surf-header-dropdown
       {:z-index    (get-in surface-config [:z-indicies :header-dropdown-active])
        :opacity    1
        :transition "opacity 0.15s linear"}
       ]]

     [:&.surf-surface-header-dropdown-active
      [:&.surf-surface-header-dropdown-full-width
       [:.surf-header-dropdown
        {:height     (px height)
         :transition "opacity 0.15s linear,  height 0.3s linear"}
        ]]]
     ]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; navbar

(defn- ->navbar [opts]
  (let [surface-config   (get opts :surface-config)
        surface-map      (->surface-map opts)
        {:keys [navbar
                header]} surface-map
        height           (get navbar :height)
        background-color (get-in surface-map [:navbar :background-color] "white")]
    [:.surf-navbar
     {:background-color background-color
      :height           (px height)
      :width            "100%"
      :z-index          (get-in surface-config [:z-indicies :navbar])}]))

(defn- ->navbar-fixed [opts]
  (let [surface-map      (->surface-map opts)
        {:keys [navbar
                header]} surface-map
        navbar-height    (get navbar :height)
        header-height    (get header :height)]
    [
     [:&.surf-surface-navbar-fixed
      [:.surf-navbar
       {:position "fixed"
        :top      (px header-height)}]
      [:.surf-body
       {:margin-top (px (+ navbar-height))}]]

     [:&.surf-surface-navbar-fixed
      [:&.surf-surface-header-fixed
       [:.surf-body
        {:margin-top (px (+ navbar-height
                            header-height))}]]]
     ]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Navbar dropdown

(defn ->navbar-dropdown [opts]
  (let [surface-config                     (get opts :surface-config)
        surface-map                        (->surface-map opts)
        {:keys [navbar-dropdown
                navbar
                header]}                   surface-map
        {:keys [height
                width
                top
                right
                bottom
                left
                active?
                background-color]
         :or   {background-color "white"}} navbar-dropdown
        header-height                      (get header :height)
        navbar-height                      (get navbar :height)]
    [
     [:.surf-navbar-dropdown
      {:position         "absolute"
       :z-index          (get-in surface-config [:z-indicies :navbar-dropdown])
       :height           (px height)
       :width            (px width)
       :top              (px (+ top
                                header-height
                                navbar-height))
       :right            (px right)
       :bottom           (px bottom)
       :left             (px left)
       :background-color "white"
       :opacity          0
       :transition       "z-index 0.15s step-end, opacity 0.15s"
       :overflow "auto"
       }]

     [:&.surf-surface-navbar-fixed
      [:.surf-navbar-dropdown
       {:position "fixed"}]]

     [:&.surf-surface-navbar-dropdown-full-width
      [:.surf-navbar-dropdown
       {:height     "0px"
        :width      "100%"
        :transition "z-index 0.3s step-end, opacity 0.3s step-end,  height 0.3s linear"}]]
     ]))

(defn ->navbar-dropdown-active [opts]
  (let [surface-config (get opts :surface-config)
        surface-map               (->surface-map opts)
        {:keys [navbar-dropdown]} surface-map
        height                    (get navbar-dropdown :height)]
    [
     [:&.surf-surface-navbar-dropdown-active
      [:.surf-navbar-dropdown
       {:z-index    (get-in surface-config [:z-indicies :navbar-dropdown-active])
        :opacity    1
        :transition "opacity 0.15s linear"}
       ]]

     [:&.surf-surface-navbar-dropdown-active
      [:&.surf-surface-navbar-dropdown-full-width
       [:.surf-navbar-dropdown
        {:height     (px height)
         :transition "opacity 0.15s linear,  height 0.3s linear"}
        ]]]
     ]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Body

(defn- ->body [opts]
  (let [surface-config   (get opts :surface-config)
        surface-map      (->surface-map opts)
        {:keys [header
                footer
                navbar]} surface-map
        header-height    (get header :height)
        navbar-height    (get navbar :height)
        footer-height    (get footer :height)
        background-color (get-in surface-map [:body :background-color] "white")]
    [:.surf-body
     {:background-color background-color
      :min-height
      (str "calc(100% - " (px (+ header-height
                                 navbar-height
                                 footer-height))")")
      :width            "100%"
      :z-index          (get-in surface-config [:z-indicies :body])}]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Footer

(defn- ->footer [opts]
  (let [surface-config   (get opts :surface-config)
        surface-map      (->surface-map opts)
        {:keys [footer]} surface-map
        height           (get footer :height)
        background-color (get-in surface-map [:footer :background-color] "white")]
    [:.surf-footer
     {:background-color background-color
      :height           (px height)
      :width            "100%"
      :z-index          (get-in surface-config [:z-indicies :footer])}]))

(defn- ->footer-fixed [opts]
  (let [surface-map      (->surface-map opts)
        {:keys [footer]} surface-map
        height           (get footer :height)]
    [:&.surf-surface-footer-fixed
     [:.surf-footer
      {:position "fixed"
       :bottom   (px 0)}]

     [:.surf-body
      {:margin-bottom (px height)}]]))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Style

(defn- style [opts]
  [:style
   (css
    [[:body
      {:margin 0}]

     [:.surf-surface

      ;; needs to be above sidebar/modal
      (->dimmer opts)

      (->sidebar-left opts)
      (->sidebar-left-active opts)
      (->sidebar-left-fixed opts)

      (->sidebar-right opts)
      (->sidebar-right-active opts)
      (->sidebar-right-fixed opts)

      (->sidebars-active opts)

      (->modal opts)
      (->modal-active opts)

      (->modal-fullscreen opts)
      (->modal-fullscreen-active opts)


      ;; main

      (->main opts)

      (->header opts)
      (->header-fixed opts)

      (->header-dropdown opts)
      (->header-dropdown-active opts)

      (->navbar opts)
      (->navbar-fixed opts)

      (->navbar-dropdown opts)
      (->navbar-dropdown-active opts)

      (->body opts)

      (->footer opts)
      (->footer-fixed opts)

      ]])])
