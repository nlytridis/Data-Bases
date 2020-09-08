WITH SUM_TRUE AS (
	select count(idroom) AS katilimena,"hotelID" as hotel1  from rooms  B1
	where B1.vacant='f' 
	group by "hotelID" order by "hotelID" ASC),SUM_1 AS(
	       SELECT count(idroom) AS sinolo_dwmatiwn,"hotelID" as hotel2  from rooms
	       group by "hotelID" order by "hotelID" ASC)
       -- SELECT ONOMA1,ONOMA2 FROM SUM_TRUE JOIN SUM_1 ON  SUM_TRUE.hotel1=SUM_1.hotel2 
        SELECT SUM_TRUE.hotel1, 100*(CAST ((katilimena) AS DOUBLE PRECISION)/CAST ((sinolo_dwmatiwn) AS DOUBLE PRECISION)) as plirotita FROM SUM_TRUE JOIN SUM_1 ON  SUM_TRUE.hotel1=SUM_1.hotel2 