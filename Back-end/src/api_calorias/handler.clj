(ns api-calorias.handler
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body wrap-json-response]]
            [ring.util.response :refer [response]]
            [api-calorias.db :as db]
            [api-calorias.apis :as apis]
            [ring.middleware.cors :refer [wrap-cors]]))

(defroutes rotas-da-api

           (POST "/usuario" requisicao
             (let [dados (:body requisicao)
                   usuario-formatado (db/estruturar-usuario (:altura dados) (:peso dados) (:idade dados) (:sexo dados))]
               (db/salvar-usuario! usuario-formatado)
               (response {:mensagem "Usuário salvo com sucesso!"})))

           (GET "/usuario" []
             (response (db/obter-perfil-utilizador)))

           (POST "/alimentos" requisicao
             (let [dados (:body requisicao)
                   nome-alimento (:descricao dados)
                   calorias (apis/buscar-calorias-alimento nome-alimento)
                   nova-transacao (db/estruturar-transacao :alimento nome-alimento calorias (:data dados))]
               (db/adicionar-transacao! nova-transacao)
               (response {:mensagem "Alimento registrado!" :calorias calorias})))

           (POST "/exercicios" requisicao
             (let [dados (:body requisicao)
                   nome-exercicio (:descricao dados)
                   calorias (apis/buscar-calorias-exercicio nome-exercicio)
                   nova-transacao (db/estruturar-transacao :exercicio nome-exercicio calorias (:data dados))]
               (db/adicionar-transacao! nova-transacao)
               (response {:mensagem "Exercício registrado!" :calorias-gastas calorias})))

           (GET "/extrato" []
             (let [todas-transacoes (:transacoes @db/base-de-dados)]
               (response {:extrato todas-transacoes})))

           (GET "/saldo" []
             (let [todas-transacoes (:transacoes @db/base-de-dados)
                   saldo-calculado (db/calcular-saldo todas-transacoes)]
               (response {:saldo-calorico saldo-calculado})))

           (GET "/exercicios" []
             (let [todas-transacoes (:transacoes @db/base-de-dados)
                   apenas-exercicios (db/extrato-por-tipo todas-transacoes :exercicio)]
               (response {:exercicios apenas-exercicios})))

           (GET "/alimentos" []
             (let [todas-transacoes (:transacoes @db/base-de-dados)
                   apenas-alimentos (db/extrato-por-tipo todas-transacoes :alimento)]
               (response {:alimentos apenas-alimentos})))

           (route/not-found "Rota não encontrada na Calculadora!"))

(def app
  (-> rotas-da-api
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :post])
      (wrap-json-body {:keywords? true})
      (wrap-json-response)))