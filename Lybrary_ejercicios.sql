/*
revisar 67

*/






/*************************
EJEMPLOS
*/
select * from BORROWS where year(BORROW_DATE) < year(now());

#1. Llista de poblacions dels socis (sense duplicats).

select
	distinct(city)
from
	MEMBERS;



#2. Nom i cognoms de tots els socis que viuen a Manacor o Felanitx.

select
	name as Nombre,
    surnames as Apellido
from
	MEMBERS
where 
	lower(city) = 'felanitx' or 
    lower(city) = 'manacor';


#3. Nom i cognoms de tots els socis que viuen a Manacor i s'han donat d'alta abans de 01/01/2009.

select
	name as Nombre,
    surnames as Apellidos
from
	MEMBERS
where
	lower(city) = 'manacor' and
    ENTRY_DATE < '2009-01-01';


#4. Nom i cognoms de tots els socis que vien a Manacor ordenat per cognom i després per nom (en cas de tenir el mateix llinatge ha d'ordenar per nom)

select
	surnames as Apellidos,
    name as Nombre
from
	MEMBERS
where
	lower(city) = 'manacor'
order by
	surnames,
    name;



#5. Nom i cognoms de tots els socis amb codi entre 10 i 20.

select
	name as Nombre,
    surnames as Apellidos
from
	MEMBERS
where
	MEMBER_CODE between '10' and '20';

#6. Titol dels llibres que no sabem la seva editorial.

select
	title as Titulo
from
	BOOKS
where
	PUBLISHER_CODE is null;


#7. Nom i cognoms de tots els socis de Manacor que no sabem el seu telefon.

select
	name as Nombre,
    surnames as Apellidos
from
	MEMBERS
where
	lower(PHONE) = 'null' or
    PHONE is null;

#8. Recuperar el número d'exemplar i el codi del soci de tots els préstecs fets aquest any i que encara no s'han retornat.

select
	MEMBER_CODE as CodigoMiembro,
    COPY_CODE as CodigoLibro
from
	BORROWS
where
	year(BORROW_DATE) = 2012
    RETURN_DATE is null;

#9. Titol dels llibres que contenen la paraula 'TIERRA' o la paraula 'FUEGO'.

select
	TITLE as Titulo
from
	BOOKS
where
	lower(TITLE) like '%tierra%' or
    lower(TITLE) like '%fuego%';


#10. Titol dels llibres que contenen la paraula 'TIERRA' i no contenen la paraula 'PILARES'

select
	TITLE as Titulo
from
	BOOKS
where
	lower(TITLE) like '%tierra%' and (not
    lower(TITLE) like '%pilares%');

#11. Editorials de les que si sabem la provincia i no sabem la poblacio

select
	NAME as Editorial
from
	PUBLISHERS
where
	STATE is not null and
    CITY is null;
    

#12. Prestecs que han estat retornats fora de pla�

select
	*
from
	BORROWS
where
	date(RETURN_DATE) > date(DEAD_DATE);

#13. Titol, nom complet de l'autor i nom de l'editorial de tots els llibres 

select
	bo.TITLE as TituloLibro,
    concat(au.NAME, " ", au.SURNAMES) as Autor,
	pu.NAME as Editorial
from
	BOOKS bo,
    AUTHORS au,
    PUBLISHERS pu
where
	bo.AUTHOR_CODE = au.AUTHOR_CODE and
    bo.PUBLISHER_CODE = pu.PUBLISHER_CODE;

#14. Titol, nom complet de l'autor i nom de l'editorial de tots els llibres escrits en l'idioma 'CA'

select
	bo.TITLE as TituloLibro,
    concat(au.NAME, " ", au.SURNAMES) as Autor,
	pu.NAME as Editorial
from
	BOOKS bo,AUTHORS au,
    PUBLISHERS pu
where
	bo.AUTHOR_CODE = au.AUTHOR_CODE and
    bo.PUBLISHER_CODE = pu.PUBLISHER_CODE and
    lower(bo.LANGUAGE) = 'ca';


#15. Socis que son de Manacor i que han realitzat qualque prestec

select
	distinct concat(me.NAME, " ", SURNAMES) as `Nombre Completo`
