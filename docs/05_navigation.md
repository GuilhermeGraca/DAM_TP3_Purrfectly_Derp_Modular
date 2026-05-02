# 05 Navegação

Este ficheiro mostra como deve ser o fluxo de navegação entre os ecrãs da app 

## Fluxo Principal
* MainActivity (Mural)
    - clicar num item da grid (RecyclerView) ->  DetailActivity
    - clicar no ícone de "Hall of Fame" na Toolbar -> FavoritesActivity
  
* DetailActivity
    - clicar no botão de "Voltar" (Back arrow) na Toolbar -> volta à MainActivity
    - clicar em "Adicionar aos Favoritos" -> Permanece no ecrã (mostra feedback visual) ou regressa à **MainActivity** (configurável).

* FavoritesActivity (Hall of Fame)
    - clicar numa imagem da lista de favoritos -> DetailActivity
    - clicar no botão de "Voltar" na Toolbar -> volta à MainActivity
