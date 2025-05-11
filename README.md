# Лабораторная 1

# Отрабатываемый материал

Изучение языка Java, инстументов сборки и встроенных инструментов для документирования исходного кода.

# Цель

Получение прикладных навыков по настройке окружения для корректной работы с Java-совместимыми проектами.  
Отработка навыков работы с JavaDoc, JUnit, Системами сбороки Maven/Gradle.

# Задание

Реализовать систему банкомата, настроить CI.  

# Функциональные требования

- создание счета
- просмотр баланса счета
- снятие денег со счета
- пополнение счета
- просмотр истории операций

# Не функциональные требования

- При запуске сборки приложения должна автоматически генерироваться документация JavaDoc.   
- Отдельная задача внутри пайплайна системы сборки для запуска интерактивного консольного интерфейса программы.  
- При попытке выполнения некорректных операций, методы должны выкидывать проверяемые (checked) исключения бизнес логики.  
- Использование Spring Framework и Spring Boot - запрещено.  
- Сторонние зависимости должны поставляться системой сборки автоматически.   
- Использование репозиториев отличных от Maven Central - запрещено.  
 
# Unit tests

Все методы, выбрасывающие исключения бизнес логики должны быть покрыты тестами.    
В качестве тестового фреймворка использовать JUnit.  

# Ссылки

https://learnxinyminutes.com/docs/java/  
https://maven.apache.org/guides/getting-started/  
https://docs.gradle.org/current/userguide/part1_gradle_init.html  
https://github.com/eugenp/tutorials/blob/master/testing-modules/junit-5-basics/src/test/java/com/baeldung/ExceptionUnitTest.java  



# Инстуркции по настройке CI

## Создание структуры для GitHub Actions

1. В корне репозитория создайте папку `.github/workflows`  
2. В этой папке создайте файл `java.yml`  

## Добавление CI скрипта

В зависимости от выбранной системы сборки, вам нужно будет вставить в этот файл различный код.

## Maven

```yaml
name: Java CI
on: [push]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 21
        uses: actions/setup-java@v2
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Build with Maven
        run: mvn --batch-mode --update-snapshots package
```

## Gradle

```yaml
name: Java CI
on: [push]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 21
        uses: actions/setup-java@v2
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Validate Gradle wrapper
        uses: gradle/wrapper-validation-action@v1
      - name: Build with Gradle
        uses: gradle/gradle-build-action@v1
        with:
          arguments: build
```

‼️ В данных скриптах указана версия JDK 21, если вы используете другую версию, то поставьте её в параметре `java-version`

Для решения подобной проблемы  
![image](https://github.com/user-attachments/assets/2715af2f-11fa-45c8-beca-cc28808a0024)
Пользователям Windows необходимо сделать дополнительную манипуляцию над gradle wrapper’ом:  
```sh
git update-index --chmod=+x gradlew
```
Далее сделайте `commit`+ `push`, можно напрямую в `master` ветку, без пулл реквеста.

# Лабораторная 2

# Отрабатываемый материал

Изучение языка Java, инстументов сборки и встроенных инструментов для работы с персистентными хранилищами.

# Цель

Изучение архитектурного подхода Сontroller-Service-Dao.
Получение прикладных навыков работы с JDBC (Java DataBase Connectivity).  

# Задание

Реализовать приложение для работы с базой данных через ORM.
Необходимо создать модели для описание таблиц баз данных согласно конвенциям JPA.

Дополнительно:
 Предусмотреть возможность применения миграций базы данных.

# Функциональные требования

Описать следующие сущности:

- Питомец
  
Имя  
Дата рождения  
Порода  
Цвет (один из заранее заданных вариантов)  
Хозяин  
Список котиков, с которыми дружит этот котик (из представленных в базе)  

- Хозяин (владелец питомца):
  
Имя  
Дата рождения  
Список котиков   

- Реализовать CRUD (create, read, update, delete) операции для описанных вами сущностей. 
```java
public T save(T entity);
public void deleteById(long id);
public void deleteByEntity(T entity);
public void deleteAll();
public T update(T entity);
public T getById(long id);
public List<T> getAll();
```

# Не функциональные требования

В качестве базы данных рекомендуется использовать PostgreSQL.  
Для связи с БД рекомендуется использоваться Hibernate.

- Лабораторная работа должна быть выполнена как отдельный модуль/проект.
- Рекомендуется использование JDBC-совместимых конфигураций ORM. 
- Использование Spring Framework и Spring Boot - запрещено.  
- Сторонние зависимости должны поставляться системой сборки автоматически.
 
# Unit tests

В качестве тестового фреймворка использовать JUnit.  
Избегать тестирования на настоящей базе, использовать либо готовые mock-системы по типу **TestContainers**, либо **DBUnit**, либо написание заглушек через **Mockito** напрямую.  

# Ссылки

https://github.com/codehaus/dbunit/blob/master/core/src/test/java/org/dbunit/BuilderExample.java  


### Настройка системы сборки под несколько проектов
- Maven  
https://www.baeldung.com/maven-multi-module

- Gradle  
https://docs.gradle.org/current/userguide/intro_multi_project_builds.html

### Hibernate
- tl;dr версия:  
https://docs.jboss.org/hibernate/orm/6.6/quickstart/html_single/#tutorial_jpa

- Подробная документация:  
https://docs.jboss.org/hibernate/orm/6.6/userguide/html_single/Hibernate_User_Guide.html#getting-started



# Лабораторная 3

# Отрабатываемый материал

Изучение фреймворка Spring и дополнений к нему в виде Spring Data JPA, Spring Boot.

# Цель

Получение прикладных навыков работы c фреймворком Spring.

# Задание

Реализовать веб-приложение на основе шаблона Spring MVC с использованием Spring Boot для работы с имеющейся базой данных через Spring Data JPA.  
Приложение должно предоставлять HTTP интерфейс (REST API) для получения информации о конкретных котиках и их владельцах.  

Дополнительно:  
 Если вами были предусмотрены миграции в предыдущей лабораторной - необходимо добавить новый аттрибут вашей сущности (например - длина хвоста котика)  
 Наличие [swagger](https://springdoc.org/#getting-started) спецификации.

# Функциональные требования

- Слой представления не должен отдавать через HTTP интерфейс сущности JPA. Необходимо использование Data Transfer Object'ов.
- Реализовать возможность пагинации и фильтрации результатов поисковых запросов (т.е. Первые пять рыжих котов, и т.д.)
- В качестве репозиториев должны использоваться реализации унаследованные от CrudRepository (и прочих производных)

# Не функциональные требования

- Лабораторная работа должна быть выполнена как отдельный модуль/проект.
- В качестве ORM необходимо использовать Spring Data JPA
- Все классы должны получать свои зависимости с использованием Dependency Injection на основе JavaBeans.
- Сторонние зависимости должны поставляться системой сборки автоматически.
 
# Unit tests

Необходимо проверить работоспособность endpoint'ов с использованием заглушек.  
Без старта веб-сервера возможно с использованием подхода [MockMVC](https://habr.com/ru/companies/otus/articles/746414/)

При сдаче лабораторной нужно будет показать работоспособность ваших endpoint’ов путем отправки настоящих HTTP запросов.  
Убедительная просьба подготовить их зараннее (Например сохранить коллекцию запросов в Postman).

# Ссылки

http://baeldung.com/spring-boot-start  
https://start.spring.io/  
https://www.baeldung.com/spring-data-jpa-pagination-sorting  