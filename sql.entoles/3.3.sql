
select distinct S."hotelID" from rooms S
where S.vacant='t' and 
	(SELECT COUNT( distinct "roomType")  FROM rooms 
		where "hotelID"=S."hotelID")=10 
		order by "hotelID" ASC

 

