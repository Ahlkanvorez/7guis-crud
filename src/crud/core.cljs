(ns crud.core
  (:require [reagent.core :as r]
            [reagent.dom :as rd]
            [crud.views :as views]))

(defn initial-state []
  {1 {:id 1 :first "Hans" :last "Emil"}
   2 {:id 2 :first "Max" :last "Mustermann"}
   3 {:id 3 :first "Roman" :last "Tisch"}})

(defn mount-root []
  (rd/render [views/crud (r/atom (initial-state))]
             (js/document.getElementById "app-root")))
