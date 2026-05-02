## Prompt 1 (Passo 1)

**Goal:**
Reestruturar o projeto Android antigo para a nova arquitetura multi-módulo pedida no MIP-3

**Prompt used:**
Quero que faças uma refatorização deste projeto para a versão modular. Para começar, altera a pasta da aplicação atual para o módulo `app-xml` e atualiza as configurações do Gradle para que este módulo funcione corretamente sem sobrescrever a aplicação antiga

**Result:**
A Ia conseguiu fazer a refatorização do projeto para a versão modular. Criou o modulo pedido e moveu o codigo da app antiga para ele. Ele tambem fez as alterações necessárias nos ficheiros Gradle para que este módulo funcione corretamente sem sobrescrever a aplicação antiga

## Prompt 2

**Goal:**
Configurar o módulo de biblioteca `:core`. Mover a lógica de base de dados e de acesso à API para separar as responsabilidades do projeto.

**Prompt used:**
Lê todos os ficheiros Markdown na pasta docs/ para ganhares contexto sobre a arquitetura (MVVM multi-módulo), os modelos de dados e as features exclusivas desta aplicação.

Quero agora que te foques no docs/08_implementation_plan.md e implementar exclusivamente o Passo 2.

Regras:

- Não avances para o passo seguinte sem a minha autorização.
- Se precisares de mover ficheiros entre módulos, certifica-te de que as dependências no build.gradle.kts e os imports ficam corretos.

Podes começar a executar o Passo 2

**Result:**
A IA moveu as pastas de API, Base de dados, Repositórios e Modelos do módulo antigo para o novo módulo `:core`. Configurou as dependências necessárias no build.gradle.kts do `:core` e ligou este módulo à aplicação antiga.

## Prompt 3

**Goal:**
Garantir que a aplicação antiga `:app-xml` usa bem o módulo partilhado `:core`.

**Prompt used**:
Lê todos os ficheiros Markdown na pasta docs/ para ganhares contexto sobre a arquitetura (MVVM multi-módulo), os modelos de dados e as features exclusivas desta aplicação.

Quero agora que te foques no docs/08_implementation_plan.md e implementar exclusivamente o Passo 3.

Regras:

- Não avances para o passo seguinte sem a minha autorização.
- Se precisares de mover ficheiros entre módulos, certifica-te de que as dependências no build.gradle.kts e os imports ficam corretos.

Podes começar a executar o Passo 3


**Result:**
A IA verificou que as dependências já tinham sido definidas e validou que o módulo `:app-xml` importa tudo bem do `:core`. O projeto compila e funciona sem erros.

## Prompt 4 (Módulo Compose)

**Goal:**
Configurar o módulo `:app-compose` vazio preparado para Jetpack Compose e ligado ao módulo partilhado `:core`.

**Prompt used**:
Lê todos os ficheiros Markdown na pasta docs/ para ganhares contexto sobre a arquitetura (MVVM multi-módulo), os modelos de dados e as features exclusivas desta aplicação.

O teu objetivo agora é focar-te no docs/08_implementation_plan.md e implementar exclusivamente o Passo 4.

Regras:

- Não avances para o passo seguinte sem a minha autorização.
- Se precisares de mover ficheiros entre módulos, certifica-te de que as dependências no build.gradle.kts e os imports ficam corretos.

Podes começar a executar o Passo 4

**Result:**
A IA converteu o módulo `:app-compose` de Library para Application, adicionou o plugin do Compose Compiler, configurou o BuildFeatures para Compose e adicionou todas as bibliotecas de UI e Material 3 no `build.gradle.kts`. Criou o `AndroidManifest.xml` base para a nova app

## Prompt 5

**Goal:**
Criar a base do UI Declarativo e preparar os ViewModels no `app-compose`.

**Prompt used**:
Lê todos os ficheiros Markdown na pasta docs/ para ganhares contexto sobre a arquitetura (MVVM multi-módulo), os modelos de dados e as features exclusivas desta aplicação.

O teu objetivo agora é focar-te no docs/08_implementation_plan.md e implementar exclusivamente o Passo 5.

Regras:

- Não avances para o passo seguinte sem a minha autorização.
- Se precisares de mover ficheiros entre módulos, certifica-te de que as dependências no build.gradle.kts e os imports ficam corretos.

