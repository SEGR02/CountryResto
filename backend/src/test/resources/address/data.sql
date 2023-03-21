INSERT INTO users (user_id, name, email, password, role)
VALUES (1, 'Julio', 'julion.alvarez@gmail.com',
        '$2a$10$3tiCRGGTSkUqVI6NNg1WBeP7Bs9Zj/O2zToB5Pl5ISw.YBDyrjzHC', 'NORMAL'); /* Password is: 12345678 */

INSERT INTO addresses (user_id, street, number, city, state, country, zip_code, clarifications)
VALUES (1, 'Street 1', '540', 'Mérida', 'Yucatán', 'México', '97000', 'Casa azul con rejas blancas');
