package com.synir.paiban.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Integer>{
	List<Schedule>findByCreatorusernameOrAdminlevelGreaterThan(String creatorusername,int adminlevel);
}
