package com.candidjava.spring.service;

import java.util.List;

import com.candidjava.spring.bean.Technology;

public interface TechnologyService {

	public void addTechnology(Technology technology);

	public List<Technology> getTechnology();

	public Technology getTechnologyfindByTechId(long techId);

	public Technology update(Technology technology, long l);

	public void deleteTechnologyByTechId(long techId);

}