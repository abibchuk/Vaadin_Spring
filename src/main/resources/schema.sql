CREATE PROCEDURE proc_select() SELECT * FROM user_data;
CREATE PROCEDURE proc_insert(name VARCHAR(20), password VARCHAR(20)) INSERT INTO user_data (name, password) VALUES (name, password);