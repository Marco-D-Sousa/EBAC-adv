CREATE TABLE animal (
    id INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(40) NOT NULL,
    idade INT NOT NULL,
    raca VARCHAR(70) NOT NULL,
    porte VARCHAR(40) NOT NULL,
    responsavel VARCHAR(80) NOT NULL,
    data_entrada date,
    data_saida date,
    especie VARCHAR(20) NOT NULL,
    CONSTRAINT pk_animal PRIMARY KEY (id)
);