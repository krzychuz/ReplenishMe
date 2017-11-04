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
	fcstdate date NOT NULL
);

CREATE TABLE PURCHASEO (
	locationfrom smallint NOT NULL,
	locationto smallint NOT NULL,
	ponumber int NOT NULL,
	loadingtime datetime NOT NULL,
	unloadingtime datetime NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL,
	ordparty varchar(50) NOT NULL
);

CREATE TABLE ORDERS (
	location smallint NOT NULL,
	product int NOT NULL,
	ordernumber int NOT NULL,
	loadingtime datetime NOT NULL,
	customer varchar(50) NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE RESERVATION (
	location smallint NOT NULL,
	depreqnumber int NOT NULL,
	usage int NOT NULL,
	usetime datetime NOT NULL,
	product int NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE QUALITYLOT (
	location smallint NOT NULL,
	product int NOT NULL,
	qmlotnumber int NOT NULL,
	releasetime datetime NOT NULL,
	quantity int NOT NULL
);

CREATE TABLE PROCESSO (
	location smallint NOT NULL,
	product int NOT NULL,
	prcordnumber int NOT NULL,
	schedulestart datetime NOT NULL,
	schedulefiish datetime NOT NULL,
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
	gcas int NOT NULL,
	description varchar(50) NOT NULL,
	uom char(10) NOT NULL,
	type char(10) NOT NULL,
	roundval smallint NOT NULL
);

CREATE TABLE SAFETIES (
	location smallint NOT NULL,
	product int NOT NULL,
	strategy char(10) NOT NULL,
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