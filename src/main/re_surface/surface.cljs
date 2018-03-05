(ns re-surface.surface
  (:require
   [cljs.spec.alpha :as spec]
   [clojure.string :as string]
   [re-surface.style :as style]
   [re-surface.z-index :as z-index]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Spec

(spec/def ::surface-key keyword?)

(spec/def ::opts
  (spec/keys :req-un [::surface-key]
             :opt-un [::app-state]))


(spec/def ::key keyword?)
(spec/def ::background-color string?)
(spec/def ::height int?)
(spec/def ::width int?)
(spec/def ::top int?)
(spec/def ::left int?)
(spec/def ::bottom int?)
(spec/def ::right int?)
(spec/def ::active? boolean?)
(spec/def ::full-width? boolean?)
(spec/def ::fixed? boolean?)


(spec/def ::header
  (spec/keys :req-un [::key]
             :opt-un [::height
                      ::fixed?
                      ::background-color]))

(spec/def ::header-dropdown
  (spec/keys :opt-un [::key
                      ::active?
                      ::background-color
                      ::height
                      ::width
                      ::top
                      ::left
                      ::bottom
                      ::right
                      ::full-width?]))

(spec/def ::navbar
  (spec/keys :req-un [::key]
             :opt-un [::fixed?
                      ::height
                      ::background-color]))

(spec/def ::navbar-dropdown
  (spec/keys :opt-un [::key
                      ::active?
                      ::background-color
                      ::height
                      ::width
                      ::top
                      ::left
                      ::bottom
                      ::right]))

(spec/def ::body
  (spec/keys :req-un [::key]
             :opt-un [::background-color]))

(spec/def ::footer
  (spec/keys :req-un [::key]
             :opt-un [::fixed?
                      ::height
                      ::background-color]))

(spec/def ::sidebar-left
  (spec/keys :req-un [::key
                      ::width]
             :opt-un [::active?
                      ::fixed?
                      ::background-color]))

(spec/def ::sidebar-right
  (spec/keys :req-un [::key
                      ::width]
             :opt-un [::active?
                      ::fixed?
                      ::background-color]))

(spec/def ::dimmer
  (spec/keys :req-un [::key]))


(spec/def ::modal
  (spec/keys :req-un [::width]
             :opt-un [::key
                      ::active?
                      ::height
                      ::background-color]))

(spec/def ::modal-fullscreen
  (spec/keys :opt-un [::key
                      ::active?
                      ::background-color]))


(spec/def ::surface-map
  (spec/keys :opt-un [::header
                      ::header-dropdown
                      ::navbar
                      ::navbar-dropdown
                      ::body
                      ::footer
                      ::sidebar-left
                      ::sidebar-right
                      ::dimmer
                      ::modal
                      ::modal-fullscreen]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Prepre opts

(defn- prepare-opts [opts-raw]
  (let [surface-config         (get opts-raw :surface-config)
        {:keys [style-component
                debug?
                spec?
                z-indicies]}   surface-config
        surface-config-updated {:style-component (or style-component style/style)
                                :debug?          (if (nil? debug?) false debug?)
                                :spec?           (if (nil? spec?) true spec?)
                                :z-indicies      (or z-indicies z-index/indicies)}]
    (assoc opts-raw :surface-config surface-config-updated)))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Surface

(defn- default-comp [app-state]
  [:div {:style {:height "100%"
                 :width  "100%"}}])

(defn- surface [opts-raw]
  (let [opts (prepare-opts opts-raw)

        {:keys [app-state
                surface-key
                surface-registry
                component-registry
                surface-config]} opts

        {:keys [style-component
                debug?
                spec?
                z-indicies]}   surface-config

        {:keys [header
                header-dropdown
                navbar
                navbar-dropdown
                body
                footer

                dimmer
                sidebar-left
                sidebar-right
                modal
                modal-fullscreen]
         :as surface-map} (get surface-registry surface-key)

        _ (when (and spec?
                     ;; to prevent spec failure on page redirect
                     surface-key)
            (spec/check-asserts true)
            (spec/assert ::opts opts)
            (spec/assert ::surface-map surface-map))

        header-fixed? (get header :fixed?)
        header-key    (get header :key)
        header-comp   (get-in component-registry [:header header-key])

        header-dropdown-active? (get header-dropdown :active?)
        header-dropdown-full-width? (get header-dropdown :full-width?)
        header-dropdown-key    (get header-dropdown :key)
        header-dropdown-comp   (get-in component-registry [:header-dropdown header-dropdown-key] default-comp)

        navbar-fixed? (get navbar :fixed?)
        navbar-key    (get navbar :key)
        navbar-comp   (get-in component-registry [:navbar navbar-key])

        navbar-dropdown-active? (get navbar-dropdown :active?)
        navbar-dropdown-full-width? (get navbar-dropdown :full-width?)
        navbar-dropdown-key    (get navbar-dropdown :key)
        navbar-dropdown-comp   (get-in component-registry [:navbar-dropdown navbar-dropdown-key] default-comp)

        body-key  (get body :key)
        body-comp (get-in component-registry [:body body-key])

        footer-fixed? (get footer :fixed?)
        footer-key    (get footer :key)
        footer-comp   (get-in component-registry [:footer footer-key])

        dimmer-key  (get dimmer :key)
        dimmer-comp (get-in component-registry [:dimmer dimmer-key])

        sidebar-left-fixed?  (get sidebar-left :fixed?)
        sidebar-left-active? (get sidebar-left :active?)
        sidebar-left-key     (get sidebar-left :key)
        sidebar-left-comp    (get-in component-registry [:sidebar-left sidebar-left-key])

        sidebar-right-fixed?  (get sidebar-right :fixed?)
        sidebar-right-active? (get sidebar-right :active?)
        sidebar-right-key     (get sidebar-right :key)
        sidebar-right-comp    (get-in component-registry [:sidebar-right sidebar-right-key])

        modal-active?    (get modal :active?)
        modal-key        (get modal :key)
        modal-comp       (get-in component-registry [:modal modal-key] default-comp)

        modal-fullscreen-active?    (get modal-fullscreen :active?)
        modal-fullscreen-key        (get modal-fullscreen :key)
        modal-fullscreen-comp       (get-in component-registry [:modal-fullscreen modal-fullscreen-key] default-comp)]
    [:div
     [style-component opts]
     [:div.surf-surface
      {:class
       (string/join " "
                    (remove nil?
                            [
                             (when (and
                                    header-comp
                                    header-fixed?)
                               "surf-surface-header-fixed")

                             (when (and
                                    header-dropdown-comp
                                    header-dropdown-active?)
                               "surf-surface-header-dropdown-active")

                             (when (and
                                    header-dropdown-comp
                                    header-dropdown-full-width?)
                               "surf-surface-header-dropdown-full-width")

                             (when (and
                                    navbar-comp
                                    navbar-fixed?)
                               "surf-surface-navbar-fixed")

                             (when (and
                                    navbar-dropdown-comp
                                    navbar-dropdown-active?)
                               "surf-surface-navbar-dropdown-active")

                             (when (and
                                    navbar-dropdown-comp
                                    navbar-dropdown-full-width?)
                               "surf-surface-navbar-dropdown-full-width")


                             (when (and
                                    footer-comp
                                    footer-fixed?)
                               "surf-surface-footer-fixed")


                             (when (and
                                    sidebar-left-comp
                                    sidebar-left-active?)
                               "surf-surface-sidebar-left-active")
                             (when (and
                                    sidebar-right-comp
                                    sidebar-right-active?)
                               "surf-surface-sidebar-right-active")

                             (when (and
                                    sidebar-left-comp
                                    sidebar-left-fixed?)
                               "surf-surface-sidebar-left-fixed")
                             (when (and
                                    sidebar-right-comp
                                    sidebar-right-fixed?)
                               "surf-surface-sidebar-right-fixed")


                             (when (and
                                    modal-comp
                                    modal-active?)
                               "surf-surface-modal-active")

                             (when (and
                                    modal-fullscreen-comp
                                    modal-fullscreen-active?)
                               "surf-surface-modal-fullscreen-active")
                             ]))}

      (when sidebar-left-comp
        [:div.surf-sidebar-left
         (when debug? {:style {:background-color "aqua"}})
         [sidebar-left-comp app-state]])

      (when sidebar-right-comp
        [:div.surf-sidebar-right
         (when debug? {:style {:background-color "aqua"}})
         [sidebar-right-comp app-state]])

      ;; this dimmer is for everything but dropdowns
      (when dimmer-comp
        [:div.surf-dimmer
         [dimmer-comp app-state]])

      (when modal-comp
        [:div.surf-modal
         [modal-comp app-state]])

      (when modal-fullscreen-comp
        [:div.surf-modal-fullscreen
         [modal-fullscreen-comp app-state]])


      ;; MAIN
      [:div.surf-main

       ;; this dimmer is for dropdowns
       (when dimmer-comp
         [:div.surf-dimmer-dropdown
          [dimmer-comp app-state]])

       ;; navbar dropdown if header fixed and not navbar
       (when (and navbar-comp
                  navbar-dropdown-comp
                  header-fixed?
                  (not navbar-fixed?))
         [:div.surf-navbar-dropdown
          [navbar-dropdown-comp app-state]])

       (when header-comp
         [:div.surf-header
          (when debug? {:style {:background-color "grey"}})
          [header-comp app-state]])

       (when navbar-comp
         [:div.surf-navbar
          (when debug? {:style {:background-color "lightgrey"}})
          [navbar-comp app-state]])

       ;; navbar dropdown if header isn't fixed
       (when (and navbar-comp
                  navbar-dropdown-comp
                  (or (not header-fixed?)
                      navbar-fixed?))
         [:div.surf-navbar-dropdown
          [navbar-dropdown-comp app-state]])

       (when (and header-comp
                  header-dropdown-comp)
         [:div.surf-header-dropdown
          [header-dropdown-comp app-state]])

       (when body-comp
         [:div.surf-body
          (when debug? {:style {:background-color "orange"}})
          [body-comp app-state]])

       (when footer-comp
         [:div.surf-footer
          (when debug? {:style {:background-color "purple"}})
          [footer-comp app-state]])]
      ]]))
