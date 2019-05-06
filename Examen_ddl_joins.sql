# Ejercicio 1
RENAME TABLE autores TO autor, comentarios TO comentario, fuentes TO fuente, noticias TO noticia;



#Ejercicio 2
ALTER TABLE comentario
ADD COLUMN likes INT NOT NULL DEFAULT 0,
ADD COLUMN dislikes INT NOT NULL DEFAULT 0;


#EJERCICIO 3
ALTER TABLE comentario
ADD CONSTRAINT LIKE_NOT_NEGATIVE CHECK(likes > 0),
ADD CONSTRAINT DISLIKE_NOT_NEGATIVE CHECK(dislikes > 0);

#EJERCICIO 4
SHOW CREATE TABLE noticia;

ALTER TABLE noticia 
MODIFY COLUMN autor_id INT,
ADD CONSTRAINT FK_AUTOR_ID FOREIGN KEY(autor_id) REFERENCES autor(id_autor) 
ON DELETE SET NULL 
ON UPDATE CASCADE;


#Ejercicio 5
SHOW CREATE TABLE comentario;

ALTER TABLE comentario
MODIFY COLUMN noticia INT NOT NULL,
ADD CONSTRAINT FK_IDNOTICIA FOREIGN KEY(noticia) REFERENCES noticia(id) 
ON DELETE CASCADE
ON UPDATE CASCADE;


#Ejercicio 6
ALTER TABLE noticia
ADD PRIMARY KEY(id);


CREATE TABLE noticia_fuente(
	id_noticia INT(11) NOT NULL,
    id_fuente INT(11) NOT NULL,
    
    PRIMARY KEY(id_noticia, id_fuente),
    
    FOREIGN KEY(id_noticia) REFERENCES noticia(id) ON DELETE CASCADE ON UPDATE CASCADE,
    
	FOREIGN KEY(id_fuente) REFERENCES fuente(link_id) ON DELETE RESTRICT ON UPDATE CASCADE
);




#Ejercicio 7
INSERT INTO noticia_fuente(id_noticia, id_fuente) VALUES
(66,57),
(72,58),
(80,59);



#EJERCICIO 8
INSERT INTO noticia(id,fecha,contenido,titulo) VALUES(12000, NOW(), 'PRUEBA PARA EL EXAMEN', 'EXAMEN DDL');

INSERT INTO autor(id_autor, login, password, correo) VALUES(1000, 'EXAMEN','EXAMEN', 'examen@gmail.com');


#EJERCICIO 9
UPDATE noticia SET id = 99999 WHERE id = 66;

SELECT 
*
from
comentario

WHERE
noticia = 99999;


#EJERCICIO 10


(SELECT
	*
FROM
	autor LEFT JOIN noticia ON autor.id_autor = noticia.autor_id)
UNION
(SELECT
	*
FROM
	autor RIGHT JOIN noticia ON autor.id_autor = noticia.autor_id);


#EJERCICIO 11

SELECT
	*
FROM
	noticia LEFT JOIN noticia_fuente ON noticia.id = noticia_fuente.id_noticia
    LEFT JOIN fuente ON noticia_fuente.id_fuente = fuente.link_id;

