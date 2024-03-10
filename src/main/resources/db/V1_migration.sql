insert into users(username, firstname, email, lastname, password, role)
VALUES ('azamat','admin', 'admin@mail.ru', 'admin', '$2a$10$4R2NCOV.sMJ9DDMOPY8FPO4boay4/VWI9Syd8YA/YY9Fjz0V7eg/u', 'ROLE_ADMIN')
on conflict do nothing;

