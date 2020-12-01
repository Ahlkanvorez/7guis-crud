(ns crud.users
  (:require [clojure.string :as s]))

(defn create-user
  "Give `working-user` a unique id and store it in `users`.
  The new user will have a user :id greater than any other extant user,
  with the :first and :last name given by `working-user`."
  [users working-user]
  (let [new-id (inc (apply max (keys users)))
        new-user (assoc working-user :id new-id)]
    (assoc users new-id new-user)))

(defn update-user
  "Rename the user in `users` specified by & according to `working-user`.
  The user with the `working-user`'s id will be renamed to have the
  same first & last name as `working-user`."
  [users working-user]
  (assoc users (:id working-user) working-user))

(defn delete-user
  "Delete the user with the `working-user`'s id from the `users` map."
  [users working-user]
  (dissoc users (:id working-user)))

(defn surname-starts-with?
  "Check whether the `user`'s :last name starts with the `prefix`."
  [prefix user]
  (-> (get user :last "") (s/starts-with? prefix)))

(defn filtered-users
  "Return a seq of `users` who pass surname-starts-with? for `prefix`"
  [users prefix]
  (->> (vals users)
       (sort-by :last)
       (filter #(surname-starts-with? prefix %))))

(defn format
  "Stringify the `user` in the format \"Surname, First name\"."
  [user]
  (str (:last user) ", " (:first user)))
