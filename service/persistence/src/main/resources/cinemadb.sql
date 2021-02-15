-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema cinemadb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinemadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinemadb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `cinemadb` ;

-- -----------------------------------------------------
-- Table `cinemadb`.`cinema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`cinema` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `indirizzo` VARCHAR(45) NOT NULL,
  `citta` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`sala`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`sala` (
  `id` INT NOT NULL,
  `id_cinema` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Sala_Cinema1_idx` (`id_cinema` ASC) VISIBLE,
  CONSTRAINT `fk_Sala_Cinema1`
    FOREIGN KEY (`id_cinema`)
    REFERENCES `cinemadb`.`cinema` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`posto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`posto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `prezzo` DOUBLE NOT NULL,
  `stato` TINYINT NOT NULL,
  `id_sala` INT NOT NULL,
  `posizione` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Posto_Sala1_idx` (`id_sala` ASC) VISIBLE,
  CONSTRAINT `fk_Posto_Sala1`
    FOREIGN KEY (`id_sala`)
    REFERENCES `cinemadb`.`sala` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`film`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`film` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`utente` (
  `id` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `cognome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`dipendente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`dipendente` (
  `id_utente` VARCHAR(45) NOT NULL,
  `id_cinema` INT NOT NULL,
  `ruolo` ENUM('proiezionista', 'maschera') NOT NULL,
  INDEX `fk_Dipendente_Utente1_idx` (`id_utente` ASC) VISIBLE,
  INDEX `fk_Dipendente_Cinema1_idx` (`id_cinema` ASC) VISIBLE,
  CONSTRAINT `fk_Dipendente_Cinema1`
    FOREIGN KEY (`id_cinema`)
    REFERENCES `cinemadb`.`cinema` (`id`),
  CONSTRAINT `fk_Dipendente_Utente1`
    FOREIGN KEY (`id_utente`)
    REFERENCES `cinemadb`.`utente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`proiezionista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`proiezionista` (
  `id_dipendente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_dipendente`),
  UNIQUE INDEX `idDIpendente_UNIQUE` (`id_dipendente` ASC) VISIBLE,
  INDEX `fk_Proiezionista_Dipendente1_idx` (`id_dipendente` ASC) VISIBLE,
  CONSTRAINT `fk_Proiezionista_Dipendente1`
    FOREIGN KEY (`id_dipendente`)
    REFERENCES `cinemadb`.`dipendente` (`id_utente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`proiezione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`proiezione` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_sala` INT NOT NULL,
  `id_proiezionista` VARCHAR(45) NOT NULL,
  `id_film` INT NOT NULL,
  `data` VARCHAR(12) NOT NULL,
  `inizio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Proiezione_Film1_idx` (`id_film` ASC) VISIBLE,
  INDEX `fk_Proiezione_Sala1_idx` (`id_sala` ASC) VISIBLE,
  INDEX `fk_Proiezione_Proiezionista1_idx` (`id_proiezionista` ASC) VISIBLE,
  CONSTRAINT `fk_Proiezione_Film1`
    FOREIGN KEY (`id_film`)
    REFERENCES `cinemadb`.`film` (`id`),
  CONSTRAINT `fk_Proiezione_Proiezionista1`
    FOREIGN KEY (`id_proiezionista`)
    REFERENCES `cinemadb`.`proiezionista` (`id_dipendente`),
  CONSTRAINT `fk_Proiezione_Sala1`
    FOREIGN KEY (`id_sala`)
    REFERENCES `cinemadb`.`sala` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`biglietto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`biglietto` (
  `id` INT NOT NULL,
  `id_posto` INT NOT NULL,
  `id_proiezione` INT NOT NULL,
  `id_utente` VARCHAR(45) NOT NULL,
  `prezzo` DOUBLE NOT NULL,
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
    REFERENCES `cinemadb`.`utente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`carta_di_credito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`carta_di_credito` (
  `id` INT NOT NULL,
  `id_utente` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  `data_scadenza` VARCHAR(45) NOT NULL,
  `cvv` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_CartaDiCredito_Utente1_idx` (`id_utente` ASC) VISIBLE,
  CONSTRAINT `fk_CartaDiCredito_Utente1`
    FOREIGN KEY (`id_utente`)
    REFERENCES `cinemadb`.`utente` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`turno` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `inizio` VARCHAR(15) NOT NULL,
  `fine` VARCHAR(15) NOT NULL,
  `id_dipendente` VARCHAR(45) NOT NULL,
  `data` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Turno_Dipendente1_idx` (`id_dipendente` ASC) VISIBLE,
  CONSTRAINT `fk_Turno_Dipendente1`
    FOREIGN KEY (`id_dipendente`)
    REFERENCES `cinemadb`.`dipendente` (`id_utente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinemadb`.`turno_proiezionista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinemadb`.`turno_proiezionista` (
  `turno_id` INT NOT NULL,
  `sala_id` INT NOT NULL,
  INDEX `fk_turno_proiezionista_turno1_idx` (`turno_id` ASC) VISIBLE,
  INDEX `fk_turno_proiezionista_sala1_idx` (`sala_id` ASC) VISIBLE,
  CONSTRAINT `fk_turno_proiezionista_turno1`
    FOREIGN KEY (`turno_id`)
    REFERENCES `cinemadb`.`turno` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_turno_proiezionista_sala1`
    FOREIGN KEY (`sala_id`)
    REFERENCES `cinemadb`.`sala` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `cinemadb` ;

