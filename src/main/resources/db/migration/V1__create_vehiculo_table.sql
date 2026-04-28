CREATE TABLE VEHICULOS (
    id BIGINT NOT NULL AUTO_INCREMENT,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    color VARCHAR(50) NOT NULL,
    annio_fabricacion INT NOT NULL,
    stock BIGINT NOT NULL DEFAULT 0,

    PRIMARY KEY (id),

    CONSTRAINT uk_vehiculo_marca_modelo UNIQUE (marca, modelo),

    CONSTRAINT chk_vehiculo_marca
        CHECK (marca IN ('BMW', 'Audi', 'Mercedes')),

    CONSTRAINT chk_vehiculo_annio
        CHECK (annio_fabricacion BETWEEN 1900 AND 2030),

    CONSTRAINT chk_vehiculo_stock
        CHECK (stock >= 0)
);