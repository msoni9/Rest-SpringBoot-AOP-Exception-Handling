package com.candidjava.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.candidjava.spring.bean.Technology;

public interface TechnologyRepository extends CrudRepository<Technology, Long> {

	Optional<Technology> findByTechId(long techId);

}