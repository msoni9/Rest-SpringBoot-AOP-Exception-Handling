package com.candidjava.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidjava.spring.bean.Technology;
import com.candidjava.spring.repository.TechnologyRepository;

@Service
@Transactional
public class TechnologyServiceImpl implements TechnologyService {

	@Autowired
	private TechnologyRepository technologyRepository;

	@Override
	public void addTechnology(Technology technology) {

		technologyRepository.save(technology);

	}

	@Override
	public List<Technology> getTechnology() {
		List<Technology> technologyList = (List<Technology>) technologyRepository.findAll();

		if (technologyList.size() > 0) {
			return technologyList;
		} else {
			return new ArrayList<Technology>();
		}
	}

	@Override
	public Technology getTechnologyfindByTechId(long techId) {
		Optional<Technology> technolgy = technologyRepository.findByTechId(techId);

		if (technolgy.isPresent()) {
			return technolgy.get();
		}
		return null;
	}

	@Override
	public Technology update(Technology technology, long l) {
		Optional<Technology> technologyFetched = technologyRepository.findByTechId(l);

		if (technologyFetched.isPresent()) {
			Technology newTechnology = technologyFetched.get();
			newTechnology.setTechnology(technology.getTechnology());
			return technologyRepository.save(newTechnology);
		}
		return null;
	}

	@Override
	public void deleteTechnologyByTechId(long techId) {
		Optional<Technology> technology = technologyRepository.findByTechId(techId);

		if (technology.isPresent()) {
			technologyRepository.delete(techId);
		}

	}

}
