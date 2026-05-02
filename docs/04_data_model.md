# 04 Data Model

Este documento tem as estruturas de dados utilizadas pela app

## 1. Unsplash API Models
JSON devolvido pela Unsplash API

### UnsplashResponse
* total: Int (nr total de resultados)
* results: List<UnsplashImage> (lista de imagens)

### UnsplashImage
* id: String 
* description: String? (Descrição opcional da imagem)
* alt_description: String? (Descrição opcional alternativa)
* urls: UnsplashUrls (Objeto com diferentes tamanhos de imagem)
* user: UnsplashUser (Informação do fotógrafo)

### UnsplashUrls
* regular: String (URL para imagem de alta qualidade - Detalhes)
* small: String (URL para miniatura - Grid)

### UnsplashUser
* name: String (Nome completo do fotógrafo)

## 2. Room Database Entity
Esta classe define a tabela "favorites" na base de dados local

### FavoriteDerp
* id: String (Primary Key - ID da Unsplash)
* imageUrl: String (URL da img guardada)
* photographerName: String (Nome do autor)
* description: String? (Descrição da imagem)
* derpLevel: Int (O valor de 0-100 definido pelo utilizador no Slider)
* timestamp: Long (Data de inserção - para a lógica FIFO)

## 3. Cache Model
A cache de 50 itens utilizará uma estrutura parecida à FavoriteDerp, mas numa tabela separada chamada `image_cache`, para dar acesso offline 