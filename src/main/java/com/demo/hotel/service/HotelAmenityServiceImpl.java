package com.demo.hotel.service;

import com.demo.hotel.mapper.HotelAmenityMapper;
import com.demo.hotel.model.HotelAmenity;
import com.demo.hotel.repository.HotelAmenityRepository;
import com.demo.hotel.webservice.dto.HotelAmenityDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelAmenityServiceImpl implements HotelAmenityService {

    private final HotelAmenityRepository hotelAmenityRepository;

    public HotelAmenityServiceImpl(final HotelAmenityRepository hotelAmenityRepository) {
        this.hotelAmenityRepository = hotelAmenityRepository;
    }

    @Override
    public HotelAmenityDto addAmenityToHotel(HotelAmenityDto hotelAmenityDto) {
        HotelAmenity hotelAmenity = hotelAmenityRepository.save(HotelAmenityMapper.mapToModel(hotelAmenityDto));
        return HotelAmenityMapper.mapToDto(hotelAmenity);
    }

    @Override
    public boolean deleteAmenityFromHotel(HotelAmenityDto hotelAmenityDto) {
        boolean result = false;
        Optional<HotelAmenity> hotelAmenity = hotelAmenityRepository.findHotelAmenitiesByHotelIdAndAmenityId(
                hotelAmenityDto.getHotelId(),
                hotelAmenityDto.getAmenityId());
        if(hotelAmenity.isPresent()){
            hotelAmenityRepository.deleteById(hotelAmenityDto.getHotelAmenityId());
            result = true;
        }
        return result;
    }

    @Override
    public long deleteHotelAmenitiesByHotelId(Long hotelId) {
        return hotelAmenityRepository.deleteByHotelId(hotelId);
    }
}
