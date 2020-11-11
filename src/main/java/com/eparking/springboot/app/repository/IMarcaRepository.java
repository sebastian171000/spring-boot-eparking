package com.eparking.springboot.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.eparking.springboot.app.entity.Marca;


@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Integer> {

}