-- -----------------------------------------------------
-- procedure film_attivi
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `film_attivi`()
BEGIN
    select * from cinemadb.film where attivo = True;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure login
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(IN emailParam VARCHAR(255), IN passwordParam VARCHAR(255))
BEGIN
    select *
    from cinemadb.utente
    where email = emailParam
      and password = passwordParam;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure popola
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `popola`()
BEGIN
    /* Utente */
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
/* Cinema */
    INSERT INTO `cinemadb`.`cinema` (`id` ,`nome`, `indirizzo`, `citta`)
    VALUES ('0', 'Comunale', 'via recanati 3', 'Recanati');
    INSERT INTO `cinemadb`.`cinema` (`id` ,`nome`, `indirizzo`, `citta`)
    VALUES ('1', 'MultiPlex', 'via garibaldi 1', 'Teramo');

/* Sala */
    INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('0', '0');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('1', '0');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('2','0');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('3','0');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('4','0');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('5','0');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('6','1');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('7','1');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('8','1');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('9','1');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('10','1');
       INSERT INTO `cinemadb`.`sala` (`id`,`id_cinema`)
    VALUES ('11','1');

/* Dipendente */
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('0' , '0', 'maschera');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('1', '0', 'proiezionista');
	INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('2','1', 'maschera');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('3','1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('5','0', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('7','0', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('9','0', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('11','0', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('13','0', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('15', '1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('17','1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('19', '1', 'proiezionista');
	INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('21','1', 'proiezionista');
    INSERT INTO `cinemadb`.`dipendente` (`id_utente`, `id_cinema`, `ruolo`)
    VALUES ('23', '1', 'proiezionista');

    /*proiezionista*/
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('1');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('3');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('5');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('7');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('9');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('11');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('13');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('15');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('17');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('19');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('21');
     INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('23');

/* Posto */
  /*  INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('1', 'A', '1');
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
    INSERT INTO `cinemadb`.`posto` (`numero`, `fila`, `id_sala`) VALUES ('3', 'B', '1');*/
/* Turno */
/*    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`)
    VALUES ('2020-12-15 12:00:00', '2020-12-15 18:00:00', '1');
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`)
    VALUES ('2020-12-16 08:00:00', '2020-12-16 12:00:00', '1');
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`)
    VALUES ('2020-11-15 18:00:00', '2022-12-16 00:00:00', '2');
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`)
    VALUES ('2020-12-17 10:00:00', '2020-12-18 00:00:00', '2');*/
    /* Proiezionista */
   /* INSERT INTO `cinemadb`.`proiezionista` (`id_dipendente`) VALUES ('2');*/
/* Film */
    INSERT INTO `cinemadb`.`film` (`id`) VALUES ('3');
    INSERT INTO `cinemadb`.`film` (`id`) VALUES ('5');
    INSERT INTO `cinemadb`.`film` (`id`) VALUES ('6');
    INSERT INTO `cinemadb`.`film` (`id`) VALUES ('8');
    INSERT INTO `cinemadb`.`film` (`id`) VALUES ('11');
    INSERT INTO `cinemadb`.`film` (`id`) VALUES ('15');
/* Proiezione */
    /*INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `inizio`)
    VALUES ('1', '2', '1', '2020-11-20 22:00:00');
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `inizio`)
    VALUES ('3', '2', '1', '2020-12-16 18:00:00');
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `inizio`)
    VALUES ('1', '2', '3', '2020-11-16 00:00:00');
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `inizio`)
    VALUES ('2', '2', '1', '2021-11-20 22:00:00');*/
/* Biglietto */
 /*   INSERT INTO `cinemadb`.`biglietto` (`id_posto`, `id_proiezione`, `id_utente`, `prezzo`, `stato`)
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
    VALUES ('10', '4', '2', '7', 'attivo');*/
/* Carta di credito */
    INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
    VALUES ('0', '0', '4242424242424242' ,'22/24', 354);
     INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
       VALUES ('1', '1', '4242424242424242' ,'22/24', 354);
    INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
        VALUES ('2', '2', '4242424242424242' ,'22/24', 354);
    INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
       VALUES ('3', '3', '4242424242424242' ,'22/24', 354);
        INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
       VALUES ('4', '5', '4242424242424242' ,'22/24', 354);
       INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
    VALUES ('5', '7', '4242424242424242' ,'22/24', 354);
     INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
       VALUES ('6', '9', '4242424242424242' ,'22/24', 354);
    INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
        VALUES ('7', '11', '4242424242424242' ,'22/24', 354);
    INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
       VALUES ('8', '13', '4242424242424242' ,'22/24', 354);
        INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
       VALUES ('9', '15', '4242424242424242' ,'22/24', 354);
       INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
    VALUES ('10', '17', '4242424242424242' ,'22/24', 354);
     INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
       VALUES ('11', '19', '4242424242424242' ,'22/24', 354);
    INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
        VALUES ('12', '21', '4242424242424242' ,'22/24', 354);
    INSERT INTO `cinemadb`.`carta_di_credito` (`id`, `id_utente`, `numero`, `data_scadenza`, `cvv`)
       VALUES ('13', '23', '4242424242424242' ,'22/24', 354);

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure rimborso
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `rimborso`(IN idBigliettoParam INT)
BEGIN
    update cinemadb.biglietto
    set stato = "rimborsato"
    where id = idBigliettoParam;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure trova_posti_liberi
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `trova_posti_liberi`(IN idProiezioneParam INT)
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

-- -----------------------------------------------------
-- procedure trova_proiezioni
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `trova_proiezioni`(IN idFilmParam INT)
BEGIN
    select *
    from cinemadb.proiezione
    where proiezione.id_film = idFilmParam
      and proiezione.inizio > now();
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure posti
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `posti` ()
BEGIN
DECLARE x INT;
declare y int;
declare counter int;
set counter =0;
set y = (select count(*) from sala);
SET x=0;
WHILE x < y*7 DO
      INSERT INTO `cinemadb`.`posto` (`prezzo`, `stato`,`id_sala`, `posizione` ) VALUES ('5', '0', x%y, concat('A', counter));
	INSERT INTO `cinemadb`.`posto` (`prezzo`, `stato`,`id_sala`, `posizione` ) VALUES ('5', '0', x%y,concat('B',counter));
     INSERT INTO `cinemadb`.`posto` (`prezzo`, `stato`,`id_sala`, `posizione` ) VALUES ('5', '0', x%y,concat('C',counter));
      INSERT INTO `cinemadb`.`posto` (`prezzo`, `stato`,`id_sala`, `posizione` ) VALUES ('5', '0', x%y,concat('D',counter));
       INSERT INTO `cinemadb`.`posto` (`prezzo`, `stato`,`id_sala`, `posizione` ) VALUES ('5', '0', x%y,concat('E',counter));
        INSERT INTO `cinemadb`.`posto` (`prezzo`, `stato`,`id_sala`, `posizione` ) VALUES ('5', '0', x%y,concat('F',counter));
        set counter = counter +1;
        if (counter = 7) then
        set counter = 0;
        end if;

	SET  x = x+1;
     END WHILE;
     END;$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure turni
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `turni` ()
BEGIN

declare nsala int;
declare x int;
declare h int;
declare days int;

set h=12;
set days = 15;

set nsala = (select count(*) from sala);
while days < 29 do
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
    VALUES('12:00' , '24:00', '0' ,  concat('2020-02-',days) );
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
    VALUES ('12:00' ,'24:00' , '1',  concat('2020-02-',days));
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '5' ,  concat('2020-02-',days) );
	  INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '7' ,  concat('2020-02-',days) );
     INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '9' ,  concat('2020-02-',days) );
	  INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '11' ,  concat('2020-02-',days) );
     INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '13' ,  concat('2020-02-',days) );

	INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
    VALUES('12:00' , '24:00', '2' ,  concat('2020-02-',days) );
	INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
    VALUES ('12:00' ,'24:00' , '3',  concat('2020-02-',days));
    INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '15' ,  concat('2020-02-',days) );
	  INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '17' ,  concat('2020-02-',days) );
     INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '19' ,  concat('2020-02-',days) );
	  INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '21' ,  concat('2020-02-',days) );
     INSERT INTO `cinemadb`.`turno` (`inizio`, `fine`, `id_dipendente`, `data`)
     VALUES('12:00' , '24:00', '23' ,  concat('2020-02-',days) );
	if(h>=23)then
    set h=12;
    end if;
    set h=h+1;
    set days = days +1;
