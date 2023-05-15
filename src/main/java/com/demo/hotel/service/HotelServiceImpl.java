package com.demo.hotel.service;


import com.demo.hotel.mapper.HotelMapper;
import com.demo.hotel.model.Hotel;
import com.demo.hotel.repository.HotelRepository;
import com.demo.hotel.webservice.dto.HotelDto;
import com.demo.hotel.webservice.dto.HotelListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(final HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        final Hotel hotel = HotelMapper.mapToModel(hotelDto);
        final Hotel hotelCreated = hotelRepository.save(hotel);
        return HotelMapper.mapToDto(hotelCreated);
    }

    @Override
    public HotelDto updateHotel(HotelDto hotelDto) {
        final Hotel hotel = HotelMapper.mapToModel(hotelDto);
        final Hotel hotelUpdated = hotelRepository.save(hotel);
        return HotelMapper.mapToDto(hotelUpdated);
    }

    @Override
    public boolean deleteHotel(Long hotelId) {
        boolean deleted = false;
        if (hotelRepository.existsById(hotelId)) {
            hotelRepository.deleteById(hotelId);
            deleted = true;
        }
        return deleted;
    }

    @Override
    public HotelDto findHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return HotelMapper.mapToDto(hotel.orElse(null));
    }

    @Override
    public HotelListDto getHotelList(String hotelName, Pageable pageable) {
        Page<Hotel> result;
        if (hotelName == null || hotelName.isEmpty()) {
            result = hotelRepository.findAll(pageable);
        } else {
            // String.format will generate a string %hotelName% to use in the like clause
            result = hotelRepository.findByHotelNameLikeIgnoreCase(String.format("%%%s%%", hotelName.trim()), pageable);
        }
        HotelListDto hotelListDto = new HotelListDto();
        hotelListDto.getHotelDtoList().addAll(result.getContent().stream()
                .map(HotelMapper::mapToDto)
                .collect(Collectors.toList()));
        hotelListDto.setTotalPages(result.getTotalPages());
        hotelListDto.setTotalElements(result.getTotalElements());
        return hotelListDto;
    }


}
