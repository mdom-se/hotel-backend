package com.demo.hotel.repository;

import com.demo.hotel.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Page<Hotel> findByHotelNameLikeIgnoreCaseOrderByHotelNameAsc(String hotelName, Pageable pageable);

    Optional<Hotel> findByHotelName(String hotelName);
}
