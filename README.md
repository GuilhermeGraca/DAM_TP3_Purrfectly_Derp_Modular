# Assignment 3 — Purrfectly Derp Modular

Course: Desenvolvimento de Aplicações Móveis
Student(s): Guilherme Graça A51827
Date: 2 de Maio de 2026
Repository URL: https://github.com/GuilhermeGraca/DAM_TP3_Purrfectly_Derp_Modular/
---

## 1. Introduction
O objetivo deste trabalho é transformar a aplicação  do trabalho anterior numa arquitetura modular, que suporta múltiplos métodos de interface de utilizador. Extraiu-se toda a lógica de acesso a dados para um módulo partilhado central (`:core`), e construíram-se duas interfaces distintas que o consomem: uma interface tradicional baseada em XML e uma interface moderna baseada em Jetpack Compose.

## 2. System Overview
A aplicação apresenta imagens de animais de forma aleatória obtidas através da API do Unsplash. Permite também avaliar os animais através de um medidor de "Derp" interativo e arquivar os favoritos numa base de dados local. Com a separação arquitetural em módulos, garantiu-se que ambas as interfaces (`app-xml` e `app-compose`) operam de forma independente, alimentadas pelos mesmos dados organizados do repositório.

## 3. Architecture and Design
Adotou-se uma Arquitetura Multi-Módulo do tipo MVVM (Model-View-ViewModel).
* `:core`: Contém as entidades de base de dados Room, os DAOs, a configuração do Retrofit para a API e as lógicas de Repositório.
* `:app-xml`: É a interface gráfica clássica desenvolvida com ficheiros XML, Fragments/Activities e RecyclerViews.
* `:app-compose`: É a interface gráfica moderna desenvolvida totalmente de forma declarativa com Jetpack Compose.
Esta divisão assegurou o isolamento total das regras de negócio, o que impede a duplicação de código entre as aplicações visuais.

## 4. Implementation
Implementaram-se os três módulos com fronteiras bem definidas.
No módulo `:core`, configurou-se a base de dados Room e as chamadas de rede Retrofit.
No módulo `:app-xml`, refatorou-se a app original para ligar os ViewModels diretamente ao repositório unificado.
No módulo `:app-compose`, construíram-se componentes declarativos. Incluíram-se interações dinâmicas avançadas (exclusivas ao Compose) como transições em ecrãs, navegação tátil lateral (arrasto com `HorizontalPager`), temas dinâmicos (modo escuro e claro automático integrado no `MaterialTheme`) e gavetas interativas inferiores (`ModalBottomSheet`) para apresentar os detalhes estéticos dos animais.

## 5. Testing and Validation
Validou-se o projeto com sucesso. Ambas as aplicações (`app-xml` e `app-compose`) compilam sem erros e mantêm IDs de Gradle completamente distintos. Testou-se exaustivamente a navegação fluida entre os ecrãs, a fiabilidade de acesso à base de dados para guardar favoritos e a transição robusta entre o modo escuro e claro.

## 6. Usage Instructions
Para executar o projeto com eficácia:
1. Sincroniza-se o projeto com o Gradle no Android Studio.
2. Seleciona-se o módulo pretendido (`app-xml` ou `app-compose`) no menu de execução (Run).
3. Compila-se e instala-se a aplicação num emulador ou num dispositivo físico Android.
Não é requerida a instalação de nenhuma ferramenta externa adicional.

---
# Autonomous Software Engineering Sections
## 7. Prompting Strategy
Desenvolveu-se uma estratégia em passos lógicos e sequenciais. Começou-se por desenhar e atualizar os ficheiros Markdown na pasta `docs/` para servirem como plano estrutural (Single Source of Truth). Posteriormente, solicitou-se à AI para executar o plano gradualmente. Definiram-se comandos precisos para afinar detalhes complexos, como os problemas gráficos da BottomSheet e as cores finais do módulo Compose.

## 8. Autonomous Agent Workflow
O agente autónomo (Antigravity) reescreveu os scripts de construção `build.gradle.kts`, dividiu e moveu os ficheiros para as pastas dos módulos corretos e programou a interface moderna em Kotlin. Identificaram-se e resolveram-se erros de compilação pelo próprio agente de forma proativa.

## 9. Verification of AI-Generated Artifacts
Verificou-se a integridade do código através da compilação direta via terminal (`assembleDebug`) e testes pontuais. Executaram-se validações manuais rigorosas à interface gráfica desenhada para garantir o alinhamento com a paleta de cores exigida e o bom funcionamento das caixas de diálogo modais.

## 10. Human vs AI Contribution
A intervenção humana centrou-se na coordenação da arquitetura (decisão das regras de módulos), aprovação do plano estrutural, revisão atenta do código e correção de descrições textuais nos documentos finais. O trabalho pesado, a geração de sintaxe em Kotlin para o Compose, a configuração de ficheiros do Gradle e a refatorização extensiva do código antigo para o módulo Core foram delegados ao assistente AI.

## 11. Ethical and Responsible Use
Manteve-se o rigor na verificação de todos os ficheiros modificados pelo assistente artificial. Garantiu-se que o modelo utilizou apenas as bibliotecas essenciais e evitou injeção de dependências inseguras ou componentes de código redundantes, com total transparência e respeito pelas boas práticas.

---
# Development Process
## 12. Version Control and Commit History
Utilizou-se o sistema de controlo de versões (Git) de forma orgânica ao longo do trabalho. Documentou-se e guardou-se as alterações significativas após o término de cada passo fulcral (criação do core, migração do Room, integração das lógicas e construção do módulo UI Compose).

## 13. Difficulties and Lessons Learned
As dificuldades primárias concentraram-se nos ajustes minuciosos das componentes do Compose (nomeadamente o tamanho dinâmico do ModalBottomSheet na base do telemóvel e a correta inversão de fontes no Modo Escuro). Apreendeu-se a versatilidade das lógicas de StateFlow e como as arquiteturas limpas potenciam uma manutenção e evolução simples da app móvel.

## 14. Future Improvements
Propõe-se a adição de funcionalidades como internacionalização de idioma, capacidade para remover os registos inseridos no Hall of Fame ou introdução de categorias detalhadas por espécie de animal.

---
## 15. AI Usage Disclosure (Mandatory)
Confirmou-se o uso do assistente integrado Antigravity. O assistente foi utilizado para ler documentação e refatorar pacotes na arquitetura da app, com base em pedidos estritos passo-a-passo e sob a supervisão total para manter o controlo final de qualidade da aplicação. Foi ainda utilizado como auxilio da redação deste README.md. Declara-se total responsabilidade pelas lógicas produzidas, decisões de base de dados e integridade estrutural do projeto final.
