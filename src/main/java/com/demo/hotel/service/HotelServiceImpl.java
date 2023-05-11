package com.demo.hotel.service;

import com.demo.hotel.dto.HotelDto;
import com.demo.hotel.mapper.HotelMapper;
import com.demo.hotel.model.Hotel;
import com.demo.hotel.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService{
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImpl.class);
    private final HotelRepository hotelRepository;

    public HotelServiceImpl(final HotelRepository hotelRepository){
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
        try {
            Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
            if(hotelOptional.isPresent()){
                hotelRepository.delete(hotelOptional.get());
                deleted = true;
            }
        }catch (Exception ex){
            LOGGER.error("Error delete hotel", ex);
        }
        return deleted;
    }

    @Override
    public List<HotelDto> findHotelsByName(String hotelName) {
        final List<Hotel> result = hotelRepository.findByHotelName(hotelName);
        final List<HotelDto> hotelDtoList = result.stream()
                .map(hotel -> HotelMapper.mapToDto(hotel))
                .collect(Collectors.toList());
        return hotelDtoList;
    }
}
