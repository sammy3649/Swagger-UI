--Описание структуры: у каждого человека есть машина. Причем несколько человек могут пользоваться одной машиной.
-- У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет).
-- У каждой машины есть марка, модель и стоимость.
-- Также не забудьте добавить таблицам первичные ключи и связать их.
CREATE TABLE cars
(
    id    SMALLSERIAL PRIMARY KEY,
    brand VARCHAR(88) NOT NULL,
    model VARCHAR(88) NOT NULL,
    cost  INTEGER     NOT NULL CHECK ( cost > 0 )
);
CREATE TABLE person
(
    id           SMALLSERIAL PRIMARY KEY,
    name         VARCHAR(88)                NOT NULL,
    age          INTEGER CHECK ( age >= 0 ) NOT NULL,
    licenseCheck BOOLEAN                    NOT NULL DEFAULT false,
    carId        SMALLINT REFERENCES cars (id)

);

