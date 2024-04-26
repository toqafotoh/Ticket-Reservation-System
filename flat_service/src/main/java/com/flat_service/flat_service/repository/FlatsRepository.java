package com.flat_service.flat_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flat_service.flat_service.models.Flat;

public interface FlatsRepository extends JpaRepository<Flat,String> {

}