from
	BORROWS bo,
    MEMBERS me
where
	bo.MEMBER_CODE = me.MEMBER_CODE and
    lower(me.CITY) = 'manacor';



#16. Socis que tenen llibres en prestec a dia d'avui

select
	distinct concat(me.NAME, " ", me.SURNAMES) as `Socio`
from
	BORROWS bo,
    MEMBERS me
where
	bo.MEMBER_CODE = me.MEMBER_CODE and
    bo.RETURN_DATE is null;


#17. Titol de tots els llibres que ha tret en prestec el soci número 1 

select
	distinct BOOKS.TITLE as Titulo
from
	MEMBERS me,
    BORROWS bo,
    COPIES co,
    BOOKS
where
	me.MEMBER_CODE = bo.MEMBER_CODE and
    bo.COPY_CODE = co.COPY_CODE and
    lower(co.BOOK_CODE) = lower(BOOKS.BOOK_CODE) and
    me.MEMBER_CODE = 1;







#18. Nom de les editorials que han publicat qualque llibre d'HISTORIA

select
	distinct lower(pu.NAME) as Editorial
from
	PUBLISHERS pu,
    BOOKS bo,
    GENREBOOK gen,
    GENRES ge
where
	pu.PUBLISHER_CODE = bo.PUBLISHER_CODE and
    lower(bo.BOOK_CODE) = lower(gen.BOOK_CODE) and
    lower(gen.GENRE_CODE) = lower(ge.GENRE_CODE) and
    lower(ge.NAME) = 'historia';
    

#19. Nom complet dels autors que han escrit qualque llibre publicat per l'editorial PLANETA

select
	concat(au.name, " ", au.surnames) as Autors
from
	AUTHORS au,
    BOOKS bo,
    PUBLISHERS pu
where
	au.AUTHOR_CODE = bo.AUTHOR_CODE and
    bo.PUBLISHER_CODE = pu.PUBLISHER_CODE and
    lower(pu.NAME) = 'planeta';


#20. Nom dels autors distints d'en NOAH GORDON que han escrit qualque llibre d'HISTORIA 

select
	lower(concat(au.NAME, " ", au.SURNAMES))as Autor
from
	AUTHORS au,
    BOOKS bo,
    GENREBOOK gen,
    GENRES ge
where
	au.AUTHOR_CODE = bo.AUTHOR_CODE and
    lower(bo.BOOK_CODE) = lower(gen.BOOK_CODE) and
    lower(gen.GENRE_CODE) = lower(ge.GENRE_CODE) and
    lower(ge.NAME) = 'historia' and
    not lower(au.NAME) = 'noah';

##### Lo de abajo entra para el proximo examen

#21. Nom complet dels socis que viuen a la mateixa poblaci� que el soci n�mero 1


select
	lower(concat(NAME, " ", SURNAMES)) as NombreCompleto
from
	MEMBERS 
where
	CITY = (select
				city
			from
				MEMBERS 
			where
				MEMBER_CODE = 1
			);

#22. Titol de tots els llibres publicats per la mateixa editorial que el llibre "EL MEDICO"

select 
	lower(TITLE) as Titulo
from
	BOOKS
where
    PUBLISHER_CODE = (

						select
							pu.PUBLISHER_CODE
						from
							BOOKS bo,
							PUBLISHERS pu
						where
							bo.PUBLISHER_CODE = pu.PUBLISHER_CODE and
							lower(TITLE) = 'el medico'
						);

#23. Titol de tots els llibres escrits per el mateix autor que el llibre "EL MEDICO"

select
	lower(TITLE) as Titulo
from
	BOOKS
where
	PUBLISHER_CODE = (
						select
							bo.PUBLISHER_CODE
						from
							BOOKS bo,
							AUTHORS au
						where
							bo.AUTHOR_CODE = au.AUTHOR_CODE and
							lower(bo.TITLE) = 'el medico'
						);

#24. Titol de tots els llibres que pertanyen a alg�n dels temes als que pertany el llibre "EL MEDICO"

-- select
-- 	gen.GENRE_CODE
-- from
-- 	BOOKS bo,
--     GENREBOOK gen
-- where
-- 	bo.BOOK_CODE = gen.BOOK_CODE and
--     lower(bo.TITLE) = 'el medico';

