package com.pms.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pms.bean.TicketStatusBean;

import com.pms.persistant.dao.TicketStatusRequestDao;
import com.pms.persistant.dao.TicketStatusResponseDao;

import com.pms.persistant.service.TicketStatusDeleteService;
import com.pms.persistant.service.TicketStatusRegisterService;
import com.pms.persistant.service.TicketStatusSearchService;
import com.pms.persistant.service.TicketStatusUpdateService;

@Controller
public class TicketStatusController {

	@Autowired
	TicketStatusRegisterService ticketStautsRegisterService;
	@Autowired
	TicketStatusSearchService ticketStatusSearchService;
	@Autowired
	TicketStatusUpdateService ticketStatusUpdateService;
	@Autowired
	TicketStatusDeleteService ticketStatusDeleteService;

	// call PMS-TST-01
	@RequestMapping(value = { "setupTicketStatusAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayticketStatus(@ModelAttribute("ticketStatus") TicketStatusBean ticketStatus) {
		ModelAndView model;
		model = new ModelAndView("PMS-TST-01", "ticketStatus", ticketStatus);
		try {
			List<TicketStatusResponseDao> list = ticketStatusSearchService.doService(null);
			model.addObject("ticketStatusList", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	// go ticket status add confirm page
	@RequestMapping(value = "confirmTicketStatusAdd", method = RequestMethod.POST)
	public ModelAndView confirmTicketStatusAdd(@ModelAttribute("ticketStatus") @Validated TicketStatusBean ticketStatus,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("PMS-TST-01", "ticketStatus", ticketStatus);
			try {
				List<TicketStatusResponseDao> list = ticketStatusSearchService.doService(null);
				model.addObject("ticketStatusList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			model = new ModelAndView("PMS-TST-91", "ticketStatus", ticketStatus);
		}

		return model;

	}

	// add ticket status
	@RequestMapping(value = { "addTicketStatus" }, method = RequestMethod.POST)
	public ModelAndView addTcketStatus(@ModelAttribute("ticketStatus") TicketStatusBean ticketStatus, Model model) {
		TicketStatusRequestDao reqDao = new TicketStatusRequestDao();
		reqDao.setId(ticketStatus.getId());
		reqDao.setName(ticketStatus.getName());

		boolean status;
		try {
			status = ticketStautsRegisterService.doService(reqDao);
			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "User Status has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupTicketStatusAdd", "ticketStatus", ticketStatus);
	}

	// call PMS-TST-03 page
	@RequestMapping(value = "setupTicketStatusUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupTicketStatusUpdate(@RequestParam("id") int id, @RequestParam("name") String name) {
		TicketStatusBean ticketStatus = new TicketStatusBean();
		ticketStatus.setId(id);
		ticketStatus.setName(name);
		return new ModelAndView("PMS-TST-03", "ticketStatus", ticketStatus);
	}

	// go ticket status update page
	@RequestMapping(value = "confirmTicketStatusUpdate", method = RequestMethod.POST)
	public ModelAndView confirmTicketStatusUpdate(
			@ModelAttribute("ticketStatus") @Validated TicketStatusBean ticketStatus, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-TST-03", "ticketStatus", ticketStatus);
		} else
			return new ModelAndView("PMS-TST-93", "ticketStatus", ticketStatus);
	}

	// update ticket status
	@RequestMapping(value = { "ticketStatusUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateTicket(@ModelAttribute("ticketStatus") TicketStatusBean ticketStatus, Model model) {

		TicketStatusRequestDao reqDao = new TicketStatusRequestDao();
		reqDao.setId(ticketStatus.getId());
		reqDao.setName(ticketStatus.getName());

		boolean status = false;
		try {
			status = ticketStatusUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "User Status  has been Update!");

		}
		return new ModelAndView("redirect:setupTicketStatusAdd", "ticketStatus", ticketStatus);
	}

	// delete user status
	@RequestMapping(value = "deleteTicketStatus", method = RequestMethod.POST)
	public String deleteTicketStatus(@ModelAttribute("ticketStatus") @Validated TicketStatusBean ticketStatus,
			ModelMap model) {
		TicketStatusRequestDao reqDao = new TicketStatusRequestDao();
		reqDao.setId(ticketStatus.getId());

		try {
			boolean status = ticketStatusDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail and this data is using other!");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupTicketStatusAdd";
	}

}
