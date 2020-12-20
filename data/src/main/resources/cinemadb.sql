-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- ----------------------------------------------------
-- Create User
-- -----------------------------------------------------
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
-- -----------------------------------------------------
-- Schema cinemadb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cinemadb`;

-- -----------------------------------------------------
-- Schema cinemadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinemadb` DEFAULT CHARACTER SET UTF8MB4;
SHOW WARNINGS;
USE `cinemadb`;

-- -----------------------------------------------------
-- Table `cinemadb`.`Utente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Utente`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Utente`
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
-- Table `cinemadb`.`Cinema`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Cinema`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Cinema`
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
-- Table `cinemadb`.`Dipendente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Dipendente`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Dipendente`
(
    `idUtente`       INT NOT NULL,
    `idCinema`       INT NOT NULL,
    `oreSettimanali` INT NULL,
    PRIMARY KEY (`idUtente`),
    INDEX `fk_Dipendente_Utente1_idx` (`idUtente` ASC) VISIBLE,
    INDEX `fk_Dipendente_Cinema1_idx` (`idCinema` ASC) VISIBLE,
    CONSTRAINT `fk_Dipendente_Utente1`
        FOREIGN KEY (`idUtente`)
            REFERENCES `cinemadb`.`Utente` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Dipendente_Cinema1`
        FOREIGN KEY (`idCinema`)
            REFERENCES `cinemadb`.`Cinema` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`Turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Turno`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Turno`
(
    `id`           INT      NOT NULL AUTO_INCREMENT,
    `inizio`       DATETIME NOT NULL,
    `fine`         DATETIME NOT NULL,
    `idDipendente` INT      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Turno_Dipendente1_idx` (`idDipendente` ASC) VISIBLE,
    CONSTRAINT `fk_Turno_Dipendente1`
        FOREIGN KEY (`idDipendente`)
            REFERENCES `cinemadb`.`Dipendente` (`idUtente`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`RichiestaCambio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`RichiestaCambio`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`RichiestaCambio`
(
    `id`               INT                                      NOT NULL AUTO_INCREMENT,
    `stato`            ENUM ('approvata', 'respinta', 'attesa') NOT NULL,
    `tipo`             ENUM ('cambio', 'scambio')               NOT NULL,
    `idTurnoAttuale`   INT                                      NOT NULL,
    `idTurnoRichiesto` INT                                      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_RichiestaCambio_Turno_idx` (`idTurnoAttuale` ASC) VISIBLE,
    INDEX `fk_RichiestaCambio_Turno1_idx` (`idTurnoRichiesto` ASC) VISIBLE,
    CONSTRAINT `fk_RichiestaCambio_Turno`
        FOREIGN KEY (`idTurnoAttuale`)
            REFERENCES `cinemadb`.`Turno` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_RichiestaCambio_Turno1`
        FOREIGN KEY (`idTurnoRichiesto`)
            REFERENCES `cinemadb`.`Turno` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`RichiestaFerie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`RichiestaFerie`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`RichiestaFerie`
(
    `id`            INT                                      NOT NULL AUTO_INCREMENT,
    `richiedenteId` INT                                      NOT NULL,
    `stato`         ENUM ('approvata', 'respinta', 'attesa') NOT NULL,
    `inizio`        DATETIME                                 NOT NULL,
    `fine`          DATETIME                                 NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_RichiestaFerie_Dipendente1_idx` (`richiedenteId` ASC) VISIBLE,
    CONSTRAINT `fk_RichiestaFerie_Dipendente1`
        FOREIGN KEY (`richiedenteId`)
            REFERENCES `cinemadb`.`Dipendente` (`idUtente`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`Proiezionista`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Proiezionista`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Proiezionista`
(
    `idDIpendente` INT NOT NULL,
    PRIMARY KEY (`idDIpendente`),
    INDEX `fk_Proiezionista_Dipendente1_idx` (`idDIpendente` ASC) VISIBLE,
    UNIQUE INDEX `idDIpendente_UNIQUE` (`idDIpendente` ASC) VISIBLE,
    CONSTRAINT `fk_Proiezionista_Dipendente1`
        FOREIGN KEY (`idDIpendente`)
            REFERENCES `cinemadb`.`Dipendente` (`idUtente`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`CartaDiCredito`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`CartaDiCredito`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`CartaDiCredito`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `idUtente`     INT         NOT NULL,
    `numero`       VARCHAR(45) NOT NULL,
    `dataScadenza` VARCHAR(45) NOT NULL,
    `cvv`          INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_CartaDiCredito_Utente1_idx` (`idUtente` ASC) VISIBLE,
    CONSTRAINT `fk_CartaDiCredito_Utente1`
        FOREIGN KEY (`idUtente`)
            REFERENCES `cinemadb`.`Utente` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`Film`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Film`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Film`
(
    `id`     INT         NOT NULL,
    `durata` VARCHAR(45) NOT NULL,
    `attivo` TINYINT     NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`Sala`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Sala`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Sala`
(
    `id`       INT NOT NULL AUTO_INCREMENT,
    `idCinema` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Sala_Cinema1_idx` (`idCinema` ASC) VISIBLE,
    CONSTRAINT `fk_Sala_Cinema1`
        FOREIGN KEY (`idCinema`)
            REFERENCES `cinemadb`.`Cinema` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`Proiezione`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Proiezione`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Proiezione`
(
    `id`              INT      NOT NULL AUTO_INCREMENT,
    `idSala`          INT      NOT NULL,
    `idProiezionista` INT      NOT NULL,
    `idFilm`          INT      NOT NULL,
    `inizio`          DATETIME NOT NULL,
    `incassoTotale`   DOUBLE   NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Proiezione_Film1_idx` (`idFilm` ASC) VISIBLE,
    INDEX `fk_Proiezione_Sala1_idx` (`idSala` ASC) VISIBLE,
    INDEX `fk_Proiezione_Proiezionista1_idx` (`idProiezionista` ASC) VISIBLE,
    CONSTRAINT `fk_Proiezione_Film1`
        FOREIGN KEY (`idFilm`)
            REFERENCES `cinemadb`.`Film` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Proiezione_Sala1`
        FOREIGN KEY (`idSala`)
            REFERENCES `cinemadb`.`Sala` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Proiezione_Proiezionista1`
        FOREIGN KEY (`idProiezionista`)
            REFERENCES `cinemadb`.`Proiezionista` (`idDIpendente`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`Posto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Posto`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Posto`
(
    `id`     INT         NOT NULL AUTO_INCREMENT,
    `fila`   VARCHAR(45) NOT NULL,
    `numero` INT         NOT NULL,
    `idSala` INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Posto_Sala1_idx` (`idSala` ASC) VISIBLE,
    CONSTRAINT `fk_Posto_Sala1`
        FOREIGN KEY (`idSala`)
            REFERENCES `cinemadb`.`Sala` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `cinemadb`.`Biglietto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cinemadb`.`Biglietto`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `cinemadb`.`Biglietto`
(
    `id`           INT                                         NOT NULL AUTO_INCREMENT,
    `idPosto`      INT                                         NOT NULL,
    `idProiezione` INT                                         NOT NULL,
    `idUser`       INT                                         NOT NULL,
    `prezzo`       DOUBLE                                      NOT NULL,
    `stato`        ENUM ('attivo', 'rimborsato', 'utilizzato') NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_Biglietto_Utente1_idx` (`idUser` ASC) VISIBLE,
    INDEX `fk_Biglietto_Posto1_idx` (`idPosto` ASC) VISIBLE,
    INDEX `fk_Biglietto_Proiezione1_idx` (`idProiezione` ASC) VISIBLE,
    CONSTRAINT `fk_Biglietto_Utente1`
        FOREIGN KEY (`idUser`)
            REFERENCES `cinemadb`.`Utente` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Biglietto_Posto1`
        FOREIGN KEY (`idPosto`)
            REFERENCES `cinemadb`.`Posto` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_Biglietto_Proiezione1`
        FOREIGN KEY (`idProiezione`)
            REFERENCES `cinemadb`.`Proiezione` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;
USE `cinemadb`;

-- -----------------------------------------------------
-- procedure filmAttivi
-- -----------------------------------------------------

USE `cinemadb`;
DROP procedure IF EXISTS `cinemadb`.`filmAttivi`;
SHOW WARNINGS;

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `filmAttivi`()
BEGIN
    select * from `cinemadb`.film where attivo = True;
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
    from `cinemadb`.`utente`
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
    update `cinemadb`.`biglietto`
    set stato = "rimborsato"
    where id = idBigliettoParam;
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure trovaPostiLiberi
-- -----------------------------------------------------

USE `cinemadb`;
DROP procedure IF EXISTS `cinemadb`.`trovaPostiLiberi`;
SHOW WARNINGS;

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `trovaPostiLiberi`(IN idProiezioneParam INT)
BEGIN
    select *
    from `cinemadb`.`Posto`,
         `cinemadb`.`Sala`,
         `cinemadb`.`Proiezione`
    where `Proiezione`.`id` = idProiezioneParam
      and `Sala`.`id` = Proiezione.idSala
      and `Posto`.`idSala` = Sala.id
      and `Posto`.`id` not in (
        select `Posto`.`id`
        from `cinemadb`.`Biglietto`,
             `cinemadb`.`Posto`
        where `Biglietto`.`idProiezione` = idProiezioneParam
          and `Posto`.`id` = `Biglietto`.`idPosto`
          and `Biglietto`.`stato` = "rimborsato"
    );
END$$

DELIMITER ;
SHOW WARNINGS;

-- -----------------------------------------------------
-- procedure trovaProiezioni
-- -----------------------------------------------------

USE `cinemadb`;
DROP procedure IF EXISTS `cinemadb`.`trovaProiezioni`;
SHOW WARNINGS;

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `trovaProiezioni`(IN idFilmParam INT)
BEGIN
    select *
    from `cinemadb`.`Proiezione`
    where Proiezione.idFilm = idFilmParam
      and Proiezione.inizio > now();
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
    INSERT INTO `cinemadb`.`Utente` (`nome`, `cognome`, `email`, `password`, `ruolo`)
    VALUES ('fab', 'bur', 'f@b.c', 'pippo', 'amministratore');
    INSERT INTO `cinemadb`.`Utente` (`nome`, `cognome`, `email`, `password`, `ruolo`)
    VALUES ('ivan', 'pal', 'i@p.c', 'pippo', 'proiezionista');
    INSERT INTO `cinemadb`.`Utente` (`nome`, `cognome`, `email`, `password`, `ruolo`)
    VALUES ('max', 'max', 'm@m.c', 'pippo', 'maschera');
    INSERT INTO `cinemadb`.`Utente` (`nome`, `cognome`, `email`, `password`, `ruolo`)
    VALUES ('cli', 'ent', 'c@e.c', 'pippo', 'cliente');
/* Cinema */
    INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`)
    VALUES ('multisala', 'via le mani dal naso', '00000');
    INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`) VALUES ('monosala', 'via etrusca', '00001');
    INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`) VALUES ('microsala', 'via dante', '00002');
    INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`) VALUES ('supersala', 'via roma', '00003');
    INSERT INTO `cinemadb`.`cinema` (`nome`, `indirizzo`, `telefono`) VALUES ('megasala', 'via milano', '00004');
/* Sala */
    INSERT INTO `cinemadb`.`sala` (`idCinema`) VALUES ('1');
    INSERT INTO `cinemadb`.`sala` (`idCinema`) VALUES ('1');
    INSERT INTO `cinemadb`.`sala` (`idCinema`) VALUES ('2');
    INSERT INTO `cinemadb`.`sala` (`idCinema`) VALUES ('1');
    INSERT INTO `cinemadb`.`sala` (`idCinema`) VALUES ('2');
    INSERT INTO `cinemadb`.`sala` (`idCinema`) VALUES ('2');
/* Dipendente */
    INSERT INTO `cinemadb`.`dipendente` (`idUtente`, `idCinema`, `oreSettimanali`) VALUES ('1', '2', '36');
    INSERT INTO `cinemadb`.`dipendente` (`idUtente`, `idCinema`, `oreSettimanali`) VALUES ('2', '1', '30');
/* Posto */
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('1', 'A', '1');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('2', 'A', '1');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('3', 'A', '1');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('1', 'B', '3');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('2', 'B', '3');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('3', 'B', '3');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('1', 'A', '3');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('2', 'A', '3');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('3', 'A', '3');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('1', 'B', '1');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('2', 'B', '1');
    INSERT INTO `cinemadb`.`posto` (`Numero`, `Fila`, `idSala`) VALUES ('3', 'B', '1');
/* Richiesta ferie */
    INSERT INTO `cinemadb`.`richiestaferie` (`richiedenteId`, `Stato`, `inizio`, `fine`)
    VALUES ('1', 'approvata', '2020-12-15 00:00:00', '2020-12-30 13:00:00');
    INSERT INTO `cinemadb`.`richiestaferie` (`richiedenteId`, `Stato`, `inizio`, `fine`)
    VALUES ('2', 'respinta', '2020-12-16 10:00:00', '2021-01-15 20:00:00');
    INSERT INTO `cinemadb`.`richiestaferie` (`richiedenteId`, `Stato`, `inizio`, `fine`)
    VALUES ('1', 'attesa', '2020-11-15 00:00:00', '2020-12-15 20:00:00');
/* Turno */
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `idDipendente`)
    VALUES ('2020-12-15 12:00:00', '2020-12-15 18:00:00', '1');
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `idDipendente`)
    VALUES ('2020-12-16 08:00:00', '2020-12-16 12:00:00', '1');
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `idDipendente`)
    VALUES ('2020-11-15 18:00:00', '2022-12-16 00:00:00', '2');
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `idDipendente`)
    VALUES ('2020-12-17 10:00:00', '2020-12-18 00:00:00', '2');
/* Richiesta cambio */
    INSERT INTO `cinemadb`.`richiestacambio` (`Stato`, `idTurnoAttuale`, `idTurnoRichiesto`, `Tipo`)
    VALUES ('approvata', '1', '3', 'cambio');
    INSERT INTO `cinemadb`.`richiestacambio` (`Stato`, `idTurnoAttuale`, `idTurnoRichiesto`, `Tipo`)
    VALUES ('respinta', '2', '4', 'scambio');
    INSERT INTO `cinemadb`.`richiestacambio` (`Stato`, `idTurnoAttuale`, `idTurnoRichiesto`, `Tipo`)
    VALUES ('attesa', '3', '2', 'scambio');
    INSERT INTO `cinemadb`.`richiestacambio` (`Stato`, `idTurnoAttuale`, `idTurnoRichiesto`, `Tipo`)
    VALUES ('attesa', '3', '2', 'cambio');
    /* Proiezionista */
    INSERT INTO `cinemadb`.`proiezionista` (`idDipendente`) VALUES ('2');
/* Film */
    INSERT INTO `cinemadb`.`film` (`id`, `durata`, `attivo`) VALUES ('1', '01:30:00', True);
    INSERT INTO `cinemadb`.`film` (`id`, `durata`, `attivo`) VALUES ('2', '01:00:00', True);
    INSERT INTO `cinemadb`.`film` (`id`, `durata`, `attivo`) VALUES ('3', '00:30:00', True);
    INSERT INTO `cinemadb`.`film` (`id`, `durata`, `attivo`) VALUES ('4', '02:00:00', False);
/* Proiezione */
    INSERT INTO `cinemadb`.`proiezione` (`idSala`, `idProiezionista`, `idFilm`, `inizio`)
    VALUES ('1', '2', '1', '2020-11-20 22:00:00');
    INSERT INTO `cinemadb`.`proiezione` (`idSala`, `idProiezionista`, `idFilm`, `inizio`)
    VALUES ('3', '2', '1', '2020-12-16 18:00:00');
    INSERT INTO `cinemadb`.`proiezione` (`idSala`, `idProiezionista`, `idFilm`, `inizio`)
    VALUES ('1', '2', '3', '2020-11-16 00:00:00');
    INSERT INTO `cinemadb`.`proiezione` (`idSala`, `idProiezionista`, `idFilm`, `inizio`)
    VALUES ('2', '2', '1', '2021-11-20 22:00:00');
/* Biglietto */
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('1', '1', '4', '6.5', 'utilizzato');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('4', '2', '4', '6', 'rimborsato');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('3', '3', '3', '12', 'utilizzato');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('2', '4', '2', '2', 'attivo');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('5', '1', '2', '5', 'attivo');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('6', '2', '1', '9', 'utilizzato');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('9', '3', '1', '8', 'rimborsato');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('4', '2', '4', '6', 'rimborsato');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('4', '2', '4', '6', 'attivo');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('4', '2', '4', '6', 'utilizzato');
    INSERT INTO `cinemadb`.`biglietto` (`idPosto`, `idProiezione`, `idUser`, `prezzo`, `stato`)
    VALUES ('10', '4', '2', '7', 'attivo');
/* Carta di credito */
    INSERT INTO `cinemadb`.`cartadicredito` (`idUtente`, `numero`, `dataScadenza`, `cvv`)
    VALUES ('2', '000000001', '2020-10-01', '123');
    INSERT INTO `cinemadb`.`cartadicredito` (`idUtente`, `numero`, `dataScadenza`, `cvv`)
    VALUES ('3', '000000002', '2020-11-09', '456');
    INSERT INTO `cinemadb`.`cartadicredito` (`idUtente`, `numero`, `dataScadenza`, `cvv`)
    VALUES ('4', '000000003', '2020-09-08', '223');
END$$

DELIMITER ;
SHOW WARNINGS;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
