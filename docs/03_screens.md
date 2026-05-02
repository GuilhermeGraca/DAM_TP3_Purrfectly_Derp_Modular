# 03 Screens

## Screen 1 - Main Screen

Componentes:
* Toolbar - Contém o título da aplicação ("Purr-fectly Derp") e um ícone de menu (ex: estrela ou troféu) para navegar para os Favoritos.
* SwipeRefreshLayout - Envolve a grelha principal para permitir a atualização da lista com o gesto de puxar para baixo.
* RecyclerView - O componente principal, configurado com um `GridLayoutManager` para apresentar as fotografias dos animais numa grelha elegante.
* ProgressBar - Um indicador de carregamento circular no centro do ecrã (ou no fundo da lista) que fica visível enquanto os dados da Unsplash API estão a ser obtidos.
* Error TextView - Uma mensagem de texto (escondida por defeito) que aparece caso haja falha de rede e a cache esteja vazia, com um botão "Tentar Novamente".

## Screen 2: Image Details Screen (Derp-o-Meter)
Abre quando o utilizador clica numa imagem na grelha. Serve para ver os detalhes da API e interagir com a aplicação.

Componentes:
* Toolbar - Contém um botão de voltar (Back arrow) para regressar ao ecrã anterior.
* ImageView - Uma imagem em destaque (ecrã inteiro ou grande proporção) a carregar a foto em alta resolução.
* Metadata TextViews - Campos de texto que mostram os dados reais obtidos da Unsplash (Nome do Fotógrafo e Descrição/Localização da imagem ou outros).
* Slider - A ferramenta interativa do "Derp-o-Meter". Permite ao utilizador arrastar de 0 a 100% para definir o quão cómica a foto do animal.
* Dynamic TextView - Mostra o valor atual selecionado no Slider
* FloatingActionButton (ou Botão normal) - Botão "Adicionar ao Hall of Fame" para guardar a imagem, os metadados e o Nível de Derp na base de dados Room (com a regra FIFO de 5 itens).

## Screen 3: Favorites Screen / Hall of Fame
Acedido pelo menu da Toolbar. Mostra as fotos favoritas do user (guardada offline).

Componentes:
* Toolbar - Título "Hall of Fame" e botão de voltar.
* RecyclerView - Uma lista ou grelha configurada para mostrar um máximo de 5 itens. Cada item na lista deve mostrar a miniatura da imagem e, de forma destacada, o "Nível de Derp" que o utilizador lhe atribuiu no Ecrã de Detalhes
* Empty State TextView - Mensagem do tipo: "O teu Hall of Fame está vazio" que só aparece se a base de dados não tiver favoritos guardados