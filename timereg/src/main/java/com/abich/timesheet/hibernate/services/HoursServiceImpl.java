package com.abich.timesheet.hibernate.services;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abich.timesheet.hibernate.beans.Hour;

@Service("hourService")
public class HoursServiceImpl implements HourService {
	private static final Logger logger = LoggerFactory
			.getLogger(HoursServiceImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abich.timesheet.hibernate.services.HourService#getAll()
	 */
	@Override
	@Transactional
	public List<Hour> getAll() {
		logger.debug("Retrieving all hours");

		// Retrieve hours from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Hour ORDER BY id desc");

		// Retrieve all
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abich.timesheet.hibernate.services.HourService#update(com.abich.timesheet
	 * .hibernate.beans.Hour)
	 */
	@Override
	public Hour update(final Hour hour) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(hour);
		return hour;
	}

}
