# 08 Plano de Implementação

**Passo 1 (Refactoring Inicial)**
Renomear a app original para `app-xml`.

**Passo 2 (Módulo Core)**
Criar o módulo de biblioteca Android `:core`. Mover todas as classes de modelo, base de dados (Room), DAOs e Repositório do módulo `:app-xml` para o `:core`.

**Passo 3 (Integração Core-XML)**
Adicionar a dependência do módulo `:core` ao módulo `:app-xml`. Refatorar a app XML para consumir corretamente o repositório isolado e garantir que compila e funciona exatamente como antes.

**Passo 4 (Módulo Compose)**
Criar um novo módulo Android `:app-compose` vazio configurado com Jetpack Compose. Adicionar a dependência do módulo `:core`.

**Passo 5 (Desenvolvimento UI Compose)**
Reconstruir a interface do utilizador de forma declarativa usando composables. Criar os ViewModels usando State e fluxos reativos adequados para o Compose.

**Passo 6 (Feature Exclusiva Compose)**
Adicionar ao módulo `:app-compose` as funcionalidades exclusivas: implementação nativa de **Animações e Transições** fluidas entre elementos e ecrãs, além da implementação do **Dynamic Theming (Light/Dark Mode com suporte a Material You)**. Estas features vão diferenciar tecnicamente o módulo declarativo em relação ao módulo clássico em XML.