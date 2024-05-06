package com.project.ticketreservation.dto;

import com.project.ticketreservation.models.Flat;

public interface FlatMapper {
    FlatDtoRespone toFlatDTORespone(Flat flat);

    Flat ToEntity(FlatsDto dto);

    Flat ChangeFlat(Flat flat, FlatsDto dto);
}
