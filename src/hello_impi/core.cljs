(ns hello-impi.core
  (:require [impi.core :as impi]
            [devtools.core :as devtools]))

(enable-console-print!)
(devtools/install!)

(defonce state (atom {}))

(reset!
  state
  {:pixi/renderer
   {:pixi.renderer/size             [js/window.innerWidth js/window.innerHeight]
    :pixi.renderer/background-color 0xbbbbbb
    :pixi.renderer/transparent?     false}
   :pixi/stage
   {:impi/key         :stage
    :pixi.object/type :pixi.object.type/container
    :pixi.container/children
                      (sorted-map
                        :a
                        {:impi/key :performance
                         :pixi.object/type :pixi.object.type/container
                         :pixi.container/children
                         (vec (for [i (range 50), j (range 50)]
                                {:impi/key             (keyword (str "bunny" i "_" j))
                                 :pixi.object/type     :pixi.object.type/sprite
                                 :pixi.object/position [(+ 40 (* 30 i)) (+ 40 (* 40 j))]
                                 :pixi.object/rotation 0.0
                                 :pixi.sprite/anchor   [0.5 0.5]
                                 :pixi.sprite/texture  (if (= (mod i 2) 0)
                                                         {:pixi.texture/source "img/bunny.png"}
                                                         {:pixi.texture/source "img/bunnyblink.png"})}))})}})

(defn- rotate-children [children]
  (for [child children]
    (-> child
        (update :pixi.object/rotation + 0.1)
        (update :pixi.sprite/texture
                (fn [v] (if (= v {:pixi.texture/source "img/bunny.png"})
                            {:pixi.texture/source "img/bunnyblink.png"}
                            {:pixi.texture/source "img/bunny.png"}))))))

(defn animate [state]
  (swap! state
         update-in [:pixi/stage :pixi.container/children :a :pixi.container/children]
         rotate-children)
  (js/requestAnimationFrame #(animate state)))

(let [element (.getElementById js/document "app")]
  (impi/mount :example @state element)
  (add-watch state ::mount (fn [_ _ _ s] (impi/mount :example s element))))

(defonce x (animate state))
