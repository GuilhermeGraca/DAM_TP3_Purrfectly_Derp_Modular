# Prompts Log - MIP-3

## Prompt 1 (Reestruturação Inicial)

**Goal:**
Reestruturar o projeto Android atual para a nova arquitetura multi-módulo pedida no MIP-3, começando pelo isolamento da aplicação antiga (legacy).

**Prompt used:**
Inicia a reestruturação deste projeto para a versão modular. Para começar, altera a pasta da aplicação atual para o módulo `app-xml` e atualiza as configurações do Gradle para que este módulo funcione corretamente sem sobrescrever a aplicação antiga. Atualiza também a documentação necessária para refletir estas novas alterações.

**Result:**
A pasta principal `app` foi renomeada com sucesso para `app-xml`. O ficheiro `settings.gradle.kts` foi atualizado para referenciar o novo módulo e o `applicationId` foi ajustado no Gradle para não conflituar com instalações antigas. A documentação do projeto foi adaptada para o novo formato multi-módulo. Tudo pronto para começar a extração do código para o módulo `:core`.
