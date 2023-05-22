CREATE TABLE hotels (
    hotel_id SERIAL PRIMARY KEY,
    hotel_name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    rating SMALLINT NOT NULL,
    created_by VARCHAR(50) NOT NULL DEFAULT 'system',
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NOT NULL DEFAULT 'system',
    updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX uq_hotels_hotel_name on hotels (UPPER(hotel_name) ASC);

CREATE TABLE amenities (
    amenity_id SERIAL PRIMARY KEY,
    amenity_name VARCHAR(50) NOT NULL,
    created_by VARCHAR(50) NOT NULL DEFAULT 'system',
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NOT NULL DEFAULT 'system',
    updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX uq_amenities_amenity_name on amenities (UPPER(amenity_name) ASC);

CREATE TABLE hotels_amenities(
  hotel_amenity_id serial PRIMARY KEY,
  hotel_id INTEGER NOT NULL,
  amenity_id INTEGER NOT NULL,
  created_by VARCHAR(50) NOT NULL DEFAULT 'system',
  created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_by VARCHAR(50) NOT NULL DEFAULT 'system',
  updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_hotels_amenities_hotels FOREIGN KEY (hotel_id) REFERENCES hotels (hotel_id),
  CONSTRAINT fk_hotels_amenities_amenities FOREIGN KEY (amenity_id) REFERENCES amenities (amenity_id),
  UNIQUE(hotel_id, amenity_id)
);


insert into amenities (amenity_name)
VALUES ('Bar'), ('Swimming Pool'), ('SPA'), ('Restaurant'), ('Wifi'), ('Parking');

insert into hotels (hotel_name, address, rating)
VALUES ('Cancun', 'Cancun, Quintana Roo', 5),
('Rivera Maya', 'Rivera Maya, Quintana Roo', 5),
('Cielo Real Oaxaca', 'Oaxaca de Juarez, Oaxaca', 4),
('Mision', 'Oaxaca de Juarez, Oaxaca', 3),
('Grand Luxury', 'Puerto Vallarta, Jalisco', 5),
('Grand Fiesta', 'Puerto Vallarta, Jalisco', 5),
('Real CMDX', 'Cuidad de Mexico, CDMX', 5),
('Metropolis', 'Cuidad de Mexico, CDMX', 4),
('Palmeras', 'Acapulco, Guerrero', 4),
('Villa Grand', 'Acapulco, Guerrero', 5)
;

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Cancun'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Rivera Maya'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Cielo Real Oaxaca'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Mision'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Grand Luxury'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Grand Fiesta'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Real CMDX'
  and am.amenity_name in ('Spa', 'Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Metropolis'
  and am.amenity_name in ('Spa', 'Restaurant', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Palmeras'
  and am.amenity_name in ('Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');

insert into hotels_amenities (hotel_id, amenity_id)
select
    ho.hotel_id,
    am.amenity_id
from hotels ho, amenities am
where ho.hotel_name = 'Villa Grand'
  and am.amenity_name in ('Restaurant', 'Bar', 'Swimming Pool', 'Wifi', 'Parking');