SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `hotelDB`.`Room`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Room` (
  `idRoom` VARCHAR(10) NOT NULL ,
  `floor` INT NOT NULL ,
  `number` INT NOT NULL ,
  `capacity` INT NOT NULL ,
  `discription` TEXT NULL ,
  `basePrice` FLOAT NOT NULL DEFAULT 0.0 ,
  PRIMARY KEY (`idRoom`) )
ENGINE = InnoDB
COMMENT = 'Representation of room entity.';


-- -----------------------------------------------------
-- Table `hotelDB`.`Customer`
-- -----------------------------------------------------
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


-- -----------------------------------------------------
-- Table `hotelDB`.`Rental`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Rental` (
  `Room_idRoom` VARCHAR(10) NOT NULL ,
  `Customer_idCustomer` VARCHAR(20) NOT NULL ,
  `arrivalDate` DATETIME NOT NULL ,
  `departureDate` DATETIME NOT NULL ,
  `dayPrice` FLOAT NOT NULL ,
  `checkIn` TINYINT(1) NOT NULL DEFAULT false ,
  `checkOut` TINYINT(1) NOT NULL DEFAULT false ,
  `paied` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`Room_idRoom`, `Customer_idCustomer`) ,
  INDEX `fk_Room_has_Customer_Customer1_idx` (`Customer_idCustomer` ASC) ,
  INDEX `fk_Room_has_Customer_Room_idx` (`Room_idRoom` ASC) ,
  CONSTRAINT `fk_Room_has_Customer_Room`
    FOREIGN KEY (`Room_idRoom` )
    REFERENCES `hotelDB`.`Room` (`idRoom` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Room_has_Customer_Customer1`
    FOREIGN KEY (`Customer_idCustomer` )
    REFERENCES `hotelDB`.`Customer` (`idCustomer` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Entity for reservation and current state of availeability.';


-- -----------------------------------------------------
-- Table `hotelDB`.`Offer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Offer` (
  `idOffer` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `discount` FLOAT NOT NULL DEFAULT 0 ,
  `Rental_Room_idRoom` VARCHAR(10) NOT NULL ,
  `Rental_Customer_idCustomer` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`idOffer`, `Rental_Customer_idCustomer`) ,
  INDEX `fk_Offer_Rental1_idx` (`Rental_Room_idRoom` ASC, `Rental_Customer_idCustomer` ASC) ,
  CONSTRAINT `fk_Offer_Rental1`
    FOREIGN KEY (`Rental_Room_idRoom` , `Rental_Customer_idCustomer` )
    REFERENCES `hotelDB`.`Rental` (`Room_idRoom` , `Customer_idCustomer` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Entity witch contains all offers (turist agents, discounts a' /* comment truncated */;


-- -----------------------------------------------------
-- Table `hotelDB`.`Service`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hotelDB`.`Service` (
  `idService` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `price` FLOAT NOT NULL ,
  `Rental_Room_idRoom` VARCHAR(10) NOT NULL ,
  `Rental_Customer_idCustomer` VARCHAR(20) NOT NULL ,
  `paied` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`idService`) ,
  INDEX `fk_Service_Rental1_idx` (`Rental_Room_idRoom` ASC, `Rental_Customer_idCustomer` ASC) ,
  CONSTRAINT `fk_Service_Rental1`
    FOREIGN KEY (`Rental_Room_idRoom` , `Rental_Customer_idCustomer` )
    REFERENCES `hotelDB`.`Rental` (`Room_idRoom` , `Customer_idCustomer` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'This entity connects the reservation with some extra service' /* comment truncated */;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
