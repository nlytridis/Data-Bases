select distinct "idClient"
from client,hotalbooking,rooms,roombooking
where client."idClient"=hotalbooking."bookedbyclientID" and rooms."roomType"='typename_1' and rooms.idroom=roombooking."roomID" and hotalbooking.idhotelbooking = roombooking."hotelbookingID";


