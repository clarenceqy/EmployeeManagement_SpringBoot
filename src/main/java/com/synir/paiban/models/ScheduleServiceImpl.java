package com.synir.paiban.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ScheduleRepository repository;

	@Override
	public List<Schedule> getList() {
		Sort sort = Sort.by(Sort.Direction.DESC, "year");
		return repository.findAll(PageRequest.of(0, 10, sort)).getContent();
	}

	@Override
	public void add(Schedule schedule) {
		repository.save(schedule);
		
	}

	@Override
	public List<Schedule> findByCreatorusernameOrAdminlevelGreaterThan(String creatorusername, int adminlevel) {
		
		return repository.findByCreatorusernameOrAdminlevelGreaterThan(creatorusername, adminlevel);
	}

	

}
