package com.abich.timesheet.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abich.timesheet.hibernate.beans.Hour;
import com.abich.timesheet.hibernate.services.HourService;

@Controller
@RequestMapping("/time")
public class TimeController {
	private static Logger logger = LoggerFactory
			.getLogger(TimeController.class);
	private static final String PAGED_LIST_HOLDER = "hoursPagedListHolder";
	@Resource
	private HourService hoursService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/show")
	public String showHours(
			@RequestParam(defaultValue = "-1") final Integer page,
			final Model model, final HttpSession session) {
		Integer pageNo = page;
		PagedListHolder<Hour> pagedListHolder;
		if (page < 0) {
			pagedListHolder = initializePagedListHolder(session);
			pageNo = 0;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("loading pages from Session");
			}
			pagedListHolder = (PagedListHolder<Hour>) session
					.getAttribute(PAGED_LIST_HOLDER);
			if (pagedListHolder == null) {
				pagedListHolder = initializePagedListHolder(session);
			}
		}
		pagedListHolder.setPageSize(10);
		pagedListHolder.setPage(pageNo);
		model.addAttribute("hours", pagedListHolder);
		return "time/show";
	}

	@RequestMapping("/update")
	@ResponseBody
	public Hour updateHour(@RequestBody final Hour hour) {
		return hoursService.update(hour);

	}

	private PagedListHolder<Hour> initializePagedListHolder(
			final HttpSession session) {
		if (logger.isDebugEnabled()) {
			logger.debug("reading Hours from DB");
		}
		PagedListHolder<Hour> pagedListHolder;
		pagedListHolder = new PagedListHolder<Hour>(hoursService.getAll());
		session.setAttribute(PAGED_LIST_HOLDER, pagedListHolder);
		return pagedListHolder;
	}
}
