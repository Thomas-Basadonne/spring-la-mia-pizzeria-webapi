--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 1', '2023-06-01', '2023-07-10', 1);
--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 2', '2023-06-05', '2023-07-15', 2);
--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 3', '2023-06-10', '2023-07-20', 3);
--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 4', '2023-06-15', '2023-07-25', 4);
--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 5', '2023-06-20', '2023-07-30', 5);
--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 1', '2023-06-01', '2023-07-10', 6);
--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 2', '2023-06-05', '2023-07-15', 7);
--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 3', '2023-06-10', '2023-07-20', 8);
--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 4', '2023-06-15', '2023-07-25', 9);
--INSERT INTO special_offers (title, start_date, end_date, pizza_id) VALUES ('Offerta Speciale 5', '2023-06-20', '2023-07-30', 10);


INSERT INTO pizze (name, description, photo, price)
VALUES
    ('Pizza Margherita', 'Deliziosa pizza classica con salsa di pomodoro, mozzarella e foglie di basilico.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 9.99),
    ('Pizza Pepperoni', 'Gustosa pizza con fette di salame piccante e mozzarella.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 10.99),
    ('Pizza Hawaiian', 'Pizza dolce e salata con prosciutto, pezzetti di ananas e mozzarella.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 11.99),
    ('Pizza Quattro Formaggi', 'Pizza con quattro tipi di formaggio: mozzarella, gorgonzola, provolone e parmigiano.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 12.99),
    ('Pizza Vegetariana', 'Pizza vegetariana con verdure fresche come pomodori, peperoni, cipolle e funghi.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 11.99),
    ('Pizza Diavola', 'Pizza piccante con salame piccante, peperoncini e mozzarella.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 10.99),
    ('Pizza Capricciosa', 'Pizza ricca di ingredienti come prosciutto, funghi, carciofi, olive e mozzarella.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 12.99),
    ('Pizza Prosciutto e Funghi', 'Pizza semplice ma gustosa con prosciutto, funghi e mozzarella.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 10.99),
    ('Pizza Calzone', 'Calzone ripieno di mozzarella, pomodoro, prosciutto e funghi.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 11.99),
    ('Pizza Frutti di Mare', 'Pizza con frutti di mare freschi come gamberetti, cozze, vongole e calamari.', 'https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028', 13.99);

INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (1, 6);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (3, 3);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (8, 5);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (4, 7);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (2, 9);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (6, 4);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (5, 10);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (10, 1);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (9, 8);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (7, 2);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (3, 10);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (8, 4);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (1, 7);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (5, 2);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (2, 1);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (9, 6);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (10, 3);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (4, 8);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (6, 5);
INSERT INTO pizza_ingredient (pizza_id, ingredient_id) VALUES (7, 9);



INSERT INTO users (first_name, last_name, email, password)
VALUES ('John', 'Doe', 'johndoe@example.com', 'password123');

INSERT INTO users (first_name, last_name, email, password)
VALUES ('Jane', 'Smith', 'janesmith@example.com', 'abc123');

INSERT INTO users (first_name, last_name, email, password)
VALUES ('Michael', 'Johnson', 'michaeljohnson@example.com', 'qwerty');

INSERT INTO users (first_name, last_name, email, password)
VALUES ('Emily', 'Williams', 'emilywilliams@example.com', 'pass123');


-- Associazione dell'utente con id=1 al ruolo con id=1 (ruolo admin)
INSERT INTO users_roles (user_id, roles_id) VALUES (1, 1);

-- Associazione dell'utente con id=2 al ruolo con id=1 (ruolo admin)
INSERT INTO users_roles (user_id, roles_id) VALUES (2, 1);

-- Associazione dell'utente con id=3 al ruolo con id=2 (ruolo user)
INSERT INTO users_roles (user_id, roles_id) VALUES (3, 2);

-- Associazione dell'utente con id=4 al ruolo con id=2 (ruolo user)
INSERT INTO users_roles (user_id, roles_id) VALUES (4, 2);
