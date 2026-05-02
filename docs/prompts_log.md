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