end while;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proiezioni
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `proiezioni` ()
BEGIN

declare nsala int;
declare x int;
declare h int;
declare days int;

set h=12;
set days = 15;

set x=0;  /*sala*/
set nsala = (select count(*) from sala);
while days < 29 do
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('0', '1', '3', concat('2020-02-',days), concat(h,':00'));
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('1', '5', '5', concat('2020-02-',days), concat(h,':00'));
	INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('2', '7', '6', concat('2020-02-',days), concat(h,':00'));
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('3', '9', '8', concat('2020-02-',days), concat(h,':00'));
	INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('4', '11', '11', concat('2020-02-',days), concat(h,':00'));
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('5', '13', '15', concat('2020-02-',days), concat(h,':00'));

	INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('6', '3', '3', concat('2020-02-',days), concat(h,':00'));
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('7', '15', '5', concat('2020-02-',days), concat(h,':00'));
	INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('8', '17', '6', concat('2020-02-',days), concat(h,':00'));
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('9', '19', '8', concat('2020-02-',days), concat(h,':00'));
	INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('10', '21', '11', concat('2020-02-',days), concat(h,':00'));
    INSERT INTO `cinemadb`.`proiezione` (`id_sala`, `id_proiezionista`, `id_film`, `data`, `inizio`)
    VALUES ('11', '23', '15', concat('2020-02-',days), concat(h,':00'));
	if(h>=23)then
    set h=12;
    end if;
    set h=h+1;
    set days = days +1;
end while;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure turno_proiezionista
-- -----------------------------------------------------

DELIMITER $$
USE `cinemadb`$$
CREATE PROCEDURE `turno_proiezionista` ()
BEGIN


declare sala int;
declare count int;
declare turni int;

set turni = 1;
set count = 1;
set sala=0;  /*sala*/

while sala<12 do
	while count < 15 do
		INSERT INTO `cinemadb`.`turno_proiezionista` (`turno_id`,`sala_id`)
		values(turni, sala);
        set turni = turni + 14;
        set count= count +1;
	end while;
    set count =0;
        set turni = sala+1;
	set sala = sala+1;

end while;

END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
