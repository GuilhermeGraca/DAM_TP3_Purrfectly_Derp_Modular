# 08 Plano de Implementação

**Passo 1**
Analisar a estrutura do projeto Android existente (Empty Views). Adicionar a permissão de Internet no `AndroidManifest.xml` e configurar as dependências necessárias para a API e JSON no `build.gradle`.

**Passo 2**
Criar a class do modelo de dados para representar as imagens recebidas da API do Unsplash.

**Passo 3**
Implementar o serviço de API (repositório) para obter as imagens do Unsplash.

**Passo 4**
Criar o adapter para a RecyclerView.

**Passo 5**
Desenhar o layout `activity_main.xml` (incluindo a RecyclerView e o botão de refresh).

**Passo 6**
Criar o ViewModel e conectá-lo ao repositório da API.

**Passo 7**
Apresentar as imagens na RecyclerView no ecrã principal.

**Passo 8**
Adicionar a funcionalidade da ação de refresh (recarregar imagens).