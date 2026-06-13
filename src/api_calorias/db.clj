(ns api-calorias.db)

(def base-de-dados (atom {:usuario {}
                          :transacoes []}))

(defn estruturar-usuario [altura peso idade sexo]
  {:altura altura
   :peso peso
   :idade idade
   :sexo sexo})

(defn estruturar-transacao [tipo descricao calorias data]
  {:tipo tipo
   :descricao descricao
   :calorias calorias
   :data data})

(defn salvar-usuario! [dados-do-usuario]
  (swap! base-de-dados assoc :usuario dados-do-usuario))

(defn adicionar-transacao! [nova-transacao]
  (swap! base-de-dados update :transacoes conj nova-transacao))

(defn extrato-por-tipo [lista-de-transacoes tipo-desejado]
  (filter (fn [transacao](= (:tipo transacao) tipo-desejado)) lista-de-transacoes))

(defn calcular-saldo [lista-de-transacoes]
  (reduce (fn [saldo-acumulado transacao]
            (if (= (:tipo transacao) :alimento)
              (+ saldo-acumulado (:calorias transacao))
              (- saldo-acumulado (:calorias transacao))))
          0 lista-de-transacoes))
