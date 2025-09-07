# Сервис рекомендаций банка

Service Recommendation from Users

![Java](https://img.shields.io/badge/Java-17-blue?style=flat&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.1.0-green?style=flat&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?style=flat&logo=postgresql)

Сервис для персональной рекомендации продуктов банка пользователям. Реализован на основе статических и динамических.
Предоставляет REST API для получения рекомендаций и интеграцию с Telegram-ботом.

---

## 🚀 Технологический стек

*   **Backend Framework:** [Spring Boot](https://spring.io/projects/spring-boot)
*   **Language:** Java 17
*   **Database:** PostgreSQL, H2GIS Embedded
*   **Caching:** Caffeine
*   **API Documentation:** Springdoc OpenAPI (Swagger)
*   **Build Tool:** Maven
*   **Telegram Bot:** [TelegramBots](https://github.com/rubenlagus/TelegramBots) library

---

## 📖 Основная функциональность

*   **Для пользователей:**
    *   Получение персонализированных рекомендаций продуктов банка через REST API.
    *   Получение рекомендаций и уведомлений через удобного Telegram-бота.
*   **Для менеджеров:**
    *   Управление динамическими правилами для формирования рекомендаций через API.
    *   Просмотр статистики по запросам и эффективности правил.
*   **Для внешних систем:**
    *   API для принудительного сброса кеша рекомендаций.
    *   API для сбора метрик о работе приложения.

---

## 🔧 Быстрый старт

### Предварительные требования

Перед запуском убедитесь, что у вас установлены:
*   Java 17 JDK
*   Maven
*   PostgreSQL
*   H2GIS Embedded

### Установка и запуск

1.  Клонируйте репозиторий:
    [https://github.com/your-username/your-repo-name.git](https://github.com/enot-therock/service_bank_test)

3.  Настройте переменные окружения (или отредактируйте `application.properties`):
    #DB_JDBCTemplate application.recommendations-db.url=jdbc:h2:file:./transaction

#Hibirnate 
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect 
spring.jpa.hibernate.ddl-auto=update 
spring.jpa.properties.hibernate.format_sql=true

#DB_PostgreSQL 
spring.datasource.url=jdbc:postgresql://localhost:5432/service_bank 
spring.datasource.username=users 
spring.datasource.password=123456 
spring.datasource.driver-class-name=org.postgresql.Driver

#Liquibase 
spring.liquibase.change-log=classpath:liquibase/changelog-master.yml

#Caffeine 
caffeine.maximumSize=1000 caffeine.endAccess=10

4.  Соберите и запустите приложение:
    mvn spring-boot:run

Приложение будет доступно по адресу: http://localhost:8080
