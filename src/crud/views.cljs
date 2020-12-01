(ns crud.views
  (:require [reagent.core :as r]
            [crud.users :as users]
            [crud.views.filters :refer [filterable-select]]
            [crud.views.names :refer [user-input]]))

(defn operations [users working-user selected-id]
  [:div {:display :flex}
   [:input {:type :button
            :value :Create
            :disabled (or (empty? (:last working-user))
                          (empty? (:first working-user)))
            :on-click #(swap! users users/create-user working-user)}]
   [:input {:type :button
            :value :Update
            :disabled (nil? selected-id)
            :on-click #(swap! users users/update-user working-user)}]
   [:input {:type :button
            :value :Delete
            :disabled (nil? selected-id)
            :on-click #(swap! users users/delete-user working-user)}]])

(defn crud [users]
  (let [working-user (r/atom {:first "" :last ""})
        selected-id (r/atom nil)
        on-select #(do (reset! selected-id %)
                       (reset! working-user
                           (into {} (get @users % {:first "" :last ""}))))]
    (fn [users]
      [:div {:style {:display :flex :flex-direction :column}}
       [:div {:style {:display :flex}}
        [filterable-select {:users @users
                            :on-select on-select
                            :selected-id selected-id}]
        [user-input working-user]]
       [operations users @working-user @selected-id]])))
