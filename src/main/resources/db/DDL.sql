USE db_tshake_test;

CREATE TABLE pedido(
	id INT PRIMARY KEY AUTO_INCREMENT,
    data_hora DATETIME NOT NULL,
    status VARCHAR(15) NOT NULL
);

CREATE TABLE item_pedido(
	id INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(15) NOT NULL,
    quantidade INT NOT NULL,
    pedido_id INT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedido(id)
);