select
	lower(bo.TITLE) as Titulo
from
	BOOKS bo,
    GENREBOOK gen
where
	bo.BOOK_CODE = gen.BOOK_CODE and
    gen.GENRE_CODE in (
						select
							gen.GENRE_CODE
						from
							BOOKS bo,
							GENREBOOK gen
						where
							bo.BOOK_CODE = gen.BOOK_CODE and
							lower(bo.TITLE) = 'el medico'
					);


#25. Retorna el nombre de socis de Manacor

select
	count(*) as NumeroSocios
from
	MEMBERS;

#26. Retorna el nombre de prestecs que ha fet el soci n�mero 1

select
	count(*) Prestamos
from
	MEMBERS,
    BORROWS
where
	MEMBERS.MEMBER_CODE = BORROWS.MEMBER_CODE and
	MEMBERS.MEMBER_CODE = 1;


#27. Retorna quants de llibres hi ha hagin estat publicats per la mateixa editorial que el llibre "EL MEDICO"

-- (select
-- 	PUBLISHERS.NAME
-- from
-- 	BOOKS,
--     PUBLISHERS
-- where
-- 	BOOKS.PUBLISHER_CODE = PUBLISHERS.PUBLISHER_CODE and
--     lower(BOOKS.TITLE) = 'el medico');
    
    

select
	count(*) as Cantidad
from
	BOOKS,
    PUBLISHERS
where
	BOOKS.PUBLISHER_CODE = PUBLISHERS.PUBLISHER_CODE and
    PUBLISHERS.NAME = (select
							PUBLISHERS.NAME
						from
							BOOKS,
							PUBLISHERS
						where
							BOOKS.PUBLISHER_CODE = PUBLISHERS.PUBLISHER_CODE and
							lower(BOOKS.TITLE) = 'el medico');
                            
                            

#28. Retorna el n�mero total de pr�stecs que s'han fet l'any 2011 i el nombre total de prestecs retornats pel mateix any

select
	(select
		count(*)
	from
		BORROWS
	where
		year(BORROW_DATE) = 2011
    ) as echos2011,
    
    (select
		count(*)
	from
		BORROWS
	where
		year(RETURN_DATE) = 2011
    ) as regresados2011
;

#29. Retorna el n�mero de codi de soci m�s alt que hi ha

select
	max(MEMBER_CODE)
from
	MEMBERS;

#30. Retorna en quin dia varem donar d'alta el soci m�s antic (no t� perqu� ser el soci n�mero 1)

select
	min(ENTRY_DATE)
from
	MEMBERS;

#31. Retorna la mitjana de n�mero d'edicions de tots els llibres de la base de dades.

select
	avg(EDITION_NUM)
from
	BOOKS;

#32. Retorna quants de llibres ha escrit cada autor (format de la resposta: nom cognoms total, noha gordon 7, ...)


select
	lower(concat(au.NAME, " ", au.SURNAMES)) as nombreCompleto,
    count(*) as librosEscritos
from
	BOOKS bo,
    AUTHORS au
where
	bo.AUTHOR_CODE = au.AUTHOR_CODE
group by bo.AUTHOR_CODE;


#33. Retorna quants d'exemplars hi ha en total per cada llibre (format de la resposta: titol total, el m�dico 6, ...)

select
	bo.TITLE as titulo,
    count(*) as NumeroEjemplares
from
	BOOKS bo,
    COPIES co
where
	bo.BOOK_CODE = co.BOOK_CODE
group by bo.BOOK_CODE;


#34. Retorna quants d'exemplars hi ha de mitjana per cada llibre
select
    avg(T.cantidad)
from
	(select
		count(*) as cantidad
	from
		BOOKS bo,
		COPIES co
	where
		bo.BOOK_CODE =co.BOOK_CODE
	group by bo.BOOK_CODE) T;

#35. Retorna quants de llibres hi ha de cada tema (format de la sortida: tema total, hist�ria 25, ...)

select
	ge.NAME as Tema,
    count(*)
from
	BOOKS bo,
    GENREBOOK gen,
    GENRES ge
