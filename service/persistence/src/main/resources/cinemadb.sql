-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cinemadb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cinemadb`;

-- -----------------------------------------------------
-- Schema cinemadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinemadb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
-- -----------------------------------------------------
-- Schema test_schema
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `test_schema`;

-- -----------------------------------------------------
-- Schema test_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test_schema`;
GRANT ALL ON test_schema.* TO 'admin'@'%';
FLUSH PRIVILEGES;
USE `cinemadb`;

-- -----------------------------------------------------
-- Table `cinemadb`.`cinema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`cinema`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `nome`      VARCHAR(45) NOT NULL,
    `indirizzo` VARCHAR(45) NOT NULL,
    `citta`     VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`sala`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `id_cinema` INT         NOT NULL,
    `numero`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Sala_Cinema1_idx` (`id_cinema` ASC) VISIBLE,
    CONSTRAINT `fk_Sala_Cinema1`
        FOREIGN KEY (`id_cinema`)
            REFERENCES `cinemadb`.`cinema` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`posto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`posto`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `id_sala`   INT         NOT NULL,
    `posizione` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Posto_Sala1_idx` (`id_sala` ASC) VISIBLE,
    CONSTRAINT `fk_Posto_Sala1`
        FOREIGN KEY (`id_sala`)
            REFERENCES `cinemadb`.`sala` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`film`
