(defproject hello-impi "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/clojurescript "1.9.908"]
                 [org.clojure/core.async  "0.3.443"]
                 [impi "0.0.10"]
                 [binaryage/devtools "0.9.4"]]

  :plugins [[lein-figwheel "0.5.13"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]]

  :cljsbuild
  {:builds
   {:main
    {:figwheel     true
     :source-paths ["src"]
     :compiler     {:main hello-impi.core
                    :asset-path "js/out"
                    :output-to "resources/public/js/main.js"
                    :output-dir "resources/public/js/out"
                    :optimizations :none}}}}
  :figwheel
  {:http-server-root "public"
   :server-port      3001
   :css-dirs         ["resources/public/css"]})
