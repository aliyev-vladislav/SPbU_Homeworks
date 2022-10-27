# Task 4.1 <br> Web-Weather
Написать беcсерверное web-приложение, агрегирующее данные по погоде с нескольких сайтов.<br>
Принять данные одного из сайтов - каноничными и провести регрессию данных с остальных сайтов.<br>

### Комментарий:
Приложение написано с использованием `Spring Framework`<br>
В качестве сборщика проекта взят [Maven](https://maven.apache.org/install.html)


### Используемое API
+ [OpenWeatherMap](https://openweathermap.org/api)
+ [Tomorrow](https://www.tomorrow.io/weather-api/)

## Запуск приложения

  Создать war-файл приложения
  ```sh
  mvn package
  ```
  Для локального развертывания можно использовать `Apache Tomcat`
  
  1. Скачать файл [Tomcat 9](https://tomcat.apache.org/download-90.cgi)
  2. В расспакованном архиве перейти в папку `webapps` и добавить ранее созданный war-файл
  3. Определить контекстный путь из `conf/server.xml` следующим образом
  ```xml
  <Host name="localhost"  appBase="webapps"
      unpackWARs="true" autoDeploy="true">
      <Context path="" docBase="web-weather"/>
      ...
  </Host>
  ```
  5. В папке `bin` установить права на выполнение скрипта
  ```sh
  chmod +x catalina.sh
  ```
  6. В той же директории запустить сервер
  ```sh
  sh startup.sh
  ```
  7. Открыть любой удобный браузер и в адрессной строке ввести
  ```sh
  http://localhost:8080/weather/
  ```
  