(
    `id` INT NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`proiezione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`proiezione`
(
    `id`          INT    NOT NULL AUTO_INCREMENT,
    `id_sala`     INT    NOT NULL,
    `id_film`     INT    NOT NULL,
    `data`        DATE   NOT NULL,
    `inizio`      TIME   NOT NULL,
    `prezzo_base` DOUBLE NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Proiezione_Film1_idx` (`id_film` ASC) VISIBLE,
    INDEX `fk_Proiezione_Sala1_idx` (`id_sala` ASC) VISIBLE,
    CONSTRAINT `fk_Proiezione_Film1`
        FOREIGN KEY (`id_film`)
            REFERENCES `cinemadb`.`film` (`id`),
    CONSTRAINT `fk_Proiezione_Sala1`
        FOREIGN KEY (`id_sala`)
            REFERENCES `cinemadb`.`sala` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`utente`
(
    `id`      VARCHAR(45) NOT NULL,
    `nome`    VARCHAR(45) NOT NULL,
    `cognome` VARCHAR(45) NOT NULL,
    `email`   VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`biglietto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`biglietto`
(
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `id_posto`      INT         NOT NULL,
    `id_proiezione` INT         NOT NULL,
    `id_utente`     VARCHAR(45) NOT NULL,
    `prezzo`        DOUBLE      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Biglietto_Utente1_idx` (`id_utente` ASC) VISIBLE,
    INDEX `fk_Biglietto_Posto1_idx` (`id_posto` ASC) VISIBLE,
    INDEX `fk_Biglietto_Proiezione1_idx` (`id_proiezione` ASC) VISIBLE,
    CONSTRAINT `fk_Biglietto_Posto1`
        FOREIGN KEY (`id_posto`)
            REFERENCES `cinemadb`.`posto` (`id`),
    CONSTRAINT `fk_Biglietto_Proiezione1`
        FOREIGN KEY (`id_proiezione`)
            REFERENCES `cinemadb`.`proiezione` (`id`),
    CONSTRAINT `fk_Biglietto_Utente1`
        FOREIGN KEY (`id_utente`)
            REFERENCES `cinemadb`.`utente` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`dipendente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`dipendente`
(
    `id_utente` VARCHAR(45) NOT NULL,
    `id_cinema` INT         NOT NULL,
    INDEX `fk_Dipendente_Utente1_idx` (`id_utente` ASC) VISIBLE,
    INDEX `fk_Dipendente_Cinema1_idx` (`id_cinema` ASC) VISIBLE,
    PRIMARY KEY (`id_utente`),
    CONSTRAINT `fk_Dipendente_Cinema1`
        FOREIGN KEY (`id_cinema`)
            REFERENCES `cinemadb`.`cinema` (`id`),
    CONSTRAINT `fk_Dipendente_Utente1`
        FOREIGN KEY (`id_utente`)
            REFERENCES `cinemadb`.`utente` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`turno`
(
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `inizio`        TIME        NOT NULL,
    `fine`          TIME        NOT NULL,
    `id_dipendente` VARCHAR(45) NOT NULL,
    `data`          DATE        NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Turno_Dipendente1_idx` (`id_dipendente` ASC) VISIBLE,
    CONSTRAINT `fk_Turno_Dipendente1`
        FOREIGN KEY (`id_dipendente`)
            REFERENCES `cinemadb`.`dipendente` (`id_utente`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`turno_proiezionista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`turno_proiezionista`
(
    `id_turno` INT NOT NULL,
    `id_sala`  INT NOT NULL,
    PRIMARY KEY (`id_turno`, `id_sala`),
    INDEX `fk_turno_proiezionista_turno1_idx` (`id_turno` ASC) VISIBLE,
    INDEX `fk_turno_proiezionista_sala1_idx` (`id_sala` ASC) VISIBLE,
    CONSTRAINT `fk_turno_proiezionista_sala1`
        FOREIGN KEY (`id_sala`)
            REFERENCES `cinemadb`.`sala` (`id`),
    CONSTRAINT `fk_turno_proiezionista_turno1`
        FOREIGN KEY (`id_turno`)
            REFERENCES `cinemadb`.`turno` (`id`)
            ON DELETE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`ruolo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`ruolo`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinemadb`.`ruolo_utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`ruolo_utente`
(
    `id_utente` VARCHAR(45) NOT NULL,
    `id_ruolo`  INT         NOT NULL,
    PRIMARY KEY (`id_utente`, `id_ruolo`),
    INDEX `id_utente_idx` (`id_utente` ASC) VISIBLE,
    CONSTRAINT `id_ruolo`
        FOREIGN KEY (`id_ruolo`)
            REFERENCES `cinemadb`.`ruolo` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `id_utente`
        FOREIGN KEY (`id_utente`)
            REFERENCES `cinemadb`.`utente` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

USE `test_schema`;

-- -----------------------------------------------------
-- Table `test_schema`.`test_table`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test_schema`.`test_table`
(
    `id`     INT         NOT NULL AUTO_INCREMENT,
    `field1` VARCHAR(45) NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

USE `cinemadb`;

-- -----------------------------------------------------
-- procedure login
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `login`(IN emailParam VARCHAR(255), IN passwordParam VARCHAR(255))
BEGIN
    SELECT * FROM cinemadb.utente WHERE email = emailParam AND password = passwordParam;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola`()
BEGIN
    CALL cinemadb.popola_ruoli();
    CALL cinemadb.popola_utenti();
    CALL cinemadb.popola_ruoli_utente();
    CALL cinemadb.popola_cinema();
    CALL cinemadb.popola_dipendenti();
    CALL cinemadb.popola_film();
    CALL cinemadb.popola_sale();
    CALL cinemadb.popola_posti();
    CALL cinemadb.popola_proiezioni();
    CALL cinemadb.popola_turni();
    CALL cinemadb.popola_turni_proiezionista();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_cinema
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_cinema`()
BEGIN
    INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `citta`)
    VALUES ('Comunale', 'via recanati 3', 'Recanati');
    INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `citta`)
    VALUES ('MultiPlex', 'via garibaldi 1', 'Teramo');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_dipendenti
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_dipendenti`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE id_utente VARCHAR(45);
    DECLARE maschera INT DEFAULT 1;
    DECLARE proiezionista INT DEFAULT 1;
    DECLARE nmaschere INT DEFAULT (SELECT COUNT(*)
                                   FROM utente
                                   WHERE id IN (SELECT ruolo_utente.id_utente
                                                FROM ruolo_utente,
                                                     ruolo
                                                WHERE id = id_ruolo
                                                  AND nome = "maschera"));
    DECLARE nproiezionisti INT DEFAULT (SELECT COUNT(*)
                                        FROM utente
                                        WHERE id IN (SELECT ruolo_utente.id_utente
                                                     FROM ruolo_utente,
                                                          ruolo
                                                     WHERE id = id_ruolo
                                                       AND nome = "proiezionista"));
    DECLARE cmaschera CURSOR FOR SELECT id
                                 FROM utente
                                 WHERE id IN (SELECT ruolo_utente.id_utente
                                              FROM ruolo_utente,
                                                   ruolo
                                              WHERE id = id_ruolo
                                                AND nome = "maschera");
    DECLARE cproiezionista CURSOR FOR SELECT id
                                      FROM utente
                                      WHERE id IN (SELECT ruolo_utente.id_utente
                                                   FROM ruolo_utente,
                                                        ruolo
                                                   WHERE id = id_ruolo
                                                     AND nome = "proiezionista");
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN cmaschera;
    popola_maschere:
    LOOP
        FETCH cmaschera INTO id_utente;
        IF done THEN
            LEAVE popola_maschere;
        END IF;
        IF maschera <= nmaschere / 2 THEN
            INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`) VALUES (id_utente, 1);
        ELSE
            INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`) VALUES (id_utente, 2);
        END IF;
        SET maschera = maschera + 1;
    END LOOP;
    CLOSE cmaschera;
    SET done = FALSE;
    OPEN cproiezionista;
    popola_proiezionista:
    LOOP
        FETCH cproiezionista INTO id_utente;
        IF done THEN
            LEAVE popola_proiezionista;
        END IF;
        IF proiezionista <= nproiezionisti / 2 THEN
            INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`) VALUES (id_utente, 1);
        ELSE
            INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`) VALUES (id_utente, 2);
        END IF;
        SET proiezionista = proiezionista + 1;
    END LOOP;
    CLOSE cproiezionista;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_film
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_film`()
BEGIN
    DECLARE film INT DEFAULT 15;
    DECLARE ultimo INT DEFAULT 22;
    WHILE film < (ultimo + 1)
        DO
            INSERT INTO `cinemadb`.`film` (`id`) VALUES (film);
            SET film = film + 1;
        END WHILE;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_posti
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_posti`()
BEGIN
    DECLARE nsala INT DEFAULT (SELECT COUNT(*) FROM sala);
    DECLARE counter INT DEFAULT 0;
    WHILE counter < nsala * 7
        DO
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, CONCAT('A', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, CONCAT('B', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, CONCAT('C', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, CONCAT('D', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, CONCAT('E', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, CONCAT('F', counter % 7));
            SET counter = counter + 1;
        END WHILE;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_proiezioni
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_proiezioni`()
BEGIN
    DECLARE ora INT;
    DECLARE nfilm INT DEFAULT (SELECT count(*) FROM film);
    DECLARE first_film INT DEFAULT (SELECT id FROM film ORDER BY id ASC LIMIT 1);
    DECLARE last_film INT DEFAULT (SELECT id FROM film ORDER BY id DESC LIMIT 1);
    DECLARE film INT DEFAULT first_film;
    DECLARE nsale INT DEFAULT (SELECT count(*) FROM sala);
    DECLARE sala INT;
    DECLARE _data DATE DEFAULT DATE_SUB(CURDATE(), INTERVAL 15 DAY);

    WHILE DATEDIFF(_data, CURDATE()) < 45
        DO
            IF (WEEKDAY(_data) != 0 AND WEEKDAY(_data) != 1) THEN
                SET ora = 15;
                WHILE ora < 22
                    DO
                        SET sala = 1;
                        WHILE sala < (nsale + 1)
                            DO
                                IF (DATEDIFF(_data, CURDATE()) < 15) THEN
                                    IF (film > (last_film - (nfilm / 2))) THEN
                                        SET film = first_film;
                                    END IF;
                                ELSE
                                    IF ((film < (last_film - (nfilm / 2)) + 1) OR film > last_film) THEN
                                        SET film = first_film + (nfilm / 2);
                                    END IF;
                                END IF;
                                INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_film`, `data`, `inizio`, `prezzo_base`)
                                VALUES (sala, film, _data, CONCAT(ora, ':00'), 5);
                                SET sala = sala + 1;
                                SET film = film + 1;
                            END WHILE;
                        SET ora = ora + 2;
                    END WHILE;
            END IF;
            SET _data = DATE_ADD(_data, INTERVAL 1 DAY);
        END WHILE;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_sale
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_sale`()
BEGIN
    DECLARE ncinema INT DEFAULT (SELECT COUNT(*) FROM cinema) + 1;
    DECLARE cinema INT DEFAULT 1;
    DECLARE sala INT;
    WHILE cinema < ncinema
        DO
            SET sala = 1;
            WHILE sala <= cinema
                DO
                    INSERT INTO `cinemadb`.`sala` (`id_cinema`, `numero`)
                    VALUES (cinema, CONCAT('A', sala));
                    INSERT INTO `cinemadb`.`sala` (`id_cinema`, `numero`)
                    VALUES (cinema, CONCAT('B', sala));
                    SET sala = sala + 1;
                END WHILE;
            SET cinema = cinema + 1;
        END WHILE;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_turni
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_turni`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE _data DATE DEFAULT DATE_SUB(CURDATE(), INTERVAL 15 DAY);
    DECLARE inizio VARCHAR(7) DEFAULT '14:30';
    DECLARE fine VARCHAR(7) DEFAULT '23:30';
    DECLARE id_utente VARCHAR(45);
    DECLARE nmaschere INT DEFAULT (SELECT COUNT(*)
                                   FROM utente
                                   WHERE id IN (SELECT ruolo_utente.id_utente
                                                FROM ruolo_utente,
                                                     ruolo
                                                WHERE id = id_ruolo
                                                  AND nome = "maschera"));
    DECLARE nproiezionisti INT DEFAULT (SELECT COUNT(*)
                                        FROM utente
                                        WHERE id IN (SELECT ruolo_utente.id_utente
                                                     FROM ruolo_utente,
                                                          ruolo
                                                     WHERE id = id_ruolo
                                                       AND nome = "proiezionista"));
    DECLARE cmaschera CURSOR FOR SELECT id
                                 FROM utente
                                 WHERE id IN (SELECT ruolo_utente.id_utente
                                              FROM ruolo_utente,
                                                   ruolo
                                              WHERE id = id_ruolo
                                                AND nome = "maschera");
    DECLARE cproiezionista CURSOR FOR SELECT id
                                      FROM utente
                                      WHERE id IN (SELECT ruolo_utente.id_utente
                                                   FROM ruolo_utente,
                                                        ruolo
                                                   WHERE id = id_ruolo
                                                     AND nome = "proiezionista");
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    WHILE DATEDIFF(_data, CURDATE()) < 45
        DO
            IF (WEEKDAY(_data) != 0 AND WEEKDAY(_data) != 1) THEN
                OPEN cmaschera;
                popola_maschera:
                LOOP
                    FETCH cmaschera INTO id_utente;
                    IF done THEN
                        LEAVE popola_maschera;
                    END IF;
                    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
                    VALUES (inizio, fine, id_utente, _data);
                END LOOP;
                CLOSE cmaschera;
                SET done = FALSE;
                OPEN cproiezionista;
                popola_proiezionista:
                LOOP
                    FETCH cproiezionista INTO id_utente;
                    IF done THEN
                        LEAVE popola_proiezionista;
                    END IF;
                    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
                    VALUES (inizio, fine, id_utente, _data);
                END LOOP;
                CLOSE cproiezionista;
                SET done = FALSE;
            END IF;
            SET _data = DATE_ADD(_data, INTERVAL 1 DAY);
        END WHILE;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_turni_proiezionista
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_turni_proiezionista`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE nsale INT DEFAULT (SELECT COUNT(*) FROM sala);
    DECLARE sala INT DEFAULT 1;
    DECLARE id_turno INT;
    DECLARE cturno CURSOR FOR SELECT id
                              FROM turno
                              WHERE id_dipendente IN (SELECT ruolo_utente.id_utente
                                                      FROM ruolo_utente,
                                                           ruolo
                                                      WHERE id = id_ruolo
                                                        AND nome = "proiezionista");
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN cturno;
    popola_turni:
    LOOP
        FETCH cturno INTO id_turno;
        IF done THEN
            LEAVE popola_turni;
        END IF;
        IF (SELECT id_cinema FROM sala WHERE sala.id = sala) != (SELECT id_cinema
                                                                 FROM dipendente
                                                                 WHERE id_utente = (SELECT id_dipendente FROM turno WHERE id = id_turno)) THEN
            SET sala = (SELECT id
                        FROM sala
                        WHERE id_cinema = (SELECT id_cinema
                                           FROM dipendente
                                           WHERE id_utente = (SELECT id_dipendente FROM turno WHERE id = id_turno))
                        LIMIT 1);
        END IF;
        INSERT INTO `cinemadb`.`turno_proiezionista` (`id_turno`, `id_sala`) VALUES (id_turno, sala);
        SET sala = (sala % nsale) + 1;
    END LOOP;
    CLOSE cturno;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_utenti
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_utenti`()
BEGIN
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('T8SP2uHYdHZfBk6uh3fJ356Sji52', 'Fabio', 'Buracchi', 'fb@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('gVUYDMojhmeFkErlbF0WWLQWMPn1', 'Massimo', 'Mazzetti', 'mm@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('vLmLJCuTwZZap4t4ngUclwUzwZ62', 'Ivan', 'Palmieri', 'ip@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('bvMrqf60V8OwETW9aEjeYbM9I0b2', 'Mario', 'Rossi', 'mr@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('JFDW7zA7pVh53im1Sl7fSnvaxML2', 'Luigi', 'Bianchi', 'lb@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('mg9eBHkPumcssl9dvrotbZqDbk62', 'Steve', 'Jobs', 'sj@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('C9o3hGHcfZXEXAjtJtlUEckE9WC2', 'Bill', 'Gates', 'bg@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('Tf6NcK4d8feY2TpuJVsqS74pbLf1', 'Elon', 'Musk', 'em@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('ikBgUbCQYlevLnLj7SOUb1PvS0h2', 'Alan', 'Turing', 'at@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('7jYsjrrXeFSUpu33TsdYwV135mx1', 'James', 'Gosling', 'jg@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('L02YH8zWNJXzXcwRIJSDyx3GOqC3', 'Dennis', 'Ritchie', 'dr@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('qTNkqPTioQO3cv953AqC3NR5NDf2', 'Larry', 'Page', 'lp@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('5KClU7hbNgedJAwLuF9eFVl6Qzz2', 'Mark', 'Zuckerberg', 'mz@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('ppgJVL8wS9bdjWxCxs6bll2K0Xs1', 'Jeff', 'Bezos', 'jb@cinehub.com');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_ruoli
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_ruoli`()
BEGIN
    INSERT INTO `cinemadb`.`ruolo` (`nome`) VALUES ('cliente');
    INSERT INTO `cinemadb`.`ruolo` (`nome`) VALUES ('manager');
    INSERT INTO `cinemadb`.`ruolo` (`nome`) VALUES ('dipendente');
    INSERT INTO `cinemadb`.`ruolo` (`nome`) VALUES ('maschera');
    INSERT INTO `cinemadb`.`ruolo` (`nome`) VALUES ('proiezionista');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_ruoli_utente
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola_ruoli_utente`()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE id_utente VARCHAR(45);
    DECLARE id_cliente INT DEFAULT (SELECT id FROM ruolo WHERE nome = "cliente");
    DECLARE id_manager INT DEFAULT (SELECT id FROM ruolo WHERE nome = "manager");
    DECLARE id_dipendente INT DEFAULT (SELECT id FROM ruolo WHERE nome = "dipendente");
    DECLARE id_maschera INT DEFAULT (SELECT id FROM ruolo WHERE nome = "maschera");
    DECLARE id_proiezionista INT DEFAULT (SELECT id FROM ruolo WHERE nome = "proiezionista");
    DECLARE cmanager CURSOR FOR SELECT id FROM utente LIMIT 0, 1;
    DECLARE ccliente CURSOR FOR SELECT id FROM utente LIMIT 1, 3;
    DECLARE cmaschera CURSOR FOR SELECT id FROM utente LIMIT 4, 4;
    DECLARE cproiezionista CURSOR FOR SELECT id FROM utente LIMIT 8, 6;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN cmanager;
    popola_manager:
    LOOP
        FETCH cmanager INTO id_utente;
        IF done THEN
            LEAVE popola_manager;
        END IF;
        INSERT INTO `cinemadb`.`ruolo_utente` (`id_utente`, `id_ruolo`) VALUES (id_utente, id_cliente);
        INSERT INTO `cinemadb`.`ruolo_utente` (`id_utente`, `id_ruolo`) VALUES (id_utente, id_manager);
    END LOOP;
    CLOSE cmanager;
    OPEN ccliente;
    SET done = FALSE;
    popola_cliente:
    LOOP
        FETCH ccliente INTO id_utente;
        IF done THEN
            LEAVE popola_cliente;
        END IF;
        INSERT INTO `cinemadb`.`ruolo_utente` (`id_utente`, `id_ruolo`) VALUES (id_utente, id_cliente);
    END LOOP;
    CLOSE ccliente;
    OPEN cmaschera;
    SET done = FALSE;
    popola_maschera:
    LOOP
        FETCH cmaschera INTO id_utente;
        IF done THEN
            LEAVE popola_maschera;
        END IF;
        INSERT INTO `cinemadb`.`ruolo_utente` (`id_utente`, `id_ruolo`) VALUES (id_utente, id_cliente);
        INSERT INTO `cinemadb`.`ruolo_utente` (`id_utente`, `id_ruolo`) VALUES (id_utente, id_dipendente);
        INSERT INTO `cinemadb`.`ruolo_utente` (`id_utente`, `id_ruolo`) VALUES (id_utente, id_maschera);
    END LOOP;
    CLOSE cmaschera;
    OPEN cproiezionista;
    SET done = FALSE;
    popola_proiezionista:
    LOOP
        FETCH cproiezionista INTO id_utente;
        IF done THEN
            LEAVE popola_proiezionista;
        END IF;
        INSERT INTO `cinemadb`.`ruolo_utente` (`id_utente`, `id_ruolo`) VALUES (id_utente, id_cliente);
        INSERT INTO `cinemadb`.`ruolo_utente` (`id_utente`, `id_ruolo`) VALUES (id_utente, id_dipendente);
        INSERT INTO `cinemadb`.`ruolo_utente` (`id_utente`, `id_ruolo`) VALUES (id_utente, id_proiezionista);
    END LOOP;
    CLOSE cproiezionista;
END$$

DELIMITER ;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
