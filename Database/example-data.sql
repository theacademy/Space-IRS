use spaceirs;

INSERT INTO settlement (`name`, `type`, directions, tax_modifier) VALUES 
('Earth', 'open settlement', 'between two parties for now', 2.3),
('Mars', 'not so open settlement', 'one party', 5.0),
('Jupiter', 'open settlement', 'one party', 1.1),
('Xo12XX', 'isolated settlement', 'between many parties', 2.2);

INSERT INTO tax_group (`name`, tax_rate) VALUES
('big tax', 20),
('normal tax', 10),
('small tax', 7); 

INSERT INTO species (`name`, origin, tax_group) VALUES
('human', 1, 2),
('xieons', 4, 1),
('khalames', null, 3);

INSERT INTO populations VALUES
(1, 1, 80000),
(1, 2, 40),
(2, 4, 200),
(2, 2, 3);

SELECT settlement_id, settlement.`name` AS settlement_name, settlement.`type`AS settlement_type, species.`name` AS species_name, populations.population, directions, tax_modifier 
FROM settlement, populations
INNER JOIN species ON populations.populations_species_id = species.species_id;
