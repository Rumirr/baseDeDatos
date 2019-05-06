SHOW TABLES;

# Rename every table to singular instead of plural.
RENAME TABLE AIRPORTS TO AIRPORT, CREWS TO CREW, FLIGHTS TO FLIGHT;

#Add a column to the crew table informing their salary with 2 decimals and 6 digits. This value can't be null.
ALTER TABLE CREW
ADD  SALARY FLOAT(8,2) NOT NULL;

SHOW CREATE TABLE CREW;

#Add a constraint to the new salary attribute. The value has to be positive and less than half a milion.
ALTER TABLE CREW
ADD CONSTRAINT CHK_SALARIO CHECK(SALARY > 0 AND SALARY < 500000); # MYSQL NO LO SUPORTA

SELECT *  FROM CREW;
UPDATE CREW SET SALARY =-5 WHERE id_crew=1;
INSERT INTO CREW(id_crew, SALARY) VALUES(54545454,-1);

#Modify the CREW table to not allow duplicate values in the column email and not allowing null values. Make sure to not change the type of the column.

SHOW CREATE TABLE CREW;
SELECT *  FROM CREW;


ALTER TABLE CREW
MODIFY COLUMN email VARCHAR(80) NOT NULL,
ADD UNIQUE(email);

#Delete every foreign key constraint of the table FLIGHT.
SELECT * FROM FLIGHT;
SHOW CREATE TABLE FLIGHT;

ALTER TABLE FLIGHT
DROP FOREIGN KEY FLIGHT_ibfk_1,
DROP FOREIGN KEY FLIGHT_ibfk_2,
DROP FOREIGN KEY FLIGHT_ibfk_3,
DROP FOREIGN KEY FLIGHT_ibfk_4;

#Add a foreign key for the departure and the arrival airports. It won't allow us to delete and the updates will replicate automatically.
SELECT * FROM FLIGHT;
SELECT * FROM AIRPORT;

ALTER TABLE FLIGHT
ADD CONSTRAINT FK_DEPATURE_AIRPORT FOREIGN KEY(departure_airport) REFERENCES AIRPORT(id_airport) ON DELETE RESTRICT ON UPDATE CASCADE,
ADD CONSTRAINT FK_ARRIVAL_AIRPORT FOREIGN KEY(arrival_airport) REFERENCES AIRPORT(id_airport) ON DELETE RESTRICT ON UPDATE CASCADE;

#Add a foreign key for the pilot and copilot. Whenever we remove a pilot or copilot, it will leave it without a value. If we change the id of them, the change will be repeated automatically.
SELECT * FROM FLIGHT;
SELECT * FROM CREW;

ALTER TABLE FLIGHT
ADD CONSTRAINT FK_ID_PILOT FOREIGN KEY(id_pilot) REFERENCES CREW(id_crew) ON DELETE SET NULL ON UPDATE CASCADE,
ADD CONSTRAINT FK_ID_COPILOT FOREIGN KEY(id_copilot) REFERENCES CREW(id_crew) ON DELETE SET NULL ON UPDATE CASCADE;



#Create a table called STORE. Stores are located in airports. We have the name of the store, the address inside the airport, the number of workers, the monthly rental, the email of contact and the website. The only optional attribute is the website. We must not allow negative values for either the workers and the rental. Justify the options chosen for the foreign key constraint.
SHOW CREATE TABLE AIRPORT;
SHOW CREATE TABLE STORE; 
DROP TABLE STORE;


CREATE TABLE STORE(
	ID_STORE INT AUTO_INCREMENT NOT NULL,
    ID_AIRPORT CHAR(3) NOT NULL,
    NAME VARCHAR(32) NOT NULL,
    ADDRESS VARCHAR(32) NOT NULL,
    NUM_WORKERS INT(2) NOT NULL,
    RENTAL FLOAT(7,2) NOT NULL,
    EMAIL VARCHAR(255) NOT NULL,
    WEB VARCHAR(255),
    
    PRIMARY KEY(ID_STORE),
    
    CONSTRAINT FK_ID_AIRPORT FOREIGN KEY(ID_AIRPORT) REFERENCES AIRPORT(id_airport)
    ON DELETE CASCADE -- PORQUE SI SE CIERRA UN AEROPUERTO ESA TIENDA DEJA DE EXISTIR
    ON UPDATE CASCADE,
    
    CONSTRAINT CHK_WORKERS CHECK(NUM_WORKERS > 0),
    CONSTRAINT CHK_RENTAL CHECK(RENTAL > 0)
);

#Add 3 stores to the first airport.
SELECT * FROM AIRPORT; -- AMS
SELECT	* FROM STORE;

INSERT INTO STORE(ID_AIRPORT, NAME, ADDRESS, NUM_WORKERS, RENTAL, EMAIL) VALUES
('AMS', 'STORE1', '1A', '3', '3000', 'store1@gmail.com'),
('AMS', 'STORE2', '1B', '5', '2500', 'store2@gmail.com'),
('AMS', 'STORE3', '1C', '10', '5000', 'store3@gmail.com');



#Change the id of the first airport.
SELECT * FROM AIRPORT;

UPDATE AIRPORT SET id_airport = 'AMT' WHERE id_airport='AMS';


#Delete the first pilot. What happened to his flights?
SELECT * FROM FLIGHT;

DELETE FROM CREW WHERE id_crew = 1; -- LA COLUMNA ID_PILOT Y ID_COPILOT OBTENDRAN EL VALOR NULL

#Give the id, name of the airport and the id_store associated to each airport. Show null if the airport has no stores.


SELECT
	AIRPORT.id_airport, AIRPORT.location,
FROM
	AIRPORT LEFT JOIN STORE ON AIRPORT.id_airport = STORE.ID_AIRPORT;