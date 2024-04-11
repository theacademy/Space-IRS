DROP DATABASE IF EXISTS spaceirs;
CREATE DATABASE spaceirs;
USE spaceirs;

CREATE TABLE settlement (
settlementId INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) DEFAULT "Unknown",
`type` VARCHAR(25),
directions MEDIUMTEXT,
taxModifier DECIMAL(5,2) NOT NULL
);

CREATE TABLE taxGroup (
taxGroupId INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
taxRate DECIMAL(5,2) NOT NULL
);


CREATE TABLE species (
speciesId INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
origin INT,
taxGroup INT DEFAULT 1,
FOREIGN KEY (origin)
    REFERENCES settlement(settlementId),
FOREIGN KEY (taxGroup)
    REFERENCES taxGroup(taxGroupId)
);

CREATE TABLE populations (
populations_speciesId INT,
populations_settlementId INT,
population INT NOT NULL,
PRIMARY KEY (populations_speciesId, populations_settlementId),
FOREIGN KEY (populations_speciesId)
    REFERENCES species(speciesId),
FOREIGN KEY (populations_settlementId)
    REFERENCES settlement(settlementId)
);


CREATE TABLE orbits (
parentId INT,
childId INT,
PRIMARY KEY (parentId, childId),
FOREIGN KEY (parentId)
    REFERENCES settlement(settlementId),
FOREIGN KEY (childId)
    REFERENCES settlement(settlementId)
);
