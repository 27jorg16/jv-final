CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    activo BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO usuario (codigo, password, email, rol, activo)
VALUES ('jefe123', '$2a$12$6K17lBMewyYH5A0xmo54ReNcdllE7WYsLb6F1BmjKWYet.MUSsM5G', 'jefe@libreria.com', 'ROLE_JEFE', true),
       ('empleado123', '$2a$12$6K17lBMewyYH5A0xmo54ReNcdllE7WYsLb6F1BmjKWYet.MUSsM5G', 'empleado@libreria.com', 'ROLE_EMPLEADO', true);