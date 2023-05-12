package com.demo.hotel.service;

import com.demo.hotel.dto.HotelDto;
import com.demo.hotel.dto.HotelListDto;
import com.demo.hotel.mapper.HotelMapper;
import com.demo.hotel.model.Hotel;
import com.demo.hotel.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class HotelServiceImpl implements HotelService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotelServiceImpl.class);
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
        try {
            Optional<Hotel> hotelOptional = hotelRepository.findById(hotelId);
            if (hotelOptional.isPresent()) {
                hotelRepository.delete(hotelOptional.get());
                deleted = true;
            }
        } catch (Exception ex) {
            LOGGER.error("Error delete hotel", ex);
        }
        return deleted;
    }

    @Override
    public HotelDto findHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return HotelMapper.mapToDto(hotel.orElse(null));
    }

    @Override
    public List<HotelDto> findHotelsByName(String hotelName, Pageable pageable) {
        final List<Hotel> result = hotelRepository.findByHotelName(hotelName, pageable);
        return result.stream()
                .map(HotelMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HotelListDto getHotelList(Pageable pageable) {
        Page<Hotel> result = hotelRepository.findAll(pageable);
        return new HotelListDto()
                .setHotelDtoList(result.getContent().stream()
                        .map(HotelMapper::mapToDto)
                        .collect(Collectors.toList()))
                .setTotalPages(result.getTotalPages())
                .setTotalElements(result.getTotalElements());
    }
}
