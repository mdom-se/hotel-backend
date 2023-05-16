
insert into amenities (amenity_name)
VALUES ('Bar'), ('Swimming Pool'), ('SPA'), ('Restaurant'), ('Wifi'), ('Parking');

insert into hotels (hotel_name, address, rating)
VALUES ('Hotel Cancun', 'Cancun, Quintana Roo', 5),
       ('Hotel Rivera Maya', 'Rivera Maya, Quintana Roo', 5),
       ('Hotel Cielo Real Oaxaca', 'Oaxaca de Juarez, Oaxaca', 4),
       ('Hotel Mision', 'Oaxaca de Juarez, Oaxaca', 3),
       ('Hotel Grand Luxury', 'Puerto Vallarta, Jalisco', 5),
       ('Hotel Grand Fiesta', 'Puerto Vallarta, Jalisco', 5),
       ('Hotel Real CMDX', 'Cuidad de Mexico, CDMX', 5),
       ('Hotel Metropolis', 'Cuidad de Mexico, CDMX', 4),
       ('Hotel Palmeras', 'Acapulco, Guerrero', 4),
       ('Hotel Villa Grand', 'Acapulco, Guerrero', 5)
;

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Cancun'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Rivera Maya'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Cielo Real Oaxaca'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Mision'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Grand Luxury'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Grand Fiesta'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Real CMDX'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Metropolis'
  and am.amenity_name in ('Spa', 'Restaurant', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Palmeras'
  and am.amenity_name in ('Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Hotel Villa Grand'
  and am.amenity_name in ('Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');