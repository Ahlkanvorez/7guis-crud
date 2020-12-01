(ns crud.views.filters
  (:require [reagent.core :as r]
            [crud.users :as users]
            [crud.views.input :as input]))

(defn filter-input [prefix]
  [input/labeled {:label "Filter prefix:"
                  :value @prefix
                  :on-change #(reset! prefix (.. % -target -value))}])

(defn filtered-select [selected-id on-select users prefix]
  [:select
   (merge (when @selected-id {:selected @selected-id})
          {:size (inc (count users))
           :on-change #(on-select (-> % .-target .-value int))
           :style {:flex "0 1"}})
   (for [usr (users/filtered-users users prefix)]
     [:option {:key (:id usr) :value (:id usr)} (users/format usr)])])

(defn filterable-select [{:keys [users selected-id on-select]}]
  (let [prefix (r/atom "")]
    (fn [{:keys [users selected-id on-select]}]
      [:div {:style {:display :flex :flex-direction :column}}
       [filter-input prefix]
       [filtered-select selected-id on-select users @prefix]])))
