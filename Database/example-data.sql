use spaceirs;

INSERT INTO
    settlement (
        `name`, `type`, directions, tax_modifier
    )
VALUES (
        'Earth', 'Rocky Planet', 'turn left at Alpha Centauri', 2.3
    ),
    (
        'Mars', 'Rockey Planet', 'Avoid lane closure on Asteroid Belt', 5.0
    ),
    (
        'Jupiter', 'Gas Giant', 'New shortcut calculated via Uranus', 1.1
    ),
    (
        'Xo12XX', 'Space Station', '3° Off Sirius then turn right', 2.2
    );

INSERT INTO
    tax_group (`name`, tax_rate)
VALUES ('small tax', 7),
    ('normal tax', 10),
    ('big tax', 20);

INSERT INTO
    species (`name`, origin, tax_group)
VALUES ('human', 1, 2),
    ('xieons', 4, 1),
    ('khalames', null, 3);

INSERT INTO
    populations
VALUES (1, 1, 80000),
    (1, 2, 40),
    (2, 4, 200),
    (2, 2, 3);

SELECT
    settlement_id,
    settlement.`name` AS settlement_name,
    settlement.`type` AS settlement_type,
    species.`name` AS species_name,
    populations.population,
    directions,
    tax_modifier
FROM
    settlement,
    populations
    INNER JOIN species ON populations.populations_species_id = species.species_id;
