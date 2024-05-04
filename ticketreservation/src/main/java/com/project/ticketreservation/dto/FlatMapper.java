package com.project.ticketreservation.dto;
import com.project.ticketreservation.Models.Flat;


public interface FlatMapper {
    FlatDtoRespone toFlatDTORespone(Flat flat);
    Flat ToEntity(FlatsDto dto);
    Flat ChangeFlat(Flat flat, FlatsDto dto);

}