where
	bo.BOOK_CODE = gen.BOOK_CODE and
    gen.GENRE_CODE= ge.GENRE_CODE
group by ge.GENRE_CODE;

#36. Retorna quants de prestecs ha fet en total cada soci (format de la resposta: nom cognoms total, toni mesquida 7, ...)

select
	lower(concat(me.NAME, " ", me.SURNAMES)) as NombreCompleto,
	count(*) NumeroPrestamos
from
	MEMBERS me,
    BORROWS bo
where
	me.MEMBER_CODE = bo.MEMBER_CODE
group by me.MEMBER_CODE;

#37. Retorna per cada soci, quants de prestecs ha fet en total (format de la resposta: nom cognoms total, toni mesquida  7, ...)

-- Es lo mismo que el ejercicio 36.

#38. Recupera, per cada soci, el nombre de llibres que ha agafat en pr�stec.

select
	lower(concat(me.NAME, " ", me.SURNAMES)) as nombreCompleto,
    count(*) as cantidadPrestamos
from
	MEMBERS me,
    BORROWS bo,
    COPIES co,
    BOOKS boo
where
	me.MEMBER_CODE = bo.MEMBER_CODE and
    bo.COPY_CODE = co.COPY_CODE and
    co.BOOK_CODE = boo.BOOK_CODE
group by me.MEMBER_CODE;


#39. Llista de socis que en total han realitzat m�s de 10 prestecs.

select
	me.MEMBER_CODE as codigoMiembro,
	concat(me.NAME, " ",me.SURNAMES)as nombre,
	count(*) as cantidadLibros
from
	MEMBERS me,
    BORROWS bo,
    COPIES co,
    BOOKS boo
where
	me.MEMBER_CODE = bo.MEMBER_CODE and
	bo.COPY_CODE = co.COPY_CODE and
	co.BOOK_CODE = boo.BOOK_CODE
group by codigoMiembro
having cantidadlibros > 10;



#40. Llista de socis que tenen m�s d'un llibre en prestec actualment.

select
	concat(me.NAME, " ", me.SURNAMES) as nombre,
	count(*) as cantidadLibro
from
	MEMBERS me,
	BORROWS bo
where
	me.MEMBER_CODE = bo.MEMBER_CODE and
	bo.RETURN_DATE is null
group by me.MEMBER_CODE
having 
	cantidadLibro > 1;


#41. Per el llibre "El m�dico", retorna el nombre total d'exemplars, nombre d'exemplars actualment en prestec i nombre d'exemplars disponibles (format de la sortida: titol total en_prestec disponibles, el medico 5 3 2)



select
	Tejemplares.numeroEjemplares,
    Tprestado.ejemplaresPrestado,
	(numeroEjemplares - ejemplaresPrestado) as ejemplaresStock
from
	(select
		count(*) as numeroEjemplares
	from
		BOOKS,
		COPIES
	where
		BOOKS.BOOK_CODE = COPIES.BOOK_CODE and
		lower(BOOKS.TITLE) = 'el medico'
	) Tejemplares,
			
	(select
		count(*) as ejemplaresPrestado
	from
		BOOKS,
		COPIES,
		BORROWS
	where
		BOOKS.BOOK_CODE = COPIES.BOOK_CODE and
		COPIES.COPY_CODE = BORROWS.COPY_CODE and
		lower(BOOKS.TITLE) = 'el medico' and
		BORROWS.RETURN_DATE is null
	) Tprestado
    ;



#42. Recupera el nombre de dies del pr�stec m�s llarg de l�any 2011.

select
	max(datediff(RETURN_DATE, BORROW_DATE)) as diasPrestamos
from
	BORROWS
where
	year(BORROW_DATE) = 2011 ;


#43. Recupera el nombre de llibres que hi ha a la biblioteca, el n� d�exemplars i el promig d�exemplars per llibre
select
	Tlibros.libros,
    Tcopias.copias,
    (Tlibros.libros / Tcopias.copias) as promedioEjemplares
from
	(select
		count(*) as libros
	from
		BOOKS) Tlibros,

	(select
		count(*) as copias
	from
		COPIES) Tcopias;


#44. Nombre de llibres diferents que ha agafat en pr�stec cada soci

