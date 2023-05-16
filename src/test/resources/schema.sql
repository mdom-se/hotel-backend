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
-- This index definition is not the same as the real, however it works for the test purposes
CREATE UNIQUE INDEX uq_hotels_hotel_name on hotels ( hotel_name );

CREATE TABLE amenities (
                           amenity_id SERIAL PRIMARY KEY,
                           amenity_name VARCHAR(50) NOT NULL,
                           created_by VARCHAR(50) NOT NULL DEFAULT 'system',
                           created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_by VARCHAR(50) NOT NULL DEFAULT 'system',
                           updated_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

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