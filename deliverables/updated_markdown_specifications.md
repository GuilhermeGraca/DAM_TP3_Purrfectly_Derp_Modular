# Updated Markdown Specifications

No início da implementação, a app era um bloco único onde os ecrãs e o resto da lógica da aplicação estavam juntos. Ao mudar para uma Arquitetura Multi-Módulo, conseguiu-se criar uma app nova em Jetpack Compose que pode utilizar todo o código já existente sem se ter de duplicar regras de base de dados ou internet.

A nova interface construída no módulo `:app-compose` sendo que utiliza o jetpack compose permitiu utilizar as funcionalidades dele para evoluir o que já existia e adicionar interações exclusivas impossíveis que ia ser muito mais dificieis de fazer em XML como o Theming Dinâmico de Light e dark, Animações Naturais, Interações novas:
