(ns crud.views.input)

(defn labeled [{:keys [label value on-change]}]
  [:div {:style {:display :flex}}
   [:label {:style {:flex "1 0" :margin-right "10px"}} label]
   [:input {:type :text
            :style {:flex "1 0"}
            :value value
            :on-change on-change}]])