select
	MEMBER_CODE,
	count(*) as librosPrestados
from
	BORROWS
group by MEMBER_CODE;

#45. Promig de dies que ha estat cada llibre en pr�stec

select
	boo.TITLE,
	sum(Tdias.dias) as dias,
    sum(TlibroPrestado.cantidadCopias) as cantidadCopias,
	round(sum(Tdias.dias) / sum(TlibroPrestado.cantidadCopias),2) as promedioDiasPrestados
from
	(select
		COPY_CODE, 
		datediff(ifnull(RETURN_DATE,now()), BORROW_DATE) as dias
	from
		BORROWS
	group by COPY_CODE, MEMBER_CODE, BORROW_DATE) Tdias,
	(select
		COPY_CODE,
		count(*) as cantidadCopias
	from
		BORROWS
	group by COPY_CODE, MEMBER_CODE, BORROW_DATE) TlibroPrestado,
    COPIES co,
    BOOKS boo
where 
	Tdias.COPY_CODE = TlibroPrestado.COPY_CODE and
    TlibroPrestado.COPY_CODE = co.COPY_CODE and
    co.BOOK_CODE = boo.BOOK_CODE and
    Tdias.dias is not null and
    Tdias.dias >= 0
group by Tdias.COPY_CODE
order by promedioDiasPrestados desc;
    
    
############ Bonus 1. Top 10 de usuarios com más prestamos.

select
	me.MEMBER_CODE,
    me.NAME,
	count(*) as cantidadPrestamos
from
	MEMBERS me,
    BORROWS boo
where
	me.MEMBER_CODE = boo.MEMBER_CODE
group by boo.MEMBER_CODE
order by cantidadPrestamos desc
limit 10;

#46. Nom i cognoms dels socis que viuen a Manacor o Son Servera i que s�han donat d�alta durant l'any 2011.

select
	*
from
	MEMBERS
where
	lower(CITY)='manacor' or
    lower(CITY)='son servera';

#47. Retorna el nombre de socis dels que no sabem el seu telefon

select
	count(*) as socios
from
	MEMBERS
where
	PHONE is null;

#48. Recuperar el n�mero d�exemplar i el codi del soci de tots els pr�stecs fets aquest any i que o b� han estat retornats fora de pla� o b� encara no s�han retornat i ja estan fora de pla�.

select
	MEMBER_CODE,
    COPY_CODE
from
	BORROWS
where
	year(BORROW_DATE)=2011 and
    (RETURN_DATE is null or
    RETURN_DATE > DEAD_DATE);

#49. Nombre d�exemplars de cadascun els llibres editats per "Planeta". S�ha de recuperar el t�tol, idioma, autor i nombre d�exemplars del llibre, ordenat per nombre d�exemplars i autor.

select
	TinfoLibros.titulo,
    TinfoLibros.idioma,
    concat(TinfoLibros.nombre, ", ", TinfoLibros.apellidos) as autor,
    Tcantidad.cantidad as cantidadExemplares
from
	(select
		BOOKS.BOOK_CODE as codigo,
		BOOKS.TITLE as titulo,
		BOOKS.LANGUAGE as idioma,
		AUTHORS.NAME as nombre,
		AUTHORS.SURNAMES as apellidos
		
	from
		BOOKS,
		PUBLISHERS,
		AUTHORS
	where
		BOOKS.PUBLISHER_CODE = PUBLISHERS.PUBLISHER_CODE and
		BOOKS.AUTHOR_CODE = AUTHORS.AUTHOR_CODE and
		lower(PUBLISHERS.NAME) = 'planeta'
        ) TinfoLibros,

	(select
		BOOK_CODE as codigo,
		count(*) as cantidad
	from
		COPIES
	group by COPIES.BOOK_CODE
    ) Tcantidad
where
	TinfoLibros.codigo = Tcantidad.codigo
order by cantidadExemplares, autor;


#50. Recuperar el codi dels llibres que pertanyen a m�s d�un tema.

select
	BOOKS.BOOK_CODE,
	count(GENREBOOK.GENRE_CODE) as generos
from
	BOOKS,
    GENREBOOK
where
	BOOKS.BOOK_CODE = GENREBOOK.BOOK_CODE
