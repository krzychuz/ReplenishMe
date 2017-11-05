SELECT loadingdate, dlvnumber, quantity 
FROM DELIVERIES
WHERE locationto = 2751 AND product = 83732531
UNION
SELECT date1, fcstid, quantity
FROM FORECAST
WHERE location = 2751 AND product = 83732531
UNION
SELECT unloadingdate, shipntnumber, quantity
FROM SHIPMENTS
WHERE locationto = 2751 AND product = 83732531
ORDER BY loadingdate