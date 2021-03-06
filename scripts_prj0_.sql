
--3NF

DROP TABLE IF EXISTS bankuseridentity CASCADE;
DROP TABLE IF EXISTS bankuseraddress CASCADE;
DROP TABLE IF EXISTS bankuseraccount CASCADE;

/*
CREATE TABLE bankuseridentity (
	user_id SERIAL PRIMARY KEY,
	user_email VARCHAR(50) UNIQUE NOT NULL,
	user_name VARCHAR(50) UNIQUE NOT NULL,
	user_pwd VARCHAR(30) NOT NULL,
	user_first_name VARCHAR(25),
	user_last_name VARCHAR(25),
	user_role VARCHAR(50) NOT NULL,
	user_profile_done BOOLEAN,
	user_profile_approved BOOLEAN
	--home_id INTEGER REFERENCES bankuseraddress(home_id)
	--account_id INTEGER REFERENCES bankuseraccount(account_id)
);
*/

CREATE TABLE bankuseraddress (
	home_id SERIAL PRIMARY KEY,
	home_name VARCHAR(100),
	home_number VARCHAR(10),
	home_street VARCHAR(50),
	home_city VARCHAR(50),
	home_region VARCHAR(50),
	home_zip VARCHAR(15),
	home_country VARCHAR(20),
	home_profile_done BOOLEAN,
	home_profile_approved BOOLEAN
);

CREATE TABLE bankuseraccount (
	account_id SERIAL PRIMARY KEY,
	account_balance NUMERIC(8,2),
	account_transact_prev NUMERIC(8,2),
	account_type INTEGER,
	account_holders_qty INTEGER,
	account_profile_done BOOLEAN,
	account_approved BOOLEAN,
	account_deleted BOOLEAN
);

/*
CREATE TABLE bankuseridentity (
	user_id SERIAL PRIMARY KEY,
	user_email VARCHAR(50) UNIQUE NOT NULL,
	user_name VARCHAR(50) UNIQUE NOT NULL,
	user_pwd VARCHAR(30) NOT NULL,
	user_first_name VARCHAR(25),
	user_last_name VARCHAR(25),
	user_role VARCHAR(50) NOT NULL,
	user_profile_done BOOLEAN,
	user_profile_approved BOOLEAN,
	home_id INTEGER REFERENCES bankuseraddress(home_id) ON UPDATE CASCADE,
	account_id INTEGER REFERENCES bankuseraccount(account_id) ON UPDATE CASCADE
);
*/

CREATE TABLE bankuseridentity (
	user_id SERIAL PRIMARY KEY,
	user_email VARCHAR(50) UNIQUE NOT NULL,
	user_name VARCHAR(50) UNIQUE NOT NULL,
	user_pwd VARCHAR(255) NOT NULL, --for HASHED PASSWORD
	user_salt VARCHAR(1024), --SALT
	user_first_name VARCHAR(25),
	user_last_name VARCHAR(25),
	user_role VARCHAR(50) NOT NULL,
	user_profile_done BOOLEAN,
	user_profile_approved BOOLEAN,
	home_id INTEGER,
	account_id INTEGER,
	CONSTRAINT fk_domicile
		FOREIGN KEY(home_id)
			REFERENCES bankuseraddress(home_id)
			ON UPDATE CASCADE,
	CONSTRAINT fk_ledger
	FOREIGN KEY(account_id)
		REFERENCES bankuseraccount(account_id)
			ON UPDATE CASCADE
			
	--home_id INTEGER REFERENCES bankuseraddress(home_id) ON UPDATE CASCADE,
	--account_id INTEGER REFERENCES bankuseraccount(account_id) ON UPDATE CASCADE
);

--ALTER TABLE bankuseridentity ADD COLUMN home_id INTEGER NOT NULL REFERENCES bankuseraddress(home_id);
--ALTER TABLE bankuseridentity ADD COLUMN account_id INTEGER NOT NULL REFERENCES bankuseraccount(account_id);

--ALTER TABLE bankuseridentity ADD COLUMN home_id INTEGER REFERENCES bankuseraddress(home_id);
--ALTER TABLE bankuseridentity ADD COLUMN account_id INTEGER REFERENCES bankuseraccount(account_id);

