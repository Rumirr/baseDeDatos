-- 1. Write a query to find the addresses (location_id, street_address, city, state_province, country_name) of all the departments.(Hint: Use NATURAL JOIN)
  
SELECT
	location_id, street_address, city, state_province, country_name
FROM
	locations NATURAL JOIN countries;
  
-- 2. Write a query to find the names (first_name, last name), department ID and name of all the employees.

SELECT
	FIRST_NAME,LAST_NAME,DEPARTMENT_ID
FROM
	employees;
  
-- 3. Find the names (first_name, last_name), job, department number, and department name of the employees who work in London.

SELECT
	employees.FIRST_NAME, employees.LAST_NAME, jobs.JOB_TITLE, departments.DEPARTMENT_ID, departments.DEPARTMENT_NAME
FROM
	employees INNER JOIN jobs ON jobs.JOB_ID = employees.JOB_ID
    INNER JOIN departments ON employees.DEPARTMENT_ID = departments.DEPARTMENT_ID
    INNER JOIN locations ON departments.LOCATION_ID = locations.LOCATION_ID
WHERE
	locations.CITY = 'LONDON';

-- 4. Write a query to find the employee id, name (last_name) along with their manager_id, manager name (last_name).

SELECT
	A.EMPLOYEE_ID, A.LAST_NAME, B.MANAGER_ID, B.LAST_NAME
FROM
	employees A LEFT JOIN employees B ON A.EMPLOYEE_ID = B.MANAGER_ID;

-- 5. Find the names (first_name, last_name) and hire date of the employees who were hired after 'Jones'. (two ways: subquery)

(SELECT
	employees.HIRE_DATE
FROM
	employees
WHERE
	employees.LAST_NAME = 'JONES');

SELECT
	employees.FIRST_NAME, employees.LAST_NAME, employees.HIRE_DATE
FROM
	employees
WHERE
	employees.HIRE_DATE > (SELECT
								employees.HIRE_DATE
							FROM
								employees
							WHERE
								employees.LAST_NAME = 'JONES');

-- 6. Write a query to get the department name and number of employees in the department.

SELECT
	departments.DEPARTMENT_NAME, COUNT(employees.EMPLOYEE_ID) NUMBER_EMPLOYEES
FROM
	employees RIGHT JOIN departments ON employees.DEPARTMENT_ID = departments.DEPARTMENT_ID
GROUP BY departments.DEPARTMENT_ID;
-- No surten departaments amb 0 empleats

SELECT
	departments.DEPARTMENT_NAME, COUNT(employees.EMPLOYEE_ID) NUMBER_EMPLOYEES
FROM
	employees INNER JOIN departments ON employees.DEPARTMENT_ID = departments.DEPARTMENT_ID
GROUP BY departments.DEPARTMENT_ID;

-- 7. Find the employee ID, job title, number of days between ending date and starting date for all jobs in department 90 from job history. 
USE hr;
SELECT
	employees.EMPLOYEE_ID, jobs.JOB_TITLE, DATEDIFF(job_history.END_DATE, job_history.START_DATE) NUMBER_OF_DAYS
FROM
	employees INNER JOIN jobs ON employees.JOB_ID = jobs.JOB_ID
    INNER JOIN job_history ON employees.EMPLOYEE_ID = job_history.EMPLOYEE_ID
WHERE
	job_history.DEPARTMENT_ID = 90;

-- 8. Write a query to display the department ID, department name and manager first name.

SELECT
	departments.DEPARTMENT_ID, departments.DEPARTMENT_NAME, employees.FIRST_NAME
FROM
	departments INNER JOIN employees ON departments.MANAGER_ID = employees.EMPLOYEE_ID;

-- Si volem que surtin TOTS els departaments encara que no tenguin manager
SELECT
	departments.DEPARTMENT_ID, departments.DEPARTMENT_NAME, employees.FIRST_NAME
FROM
	departments LEFT JOIN employees ON departments.MANAGER_ID = employees.EMPLOYEE_ID;

-- 9. Write a query to display the department name, manager name, and city.

SELECT
	departments.DEPARTMENT_NAME, employees.FIRST_NAME, locations.CITY
FROM
	departments INNER JOIN employees ON departments.MANAGER_ID = employees.MANAGER_ID
    INNER JOIN locations ON departments.LOCATION_ID = locations.LOCATION_ID;

-- 10. Write a query to display the job title and average salary of employees. 

SELECT
	JOB_TITLE, (MIN_SALARY + MAX_SALARY)/2 AVG
FROM
	jobs;

-- 11. Display job title, employee name, and the difference between salary of the employee and minimum salary OF the job.

SELECT
	employees.FIRST_NAME, employees.SALARY, employees.SALARY - jobs.MIN_SALARY DIFERENCIA
FROM
	employees INNER JOIN jobs ON employees.JOB_ID = jobs.JOB_ID;

-- 12. Write a query to display the job history that were done by any employee who is currently earning more than 10000 of salary.

SELECT
	*
FROM
	job_history INNER JOIN employees ON job_history.EMPLOYEE_ID = employees.EMPLOYEE_ID
WHERE
	employees.SALARY > 10000;
-- NO podem fer NATURAL JOIN pero sí utilitzar USING

-- 13. Write a query to display department name, name (first_name, last_name), hire date, salary of the manager for all managers whose experience is more than 15 years.
SELECT
	departments.DEPARTMENT_NAME, CONCAT(A.FIRST_NAME, A.LAST_NAME) NAME, A.HIRE_DATE, A.SALARY
FROM
	employees A INNER JOIN employees B ON A.EMPLOYEE_ID = B.MANAGER_ID
    RIGHT JOIN departments ON departments.DEPARTMENT_ID = A.DEPARTMENT_ID
WHERE
	DATEDIFF(NOW(), A.HIRE_DATE)/365.25 > 15;
