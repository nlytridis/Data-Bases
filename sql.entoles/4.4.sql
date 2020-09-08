WITH plir_krat AS (
			select count(idroom) AS katilimena,"hotelID" as hotel1  from roombooking, rooms  
			where roombooking."roomID"=rooms.idroom 
			group by "hotelID" order by "hotelID" ASC),
all_rooms AS(
			SELECT count(idroom) AS sinolo_dwmatiwn,"hotelID" as hotel2  from rooms
			group by "hotelID" order by "hotelID" ASC),
reservation_bymonth AS(
select count(idhotelbooking )as plithos_krathsewn,to_char(reservationdate,'Mon')as months ,"hotelID" as hotelid 
from hotalbooking,roombooking,rooms
where hotalbooking.idhotelbooking=roombooking."hotelbookingID" and roombooking."roomID"=rooms.idroom
group by to_char(reservationdate,'Mon'), rooms."hotelID" order by rooms."hotelID" ASC)
 
SELECT plir_krat.hotel1, 100*(CAST ((katilimena) AS DOUBLE PRECISION)/CAST ((sinolo_dwmatiwn) AS DOUBLE PRECISION)) as plirotita ,reservation_bymonth.months
	FROM plir_krat JOIN all_rooms ON  plir_krat.hotel1=all_rooms.hotel2 ,reservation_bymonth
	where plir_krat.hotel1=reservation_bymonth.hotelid