--ALTER TABLE bankuseridentity ADD COLUMN home_id INTEGER REFERENCES bankuseraddress(home_id) ON DELETE CASCADE ON UPDATE CASCADE;
--ALTER TABLE bankuseridentity ADD COLUMN account_id INTEGER REFERENCES bankuseraccount(account_id) ON DELETE CASCADE ON UPDATE CASCADE;

--ALTER TABLE bankuseridentity ADD COLUMN home_id INTEGER REFERENCES bankuseraddress(home_id) ON UPDATE CASCADE;
--ALTER TABLE bankuseridentity ADD COLUMN account_id INTEGER REFERENCES bankuseraccount(account_id) ON UPDATE CASCADE;	

INSERT INTO bankuseridentity (user_email, user_name, user_pwd, user_salt, user_first_name, user_last_name, user_role, user_profile_done, user_profile_approved) 
	VALUES ('aWatcful@JavaBank.com', 'hawkeye', 'admin#01', 'salt', 'Avery', 'Watchful', 'ADMIN', true, true),
	('pHelpful@JavaBank.com', 'angel', 'emplo#01', 'salt', 'Patience', 'Helpful', 'EMPLOYEE', true, true),
	('fPockets@Outlook.com', 'baron', 'custo#01', 'salt', 'Fuller', 'Pockets', 'CUSTOMER', true, true),
	('zWanders@Outlook.com', 'homeless', 'custo#02', 'salt', 'Zig', 'Wanders', 'CUSTOMER', true, true);
	--(null, 'procrastinator', 'custo#03', 'salt', 'Will', 'Register', 'CUSTOMER', false, false); --violation
	--('wRegister@Outlook.com', 'procrastinator', 'custo#03','salt', 'Will', 'Register', 'CUSTOMER', true, true);

INSERT INTO bankuseraddress (home_name, home_number, home_street, home_city, home_region, home_zip, home_country, home_profile_done, home_profile_approved)
	VALUES ('fuller manor', '100', 'penny lane', 'beverly hills', 'ca', '43210', 'usa', true, true), --existing customer with approved address
	('under bridge', '456', 'any road', 'any town', 'me', '01234', 'usa', true, false); --customer address looks suspicious, lacks administrative approval
	--('luxo apartments', '789', 'fanny road', 'mountain lakes', 'nj', '07046', 'usa', true, true); --customer address to be entered after signup and application

INSERT INTO bankuseraccount (account_balance, account_transact_prev, account_type, account_holders_qty, account_profile_done, account_approved, account_deleted)
	VALUES (1000.00, 0.0, 1, 1, true, true, false); -- existing customer account
	--(1.00, 0.0, 1, 1, true, true, false); --account to be added via account application procedure
	--(5000.00, 0.0, 1, 1, true, true, false); --account to be added via account application procedure
	
--ALTER TABLE bankuseridentity ADD COLUMN home_id INTEGER REFERENCES bankuseraddress(home_id) ON DELETE CASCADE ON UPDATE CASCADE;
--ALTER TABLE bankuseridentity ADD COLUMN account_id INTEGER REFERENCES bankuseraccount(account_id) ON DELETE CASCADE ON UPDATE CASCADE;		

UPDATE bankuseridentity SET home_id = bankuseraddress.home_id FROM bankuseraddress WHERE user_name = 'baron';
UPDATE bankuseridentity SET account_id = bankuseraccount.account_id FROM bankuseraccount WHERE user_name = 'baron';

--Triggers/Functions

ALTER TABLE bankuseraddress ADD COLUMN residents INTEGER;

CREATE OR REPLACE FUNCTION increase_residents() RETURNS TRIGGER AS 
$$
BEGIN 
	UPDATE bankuseraddress SET residents = 
		(SELECT residents FROM bankuseraddress WHERE NEW.home_id = bnnkuseraddress.home_id)+1 
		WHERE bankuseraddress.home_id = NEW.home_id;
		RETURN NEW;
END
$$
LANGUAGE plpgsql;

CREATE TRIGGER increment_residents AFTER INSERT ON bankuseraddress
	FOR EACH ROW EXECUTE PROCEDURE increase_residents();

