## Calculadora de Calorias
Aplicação cliente-servidor para controle de balanço calórico diário.

## Sobre o projeto
* O back-end é uma API REST desenvolvida em Clojure, responsável por armazenar dados pessoais do usuário, registrar transações calóricas e calcular o saldo. As calorias dos alimentos são obtidas automaticamente via Open Food Facts e as calorias de atividades físicas via API Ninjas.
* O front-end é uma aplicação web desenvolvida em HTML, CSS e JavaScript puro, que se comunica com a API via HTTP usando o formato JSON.

## Funcionalidades

* Cadastro de dados pessoais (altura, peso, idade e sexo)
* Registro de alimentos consumidos com busca automática de calorias
* Registro de atividades físicas com cálculo automático de calorias gastas
* Consulta de extrato de transações por período
* Consulta de saldo calórico por período

## Pré-requisitos

Leiningen 2.0.0 ou superior instalado

[leiningen]: https://github.com/technomancy/leiningen

## Executar

para rodar o projeto:

Terminal 1 — subir a API (Back-end):

* cd Calculadora-calorias/Back-end
* lein ring server

Terminal 2 — servir o Front-end:

* cd Calculadora-calorias/Front-end
* python3 -m http.server 8080

Depois é só acessar no navegador:
http://localhost:8080/Interface calculadora.html

## Tecnologias

* Clojure + Compojure + Ring
* HTML, CSS e JavaScript
* Open Food Facts API
* API Ninjas

## Membros

* Caio Victor Ferreira Da Silva - 2010224
* Brenno Damiany Castro Vidal - 2315088


## License

Copyright © 2026 FIXME
