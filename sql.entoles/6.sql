CREATE VIEW krathseis
AS SELECT "hotelID",idroom, to_char(reservationdate,'DY'),reservationdate,vacant, fname,lname
FROM
rooms,hotalbooking,roombooking,person
WHERE
reservationdate<CURRENT_DATE+7 and reservationdate>CURRENT_DATE AND 
rooms.idroom=roombooking."roomID" and roombooking."hotelbookingID"=hotalbooking.idhotelbooking and roombooking."bookedforpersonID"=person."idPerson"	
group by rooms."hotelID",rooms.idroom,to_char(reservationdate,'DY'),hotalbooking.reservationdate,person.fname,person.lname
order by rooms."hotelID" ASC
--select * from krathseis

select * from krathseis

