package com.project.ticketreservation.dto;

import org.springframework.stereotype.Service;

import com.project.ticketreservation.models.Flat;

@Service
public class FlatMapperImpl implements FlatMapper {

    @Override
    public FlatDtoRespone toFlatDTORespone(Flat flat) {
        FlatDtoRespone DtoResponse = new FlatDtoRespone();
        DtoResponse.setFlatId(flat.getFlatId());
        DtoResponse.setAddress(flat.getAddress());
        DtoResponse.setFlatDescription(flat.getFlatDescription());
        DtoResponse.setCountryName(flat.getCountryName());
        DtoResponse.setCapacity(flat.getCapacity());
        DtoResponse.setPrice(flat.getPrice());
        DtoResponse.setFlatOwnerId(flat.getFlatOwnerId());
        DtoResponse.setFlatOwnerPhoneNum(flat.getFlatOwner().getPhoneNum());
        DtoResponse.setFlatOwnerRate(flat.getFlatOwner().getFlatOwnerRate());
        DtoResponse.setFlatImage(flat.getFlatImage());
        return DtoResponse;
    }

    @Override
    public Flat ToEntity(FlatsDto dto) {
        Flat flat = new Flat();
        flat.setAddress(dto.getAddress());
        flat.setFlatDescription(dto.getFlatDescription());
        flat.setCountryName(dto.getCountryName());
        flat.setCapacity(dto.getCapacity());
        flat.setPrice(dto.getPrice());
        flat.setFlatId(dto.getFlatId());
        flat.setFlatImage(dto.getFlatImage());
        return flat;
    }

    private void SetNewFlat(Flat flat, FlatsDto dto) {
        flat.setAddress(dto.getAddress());
        flat.setFlatDescription(dto.getFlatDescription());
        flat.setCountryName(dto.getCountryName());
        flat.setCapacity(dto.getCapacity());
        flat.setPrice(dto.getPrice());
        flat.setFlatId(dto.getFlatId());
        flat.setFlatImage(dto.getFlatImage());
    }

    public Flat ChangeFlat(Flat flat, FlatsDto dto) {
        SetNewFlat(flat, dto);
        return flat;
    }
}