Podes começar a executar o Passo 5

**Result:**
A IA criou a `MainActivity` base em Compose e o sistema de "Dynamic Theming" que herda as cores originais da App XML mas agora suporta cores dinâmicas. Foram recriados os `MainViewModel` e `FavoritesViewModel` adaptando a base de dados e a API para usarem `StateFlow`.

## Prompt 6 (Passo 5 - Ecrãs Visuais)

**Goal:**
Criar os Ecrãs Visuais no `app-compose`.

**Prompt used**:
Lê todos os ficheiros Markdown na pasta docs/ para ganhares contexto sobre a arquitetura (MVVM multi-módulo), os modelos de dados e as features exclusivas desta aplicação.

O teu objetivo agora é focar-te no docs/08_implementation_plan.md e implementar exclusivamente a continuação do passo 5 - criação de ecras visuais.

Regras:

- Não avances para o passo seguinte sem a minha autorização.
- Se precisares de mover ficheiros entre módulos, certifica-te de que as dependências no build.gradle.kts e os imports ficam corretos.

Podes começar continuação do passo 5 - criação de ecras visuais.

**Result:**
A IA instalou a biblioteca `coil-compose` para carregar de forma assíncrona as imagens e construiu os 3 ecrãs visuais principais - Derp Grid Screen, Favorites Screen e Derp-o-Meter. Ligou a navegação base destes ecrãs na `MainActivity`.

## Prompt 7 (Correção de Erro de inicialização do app-compose)

**Goal:**
Corrigir o problema que impedia a inicialização do `app-compose`.

**Prompt used**:
Estou a tentar compilar a app para o modulo `app-compose` pelo Android Studio, no entanto a app ao tentar inciar falha e volta a fechar antes de mostrar qualquer ecra. No telemovel é mostrado uma mensagem de alerta a dizer: "Purrfectly Derp keeps stopping".

Analisa as tuas alterações nos ultimos passos implementados para o modulo `app-compose` de modo a descobrir a origem deste problema e corrige o erro que está a causar esta falha na inicialização da app. Não te esqueças de verificar as depencias do gradle e os manifests.

**Result:**
A IA correu o comando `adb logcat` e viu um `ClassNotFoundException` diretamente do emulador. O problema era que basicamente quando a app tentava abrir, o sistema ia procurar pela `MainActivity` no pacote errado e "crashava" instantaneamente. Ao corrigir o `namespace` no Gradle, a app já corre sem erros.

## Prompt 8 (Ajustes Visuais)

**Goal:**
Replicar fielmente as cores e layouts do XML Views original no módulo de Compose.

**Prompt used**:
Quero que anilizes melhor o visual da app no XML views e quero que o repliques melhor no Compose. Toma expecial atenção nas cores.

A IA analisou o ficheiro `colors.xml` e `activity_main.xml` do projeto antigo, copiou os códigos hexadecimais exatos e aplicou-os no `Theme.kt`. Removeu os componentes padrão do Material Design e recriou o fundo em gradiente e o layout do header.

## Prompt 9 (Passo 6 - Animações e Transições)

**Goal:**
Adicionar as funcionalidades exclusivas e nativas de Animações do Compose ao módulo `:app-compose`.

**Prompt used**:
Lê todos os ficheiros Markdown na pasta docs/ para ganhares contexto sobre a arquitetura (MVVM multi-módulo), os modelos de dados e as features exclusivas desta aplicação.

O teu objetivo agora é focar-te no docs/08_implementation_plan.md e implementar exclusivamente o Passo 6.

Regras:

- Não avances para o passo seguinte sem a minha autorização.
- Se precisares de mover ficheiros entre módulos, certifica-te de que as dependências no build.gradle.kts e os imports ficam corretos.

Podes começar a executar o Passo 6.

**Result:**
A IA configurou transições de navegação com `AnimatedContent` na `MainActivity` (com efeitos de *slide* e *fade* ao trocar entre ecrãs).Aplicou o modificador `.animateItem()` para que, quando as imagens carregam ou são atualizadas, elas aparecem na interface de forma dinâmica. O botão de *refresh* foi substituído por um interrutor de **Dark Mode** manual que troca o estado geral da app na hora, e a atualização das imagens é agora controlada por um *Push-to-Refresh*
