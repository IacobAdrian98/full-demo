insert into car(id, brand, model, yearOfFabrication, color)
values (1, 'BMW', 'X5', 2024, 'RED'),
       (2, 'BMW', 'M3', 2022, 'GREEN'),
       (3, 'BMW', 'M2', 2021, 'BLACK'),
       (4, 'BMW', 'X3', 2020, 'WHITE');


alter sequence car_seq restart with 5;
