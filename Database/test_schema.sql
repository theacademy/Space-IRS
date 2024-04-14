DROP DATABASE IF EXISTS spaceirsDBtest;
CREATE DATABASE spaceirsDBtest;
USE spaceirsDBtest;

CREATE TABLE settlement (
settlement_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) DEFAULT "Unknown",
`type` VARCHAR(25),
directions MEDIUMTEXT,
tax_modifier DECIMAL(5,2) NOT NULL
);

CREATE TABLE tax_group (
tax_group_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
tax_rate DECIMAL(5,2) NOT NULL
);

CREATE TABLE species (
species_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
origin INT,
tax_group INT DEFAULT 1,
FOREIGN KEY (origin)
    REFERENCES settlement(settlement_id),
FOREIGN KEY (tax_group)
    REFERENCES tax_group(tax_group_id)
);

CREATE TABLE populations (
populations_species_id INT,
populations_settlement_id INT,
population INT NOT NULL,
PRIMARY KEY (populations_species_id, populations_settlement_id),
FOREIGN KEY (populations_species_id)
    REFERENCES species(species_id),
FOREIGN KEY (populations_settlement_id)
    REFERENCES settlement(settlement_id)
);

CREATE TABLE orbits (
parent_id INT,
child_id INT,
PRIMARY KEY (parent_id, child_id),
FOREIGN KEY (parent_id)
    REFERENCES settlement(settlement_id),
FOREIGN KEY (child_id)
    REFERENCES settlement(settlement_id)
);