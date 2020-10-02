package com.hafiz.sm.post.controllers;

import com.hafiz.sm.post.core.constants.EndPoint;
import com.hafiz.sm.post.core.crud.CrudController;
import com.hafiz.sm.post.dataclass.place.PlaceResponseDTO;
import com.hafiz.sm.post.entities.Place;
import com.hafiz.sm.post.services.PlaceService;
import com.hafiz.sm.post.utility.PlaceConversionUtility;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO Create another CRUD Controller when create and update dto need not to be separeted

@RestController
@RequestMapping(EndPoint.PLACE_URL)
public class PlaceController extends CrudController<Place, PlaceResponseDTO, PlaceResponseDTO, PlaceResponseDTO> {

    PlaceController(PlaceService service, PlaceConversionUtility conversionUtility) {
        super(service, conversionUtility);
    }

}
