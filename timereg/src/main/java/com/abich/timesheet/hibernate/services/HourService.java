package com.abich.timesheet.hibernate.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.abich.timesheet.hibernate.beans.Hour;

public interface HourService {

	/**
	 * Retrieves all hours
	 * 
	 * @return a list of hours
	 */
	@Transactional
	public abstract List<Hour> getAll();

	public abstract Hour update(Hour hour);

}