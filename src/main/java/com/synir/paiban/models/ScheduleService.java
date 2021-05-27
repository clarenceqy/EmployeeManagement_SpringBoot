package com.synir.paiban.models;

import java.util.List;

public interface ScheduleService {
	public void add(Schedule schedule);
	public List<Schedule> getList();
	public List<Schedule>findByCreatorusernameOrAdminlevelGreaterThan(String creatorusername,int adminlevel);

}
