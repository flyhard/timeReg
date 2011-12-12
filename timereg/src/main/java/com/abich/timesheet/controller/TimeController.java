package com.abich.timesheet.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abich.timesheet.hibernate.beans.Hour;
import com.abich.timesheet.hibernate.services.HoursService;

@Controller
@RequestMapping("/time")
public class TimeController {
	private static Logger logger = LoggerFactory
			.getLogger(TimeController.class);
	private static final String PAGED_LIST_HOLDER = "pagedListHolder";
	@Resource
	private HoursService hoursService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/show")
	public String showHours(
			@RequestParam(defaultValue = "-1") final Integer page,
			final Model model, final HttpSession session) {
		Integer pageNo = page;
		PagedListHolder<Hour> pagedListHolder;
		if (page == -1) {
			if (logger.isDebugEnabled()) {
				logger.debug("reading Hours from DB");
			}
			pagedListHolder = new PagedListHolder<Hour>(hoursService.getAll());
			session.setAttribute(PAGED_LIST_HOLDER, pagedListHolder);
			pageNo = 0;
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("loading pages from Session");
			}
			pagedListHolder = (PagedListHolder<Hour>) session
					.getAttribute(PAGED_LIST_HOLDER);
		}
		pagedListHolder.setPageSize(10);
		pagedListHolder.setPage(pageNo);
		model.addAttribute("hours", pagedListHolder);
		return "time/show";
	}
}
