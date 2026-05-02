# 07 Uso da API

Este ficheiro descreve a API utilizada para obter as imagens dos animais

## API Utilizada
* Unsplash API
* Documentação - https://unsplash.com/documentation

## Endpoint Principal
Para pesquisar as imagens "derp", utilizamos o este endpoint:
`GET /search/photos`

**Parâmetros principais:**
* `query`: search terms / keywords a utilizar, como "funny animal" ou "derp dog"
* `per_page`: Quantidade de imagens por página  (Optional; default: 10).
* `client_id`:Access Key da app

## Formato da Resposta
A API devolve um JSON que contém a lista de resultados.
Cada item da lista representa uma imagem com os seus metadados.

## Exemplo de Resposta JSON (simplificado para o que é utilizado na App)
```json
{
  "total": 133,
  "total_pages": 7,
  "results": [
    {
      "id": "eOLpJytrbsQ",
      "description": "A man drinking a coffee.",
      "urls": {
        "regular": "[https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=92f3e02f63678acc8416d044e189f515](https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=92f3e02f63678acc8416d044e189f515)",
        "thumb": "[https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=8aae34cf35df31a592f0bef16e6342ef](https://images.unsplash.com/photo-1416339306562-f3d12fefd36f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=8aae34cf35df31a592f0bef16e6342ef)"
      }
    }
  ]
}