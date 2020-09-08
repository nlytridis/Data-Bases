select distinct "idHotel" 
from hotelfacilities,hotel,rooms,roomfacilities
where hotelfacilities."nameFacility"='hotel_facility_1_2_2_1_1' and hotelfacilities."idHotel"=hotel."idHOTEL" and vacant=TRUE and roomfacilities."nameFacility"='room_facility_7_7_6';