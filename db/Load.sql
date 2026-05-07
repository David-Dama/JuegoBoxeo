-- Creamos las partidas
INSERT INTO partida (id)
VALUES (1);
INSERT INTO partida (id)
VALUES (2);
INSERT INTO partida (id)
VALUES (3);

-- Insertamos los boxeadores

INSERT INTO boxeador(nombre, anyo_nacimiento, descripcion, vida, stamina)
VALUES ('Payo Muay Thai', 2007,
        'Es un matao, es más payo que despertarse a las 5am para sacar al perro, más que pagar con tarjeta o incluso más payo que no tener a sus primos. No tiene ningún punto fuerte, y encima es payo',
        100, 100);

INSERT INTO boxeador(nombre, anyo_nacimiento, descripcion, vida, stamina)
VALUES ('Vallecas Gangsta Boy', 2007,
        'Luchador de Vallecas, acostumbrado a los cuchillos y pistolas. Vive en uno de los barrios más conflictivos del mundo. Sus reflejos son increíblemente altos y esquiva con facilidad (acostumbrado a las peleas y esquivar balas)',
        100, 100);

INSERT INTO boxeador(nombre, anyo_nacimiento, descripcion, vida, stamina)
VALUES ('Chino final boss', 2002,
        'Nacido en Tenerife (chino de hacendado) y con los ojos más cerrados que un topo. Tiene un aguante descomunal (puede trabajar 25 horas al día), va al gimnasio 8 días a la semana. Un enemigo formidable que tiene ventaja genética (es chino, y siempre habrá un chino que lo haga mejor que tú)',
        100, 100);

INSERT INTO boxeador(nombre, anyo_nacimiento, descripcion, vida, stamina)
VALUES ('Chiquitito', 2007,
        'Con 5 metros de alto y brazos del tamaño de sus cuádriceps, chiquitito destaca por su fuerza y golpes contundentes. No acierta todos los golpes, pero como chiquitito te de un one-tap vete preparándote para saludar a San Pedro',
        100, 100);

INSERT INTO boxeador(nombre, anyo_nacimiento, descripcion, vida, stamina)
VALUES ('MPOfessor', 1999,
        'Todo un máquina en programación y en el deporte, completamente imbatible, todas las stats al máximo, solo hay una manera de vencerle... throwearle al próximo contrincante y que se ocupe otro (las famosas buenas prácticas).',
        100, 100);

-- Insertamos los datos en partida_boxeador

-- PARTIDA 1
INSERT INTO partida_boxeador (id_partida, id_boxeador, rol, victorias, derrotas)
VALUES (1, 1, 'IA', 0, 0),
       (1, 2, 'IA', 0, 0),
       (1, 3, 'IA', 0, 0),
       (1, 4, 'IA', 0, 0),
       (1, 5, 'IA', 0, 0);

-- PARTIDA 2
INSERT INTO partida_boxeador (id_partida, id_boxeador, rol, victorias, derrotas)
VALUES (2, 1, 'IA', 0, 0),
       (2, 2, 'IA', 0, 0),
       (2, 3, 'IA', 0, 0),
       (2, 4, 'IA', 0, 0),
       (2, 5, 'IA', 0, 0);

-- PARTIDA 3
INSERT INTO partida_boxeador (id_partida, id_boxeador, rol, victorias, derrotas)
VALUES (3, 1, 'IA', 0, 0),
       (3, 2, 'IA', 0, 0),
       (3, 3, 'IA', 0, 0),
       (3, 4, 'IA', 0, 0),
       (3, 5, 'IA', 0, 0);

-- Informacion_golpe

-- BOXEADOR 1
INSERT INTO informacion_golpe
VALUES (1, 'JAB', 1, 1, 1, 1),
       (1, 'CROSS', 1, 1, 1, 1),
       (1, 'HOOK', 1, 1, 1, 1),
       (1, 'UPPER', 1, 1, 1, 1);

-- BOXEADOR 2
INSERT INTO informacion_golpe
VALUES (2, 'JAB', 2, 2, 2, 2),
       (2, 'CROSS', 2, 2, 2, 2),
       (2, 'HOOK', 2, 2, 2, 2),
       (2, 'UPPER', 2, 2, 2, 2);

-- BOXEADOR 3
INSERT INTO informacion_golpe
VALUES (3, 'JAB', 3, 3, 3, 3),
       (3, 'CROSS', 3, 3, 3, 3),
       (3, 'HOOK', 3, 3, 3, 3),
       (3, 'UPPER', 3, 3, 3, 3);

-- BOXEADOR 4
INSERT INTO informacion_golpe
VALUES (4, 'JAB', 4, 4, 4, 4),
       (4, 'CROSS', 4, 4, 4, 4),
       (4, 'HOOK', 4, 4, 4, 4),
       (4, 'UPPER', 4, 4, 4, 4);

-- BOXEADOR 5
INSERT INTO informacion_golpe
VALUES (5, 'JAB', 5, 5, 5, 5),
       (5, 'CROSS', 5, 5, 5, 5),
       (5, 'HOOK', 5, 5, 5, 5),
       (5, 'UPPER', 5, 5, 5, 5);