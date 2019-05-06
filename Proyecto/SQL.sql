CREATE DATABASE AEMET;

CREATE TABLE ESTACION(
	ID_ESTACION VARCHAR(10) NOT NULL PRIMARY KEY,
    NOMBRE VARCHAR(150) NOT NULL,
    ALTITUD SMALLINT(4) NOT NULL,
    ID_PROVINCIA INT NOT NULL,
    LATITUD DECIMAL(13,10) NOT NULL,
    LONGITUD DECIMAL(13,10) NOT NULL,
    
    CONSTRAINT FK_PROVINCIA FOREIGN KEY (ID_PROVINCIA) REFERENCES PROVINCIA(ID_PROVINCIA)
    ON DELETE CASCADE ON UPDATE CASCADE

);


CREATE TABLE PROVINCIA(
	ID_PROVINCIA INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    PROVINCIA VARCHAR(32) NOT NULL
);

CREATE TABLE PREDICCION_TIEMPO(
	ID_ESTACION VARCHAR(10) NOT NULL,
    FECHA DATETIME NOT NULL,
    UBICACION VARCHAR(50) NOT NULL,
    PRECIPITACION DECIMAL(4,1),
    VIENTO_VELO_MAX DECIMAL(5,2),
    VIENTO_VELO_MEDIA DECIMAL(5,2),
    VIENTO_DIREC_MEDIA DECIMAL(4,1),
    HUMEDAD DECIMAL(4,1),
    INSOLACION DECIMAL(4,1),
    PRESION_AIRE DECIMAL(6,1),
    TEMP DECIMAL(3,1),
    TEMP_MIN DECIMAL(3,1),
    TEMP_MAX DECIMAL(3,1),
    VISIBILIDAD DECIMAL(3,1),
    ESPESOR_NIEVE DECIMAL(4,1),
    
	PRIMARY KEY (ID_ESTACION,FECHA),
    CONSTRAINT FK_ESTACION FOREIGN KEY (ID_ESTACION) REFERENCES ESTACION(ID_ESTACION)
    ON DELETE CASCADE ON UPDATE CASCADE

);