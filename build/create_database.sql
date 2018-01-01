CREATE TABLE STOCK (
	location smallint NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE SHIPMENTS (
	locationfrom smallint NOT NULL,
	locationto smallint NOT NULL,
	shipntnumber int NOT NULL,
	loadingdate date NOT NULL,
	loadingtime time NOT NULL,
	unloadingdate date NOT NULL,
	unloadingtime time NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL,
	shipparty varchar(50) NOT NULL
);

CREATE TABLE DELIVERIES (
	locationfrom smallint NOT NULL,
	locationto smallint NOT NULL,
	dlvnumber int NOT NULL,
	loadingdate date NOT NULL,
	loadingtime time NOT NULL,
	unloadingdate date NOT NULL,
	unloadingtime time NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL,
	dlvparty varchar(50) NOT NULL
);

CREATE TABLE FORECAST (
	location smallint NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL,
	date1 date NOT NULL,
	fcstdate date NOT NULL,
	fcstid int NOT NULL
);

CREATE TABLE PURCHASEO (
	locationfrom smallint NOT NULL,
	locationto smallint NOT NULL,
	ponumber int NOT NULL,
	loadingdate date NOT NULL,
	loadingtime time NOT NULL,
	unloadingdate date NOT NULL,
	unloadingtime time NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL,
	ordparty varchar(50) NOT NULL
);

CREATE TABLE ORDERS (
	location smallint NOT NULL,
	product int NOT NULL,
	ordernumber int NOT NULL,
	loadingdate date NOT NULL,
	loadingtime time NOT NULL,
	customer varchar(50) NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE RESERVATION (
	location smallint NOT NULL,
	depreqnumber int NOT NULL,
	usage int NOT NULL,
	usedate date NOT NULL,
	usetime time NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE QUALITYLOT (
	location smallint NOT NULL,
	qmlotnumber int NOT NULL,
	releasedate date NOT NULL,
	releasetime time NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE PROCESSO (
	location smallint NOT NULL,
	product int NOT NULL,
	prcordnumber int NOT NULL,
	startdate date NOT NULL,
	starttime time NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE REPLENISHIN (
	locationfrom smallint NOT NULL,
	locationto smallint NOT NULL,
	plordnumber int NOT NULL,
	date date NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE REPLENISHOUT (
	locationfrom smallint NOT NULL,
	locationto smallint NOT NULL,
	plorelnumber int NOT NULL,
	date date NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE PRODUCTS (
	location smallint NOT NULL,
	locationfrom smallint NOT NULL,
	gcas int NOT NULL,
	description varchar(50) NOT NULL,
	uom char(10) NOT NULL,
	type char(10) NOT NULL,
	procurement char(5) NOT NULL,
	safetystrategy char(5) NOT NULL,
	target int NOT NULL,
	roundval smallint NOT NULL
);

CREATE TABLE SAFETIES (
	location smallint NOT NULL,
	product int NOT NULL,
	safetyStrategy char(10) NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE LOCATIONS (
	plantcode smallint NOT NULL,
	description varchar(50) NOT NULL,
	type char(10) NOT NULL
);

CREATE TABLE TLANES (
	startloc smallint NOT NULL,
	endloc smallint NOT NULL,
	duration smallint NOT NULL,
	distance smallint NOT NULL
);

CREATE TABLE LASTDOC (
	docname varchar(50) NOT NULL,
	docnumber varchar(50) NOT NULL
)

CREATE TABLE SUFACTOR (
	product int NOT NULL,
	sufactor float NOT NULL
)

CREATE TABLE IDOCREF (
	idocnum int NOT NULL,
	refnum int NOT NULL
)

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('QMLOT', '0000000');

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('ORDER', '1000000');

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('SHIPNT', '2000000');

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('DELIV', '3000000');

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('PCHORD', '4000000');

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('DEPREQ', '5000000');

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('PRCORD', '6000000');

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('PLOREL', '7000000');

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('PLORD', '8000000');

INSERT INTO LASTDOC (docname, docnumber)
VALUES ('INDREQ', '9000000');

INSERT INTO LOCATIONS (plantcode, description, type)
VALUES (5053, 'BORISPOL PLANT', 'P');

INSERT INTO LOCATIONS (plantcode, description, type)
VALUES (2751, 'YOBULAN DC', 'F');

INSERT INTO LOCATIONS (plantcode, description, type)
VALUES (2621, 'SEKERPINAR DC', 'F');

INSERT INTO LOCATIONS (plantcode, description, type)
VALUES (4853, 'CRAILSHEIM PLANT', 'P');

INSERT INTO LOCATIONS (plantcode, description, type)
VALUES (4850, 'BUDAPEST PLANT', 'P');

INSERT INTO LOCATIONS (plantcode, description, type)
VALUES (2725, 'GEBZE PLANT', 'P');

INSERT INTO LOCATIONS (plantcode, description, type)
VALUES (2742, 'ELIT CM', 'P');

INSERT INTO LOCATIONS (plantcode, description, type)
VALUES (2727, 'BALNAK', 'P');

INSERT INTO LOCATIONS (plantcode, description, type)
VALUES (9979, 'URLATI DC', 'F');