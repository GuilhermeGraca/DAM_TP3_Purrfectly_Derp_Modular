# 02 Features

Abaixo estão as funcionalidades principais da app "Purr-fectly Derp", garante o cumprimento de todos os requisitos funcionais documentados.

1. Uso da API Unsplash - Obter imagens de animais engraçado/fofos com keyword do tipo: "funny animal", "derp dog". Extrai também metadados relevantes, como por exemplo o nome do fotógrafo e descrição.

2. Mural Derp (Image Grid) - Apresentar as imagens obtidas de forma responsiva numa grid, com uma `RecyclerView`

3. Atualização de conteudo (Refresh) - Permite ao utilizador carregar novas imagens de forma simples ao fazer um *Swipe-to-Refresh* ou de um botão de atualização dedicado

4. Indicador de Carregamento (Loading Indicator) - Apresenta um loading circle/throbber sempre que a aplicação estiver a comunicar com a API ou a carregar dados locais

5. Ecrã de Detalhes interativo - Um ecrã focado na imagem selecionada que mostra os dados da Unsplash. Inclui uma interface interativa,como um *Slider* onde o utilizador  avalia o "Nível de Derp" de 0% a 100% daquele animal

6. Favoritos (Hall of Fame) via FIFO - Sistema de favoritos acessível diretamente a partir de qualquer ecrã através das imagens. É gerido por uma fila estrita FIFO (*First-In, First-Out*) com um limite máximo de 5 items. Ao adicionar o 6º item remove o mais antigo

7. Cache Offline Inteligente - Sistema de cache local (Room Database) que armazena até 50 itens, excluindo os favoritos. A lógica da cache mantém pelo menos 10 itens à frente e 10 itens atrás da posição atual de navegação do utilizador

8. Acesso Offline - Capacidade total de visualizar os itens armazenados na cache e consultar o "Hall of Fame"/Favoritos mesmo sem ligação à Internet

9. Tratamento de Erros - Gestão de falhas de rede ou erros de API, apresentando mensagens de erro amigáveis ao utilizador ou ao recorrer aos dados da cache local

10. Arquitetura MVVM - Separação estrutural clara com o uso do padrão *Model-View-ViewModel*