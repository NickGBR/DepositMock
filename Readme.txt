Инструкция по настройки приложения deposit creator.

1. скачать 13-ю версию PostgresSql https://www.postgresql.org/download/
2. установить скаченный файл, при настройке установить пароль postgres, порт оставить по умолчанию.
3. установить Intellij idea, c jdk 8
4. в Intellij idea выбрать File-> New -> Project from version control
5. Указать в поле URL - https://github.com/NickGBR/DepositMock
6. Нажать кнопу Clone
7. Idea скачает проект и все необходимые зависимости
8. Установите Lombok plugin File->Settings->Plugins
9. Запустите панель управления postgres. По умолчанию "C:\Program Files\PostgreSQL\13\pgAdmin 4\bin\pgAdmin4.exe"
10. Создайте базу данных mock
11. В Idea выбираем ветку develop и запускаем приложение
12. Для заполнения базы данных мвд, открываем плагин flyway
13. Нажимаем clean
14. Запускае проект, в процессе запуска создадутся необходимые таблицы в Бд
15. Открывем плагин flyway, нажимаем baseline, затем migrate