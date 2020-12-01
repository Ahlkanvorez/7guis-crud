(ns crud.views.names
  (:require [crud.views.input :as input]))

(defn name-input [working-user label name]
  [input/labeled
   {:label label
    :value (get @working-user name)
    :on-change
    #(swap! working-user assoc name (.. % -target -value))}])

(defn user-input [working-user]
  [:div {:style {:display :flex :flex "0 1"}}
   [:div {:style {:display :flex
                  :flex-direction :column
                  :justify-content :flex-end
                  :margin-left "15px"
                  :flex "1 0"}}
    [name-input working-user "Name:" :first]
    [name-input working-user "Surname:" :last]]])
