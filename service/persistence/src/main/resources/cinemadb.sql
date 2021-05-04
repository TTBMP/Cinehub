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
    `id_utente` VARCHAR(45)                        NOT NULL,
    `id_cinema` INT                                NOT NULL,
    `ruolo`     ENUM ('proiezionista', 'maschera') NOT NULL,
    INDEX `fk_Dipendente_Utente1_idx` (`id_utente` ASC) VISIBLE,
    INDEX `fk_Dipendente_Cinema1_idx` (`id_cinema` ASC) VISIBLE,
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
    `turno_id` INT NOT NULL,
    `sala_id`  INT NOT NULL,
    INDEX `fk_turno_proiezionista_turno1_idx` (`turno_id` ASC) VISIBLE,
    INDEX `fk_turno_proiezionista_sala1_idx` (`sala_id` ASC) VISIBLE,
    CONSTRAINT `fk_turno_proiezionista_sala1`
        FOREIGN KEY (`sala_id`)
            REFERENCES `cinemadb`.`sala` (`id`),
    CONSTRAINT `fk_turno_proiezionista_turno1`
        FOREIGN KEY (`turno_id`)
            REFERENCES `cinemadb`.`turno` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

USE `cinemadb`;

-- -----------------------------------------------------
-- procedure login
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `login`(IN emailParam VARCHAR(255), IN passwordParam VARCHAR(255))
BEGIN
    SELECT *
    FROM cinemadb.utente
    WHERE email = emailParam
      AND password = passwordParam;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `popola`()
