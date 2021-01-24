-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cinemadb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cinemadb`;

-- -----------------------------------------------------
-- Schema cinemadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinemadb` DEFAULT CHARACTER SET utf8mb4;
SHOW WARNINGS;
USE `cinemadb`;

-- -----------------------------------------------------
-- Table `cinemadb`.`utente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`utente`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`utente`
(
    `id`       INT                                                             NOT NULL AUTO_INCREMENT,
    `nome`     VARCHAR(45)                                                     NOT NULL,
    `cognome`  VARCHAR(45)                                                     NOT NULL,
    `email`    VARCHAR(45)                                                     NOT NULL,
    `password` VARCHAR(45)                                                     NOT NULL,
    `ruolo`    ENUM ('amministratore', 'maschera', 'proiezionista', 'cliente') NOT NULL,
    PRIMARY KEY (`id`)
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`cinema`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`cinema`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`cinema`
(
    `id`        INT         NOT NULL AUTO_INCREMENT,
    `nome`      VARCHAR(45) NOT NULL,
    `indirizzo` VARCHAR(45) NOT NULL,
    `telefono`  VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`)
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`dipendente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`dipendente`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`dipendente`
(
    `id_utente`       INT NOT NULL,
    `id_cinema`       INT NOT NULL,
    `ore_settimanali` INT NULL,
    PRIMARY KEY (`id_utente`),
    INDEX `fk_Dipendente_Utente1_idx` (`id_utente` ASC) VISIBLE,
    INDEX `fk_Dipendente_Cinema1_idx` (`id_cinema` ASC) VISIBLE,
    CONSTRAINT `fk_Dipendente_Utente1`
    FOREIGN KEY (`id_utente`)
    REFERENCES `cinemadb`.`utente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Dipendente_Cinema1`
    FOREIGN KEY (`id_cinema`)
    REFERENCES `cinemadb`.`cinema` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`turno`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`turno`
(
    `id`            INT      NOT NULL AUTO_INCREMENT,
    `inizio`        DATETIME NOT NULL,
    `fine`          DATETIME NOT NULL,
    `id_dipendente` INT      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Turno_Dipendente1_idx` (`id_dipendente` ASC) VISIBLE,
    CONSTRAINT `fk_Turno_Dipendente1`
    FOREIGN KEY (`id_dipendente`)
    REFERENCES `cinemadb`.`dipendente` (`id_utente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`richiesta_cambio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`richiesta_cambio`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`richiesta_cambio`
(
    `id`                 INT                                      NOT NULL AUTO_INCREMENT,
    `stato`              ENUM ('approvata', 'respinta', 'attesa') NOT NULL,
    `tipo`               ENUM ('cambio', 'scambio')               NOT NULL,
    `id_turno_attuale`   INT                                      NOT NULL,
    `id_turno_richiesto` INT                                      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_RichiestaCambio_Turno_idx` (`id_turno_attuale` ASC) VISIBLE,
    INDEX `fk_RichiestaCambio_Turno1_idx` (`id_turno_richiesto` ASC) VISIBLE,
    CONSTRAINT `fk_RichiestaCambio_Turno`
    FOREIGN KEY (`id_turno_attuale`)
    REFERENCES `cinemadb`.`turno` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_RichiestaCambio_Turno1`
    FOREIGN KEY (`id_turno_richiesto`)
    REFERENCES `cinemadb`.`turno` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`richiesta_ferie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`richiesta_ferie`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`richiesta_ferie`
(
    `id`             INT                                      NOT NULL AUTO_INCREMENT,
    `id_richiedente` INT                                      NOT NULL,
    `stato`          ENUM ('approvata', 'respinta', 'attesa') NOT NULL,
    `inizio`         DATETIME                                 NOT NULL,
    `fine`           DATETIME                                 NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_RichiestaFerie_Dipendente1_idx` (`id_richiedente` ASC) VISIBLE,
    CONSTRAINT `fk_RichiestaFerie_Dipendente1`
    FOREIGN KEY (`id_richiedente`)
    REFERENCES `cinemadb`.`dipendente` (`id_utente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`proiezionista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`proiezionista`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`proiezionista`
(
    `id_dipendente` INT NOT NULL,
    PRIMARY KEY (`id_dipendente`),
    INDEX `fk_Proiezionista_Dipendente1_idx` (`id_dipendente` ASC) VISIBLE,
    UNIQUE INDEX `idDIpendente_UNIQUE` (`id_dipendente` ASC) VISIBLE,
    CONSTRAINT `fk_Proiezionista_Dipendente1`
    FOREIGN KEY (`id_dipendente`)
    REFERENCES `cinemadb`.`dipendente` (`id_utente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`carta_di_credito`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`carta_di_credito`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`carta_di_credito`
(
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `id_utente`     INT         NOT NULL,
    `numero`        VARCHAR(45) NOT NULL,
    `data_scadenza` VARCHAR(45) NOT NULL,
    `cvv`           INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_CartaDiCredito_Utente1_idx` (`id_utente` ASC) VISIBLE,
    CONSTRAINT `fk_CartaDiCredito_Utente1`
    FOREIGN KEY (`id_utente`)
    REFERENCES `cinemadb`.`utente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`film`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`film`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`film`
(
    `id`     INT         NOT NULL,
    `durata` VARCHAR(45) NOT NULL,
    `attivo` TINYINT     NOT NULL,
    PRIMARY KEY (`id`)
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`sala`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`sala`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`sala`
(
    `id`        INT NOT NULL AUTO_INCREMENT,
    `id_cinema` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Sala_Cinema1_idx` (`id_cinema` ASC) VISIBLE,
    CONSTRAINT `fk_Sala_Cinema1`
    FOREIGN KEY (`id_cinema`)
    REFERENCES `cinemadb`.`cinema` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`proiezione`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`proiezione`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`proiezione`
(
    `id`               INT      NOT NULL AUTO_INCREMENT,
    `id_sala`          INT      NOT NULL,
    `id_proiezionista` INT      NOT NULL,
    `id_film`          INT      NOT NULL,
    `inizio`           DATETIME NOT NULL,
    `incasso_totale`   DOUBLE   NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Proiezione_Film1_idx` (`id_film` ASC) VISIBLE,
    INDEX `fk_Proiezione_Sala1_idx` (`id_sala` ASC) VISIBLE,
    INDEX `fk_Proiezione_Proiezionista1_idx` (`id_proiezionista` ASC) VISIBLE,
    CONSTRAINT `fk_Proiezione_Film1`
    FOREIGN KEY (`id_film`)
    REFERENCES `cinemadb`.`film` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Proiezione_Sala1`
    FOREIGN KEY (`id_sala`)
    REFERENCES `cinemadb`.`sala` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Proiezione_Proiezionista1`
    FOREIGN KEY (`id_proiezionista`)
    REFERENCES `cinemadb`.`proiezionista` (`id_dipendente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`posto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`posto`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`posto`
(
    `id`      INT         NOT NULL AUTO_INCREMENT,
    `fila`    VARCHAR(45) NOT NULL,
    `numero`  INT         NOT NULL,
    `id_sala` INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Posto_Sala1_idx` (`id_sala` ASC) VISIBLE,
    CONSTRAINT `fk_Posto_Sala1`
    FOREIGN KEY (`id_sala`)
    REFERENCES `cinemadb`.`sala` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`biglietto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`biglietto`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`biglietto`
(
    `id`            INT                                         NOT NULL AUTO_INCREMENT,
    `id_posto`      INT                                         NOT NULL,
    `id_proiezione` INT                                         NOT NULL,
    `id_utente`     INT                                         NOT NULL,
    `prezzo`        DOUBLE                                      NOT NULL,
    `stato`         ENUM ('attivo', 'rimborsato', 'utilizzato') NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Biglietto_Utente1_idx` (`id_utente` ASC) VISIBLE,
    INDEX `fk_Biglietto_Posto1_idx` (`id_posto` ASC) VISIBLE,
    INDEX `fk_Biglietto_Proiezione1_idx` (`id_proiezione` ASC) VISIBLE,
    CONSTRAINT `fk_Biglietto_Utente1`
    FOREIGN KEY (`id_utente`)
    REFERENCES `cinemadb`.`utente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Biglietto_Posto1`
    FOREIGN KEY (`id_posto`)
    REFERENCES `cinemadb`.`posto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `fk_Biglietto_Proiezione1`
    FOREIGN KEY (`id_proiezione`)
    REFERENCES `cinemadb`.`proiezione` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    )
    ENGINE = InnoDB;

SHOW WARNINGS;
USE `cinemadb`;

-- -----------------------------------------------------
-- procedure film_attivi
-- -----------------------------------------------------

USE `cinemadb`;
DROP procedure IF EXISTS `cinemadb`.`film_attivi`;
SHOW WARNINGS;

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `film_attivi`()
BEGIN
select * from cinemadb.film where attivo = True;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure login
-- -----------------------------------------------------

USE `cinemadb`;
DROP procedure IF EXISTS `cinemadb`.`login`;
SHOW WARNINGS;

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `login`(IN emailParam VARCHAR(255), IN passwordParam VARCHAR(255))
BEGIN
select *
from cinemadb.utente
where email = emailParam
  and password = passwordParam;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure rimborso
-- -----------------------------------------------------

USE `cinemadb`;
DROP procedure IF EXISTS `cinemadb`.`rimborso`;
SHOW WARNINGS;

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `rimborso`(IN idBigliettoParam INT)
BEGIN
update cinemadb.biglietto
set stato = "rimborsato"
where id = idBigliettoParam;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure trova_posti_liberi
-- -----------------------------------------------------

USE `cinemadb`;
DROP procedure IF EXISTS `cinemadb`.`trova_posti_liberi`;
SHOW WARNINGS;

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `trova_posti_liberi`(IN idProiezioneParam INT)
BEGIN
select *
from cinemadb.posto,
     cinemadb.sala,
     cinemadb.proiezione
where proiezione.id = idProiezioneParam
  and sala.id = proiezione.id_sala
  and posto.id_sala = sala.id
  and posto.id not in (
    select posto.id
    from cinemadb.biglietto,
         cinemadb.posto
    where biglietto.id_proiezione = idProiezioneParam
      and posto.id = biglietto.id_posto
      and biglietto.stato = "rimborsato"
);
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure trova_proiezioni
-- -----------------------------------------------------

USE `cinemadb`;
DROP procedure IF EXISTS `cinemadb`.`trova_proiezioni`;
SHOW WARNINGS;

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `trova_proiezioni`(IN idFilmParam INT)
BEGIN
select *
from cinemadb.proiezione
where proiezione.id_film = idFilmParam
  and proiezione.inizio > now();
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure popola
-- -----------------------------------------------------

USE `cinemadb`;
DROP procedure IF EXISTS `cinemadb`.`popola`;
SHOW WARNINGS;

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `popola`()
BEGIN
    /* Utente */
INSERT INTO `cinemadb`.`utente` (`nome`, `cognome`, `email`, `password`, `ruolo`)
VALUES ('fab', 'bur', 'f@b.c', 'pippo', 'amministratore');
INSERT INTO `cinemadb`.`utente` (`nome`, `cognome`, `email`, `password`, `ruolo`)
VALUES ('ivan', 'pal', 'i@p.c', 'pippo', 'proiezionista');
INSERT INTO `cinemadb`.`utente` (`nome`, `cognome`, `email`, `password`, `ruolo`)
VALUES ('max', 'max', 'm@m.c', 'pippo', 'maschera');
INSERT INTO `cinemadb`.`utente` (`nome`, `cognome`, `email`, `password`, `ruolo`)
VALUES ('cli', 'ent', 'c@e.c', 'pippo', 'cliente');
/* Cinema */
INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`)
VALUES ('multisala', 'via le mani dal naso', '00000');
INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`) VALUES ('monosala', 'via etrusca', '00001');
INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`) VALUES ('microsala', 'via dante', '00002');
INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`) VALUES ('supersala', 'via roma', '00003');
INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`) VALUES ('megasala', 'via milano', '00004');
/* Sala */
INSERT INTO `cinemadb`.`sala` (`id_cinema`) VALUES ('1');
INSERT INTO `cinemadb`.`sala` (`id_cinema`) VALUES ('1');
INSERT INTO `cinemadb`.`sala` (`id_cinema`) VALUES ('2');
INSERT INTO `cinemadb`.`sala` (`id_cinema`) VALUES ('1');
INSERT INTO `cinemadb`.`sala` (`id_cinema`) VALUES ('2');
INSERT INTO `cinemadb`.`sala` (`id_cinema`) VALUES ('2');
/* Dipendente */
INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ore_settimanali`) VALUES ('1', '2', '36');
INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ore_settimanali`) VALUES ('2', '1', '30');
/* Posto */
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('1', 'A', '1');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('2', 'A', '1');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('3', 'A', '1');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('1', 'B', '3');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('2', 'B', '3');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('3', 'B', '3');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('1', 'A', '3');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('2', 'A', '3');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('3', 'A', '3');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('1', 'B', '1');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('2', 'B', '1');
INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('3', 'B', '1');
/* Richiesta ferie */
INSERT INTO `cinemadb`.`richiesta_ferie` (`id_richiedente`, `stato`, `inizio`, `fine`)
VALUES ('1', 'approvata', '2020-12-15 00:00:00', '2020-12-30 13:00:00');
INSERT INTO `cinemadb`.`richiesta_ferie` (`id_richiedente`, `stato`, `inizio`, `fine`)
VALUES ('2', 'respinta', '2020-12-16 10:00:00', '2021-01-15 20:00:00');
INSERT INTO `cinemadb`.`richiesta_ferie` (`id_richiedente`, `stato`, `inizio`, `fine`)
VALUES ('1', 'attesa', '2020-11-15 00:00:00', '2020-12-15 20:00:00');
/* Turno */
INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`)
VALUES ('2020-12-15 12:00:00', '2020-12-15 18:00:00', '1');
INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`)
VALUES ('2020-12-16 08:00:00', '2020-12-16 12:00:00', '1');
INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`)
VALUES ('2020-11-15 18:00:00', '2022-12-16 00:00:00', '2');
INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`)
VALUES ('2020-12-17 10:00:00', '2020-12-18 00:00:00', '2');
/* Richiesta cambio */
INSERT INTO `cinemadb`.`richiesta_cambio` (`stato`, `id_turno_attuale`, `id_turno_richiesto`, `tipo`)
VALUES ('approvata', '1', '3', 'cambio');
INSERT INTO `cinemadb`.`richiesta_cambio` (`stato`, `id_turno_attuale`, `id_turno_richiesto`, `tipo`)
VALUES ('respinta', '2', '4', 'scambio');
INSERT INTO `cinemadb`.`richiesta_cambio` (`stato`, `id_turno_attuale`, `id_turno_richiesto`, `tipo`)
VALUES ('attesa', '3', '2', 'scambio');
INSERT INTO `cinemadb`.`richiesta_cambio` (`stato`, `id_turno_attuale`, `id_turno_richiesto`, `tipo`)
VALUES ('attesa', '3', '2', 'cambio');
/* Proiezionista */
INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('2');
/* Film */
INSERT INTO `cinemadb`.`film` (`id`, `durata`, `attivo`) VALUES ('1', '01:30:00', True);
INSERT INTO `cinemadb`.`film` (`id`, `durata`, `attivo`) VALUES ('2', '01:00:00', True);
INSERT INTO `cinemadb`.`film` (`id`, `durata`, `attivo`) VALUES ('3', '00:30:00', True);
INSERT INTO `cinemadb`.`film` (`id`, `durata`, `attivo`) VALUES ('4', '02:00:00', False);
/* Proiezione */
INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `inizio`)
VALUES ('1', '2', '1', '2020-11-20 22:00:00');
INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `inizio`)
VALUES ('3', '2', '1', '2020-12-16 18:00:00');
INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `inizio`)
VALUES ('1', '2', '3', '2020-11-16 00:00:00');
INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `inizio`)
VALUES ('2', '2', '1', '2021-11-20 22:00:00');
/* Biglietto */
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('1', '1', '4', '6.5', 'utilizzato');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('4', '2', '4', '6', 'rimborsato');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('3', '3', '3', '12', 'utilizzato');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('2', '4', '2', '2', 'attivo');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('5', '1', '2', '5', 'attivo');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('6', '2', '1', '9', 'utilizzato');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('9', '3', '1', '8', 'rimborsato');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('4', '2', '4', '6', 'rimborsato');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('4', '2', '4', '6', 'attivo');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('4', '2', '4', '6', 'utilizzato');
INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
VALUES ('10', '4', '2', '7', 'attivo');
/* Carta di credito */
INSERT INTO `cinemadb`.`carta_di_credito` (`id_utente`, `numero`, `data_scadenza`, `cvv`)
VALUES ('2', '1', '2020-10-01', '123');
INSERT INTO `cinemadb`.`carta_di_credito` (`id_utente`, `numero`, `data_scadenza`, `cvv`)
VALUES ('3', '2', '2020-11-09', '456');
INSERT INTO `cinemadb`.`carta_di_credito` (`id_utente`, `numero`, `data_scadenza`, `cvv`)
VALUES ('4', '3', '2020-09-08', '223');
END$$

DELIMITER ;
SHOW WARNINGS;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
