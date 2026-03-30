# Social Network API

Spring Boot REST приложение с реляционной БД и демонстрацией JPA-паттернов: связи, N+1, каскады и транзакции.

## Что реализовано

1. Подключена реляционная БД:
- по умолчанию `PostgreSQL`
- тесты используют `H2` только в `src/test/resources/application.yaml`, чтобы `mvn verify` не зависел от внешней БД

2. Модель данных (5 сущностей):
- `User`
- `Post`
- `Comment`
- `Like`
- `Tag`

3. Связи:
- `OneToMany`: `User -> Post`, `Post -> Comment`, `Post -> Like`
- `ManyToMany`: `Post <-> Tag`

4. CRUD:
- `User`: `/api/users`
- `Post`: `/api/posts`
- `Tag`: `/api/tags`

5. N+1 и решение:
- проблема: `GET /api/posts/demo/nplus1`
- решение через `@EntityGraph`: `GET /api/posts/demo/entity-graph`
- дополнительный вариант через `fetch join`: `GET /api/posts/demo/fetch-join`

6. Транзакционность при сохранении связанных сущностей:
- без транзакции: `POST /demo/transaction/without-tx`
- с `@Transactional`: `POST /demo/transaction/with-tx`

Ответ показывает счётчики записей до/после. В `without-tx` часть данных сохраняется, в `with-tx` операция полностью откатывается при ошибке.

## Обоснование CascadeType и FetchType

- `User -> posts/comments/likes`: `cascade = ALL`, `orphanRemoval = true`, `LAZY`.
Это агрегат пользователя: дочерние записи должны удаляться/сохраняться вместе с владельцем.

- `Post -> comments/likes`: `cascade = ALL`, `orphanRemoval = true`, `LAZY`.
Комментарии и лайки принадлежат посту и не нужны отдельно.

- `Post <-> Tag`: `cascade = {PERSIST, MERGE}`, `LAZY`.
Теги переиспользуются между постами, поэтому каскадное `REMOVE` не используется.

- `ManyToOne` (`Post -> User`, `Comment -> User/Post`, `Like -> User/Post`): `LAZY`.
Снижает объём данных при стандартных выборках.

## Запуск

```bash
export DB_URL=jdbc:postgresql://localhost:5432/socialnetwork
export DB_USERNAME=socialuser
export DB_PASSWORD=your_secret
./mvnw spring-boot:run
```

## Безопасное хранение credentials

- Логин/пароль вынесены из `application.yaml` в переменные окружения: `DB_USERNAME`, `DB_PASSWORD`.
- Можно использовать локальный `.env` или `config/application-secrets.properties` (оба файла не должны коммититься).
- В репозитории оставлены только шаблоны: `.env.example` и `config/application-secrets.properties.example`.

Пример локального файла секретов:

```bash
cp .env.example .env
# затем замените DB_PASSWORD на реальный
```

## Проверки

```bash
./mvnw clean verify
```

Checkstyle запускается в фазе `validate`.