BEGIN
    CALL cinemadb.popola_utente();
    CALL cinemadb.popola_cinema();
    CALL cinemadb.popola_dipendente();
    CALL cinemadb.popola_film();
    CALL cinemadb.popola_sala();
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
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `popola_cinema`()
BEGIN
    INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `citta`)
    VALUES ('Comunale', 'via recanati 3', 'Recanati');
    INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `citta`)
    VALUES ('MultiPlex', 'via garibaldi 1', 'Teramo');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_dipendente
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `popola_dipendente`()
BEGIN
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('0', '1', 'maschera');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('1', '1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('2', '2', 'maschera');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('3', '1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('5', '1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('7', '1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('9', '1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('11', '1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('13', '2', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('15', '2', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('17', '2', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('19', '2', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('21', '2', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('23', '2', 'proiezionista');
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_film
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `popola_film`()
BEGIN
    declare film int;
    declare ultimo int;
    set film = 15;
    set ultimo = 22;
    while film < (ultimo + 1)
        do
            INSERT INTO `cinemadb`.`film` (`id`) VALUES (film);
            set film = film + 1;
        end while;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_posti
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `popola_posti`()
BEGIN
    DECLARE nsala INT;
    DECLARE counter INT;
    SET nsala = (select count(*) from sala);
    SET counter = 0;
    WHILE counter < nsala * 7
        DO
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, concat('A', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, concat('B', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, concat('C', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, concat('D', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, concat('E', counter % 7));
            INSERT INTO `cinemadb`.`posto` (`id_sala`, `posizione`)
            VALUES ((counter % nsala) + 1, concat('F', counter % 7));
            set counter = counter + 1;
        END WHILE;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_proiezioni
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `popola_proiezioni`()
BEGIN
    DECLARE anno VARCHAR(5);
    DECLARE mese VARCHAR(5);
    DECLARE giorno INT;
    DECLARE ora INT;
    declare film int;
    declare nfilm int;
    declare sala int;
    declare nsale int;
    set nfilm = (select count(*) from film);
    set nsale = (select count(*) from sala);

    SET anno = '2021';
    SET mese = '05';
    SET giorno = 10;
    SET ora = 12;

    WHILE giorno < 29
        DO
            set sala = 1;
            set film = 15;
            while sala < (nsale + 1)
                do
                    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_film`, `data`, `inizio`, `prezzo_base`)
                    VALUES (sala, film, concat(anno, '-', mese, '-', giorno), concat(ora, ':00'), 5);
                    set sala = sala + 1;
                    set film = film + 1;
                    if (film < 15 or film > 22) then
                        set film = 15;
                    end if;
                end while;
            IF ora > 22 THEN
                SET ora = 12;
            END IF;
            SET ora = ora + 1;
            SET giorno = giorno + 1;
        END WHILE;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_sala
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `popola_sala`()
BEGIN
    DECLARE ncinema INT;
    DECLARE cinema INT;
    DECLARE sala INT;
    SET ncinema = (select count(*) from cinema) + 1;
    SET cinema = 1;
    WHILE cinema < ncinema
        DO
            SET sala = 1;
            WHILE sala < 3
                DO
                    INSERT INTO `cinemadb`.`sala` (`id_cinema`, `numero`)
                    VALUES (cinema, concat('A', sala));
                    INSERT INTO `cinemadb`.`sala` (`id_cinema`, `numero`)
                    VALUES (cinema, concat('B', sala));
                    INSERT INTO `cinemadb`.`sala` (`id_cinema`, `numero`)
                    VALUES (cinema, concat('C', sala));
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
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `popola_turni`()
BEGIN
    DECLARE anno VARCHAR(7);
    DECLARE mese VARCHAR(7);
    DECLARE giorno INT;
    DECLARE inizio VARCHAR(7);
    DECLARE fine VARCHAR(7);
    DECLARE nmaschera INT;
    DECLARE nproiezionisti INT;
    DECLARE dipendente INT;
    SET nproiezionisti = (select count(*) from dipendente where ruolo = 'proiezionista');
    SET nmaschera = (select count(*) from dipendente where ruolo = 'maschera');
    SET anno = '2021';
    SET mese = '05';
    SET fine = '20:00';
    SET inizio = '12:00';
    SET giorno = 10;
    WHILE giorno < 28
        DO
            INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
            VALUES (inizio, fine, '0', concat(anno, '-', mese, '-', giorno));
            INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
            VALUES (inizio, fine, '2', concat(anno, '-', mese, '-', giorno));
            SET dipendente = 1;
            WHILE dipendente < 2 * nproiezionisti
                DO
                    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
                    VALUES (inizio, fine, dipendente, concat(anno, '-', mese, '-', giorno));
                    SET dipendente = dipendente + 2;
                END WHILE;
            SET giorno = giorno + 1;
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
    DECLARE ndipendenti int;
    DECLARE nmaschere int;
    DECLARE nturni int;
    DECLARE nsale int;
    DECLARE sala int;
    DECLARE turno int;
    SET ndipendenti = (select count(*) from dipendente);
    SET nmaschere = (select count(*) from dipendente where ruolo = 'maschera');
    SET nturni = (select count(*) from turno);
    SET nsale = (select count(*) from sala);
    SET sala = 1;
    SET turno = nmaschere + 1;
    WHILE (turno < (nturni + 1))
        DO
            WHILE ((turno - 1) % ndipendenti AND turno < (nturni + 1))
                DO
                    INSERT INTO `cinemadb`.`turno_proiezionista` (`turno_id`, `sala_id`)
                    VALUES (turno, sala);
                    SET turno = turno + 1;
                    SET sala = (sala % nsale) + 1;
                END WHILE;
            SET turno = turno + nmaschere;
        END WHILE;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola_utente
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE
    DEFINER = `root`@`localhost` PROCEDURE `popola_utente`()
BEGIN
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('0', 'Fabio', 'Buracchi', 'fb@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('1', 'Massimo', 'Mazzetti', 'mm@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('2', 'Ivan', 'Palmieri', 'ip@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('3', 'Mario', 'Rossi', 'mr@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('5', 'Luigi', 'Bianchi', 'lb@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('7', 'Steve', 'Jobs', 'sj@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('9', 'Bill', 'Gates', 'bg@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('11', 'Elon', 'Musk', 'em@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('13', 'Alan', 'Turing', 'at@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('15', 'James', 'Gosling', 'jg@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('17', 'Dennis', 'Ritchie', 'dr@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('19', 'Larry', 'Page', 'lp@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('21', 'Mark', 'Zuckerberg', 'mz@cinehub.com');
    INSERT INTO `cinemadb`.`utente` (`id`, `nome`, `cognome`, `email`)
    VALUES ('23', 'Jeff', 'Bezos', 'jb@cinehub.com');
END$$

DELIMITER ;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
