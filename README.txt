SPACE
Задание:
Открытое API от SpaceX расположено по адресу
https://docs.spacexdata.com/?version=latest

1	Написать клиент API к разделам Rockets и Launches.
2	Написать RestApi c двумя методами
1.	Получить список rocketId из раздела Rockets
2.	Получить по rocketId все возможные Launches. Но показывать только mission_name, launch_year, links.
3	Сохранять в базу все запросы к RestAPI, сохраняя структуру ответов


Запуск проекта
Для запуска вам потребуется:

Скачать архив приложения (или выполнить fork в свой репозиторий и клонировать его на свой ПК).
На ПК полжна быть запущена база данных Postgres. Она должна работать на стандартном порту (5432).
В базе данных необходимо создать пользователя 'postgres' и установить ему пароль. Далее внести эти данные в файл application properties приложения в соотвествующие поля.
В базе необходимо создать схему 'space', выполнить команду:
SET SEARCH_PATH to space;
Далее открыть файл space_script.txt и скопировать содержимое файла в схему.

Проект готов к запуску.
Технологии:

Spring Boot,
REST API,
Spring DATA,
Spring Web,
Thymeleaf,
PostgreSQL,
Bootstrap,
HTML,
CSS,
Lombok.


