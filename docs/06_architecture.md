# 06 Arquitetura

## Padrão de Desenho
A aplicação utiliza o padrão MVVM (Model-View-ViewModel)

## Camadas da App 

View/UI → ViewModel → Repository → API Service/Model

* View / UI
    - Atividades e ficheiros XML
    - Mostra os dados ao utilizador e recebe os cliques.


* ViewModel
    - Faz a ponte entre os dados e a interface
    - Guarda o estado do ecrã, como a lista de animais que estamos a ver
    - Decide quando pedir dados novos.

* Repository
    - É onde tem toda a logica que decide de onde vêm os dados
    - Se houver internet, pede à API.
    - Se estiver offline, vai buscar à base de dados local / ROOM DB

* Model
    - Online: API Unsplash de onde vêm as fotos novas
    - Local: Room Database que guarda os 5 favoritos (FIFO) e a cache de 50 items

## Estrutura Multi-Módulo (MIP-3)
Para promover a reutilização de código e separação de responsabilidades, o projeto encontra-se dividido em 3 módulos principais:

* **:core (Shared Module)**
    - Contém os Data Models (data classes).
    - API Client (Retrofit/Ktor).
    - Repository layer (caching e Room database).
    - Casos de Uso / Business Logic.

* **:app-xml (Legacy App)**
    - Utiliza o módulo :core.
    - Interface tradicional construída com XML Layouts, Activities e Fragments.

* **:app-compose (Modern App)**
    - Utiliza o módulo :core.
    - Interface baseada em Jetpack Compose.
    - Implementa UI declarativa e partilha a mesma lógica de negócio.