# Diagrama de Módulos (Module Diagram)

O projeto "Purrfectly Derp" está dividido em 3 módulos principais independentes:

**Módulo `:core`**
Funciona como o cérebro da app. Não tem nenhuma interface gráfica.
É responsável por ir buscar as imagens dos animais engraçados à internet com o uso da Unsplash API, guarda-as e lê as fotos favoritas na base de dados do telemóvel com a Room Database e fornece esses dados já organizados.

**Módulo `:app-xml`**
Este módulo depende só do `:core`. Funciona de forma independente do módulo compose.
É a interface gráfica mais basica tal como já tinha sido desenvolvido no TP2. Agora foi apenas refatorada para separar a logica e os dados no módulo :core.

**Módulo `:app-compose`**
Este módulo também depende exclusivamente do `:core`. Funciona de forma independente do módulo xml.
É a interface gráfica mais avançada que usa o jetpack compose. E neste modulo que foram implementadas as funcionalidades mais dinamicas, como a navegação por "swipe", animações, e a alteração de cores com o Dark e Light mode.

---

Diagrama Visual:

  [ :app-compose ]           [ :app-xml ]
         |                         |
         |                         |
         |------------ ------------|
                      |
                      v
                 [ :core ]
