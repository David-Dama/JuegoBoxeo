CREATE DATABASE bdjuegoboxeo;
USE bdjuegoboxeo;
CREATE TABLE partida
(
    id INT PRIMARY KEY
);

CREATE TABLE boxeador
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(50) NOT NULL,
    anyo_nacimiento INT         NOT NULL,
    descripcion     TEXT        NOT NULL,
    vida            INT         NOT NULL CHECK ( vida > 0 ),
    stamina         INT         NOT NULL CHECK ( stamina > 0 )
);

CREATE TABLE partida_boxeador
(
    id_partida  INT                   NOT NULL,
    id_boxeador INT                   NOT NULL,
    rol         ENUM ('JUGADOR','IA') NOT NULL,
    victorias   INT                   NOT NULL DEFAULT 0,
    derrotas    INT                   NOT NULL DEFAULT 0,
    PRIMARY KEY (id_partida, id_boxeador),
    FOREIGN KEY (id_partida) REFERENCES partida (id) ON DELETE CASCADE,
    FOREIGN KEY (id_boxeador) REFERENCES boxeador (id) ON DELETE CASCADE
);

CREATE TABLE informacion_golpe
(
    boxeador_id          INT                                    NOT NULL,
    golpe                ENUM ('JAB', 'CROSS', 'HOOK', 'UPPER') NOT NULL,
    danyo                INT                                    NOT NULL CHECK ( danyo > 0 ),
    precision_golpe      INT                                    NOT NULL CHECK ( precision_golpe > 0 ),
    probabilidad_critico INT                                    NOT NULL CHECK ( probabilidad_critico > 0 ),
    coste_stamina        INT                                    NOT NULL CHECK ( coste_stamina > 0 ),
    PRIMARY KEY (boxeador_id, golpe),
    FOREIGN KEY (boxeador_id) REFERENCES boxeador (id)
        ON DELETE CASCADE
);