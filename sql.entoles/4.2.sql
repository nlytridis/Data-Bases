
select AVG(CURRENT_DATE - dateofbirth)/365 as meso_oro_ilikias from person,roombooking,rooms
 where rooms."roomType"='typename_3' and roombooking."roomID"=rooms.idroom and roombooking."bookedforpersonID"=person."idPerson"

