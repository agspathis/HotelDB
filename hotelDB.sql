SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `hotelDB` ;
CREATE SCHEMA IF NOT EXISTS `hotelDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
SHOW WARNINGS;
USE `hotelDB` ;

-- -----------------------------------------------------
-- Table `hotelDB`.`Room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotelDB`.`Room` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Room` (
  `idRoom` VARCHAR(10) NOT NULL ,
  `floor` INT NOT NULL ,
  `number` INT NOT NULL ,
  `capacity` INT NOT NULL ,
  `discription` TEXT NULL ,
  `basePrice` FLOAT NULL DEFAULT 0.0 ,
  PRIMARY KEY (`idRoom`) )
ENGINE = InnoDB
COMMENT = 'Representation of room entity.';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `hotelDB`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotelDB`.`Customer` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Customer` (
  `idCustomer` VARCHAR(20) NOT NULL ,
  `lastName` VARCHAR(45) NOT NULL ,
  `firstName` VARCHAR(20) NOT NULL ,
  `city` VARCHAR(45) NULL ,
  `country` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  PRIMARY KEY (`idCustomer`) )
ENGINE = InnoDB
COMMENT = 'The customer entity.';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `hotelDB`.`Rental`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotelDB`.`Rental` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Rental` (
  `idRental` INT(11) NOT NULL AUTO_INCREMENT ,
  `idRoom` VARCHAR(10) NOT NULL ,
  `idCustomer` VARCHAR(20) NOT NULL ,
  `arrivalDate` DATETIME NOT NULL ,
  `departureDate` DATETIME NOT NULL ,
  `dayPrice` FLOAT NOT NULL ,
  `checkIn` TINYINT(1) NOT NULL DEFAULT false ,
  `checkOut` TINYINT(1) NOT NULL DEFAULT false ,
  `payed` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`idRental`) ,
  INDEX `fk_Room_has_Customer_Customer1_idx` (`idCustomer` ASC) ,
  INDEX `fk_Room_has_Customer_Room_idx` (`idRoom` ASC) ,
  CONSTRAINT `fk_Room_has_Customer_Room`
    FOREIGN KEY (`idRoom` )
    REFERENCES `hotelDB`.`Room` (`idRoom` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Room_has_Customer_Customer1`
    FOREIGN KEY (`idCustomer` )
    REFERENCES `hotelDB`.`Customer` (`idCustomer` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Entity for reservation and current state of availeability.';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `hotelDB`.`Discount`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotelDB`.`Discount` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Discount` (
  `idDiscount` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `discount` FLOAT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`idDiscount`) )
ENGINE = InnoDB
COMMENT = 'Entity witch contains all offers (turist agents, discounts a' /* comment truncated */;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `hotelDB`.`Service`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotelDB`.`Service` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Service` (
  `idService` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `price` FLOAT NOT NULL ,
  PRIMARY KEY (`idService`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB
COMMENT = 'This entity connects the reservation with some extra service' /* comment truncated */;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `hotelDB`.`Offer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotelDB`.`Offer` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Offer` (
  `idOffer` INT(11) NOT NULL AUTO_INCREMENT ,
  `idRental` INT(11) NOT NULL ,
  `idDiscount` INT(11) NOT NULL ,
  INDEX `fk_Rental_has_Offer_Offer1` (`idDiscount` ASC) ,
  INDEX `fk_Rental_has_Offer_Rental1` (`idRental` ASC) ,
  PRIMARY KEY (`idOffer`) ,
  CONSTRAINT `fk_Rental_has_Offer_Rental1`
    FOREIGN KEY (`idRental` )
    REFERENCES `hotelDB`.`Rental` (`idRental` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rental_has_Offer_Offer1`
    FOREIGN KEY (`idDiscount` )
    REFERENCES `hotelDB`.`Discount` (`idDiscount` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `hotelDB`.`Trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hotelDB`.`Trade` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Trade` (
  `idTrade` INT(11) NOT NULL AUTO_INCREMENT ,
  `idService` INT(11) NOT NULL ,
  `idRental` INT(11) NOT NULL ,
  `payed` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`idTrade`) ,
  INDEX `fk_Order_Service1` (`idService` ASC) ,
  INDEX `fk_Order_Rental1` (`idRental` ASC) ,
  CONSTRAINT `fk_Order_Service1`
    FOREIGN KEY (`idService` )
    REFERENCES `hotelDB`.`Service` (`idService` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Rental1`
    FOREIGN KEY (`idRental` )
    REFERENCES `hotelDB`.`Rental` (`idRental` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `hotelDB`.`Room`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotelDB`;
INSERT INTO `hotelDB`.`Room` (`idRoom`, `floor`, `number`, `capacity`, `discription`, `basePrice`) VALUES ('101', 1, 1, 2, 'seeview', 50);
INSERT INTO `hotelDB`.`Room` (`idRoom`, `floor`, `number`, `capacity`, `discription`, `basePrice`) VALUES ('201', 2, 1, 1, 'seeview', 40);
INSERT INTO `hotelDB`.`Room` (`idRoom`, `floor`, `number`, `capacity`, `discription`, `basePrice`) VALUES ('301', 3, 1, 3, 'seeview', 60);

COMMIT;

-- -----------------------------------------------------
-- Data for table `hotelDB`.`Customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotelDB`;
INSERT INTO `hotelDB`.`Customer` (`idCustomer`, `lastName`, `firstName`, `city`, `country`, `email`) VALUES ('7436', 'Stanev', 'Dimitar', 'Patra', 'Greece', 'jimstanev@gmail.com');
INSERT INTO `hotelDB`.`Customer` (`idCustomer`, `lastName`, `firstName`, `city`, `country`, `email`) VALUES ('7729', 'ΣΠΑΘΗΣ', 'ΑΡΙΣΤΟΤΕΛΗΣ', 'Kefalonia', 'Greece', 'ece7729@upnet.gr');

COMMIT;

-- -----------------------------------------------------
-- Data for table `hotelDB`.`Rental`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotelDB`;
INSERT INTO `hotelDB`.`Rental` (`idRental`, `idRoom`, `idCustomer`, `arrivalDate`, `departureDate`, `dayPrice`, `checkIn`, `checkOut`, `payed`) VALUES (1, '101', '7436', '2012-07-07 12:00:00', '2012-07-10', 55, 1, 1, 1);
INSERT INTO `hotelDB`.`Rental` (`idRental`, `idRoom`, `idCustomer`, `arrivalDate`, `departureDate`, `dayPrice`, `checkIn`, `checkOut`, `payed`) VALUES (2, '301', '7729', '2013-07-07 12:00:00', '2012-07-12 12:00:00', 70, 0, 0, 0);

COMMIT;

-- -----------------------------------------------------
-- Data for table `hotelDB`.`Discount`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotelDB`;
INSERT INTO `hotelDB`.`Discount` (`idDiscount`, `name`, `discount`) VALUES (1, 'Child', 0.1);
INSERT INTO `hotelDB`.`Discount` (`idDiscount`, `name`, `discount`) VALUES (2, 'EarlyBooking', 0.2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `hotelDB`.`Service`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotelDB`;
INSERT INTO `hotelDB`.`Service` (`idService`, `name`, `price`) VALUES (1, 'Coffee', 3);
INSERT INTO `hotelDB`.`Service` (`idService`, `name`, `price`) VALUES (2, 'Tost', 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `hotelDB`.`Offer`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotelDB`;
INSERT INTO `hotelDB`.`Offer` (`idOffer`, `idRental`, `idDiscount`) VALUES (1, 1, 2);

COMMIT;

-- -----------------------------------------------------
-- Data for table `hotelDB`.`Trade`
-- -----------------------------------------------------
START TRANSACTION;
USE `hotelDB`;
INSERT INTO `hotelDB`.`Trade` (`idTrade`, `idService`, `idRental`, `payed`) VALUES (1, 1, 1, 1);

COMMIT;
