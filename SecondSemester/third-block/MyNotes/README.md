# My Notes 
MyNotes — это desktop-приложение для заметок, написанное с использованием библиотеки `Swing`<br>
За основу хранения данных взята компактная встраиваемая СУБД — `SQLite`

  ## Запуск приложения
  Для сборки проекта используется `Gradle Wrapper`. Он содержит bat-скрипты для **Windows** и shell-скрипты для **Mac OS** и **Linux**. 
  Эти скрипты позволяют запускать сборку с Gradle без необходимости установки самого Gradle в вашу систему. <br></br>
  
  Запустите следующую команду для загрузки и инициализации wrapper-скриптов:
  <br></br>
  Windows
  ```sh
  gradlew.bat build
  ```
  Linux и Max OS
  ```sh
  ./gradlew build
  ```
  <br></br>
  Затем запустите приложение:
  
  Windows
  ```sh
  gradlew.bat run
  ```
  Linux и Mac OS
  ```sh
  ./gradlew run
  ```
  
  <br></br>
  При необходимости можете очистить сборку:
  
  Windows
  ```sh
  gradlew.bat clean
  ```
  Linux и Mac OS
  ```sh
  
  ./gradlew clean
  ```
