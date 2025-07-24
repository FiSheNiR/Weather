ALTER TABLE weather.users
    ADD CONSTRAINT uk_users_login UNIQUE (login);