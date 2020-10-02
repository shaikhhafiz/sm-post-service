package com.hafiz.sm.post.utility;

import com.hafiz.sm.post.core.crud.ConversionUtility;
import com.hafiz.sm.post.dataclass.place.PlaceResponseDTO;
import com.hafiz.sm.post.entities.Place;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PlaceConversionUtility extends ConversionUtility<Place, PlaceResponseDTO> {

    PlaceConversionUtility(ModelMapper modelMapper) {
        super(modelMapper, Place.class, PlaceResponseDTO.class);
    }
}
