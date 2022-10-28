
# Проект - "Автонарушители"

## Общая информация.

Проект разработан с целью улучшения навыков работы с различными стеками технологий
и представляет собой сервис по регистрации автонарушений.
При необходимости функционал проекта можно расширить.

Главная страница является таблицей с поиском. 

В системе существует две роли:

1. Пользователь - добавляет заявку с описанием автонарушений;
2. Автоинспектор - принимает, отклоняет, закрывает заявку.

Заявка имеет три статуса:

- Принята;
- Отклонена;
- Завершена.

Для корректной работы приложения необходимо установить следующие программы:

- Java 16 или выше;
- PostgreSQL 14 или выше;
- Apache Maven 3.8.3 или выше.

## Пример функционирования.

### 1. Регистрация/Авторизация пользователя:

<details>
  <summary>Пример успешной регистрации:</summary>
  <img src="docs/reg.jpg">
  <img src="docs/reg_success.jpg">
</details>

<details>
  <summary>Пример неудачной регистрации одного и того же пользователя два раза:</summary>
  <img src="docs/reg.jpg">
  <img src="docs/reg_fail.jpg">
</details>

## Технологии

[![java](https://img.shields.io/badge/java-16-red)](https://www.java.com/)
[![maven](https://img.shields.io/badge/apache--maven-3.8.3-blue)](https://maven.apache.org/)
[![Spring Boot](https://img.shields.io/badge/spring%20boot-2.7.3-brightgreen)](https://spring.io/projects/spring-boot)
[![PostgresSQL](https://img.shields.io/badge/postgreSQL-14-blue)](https://www.postgresql.org/)

[![Actions Status](https://github.com/alxkzncoff/job4j_car_accident/workflows/java-ci/badge.svg)](https://github.com/alxkzncoff/job4j_car_accident/actions)