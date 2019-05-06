select 
	* 
from 
	country;
    
    
    

select 
	min(IndepYear) 
from 
	country;
    
    
    

select 
	round(avg(LifeExpectancy)) 
from 
	country;
    
    
    

select 
	upper(name) 
from 
	country;
    
    
    

select 
	concat(Region, ", ", Continent) 
from 
	country;
    
    
    

select 
	count(*) 
from 
	country;
    
    
    

/*
select #set how to project results
	*
from # set the data sources
	country;
where #filter the data source through conditions. Discard every row that doesn't meet the condition (condition is false)
*/

select 
	* 
from 
	country 
where 
	Continent != "Asia";
    
    
    

select 
	* 
from 
	country 
where 
	Population > 1000000;
    
    
    

select 
	* 
from 
	country 
where 
	Population >= 1000000;
    
    
    

select 
	* 
from 
	country 
where 
	Population < 1000000;
    
    
    

select 
	* 
from 
	country 
where 
	IndepYear between 1900 and 2000;
    
    
    

# && (and) || (or)  ! (not) JAVA
# and	    or		not		SQL

select 
	count(*) 
from 
	country 
where 
	LifeExpectancy > 60 and Continent="asia";
    
    
    

select 
	distinct(GovernmentForm) 
from 
	country;
    
    
    

select 
	* 
from 
	country 
where 
	upper(GovernmentForm)="REPUBLIC";
    
    
    

select 
	distinct(GovernmentForm) 
from 
	country 
where 
	upper(GovernmentForm) LIKE '%REPUBLIC%';
    
    
    

select 
	* 
from 
	country 
where 
	upper(GovernmentForm) like '%REPUBLIC%';
    
    
    

select 
	Name 
from 
	country 
where 
	upper(Name) like '%ISTAN';
    
    
    

#Names of countries starting with B
select 
	Name 
from 
	country 
where lower(Name) like 'b%';




#Wildcards for LIKE statement % any string _ any character
#Names of countries starting with B and having the 3rd char l
select 
	Name 
from 
	country 
where lower(Name) like 'b_l%';




#Names of cuntries with more than 7 characters
select 
	Name 
from 
	country 
where 
	lower(Name) like '______%'; #chapusa




select 
	Name, 
    char_length(Name) as length 
from 
	country 
where 
	char_length(Name)>7; #forma de hacerlo
    
    
select 
	Code,
    name
from 
	country;


select
	name,
	countrycode
from
	city;
    

#juntar dos entidades (También llamado JOIN), las dos entidades tiene que que estar relacionadas por un camino.
#Name of the city and name of the country.
select
	city.Name as ciudad,
    country.Name as pais
from
	city,
    country
where
	city.CountryCode = country.Code;
    



#Languages spoken in Asia
select
    countrylanguage.Language
from
	country,
    countrylanguage
where
	country.Code = countrylanguage.CountryCode and 
    upper(country.Continent)="ASIA";
    


#Name of countries where English is official.
select
	c.Name
from
	country c,
    countrylanguage cl
where
	c.Code = cl.CountryCode and 
    upper(cl.IsOfficial)='T' and 
    upper(cl.Language)='ENGLISH';



#Name of country and name of capital of countries from America where spanish is spoken.
select
	co.Name as pais,
    ci.Name as capital
from
	country co,
    countrylanguage cl,
    city ci
where
	co.Code = cl.CountryCode and
    co.Capital = ci.ID and
    upper(cl.Language) = 'SPANISH' and
    upper(co.Continent) like '%AMERICA';
    
    
select now();#Devuelve el día de hoy

    
