# UI Contract

Neste projeto, criou-se uma barreira entre a UI e o core.

Como as interfaces de XML e Compose comunicam com o core:
- Nenhum ecrã da aplicação pode ir à internet diretamente ou tentar aceder ao armazenamento do dispositivo. O trabalho dos ecrãs é só desenhar a UI.
- Sempre que a `app-xml` ou a `app-compose` precisam de mostrar fotografias ou a lista de favoritos, elas só pedem aos ViewModels `MainViewModel` ou `FavoritesViewModel` que estam no :core.
- O :core usa StateFlows, que permite que a aplicação reaja automaticamente às alterações dos dados. Desta forma, sempre que um novo favorito é adicionado à base de dados, o :core avisa automaticamente os ecrãs para atualizarem sozinhos em tempo real.