group by BOOKS.BOOK_CODE
having generos > 1;
    


#51. T�tol dels llibres deixats en pr�stec l�any 2011

select
	distinct BOOKS.TITLE
from
	BORROWS,
    COPIES,
    BOOKS
where
	BORROWS.COPY_CODE = COPIES.COPY_CODE and
    COPIES.BOOK_CODE = BOOKS.BOOK_CODE and
	year(BORROWS.BORROW_DATE) = 2011 and
    year(BORROWS.RETURN_DATE) = 2011;


#52. Nombre total de pr�stecs que han fet els socis que han tret algun llibre durant l�any 2011.

select
	count(*) as prestamos
from
	BORROWS
where
	year(BORROW_DATE) = 2011 and
    year(RETURN_DATE) = 2011;


#53. Llista de socis que no han realitzat cap prestec aquest any.

select
	distinct concat(MEMBERS.NAME, " ", MEMBERS.SURNAMES) as socios
from
	BORROWS,
    MEMBERS
where
	BORROWS.MEMBER_CODE = MEMBERS.MEMBER_CODE and
	BORROWS.MEMBER_CODE not in (select
							distinct MEMBER_CODE
						from
							BORROWS
						where
							year(BORROW_DATE) = 2011);


select
	distinct MEMBER_CODE
from
	BORROWS
where
	year(BORROW_DATE) = 2011;

#54. Llista prestecs que s'han realitzat al m�s de febrer de 2011

select
	*
from
	BORROWS
where
	extract(year_month from BORROW_DATE) = 201103;

#55. Llista de prestecs de m�s de 15 dies de durada

select
	*,
    datediff(DEAD_DATE, BORROW_DATE)
from
	BORROWS
where
	datediff(DEAD_DATE, BORROW_DATE) > 15;

#56. Llista de socis que s'han donat d'alta el mateix mes que el soci n�mero 1

-- select
-- 	month(ENTRY_DATE)
-- from
-- 	MEMBERS
-- where
-- 	MEMBER_CODE=1;
-- 
select
	 distinct concat(NAME, " ", SURNAMES) as socios
from
	MEMBERS
where
	month(ENTRY_DATE)= (select
							month(ENTRY_DATE)
						from
							MEMBERS
						where
							MEMBER_CODE=1) and
	year(ENTRY_DATE)= (select
							year(ENTRY_DATE)
						from
							MEMBERS
						where
							MEMBER_CODE=1)
                            ;

#57. T�tol del llibre amb m�s edicions

select
	BOOKS.TITLE
from
	BOOKS
where
    BOOKS.EDITION_NUM = (select
								max(EDITION_NUM)
							from
								BOOKS);
    
#58. Nom i cognoms dels socis que tenen algun llibre no retornat.

select
	distinct concat(MEMBERS.NAME," ", MEMBERS.SURNAMES)
from
	BORROWS,
    MEMBERS
where
	BORROWS.MEMBER_CODE = MEMBERS.MEMBER_CODE and
	BORROWS.RETURN_DATE is null;

#59. Retorna el t�tol dels llibres escripts per autors que han escrit m�s de 2 llibres

select
	BOOKS.TITLE
from
	(select
		AUTHOR_CODE as autor
	from
		BOOKS
	group by AUTHOR_CODE
	having count(*) > 2) Tautores,
    BOOKS
where
	BOOKS.AUTHOR_CODE = Tautores.autor;

#60. Nom i cognoms del soci que ha realitzat m�s prestecs

-- select
--     max(T.prestamos)
-- from
-- 	(select
-- 		count(*) as prestamos
-- 	from
-- 		BORROWS
-- 	where
-- 		BORROWS.RETURN_DATE is not null
-- 	group by BORROWS.MEMBER_CODE
--     ) T;


select
	BORROWS.MEMBER_CODE as miembro,
	MEMBERS.NAME as nombre,
	MEMBERS.SURNAMES as apellidos,
	count(*) as prestamos
from
	BORROWS,
	MEMBERS
where
	BORROWS.MEMBER_CODE = MEMBERS.MEMBER_CODE and
	BORROWS.RETURN_DATE is not null
