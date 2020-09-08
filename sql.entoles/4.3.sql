
WITH 	city_totalamount as (

      select hotel."idHOTEL" as idhotel , hotel.city as cit,hotalbooking.totalamount as amount
      from hotalbooking,hotel,rooms,roombooking
      where   hotalbooking.idhotelbooking=roombooking."hotelbookingID" and roombooking."roomID"=rooms.idroom and hotel."idHOTEL"=rooms."hotelID"
      group by hotel.city  , hotel."idHOTEL",hotalbooking.totalamount order by hotel.city ASC),amount1 as(


      SELECT  max(city_totalamount.amount) as max1 ,city_totalamount.cit as cit1
      FROM city_totalamount
      GROUP BY city_totalamount.cit)

      select city_totalamount.idhotel,city_totalamount.cit,city_totalamount.amount 
      from  city_totalamount   JOIN  amount1 on  city_totalamount.amount=amount1.max1 and city_totalamount.cit=amount1.cit1
      





    