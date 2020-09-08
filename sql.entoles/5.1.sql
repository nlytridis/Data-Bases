CREATE TRIGGER trig_roombooking_FK
  before INSERT OR UPDATE OF "hotelbookingID"
  ON roombooking
  FOR EACH ROW
  EXECUTE PROCEDURE f_trig_roombooking_FK();


CREATE OR REPLACE FUNCTION f_trig_roombooking_FK()
  RETURNS trigger AS
$BODY$
begin
INSERT INTO hotalbooking(idhotelbooking ,  reservationdate  , cancellationdate  ,totalamount ,payed  , status ,  "bookedbyclientID")

VALUES  (new."hotelbookingID" ,  new.checkin  ,   null,(new.checkout - new.checkin)*new.rate ,((new.checkout - new.checkin)*new.rate)+5 , 1,  new."bookedforpersonID");

return new;
end
$BODY$
  LANGUAGE plpgsql VOLATILE

