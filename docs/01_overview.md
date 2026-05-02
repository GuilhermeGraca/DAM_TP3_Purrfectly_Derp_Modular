# 01 Visão Geral

## Propósito da aplicação

"Purr-fectly Derp" é uma aplicação Android de entretenimento, feita para pesquisar e exibir imagens de alta qualidade de animais em situações engraçadas ou a fazer caretas ("derps"). O propósito é proporcionar uma pausa divertida aos utilizadores, funciona como uma galeria de arte engraçada e fofinha e um "Derp-o-Meter", que atribui uma percentagem fictícia de "Nível de Derp" a cada fotografia.
## Utilizadores alvo
Pessoas que queiram algum entretenimento rápido, amantes de animais e qualquer utilizador que aprecie fotografias de animais e/ou um sentido de humor minimo.

## Ideia geral de funcionamento do sistema
A aplicação liga-se à API pública da Unsplash para pesquisar imagens com o uso de keywords específicas como "funny animal", "derp dog". Estas imagens fotográficas são apresentadas num `Responsive Grid Layout` através de uma `RecyclerView`.

A navegação para o 'Ecrã de Detalhes' permite ao utilizador visualizar informações detalhadas da API Unsplash
e interagir com a da app ao avaliar o 'Nível de Derp' do animal (0-100%).
Esta avaliação é integrada no modelo de dados da aplicação e persistida com uma Room Database, assim, as escolhas do utilizador presistem no seu 'Hall of Fame' pessoal.

Para garantir uma boa User Experience:
* **Favoritos (Hall of Fame):** Os utilizadores podem guardar as suas 5 fotografias de animais favoritas. O sistema utiliza uma gestão de fila FIFO (First-In, First-Out)  — ao adicionar uma 6ª imagem, o favorito mais antigo é automaticamente removido.
* **Cache Offline Inteligente:** A aplicação mantém uma cache local (Room Database) de até 50 itens (excluindo os favoritos). O algoritmo da cache monitoriza a navegação do utilizador, asegura que imagens recentes e futuras estejam disponíveis mesmo sem ligação à Internet.