group by BORROWS.MEMBER_CODE
having prestamos = (select
						max(T.prestamos)
					from
						(select
							count(*) as prestamos
						from
							BORROWS
						where
							BORROWS.RETURN_DATE is not null
						group by BORROWS.MEMBER_CODE
						) T
					);

#61. Per cada llibre de l'autor 'follett' recuperar el n�mero pr�stecs i el promig de dies que ha estat en pr�stec. Els llibres que estan en prestenc i encara no han sigut retornats contarem fins al dia d'avui.

SELECT 
	COUNT(DIAS) PRESTAMOS, 
	SUM(DIAS)/COUNT(DIAS) DIAS 
FROM
	(select
		datediff(ifnull(RETURN_DATE, NOW()), BORROW_DATE) DIAS
	from
		AUTHORS,
		BOOKS,
		COPIES,
		BORROWS
	where
		AUTHORS.AUTHOR_CODE = BOOKS.AUTHOR_CODE and
		BOOKS.BOOK_CODE = COPIES.BOOK_CODE and
		COPIES.COPY_CODE = BORROWS.COPY_CODE and
		lower(AUTHORS.SURNAMES) = 'follett') T;
    
    
    
    
    
#62. Quin es el llibre del que tenim m�s exemplars

(select
	count(*)
from
	BOOKS,
    COPIES
where
	BOOKS.BOOK_CODE = COPIES.BOOK_CODE
group by BOOKS.BOOK_CODE);

#63. N�mero de socis que no han agafat en prestec cap llibre des de fa m�s d'un any
select
	count(*) socios
from
	(select
		MEMBER_CODE,
		max(RETURN_DATE),
		truncate(datediff(now(), ifnull(max(RETURN_DATE), now())) / 365,0) tiempo
	from
		BORROWS
	group by MEMBER_CODE
	having tiempo > 1
    ) T;


#64. Quantitat de prestecs realitzats per tots els socis de la mateixa edat (format de la resposta: edat nro_prestecs, 20 (anys) 20 (prestecs))

select
    truncate(datediff(now(), MEMBERS.ENTRY_DATE)/365,0) as edat,
    count(BORROWS.MEMBER_CODE) as numeroPrestamos
from
	MEMBERS, BORROWS
where
	MEMBERS.MEMBER_CODE = BORROWS.MEMBER_CODE
group by edat;


#65. Tots els llibres que el seu titol cont� la paraula 'viatge' i estan disponibles 

select
	*
from
	BOOKS,
	COPIES,
	BORROWS
where
	BOOKS.BOOK_CODE = COPIES.BOOK_CODE and
	COPIES.COPY_CODE = BORROWS.COPY_CODE and
	lower(BOOKS.TITLE) like '%demons%' and
	BORROWS.RETURN_DATE is not null and
    date(BORROWS.RETURN_DATE) <= date(now()) ;

#66. Dades dels socis que han tret m�s de 2 vegades el mateix llibre

select
	BORROWS.MEMBER_CODE,
    BORROWS.COPY_CODE,
    MEMBERS.NAME,
    MEMBERS.SURNAMES,
    count(*) as prestamos
from
	BORROWS,
    MEMBERS
where
	BORROWS.MEMBER_CODE = MEMBERS.MEMBER_CODE
group by BORROWS.COPY_CODE, BORROWS.MEMBER_CODE
having prestamos > 2;

#67. Dades dels prestecs (dates, soci i llibre) que han sigut retornats 20 dies, o m�s, tard de la data indicada O que no han sigut retornats i ja han passat mes de 20 dies de la data estipulada

-- select
-- 	*,
--     datediff(RETURN_DATE, DEAD_DATE)
-- from
-- 	BORROWS
-- where
-- 	RETURN_DATE is null and
-- 	date_add(DEAD_DATE, interval 20 day) <= now() or
-- 	datediff(RETURN_DATE, DEAD_DATE) >= 20;


select 
	BORROWS.BORROW_DATE,
    BORROWS.RETURN_DATE,
    BORROWS.DEAD_DATE,
    concat(MEMBERS.SURNAMES, ", ", MEMBERS.NAME) as SOCIO,
    BOOKS.TITLE
from
	BOOKS,
    COPIES,
    BORROWS,
    MEMBERS
