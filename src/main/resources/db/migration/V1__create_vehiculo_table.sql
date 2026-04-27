CREATE TABLE vehiculos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    marca VARCHAR(100) NOT NULL UNIQUE,
    modelo VARCHAR(255) NOT NULL,
    color VARCHAR(50) NOT NULL,
    annioFabricacion INT NOT NULL,
    PRIMARY KEY (id)
);