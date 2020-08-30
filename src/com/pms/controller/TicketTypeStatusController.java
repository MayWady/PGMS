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

import com.pms.bean.TicketTypeStatusBean;
import com.pms.persistant.dao.TicketTypeStatusRequestDao;
import com.pms.persistant.dao.TicketTypeStatusResponseDao;
import com.pms.persistant.service.TicketTypeStatusDeleteService;
import com.pms.persistant.service.TicketTypeStatusRegisterService;
import com.pms.persistant.service.TicketTypeStatusSearchService;
import com.pms.persistant.service.TicketTypeStatusUpdateService;
@Controller
public class TicketTypeStatusController {

	@Autowired
	TicketTypeStatusRegisterService tickettypeStautsRegisterService;
	@Autowired
	TicketTypeStatusSearchService tickettypeStatusSearchService;
	@Autowired
	TicketTypeStatusUpdateService tickettypeStatusUpdateService;
	@Autowired
	TicketTypeStatusDeleteService tickettypeStatusDeleteService;

	// call PMS-TTS-01
	@RequestMapping(value = { "setupTicketTypeStatusAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displaytickettypeStatus(@ModelAttribute("ticketTypeStatus") TicketTypeStatusBean ticketTypeStatus) {
		ModelAndView model;
		model = new ModelAndView("PMS-TTS-01", "ticketTypeStatus", ticketTypeStatus);
		try {
			List<TicketTypeStatusResponseDao> list = tickettypeStatusSearchService.doService(null);
			model.addObject("ticketTypeStatusList", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	// go ticket status add confirm page
	@RequestMapping(value = "confirmTicketTypeStatusAdd", method = RequestMethod.POST)
	public ModelAndView confirmTicketTypeStatusAdd(@ModelAttribute("ticketTypeStatus") @Validated TicketTypeStatusBean ticketTypeStatus,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("PMS-TTS-01", "ticketTypeStatus", ticketTypeStatus);
			try {
				List<TicketTypeStatusResponseDao> list = tickettypeStatusSearchService.doService(null);
				model.addObject("ticketTypeStatusList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			model = new ModelAndView("PMS-TTS-91", "ticketTypeStatus", ticketTypeStatus);
		}

		return model;

	}

	// add ticket type status
	@RequestMapping(value = { "addTicketTypeStatus" }, method = RequestMethod.POST)
	public ModelAndView addTcketStatus(@ModelAttribute("ticketTypeStatus") TicketTypeStatusBean ticketTypeStatus, Model model) {
		TicketTypeStatusRequestDao reqDao = new TicketTypeStatusRequestDao();
		reqDao.setId(ticketTypeStatus.getId());
		reqDao.setName(ticketTypeStatus.getName());

		boolean status;
		try {
			status = tickettypeStautsRegisterService.doService(reqDao);
			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Ticket Type Status has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupTicketTypeStatusAdd", "ticketTypeStatus", ticketTypeStatus);
	}

	// call PMS-TTS-03 page
	@RequestMapping(value = "setupTicketTypeStatusUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupTicketTypeStatusUpdate(@RequestParam("id") int id, @RequestParam("name") String name) {
		TicketTypeStatusBean ticketTypeStatus = new TicketTypeStatusBean();
		ticketTypeStatus.setId(id);
		ticketTypeStatus.setName(name);
		return new ModelAndView("PMS-TTS-03", "ticketTypeStatus", ticketTypeStatus);
	}

	// go ticket type status update page
	@RequestMapping(value = "confirmTicketTypeStatusUpdate", method = RequestMethod.POST)
	public ModelAndView confirmTicketTypeStatusUpdate(
			@ModelAttribute("ticketTypeStatus") @Validated TicketTypeStatusBean ticketTypeStatus, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-TTS-03", "ticketTypeStatus", ticketTypeStatus);
		} else
			return new ModelAndView("PMS-TTS-93", "ticketTypeStatus", ticketTypeStatus);
	}

	// update ticket type status
	@RequestMapping(value = { "ticketTypeStatusUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateTicketStatus(@ModelAttribute("ticketTypeStatus") TicketTypeStatusBean ticketTypeStatus, Model model) {

		TicketTypeStatusRequestDao reqDao = new TicketTypeStatusRequestDao();
		reqDao.setId(ticketTypeStatus.getId());
		reqDao.setName(ticketTypeStatus.getName());

		boolean status = false;
		try {
			status = tickettypeStatusUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "Ticket Type Status  has been Update!");

		}
		return new ModelAndView("redirect:setupTicketTypeStatusAdd", "ticketTypeStatus", ticketTypeStatus);
	}

	// delete ticket type status
	@RequestMapping(value = "deleteTicketTypeStatus", method = RequestMethod.POST)
	public String deleteTicketTypeStatus(@ModelAttribute("ticketTypeStatus") @Validated TicketTypeStatusBean ticketTypeStatus,
			ModelMap model) {
		TicketTypeStatusRequestDao reqDao = new TicketTypeStatusRequestDao();
		reqDao.setId(ticketTypeStatus.getId());

		try {
			boolean status = tickettypeStatusDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail and this data is using other!");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupTicketTypeStatusAdd";
	}

}
