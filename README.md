# team11_hackathon

Команда 11:

Aleksei Pereverzev [https://t.me/apereverzev](url)
Artur Litovko [https://t.me/Arthur](url)
Evgeniy Nikolaev [https://t.me/NikolaevEvgenij](url)


Проект Memory Game.
Backend написан на Java (Spring, Бд PostgreSQL, деплой на render.com docker-контейнера)

Работает 3 контроллера: AuthController - авторизация пользователя, информация о пользователе; UserController - регистрация и редактирование пользователя; MemoryController - сохранения и возврат результатов игры.
Взаимодействие с frontend осущетсвялется с использованием jwt-токенов, интерфейс - REST. Пароли хранятся в БД в зашифрованном виде.

Frontend: [https://github.com/twin-towers/team11_front](url)
