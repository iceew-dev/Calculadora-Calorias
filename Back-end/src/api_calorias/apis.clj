(ns api-calorias.apis
  (:require [clj-http.client :as http-client]
            [cheshire.core :refer [parse-string]]))

(def chave-exercicios (System/getenv "CHAVE_API"))

(defn buscar-calorias-alimento [nome-do-alimento]
  (-> (http-client/get "https://world.openfoodfacts.org/cgi/search.pl"
                       {:query-params {"search_terms" nome-do-alimento
                                       "search_simple" "1"
                                       "action" "process"
                                       "json" "1"}})
      (:body)
      (parse-string true)
      (get-in [:products 0 :nutriments :energy-kcal_100g])))

(defn buscar-calorias-exercicio [nome-do-exercicio]
  (-> (http-client/get "https://api.api-ninjas.com/v1/caloriesburned"
                       {:headers {"X-Api-Key" chave-exercicios}
                        :query-params {"activity" nome-do-exercicio}})
      (:body)
      (parse-string true)
      (first)
      (:total_calories)))