ALTER TABLE bankuseraddress DROP COLUMN IF EXISTS residents CASCADE;

-- start: EXAMPLE SQL SCRIPTS FOR REFERENCE

--Triggers/Functions

--ALTER TABLE homes ADD COLUMN residents INTEGER;

/*
CREATE OR REPLACE FUNCTION increase_residents() RETURNS TRIGGER AS 
$$
BEGIN 
	UPDATE homes SET residents = 
		(SELECT residents FROM homes WHERE NEW.home_name = homes.home_name)+1 
		WHERE homes.home_name = NEW.home_name;
		RETURN NEW;
END
$$
LANGUAGE plpgsql;

CREATE TRIGGER increment_residents AFTER INSERT ON avengers
	FOR EACH ROW EXECUTE PROCEDURE increase_residents();
*/





-- week 1 day3 demo: Sublanguages-Constranits.sql

-- This is a single line comment

/*
	This is a multi-line comment just like Java. 
*/

--DDL

--CREATE DATABASE demos;
/*
DROP TABLE IF EXISTS avengers;

CREATE TABLE avengers (
	superhero_name VARCHAR(30),
	superhero_power VARCHAR(150),
	real_name VARCHAR(50),
	power_level INTEGER
);

ALTER TABLE avengers ADD COLUMN active BOOLEAN;

TRUNCATE TABLE avengers;

--DML

INSERT INTO avengers (superhero_name, superhero_power, real_name, power_level) 
	VALUES ('capt. america', 'ultimate frisbee champion', 'steve rogers', 50),
	('iron man', 'being super rich', 'tony stark', 300);

UPDATE avengers SET active = TRUE WHERE superhero_name = 'iron man';
UPDATE avengers SET active = FALSE WHERE superhero_name = 'capt. america';

DELETE FROM avengers WHERE power_level < 100;

INSERT INTO avengers (superhero_name, superhero_power, real_name, power_level, active)
	VALUES ('hawkeye', 'massive biceps', 'clint barton', 3700, TRUE);

--DQL

SELECT superhero_name, power_level FROM avengers; 

SELECT * FROM avengers WHERE power_level > 500; 

SELECT * FROM avengers ORDER BY real_name ASC;

--TCL

BEGIN;
INSERT INTO avengers (superhero_name, superhero_power)
	VALUES ('hulk', 'anger issues');
UPDATE avengers SET real_name = 'bruce banner' WHERE superhero_name = 'hulk';
UPDATE avengers SET power_level = 8001 WHERE superhero_name = 'hulk';
UPDATE avengers SET active = TRUE WHERE superhero_name = 'hulk';
COMMIT;

--ROLLBACK;

DELETE FROM avengers WHERE superhero_name = 'hulk';

-- Constraints

DROP TABLE IF EXISTS avengers;

CREATE TABLE avengers (
	superhero_name VARCHAR(30) NOT NULL,
	superhero_power VARCHAR(150) NOT NULL UNIQUE,
	real_name VARCHAR(50) NOT NULL,
	power_level INTEGER CHECK (power_level > 0),
	active BOOLEAN DEFAULT FALSE
);

INSERT INTO avengers (superhero_name, superhero_power, real_name, power_level) 
	VALUES ('capt. america', 'ultimate frisbee champion', 'steve rogers', 50),
	('iron man', 'being super rich', 'tony stark', 300);


INSERT INTO avengers (superhero_name, superhero_power, real_name, power_level, active)
	VALUES ('hawkeye', 'massive biceps', 'clint barton', 3700, TRUE);

CREATE TABLE homes (
	home_name VARCHAR(100) PRIMARY KEY,
	home_address VARCHAR(150) NOT NULL UNIQUE
);

INSERT INTO homes (home_name, home_address) VALUES ('stark tower', '123 stark blvd, new york, ny');

ALTER TABLE avengers ADD COLUMN home VARCHAR(100) REFERENCES homes(home_name);

UPDATE avengers SET home = 'stark tower' WHERE superhero_name = 'iron man';

*/
-- week 2 day4 demo: Normalization-Joins-Operations-Functions.sql
/*
DROP TABLE IF EXISTS avengers;
DROP TABLE IF EXISTS homes;

--0NF

CREATE TABLE avengers (
	superhero_name VARCHAR(30) NOT NULL,
	superhero_power VARCHAR(150),
	real_name VARCHAR (50),
	power_level INTEGER,
	home_name VARCHAR(50),
	home_address VARCHAR(200)
);

--1NF
DROP TABLE IF EXISTS avengers;

CREATE TABLE avengers (
	superhero_name VARCHAR(30) NOT NULL,
	superhero_power VARCHAR(150),
	first_name VARCHAR(25),
	last_name VARCHAR(25),
	power_level INTEGER,
	home_name VARCHAR(50),
	home_number VARCHAR(10),
	home_street VARCHAR(50),
	home_city VARCHAR(50),
	home_region VARCHAR(50),
	home_zip VARCHAR(15),
	home_country VARCHAR (20)
);

ALTER TABLE avengers ADD PRIMARY KEY (superhero_name, first_name, last_name);

--this is bad practice, don't do it. 
INSERT INTO avengers VALUES ('capt. america', 'magic frisbee', 'steve', 'rogers', 50, 'stark tower',
	'123', 'stark lane', 'new york', 'ny', '10587', 'usa'); 

INSERT INTO avengers VALUES ('capt. america', 'magic frisbee', 'bucky', 'barnes', 75, 'stark tower',
	'123', 'stark lane', 'new york', 'ny', '10587', 'usa'); 

INSERT INTO avengers VALUES ('hawkeye', 'paleolithic weapons expert', 'clint', 'barton', 505, 'stark tower',
	'123', 'stark lane', 'new york', 'ny', '10587', 'usa'); 

--2NF

DROP TABLE IF EXISTS avengers;

CREATE TABLE avengers (
	superhero_id SERIAL PRIMARY KEY, 
	superhero_name VARCHAR(30) NOT NULL,
	superhero_power VARCHAR(150),
	first_name VARCHAR(25),
	last_name VARCHAR(25),
	power_level INTEGER,
	home_name VARCHAR(50),
	home_number VARCHAR(10),
	home_street VARCHAR(50),
	home_city VARCHAR(50),
	home_region VARCHAR(50),
	home_zip VARCHAR(15),
	home_country VARCHAR (20)
);



INSERT INTO avengers (superhero_name,	superhero_power,	first_name,	last_name,	power_level,	home_name,	home_number,	home_street,	home_city,	home_region,	home_zip,	home_country) 
	VALUES ('capt. america', 'magic frisbee', 'steve', 'rogers', 50, 'stark tower', '123', 'stark lane', 'new york', 'ny', '10587', 'usa'); 

INSERT INTO avengers (superhero_name,	superhero_power,	first_name,	last_name,	power_level,	home_name,	home_number,	home_street,	home_city,	home_region,	home_zip,	home_country) 
	VALUES ('capt. america', 'magic frisbee', 'bucky', 'barnes', 75, 'stark tower', '123', 'stark lane', 'new york', 'ny', '10587', 'usa'); 

INSERT INTO avengers (superhero_name,	superhero_power,	first_name,	last_name,	power_level,	home_name,	home_number,	home_street,	home_city,	home_region,	home_zip,	home_country) 
	VALUES ('hawkeye', 'paleolithic weapons expert', 'clint', 'barton', 505, 'stark tower', '123', 'stark lane', 'new york', 'ny', '10587', 'usa'); 

--3NF

DROP TABLE IF EXISTS avengers;

CREATE TABLE powers (
	superhero_name VARCHAR(30) PRIMARY KEY,
	superhero_power VARCHAR(150)
);

CREATE TABLE homes (
	home_name VARCHAR(50) PRIMARY KEY,
	home_number VARCHAR(10),
	home_street VARCHAR(50),
	home_city VARCHAR(50),
	home_region VARCHAR(50),
	home_zip VARCHAR(15),
	home_country VARCHAR (20)
);

CREATE TABLE avengers (
	superhero_id SERIAL PRIMARY KEY, 
	superhero_name VARCHAR(30) REFERENCES powers(superhero_name) NOT NULL,
	first_name VARCHAR(25),
	last_name VARCHAR(25),
	power_level INTEGER,
	home_name VARCHAR(50) REFERENCES homes(home_name)
);

INSERT INTO powers (superhero_name, superhero_power)
	VALUES ('capt. america', 'magic frisbee'), ('hawkeye', 'paleolithic weapons expert'); 

INSERT INTO powers (superhero_name, superhero_power)
	VALUES ('hulk', 'rage machine'); 

INSERT INTO homes (home_name, home_number, home_street, home_city, home_region, home_zip, home_country)
	VALUES ('stark tower', '123', 'stark lane', 'new york', 'ny', '10587', 'usa');

INSERT INTO homes (home_name, home_number, home_street, home_city, home_region, home_zip, home_country)
	VALUES ('helicarrier', '111', 'patomic river', 'washington', 'dc', '20105', 'usa');

INSERT INTO avengers (superhero_name,	first_name,	last_name,	power_level,	home_name) 
	VALUES ('capt. america', 'steve', 'rogers', 50, 'stark tower'); 

INSERT INTO avengers (superhero_name,	first_name,	last_name,	power_level,	home_name) 
	VALUES ('capt. america', 'bucky', 'barnes', 75, 'stark tower'); 

INSERT INTO avengers (superhero_name,	first_name,	last_name,	power_level,	home_name) 
	VALUES ('hawkeye', 'clint', 'barton', 505, 'stark tower'); 

INSERT INTO avengers (superhero_name,	first_name,	last_name,	power_level,	home_name) 
	VALUES ('hulk', 'bruce', 'banner', 8505, null);

--JOINS

SELECT * FROM avengers a INNER JOIN avengers h ON h.home_name = a.home_name;

SELECT * FROM avengers AS a LEFT JOIN homes AS h ON h.home_name = a.home_name;

SELECT * FROM avengers AS a RIGHT JOIN homes AS h ON h.home_name = a.home_name;

SELECT * FROM avengers FULL JOIN homes ON avengers.home_name = homes.home_name;

--Subquery

SELECT * FROM (SELECT * FROM avengers AS a FULL JOIN homes AS h ON h.home_name = a.home_name) 
	AS avhome FULL JOIN powers AS p ON p.superhero_name = avhome.superhero_name;

--Scalar and Aggregate

SELECT UPPER (superhero_name) AS sup_names FROM avengers; --SCALAR

SELECT AVG (power_level) AS avg_power FROM avengers; --AGGREGATE

--GROUP BY

SELECT superhero_name, SUM(power_level) FROM avengers GROUP BY superhero_name 
	ORDER BY superhero_name DESC;

--Union

SELECT superhero_name, last_name FROM avengers UNION SELECT home_name, home_street FROM homes;

--Triggers/Functions

ALTER TABLE homes ADD COLUMN residents INTEGER;

CREATE OR REPLACE FUNCTION increase_residents() RETURNS TRIGGER AS 
$$
BEGIN 
	UPDATE homes SET residents = 
		(SELECT residents FROM homes WHERE NEW.home_name = homes.home_name)+1 
		WHERE homes.home_name = NEW.home_name;
		RETURN NEW;
END
$$
LANGUAGE plpgsql;

CREATE TRIGGER increment_residents AFTER INSERT ON avengers
	FOR EACH ROW EXECUTE PROCEDURE increase_residents();

UPDATE homes SET residents = 0 WHERE home_name = 'stark tower';
UPDATE homes SET residents = 0 WHERE home_name = 'helicarrier';

TRUNCATE TABLE avengers; 

INSERT INTO avengers (superhero_name,	first_name,	last_name,	power_level,	home_name) 
	VALUES ('capt. america', 'steve', 'rogers', 50, 'stark tower'); 

INSERT INTO avengers (superhero_name,	first_name,	last_name,	power_level,	home_name) 
	VALUES ('capt. america', 'bucky', 'barnes', 75, 'stark tower'); 

INSERT INTO avengers (superhero_name,	first_name,	last_name,	power_level,	home_name) 
	VALUES ('hawkeye', 'clint', 'barton', 505, 'stark tower'); 

INSERT INTO avengers (superhero_name,	first_name,	last_name,	power_level,	home_name) 
	VALUES ('hulk', 'bruce', 'banner', 8505, null);
*/

-- end: EXAMPLE SQL SCRIPTS FOR REFERENCE



