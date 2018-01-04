(defproject re-surface "0.2.0-alpha1"
  :description "This library is an attempt to provide structure to a reagent application at the page level."
  :url "https://github.com/gadfly361/re-surface"
  :license {:name "MIT"}
  :scm {:name "git"
        :url  "https://github.com/gadfly361/re-surface"}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [garden "1.3.2"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/main"]

  :clean-targets ^{:protect false} ["resources/public/js"
                                    "demo/js"
                                    "target"]

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.8.2"]
                   [reagent "0.6.1"]
                   [re-frisk "0.3.1"]
                   [secretary "1.2.3"]]

    :plugins [[lein-figwheel "0.5.10"]]

    :cljsbuild
    {:builds
     [{:id           "dev"
       :source-paths ["src/demo" "src/main"]
       :figwheel     {:on-jsload "re-surface.core-demo/reload"}
       :compiler     {:main                 re-surface.core-demo
                      :optimizations        :none
                      :output-to            "resources/public/js/app.js"
                      :output-dir           "resources/public/js/dev"
                      :asset-path           "js/dev"
                      :source-map-timestamp true
                      :preloads             [devtools.preload]
                      :external-config
                      {:devtools/config
                       {:features-to-install    [:formatters :hints]
                        :fn-symbol              "F"
                        :print-config-overrides true}}}}]}}

   :prod
   {:dependencies [[secretary "1.2.3"]
                   [reagent "0.6.1"]
                   [re-frisk "0.3.1"]]
    :plugins      [[lein-cljsbuild "1.1.4"]]
    :cljsbuild
    {:builds
     [{:id           "min"
       :source-paths ["src/demo" "src/main"]
       :compiler     {:main            re-surface.core-demo
                      :optimizations   :advanced
                      :output-to       "demo/js/app.js"
                      :output-dir      "demo/js/min"
                      :elide-asserts   true
                      :closure-defines {goog.DEBUG false}
                      :pretty-print    false}}]}}})
