# Refactoring Plan

Os passos executados para extrair a lógica e criar a nova arquitetura partilhada foram:

* Atualizou-se a documentação:
    - Modificaram-se os ficheiros na pasta `docs/` para guiar o trabalho de desenvolvimento ao longo do projeto e ter uma single source of truth.

* Criação do módulo app-xml:
    - Renomeou-se o módulo principal antigo para `:app-xml`.
    - Com o auxílio da AI, refatorou-se o código da app antiga, sendo que o projeto começou por ser um clone do projeto antigo do TP2.

* Criou-se o módulo Core:
    - Adicionou-se um módulo Android do tipo "Biblioteca" chamado `:core` para armazenar a lógica de regras e de dados de forma central.

* Criação do módulo app-compose:
    - Adicionou-se o novo módulo `:app-compose` vazio configurado com Jetpack Compose, que usa o mesmo `:core` para alimentar a nova interface moderna e reativa.

* Separação de responsabilidades do projeto antigo no módulo :core:
    - Pediu-se à AI para ir implementando os passos definidos no ficheiro `08_implementation_plan.md`, de forma a refatorar o projeto antigo e passar a lógica de dados para uma base de dados Room dentro do core.
    - Moveu-se o código que faz os pedidos de rede à API do Unsplash (Retrofit, clientes e modelos de imagem) para o módulo `:core`.
    - Apagaram-se as configurações antigas do `build.gradle` da app original e configurou-se a app para ir buscar os dados apenas do módulo `:core`.

* Separação de responsabilidades no módulo :app-compose:
    - Pediu-se à AI para implementar os passos finais do plano de construir a interface gráfica totalmente separada da logica do modulo :core.
    - Implementaram-se ViewModels independentes com o jetpack compose, assim, os ecrãs só tem a responsabilidade de mostrar dados.
    - Implementaram-se novas funcionalidades visuais exclusivas deste módulo - transições, "swipe", tema escuro e claro, janela deslizante de detalhes.
    
