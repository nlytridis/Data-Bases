select distinct "nameFacility", "idHotel"from hotelfacilities,rooms
where hotelfacilities."idHotel" in (
				select distinct S."hotelID" from rooms S
				where S.vacant='t' and 
					(SELECT COUNT( distinct "roomType")  FROM rooms 
						where "hotelID"=S."hotelID")>=8 
						order by "hotelID" ASC)
						order by "idHotel" ASC
