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