where
	BOOKS.BOOK_CODE = COPIES.BOOK_CODE and
    COPIES.COPY_CODE = BORROWS.COPY_CODE and
    BORROWS.MEMBER_CODE = MEMBERS.MEMBER_CODE and
    (BORROWS.RETURN_DATE is null and
    date_add(BORROWS.DEAD_DATE, interval 20 day) <= now() or
    datediff(BORROWS.RETURN_DATE, BORROWS.DEAD_DATE) >= 20);

-- select
-- 	*,
-- 	datediff(RETURN_DATE, DEAD_DATE) as diferencia1
-- from
-- 	BORROWS
-- where
-- 	datediff(RETURN_DATE, DEAD_DATE) >= 20;


#68. Nom I cognoms del soci m�s antic de la biblioteca.

select
	NAME,
    SURNAMES
from
	MEMBERS
where
	datediff(now(),ENTRY_DATE) = (select
									max(datediff(now(),ENTRY_DATE))
								from
									MEMBERS
								);

#69. Codi dels socis que durant l�any 2010 han agafat en pr�stec algun dels llibres escrits  per 'gordon'.

select
	 distinct MEMBERS.MEMBER_CODE
from
	BOOKS,
    AUTHORS,
    COPIES,
    BORROWS,
    MEMBERS
where
	BOOKS.AUTHOR_CODE = AUTHORS.AUTHOR_CODE and
    BOOKS.BOOK_CODE = COPIES.BOOK_CODE and
    COPIES.COPY_CODE = BORROWS.COPY_CODE and
    BORROWS.MEMBER_CODE = MEMBERS.MEMBER_CODE and
    lower(AUTHORS.SURNAMES) = 'gordon' and
    year(BORROWS.BORROW_DATE) = 2010;

#70. Recuperar tots els llibres del tema "HISTORIA" indicant el seu n�mero de prestecs

select
	BOOKS.TITLE,
    count(*) as prestamos
from
	BOOKS,
    GENREBOOK,
    GENRES,
    COPIES,
    BORROWS
where
	BOOKS.BOOK_CODE = GENREBOOK.BOOK_CODE and
    GENREBOOK.GENRE_CODE = GENRES.GENRE_CODE and
    BOOKS.BOOK_CODE = COPIES.BOOK_CODE and
    COPIES.COPY_CODE = BORROWS.COPY_CODE and
    GENRES.NAME = 'historia'
group by BOOKS.BOOK_CODE;


#71. Quin es el llibre m�s sol�licitat per cada any, �s a dir, que ha estat tret en prestec m�s vegades.

-- select
-- 	count(BOOKS.BOOK_CODE),
--     BOOKS.BOOK_CODE,
-- 
-- from
-- 	BORROWS,
--     COPIES,
--     BOOKS
-- where
-- 	BORROWS.COPY_CODE = COPIES.COPY_CODE and
--     COPIES.BOOK_CODE = BOOKS.BOOK_CODE
-- group by BOOKS.BOOK_CODE;

select
	*
from
	(select
		year(BORROWS.BORROW_DATE) c1,
		BOOKS.TITLE,
		count(*) prestamos
	from
		BORROWS,
		COPIES,
		BOOKS
	where
		BORROWS.COPY_CODE = COPIES.COPY_CODE and
		COPIES.BOOK_CODE = BOOKS.BOOK_CODE
	group by c1, BOOKS.BOOK_CODE) Tprestamos,

	(select
		TprestamosYaer.c1,
		max(TprestamosYaer.prestamos) as prestamos
	from
		(select
			year(BORROWS.BORROW_DATE) c1,
			BOOKS.TITLE,
			count(*) prestamos
		from
			BORROWS,
			COPIES,
			BOOKS
		where
			BORROWS.COPY_CODE = COPIES.COPY_CODE and
			COPIES.BOOK_CODE = BOOKS.BOOK_CODE
		group by c1, BOOKS.BOOK_CODE) TprestamosYaer
        
	group by TprestamosYaer.c1) TmaxPrestamoYear
     
where
	Tprestamos.c1 = TmaxPrestamoYear.c1 and
    Tprestamos.prestamos = TmaxPrestamoYear.prestamos;


