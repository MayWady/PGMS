package com.pms.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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

import com.pms.bean.PlaythingBean;
import com.pms.bean.TicketBean;
import com.pms.bean.TicketTypeBean;
import com.pms.bean.UserStatusBean;
import com.pms.persistant.dao.PlaythingCategoriesResponseDao;
import com.pms.persistant.dao.PlaythingResponseDao;
import com.pms.persistant.dao.TicketTypePlaythingRequestDao;
import com.pms.persistant.dao.TicketTypeRequestDao;
import com.pms.persistant.dao.TicketTypeResponseDao;
import com.pms.persistant.dao.TicketTypeStatusResponseDao;
import com.pms.persistant.dao.UserStatusRequestDao;
import com.pms.persistant.dao.UserStatusResponseDao;
import com.pms.persistant.service.PlaythingSearchService;
import com.pms.persistant.service.TicketTypeDeleteService;
import com.pms.persistant.service.TicketTypeIdSearchService;
import com.pms.persistant.service.TicketTypePlaythingAddService;
import com.pms.persistant.service.TicketTypeRegisterService;
import com.pms.persistant.service.TicketTypeSearchService;
import com.pms.persistant.service.TicketTypeStatusSearchService;
import com.pms.persistant.service.TicketTypeUpdateService;

@Controller
public class TicketTypeController {
	@Autowired
	TicketTypeRegisterService ticketTypeRegisterService;
	@Autowired
	TicketTypeStatusSearchService tickettypeStatusSearchService;
	@Autowired
	TicketTypeSearchService ticketTypeSearchService;
	@Autowired
	TicketTypeUpdateService ticketTypeUpdateService;
	@Autowired
	TicketTypeDeleteService ticketTypeDeleteService;
	@Autowired
	TicketTypeIdSearchService ticketTypeIdSearchService;
	@Autowired
	PlaythingSearchService playthingSearchService;
	@Autowired
	TicketTypePlaythingAddService ticketTypePlaythingAddService;

	@ModelAttribute("playthingList")
	public List<PlaythingResponseDao> getPlaythingList() {
		try {
			return playthingSearchService.doService(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// call PMS-TKT-01
	@RequestMapping(value = { "setupTicketTypeAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayTicketType() {
		TicketTypeBean bean = new TicketTypeBean();
		bean.setTicketTypeId(getId());
		return new ModelAndView("PMS-TKT-01", "ticketType", bean);
	}

	public String getId() {
		String ans = "";
		String id = ticketTypeIdSearchService.getId();
		String localdate1 = LocalDate.now().toString();
		String localdate2 = localdate1.substring(2, 4);
		localdate2 += localdate1.substring(5, 7);
		if (id.equals("")) {
			ans = "TKT" + localdate2 + "001";
		} else {
			String lastdate = id.substring(3, 7);
			String lastId = id.substring(7);

			if (localdate2.equals(lastdate)) {
				int i = Integer.valueOf(lastId);
				i++;
				lastId = String.valueOf(i);
				if (lastId.length() == 1) {
					ans = ("TKT" + lastdate + "00" + lastId);
				} else if (lastId.length() == 2) {
					ans = ("TKT" + lastdate + "0" + lastId);
				} else {
					ans = ("TKT" + lastdate + lastId);
				}

			} else {
				ans = ("TKT" + localdate2 + "001");
			}
		}
		return ans;
	}

	// go user status add confirm page
	@RequestMapping(value = "confirmTicketTypeAdd", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmTicketTypeAdd(@ModelAttribute("ticketType") @Validated TicketTypeBean ticketType,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-TKT-01", "ticketType", ticketType);
		}
		String s = "";
		for (String p : ticketType.getPlaything()) {
			s += ticketTypeIdSearchService.getPlaythingId(p) + "  ";
		}
		ticketType.setPlaythingName(s);
		ticketType.setTicketTypeStatusName(
				ticketTypeIdSearchService.getStatusName(Integer.valueOf(ticketType.getTicketTypeStatusId())));
		return new ModelAndView("PMS-TKT-91", "ticketType", ticketType);
	}

	@ModelAttribute("statusList")
	public List<TicketTypeStatusResponseDao> getClassList() {
		try {
			return tickettypeStatusSearchService.doService(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// add user status
	@RequestMapping(value = { "addTicketType" }, method = RequestMethod.POST)
	public ModelAndView addTicketType(@ModelAttribute("ticketType") TicketTypeBean ticketType, Model model) {
		
		TicketTypeRequestDao reqDao = new TicketTypeRequestDao();
//		value1=reqDao.setValidFrom(Date.valueOf(ticketType.getValidFrom));
//		value2=reqDao.setValidTo(Date.valueOf(ticketType.getValidTo));
		reqDao.setTicketTypeId(ticketType.getTicketTypeId());
		reqDao.setTicketTypeName(ticketType.getTicketTypeName());
		reqDao.setPrice(Double.valueOf(ticketType.getPrice()));
        
		System.out.println(ticketType.getValidFrom() + "date");
		reqDao.setValidFrom(Date.valueOf(ticketType.getValidFrom()));
		reqDao.setValidTo(Date.valueOf(ticketType.getValidTo()));
		reqDao.setTicketTypeStatusId(ticketType.getTicketTypeStatusId());

		boolean status;
		try {
			status = ticketTypeRegisterService.doService(reqDao);
			for (String p : ticketType.getPlaything()) {
				TicketTypePlaythingRequestDao ttp = new TicketTypePlaythingRequestDao();
				ttp.setPlaythingId(p);
				ttp.setTicketTypeId(ticketType.getTicketTypeId());
				ticketTypePlaythingAddService.doService(ttp);
			}
			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Ticket Type has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}

		return new ModelAndView("redirect:setupTicketTypeAdd", "ticketType", ticketType);
	}

	// call PMS-TKT-01
	@RequestMapping(value = { "setupTicketTypeSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayTicketTypeSearch(@ModelAttribute("ticketType") TicketTypeBean ticketType,
			HttpServletRequest request) {
		ServletContext app = request.getServletContext();
		List<TicketTypeResponseDao> list = new ArrayList();
		app.setAttribute("ticketTypeList", list);
		return new ModelAndView("PMS-TKT-02", "ticketType", new TicketTypeBean());
	}

	@RequestMapping(value = { "ticketTypeSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayTicketType(@ModelAttribute("ticketType") TicketTypeBean ticketType,
			HttpServletRequest request) {
		ModelAndView model;
		model = new ModelAndView("PMS-TKT-02", "ticketType", ticketType);
		TicketTypeRequestDao reqDao = new TicketTypeRequestDao();

		if (ticketType != null) {
			if (!ticketType.getTicketTypeId().equals(""))
				reqDao.setTicketTypeId(ticketType.getTicketTypeId());
			if (!ticketType.getTicketTypeName().equals(""))
				reqDao.setTicketTypeName(ticketType.getTicketTypeName());
			if (!ticketType.getValidFrom().equals(""))
				reqDao.setValidFrom(Date.valueOf(ticketType.getValidFrom()));
			if (!ticketType.getValidTo().equals(""))
				reqDao.setValidTo(Date.valueOf(ticketType.getValidTo()));
			if (!ticketType.getPrice().equals(""))
				reqDao.setPrice(Double.valueOf(ticketType.getPrice()));
			reqDao.setTicketTypeStatusId(ticketType.getTicketTypeStatusId());
			try

			{
				List<TicketTypeResponseDao> list = ticketTypeSearchService.doService(reqDao);
				System.out.println(list.size() + "size");
				ServletContext app = request.getServletContext();
				if (list.size() == 0) {
					model.addObject("msg", "Data not found!");
					app.setAttribute("ticketTypeList", new ArrayList());
				} else {
					app.setAttribute("ticketTypeList", list);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return model;
	}

	@RequestMapping(value = { "backTicketTypeSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView backTicketTypeSearch(@ModelAttribute("ticketType") TicketTypeBean ticketType) {
		if (ticketType != null)
			return new ModelAndView("PMS-TKT-02", "ticketType", ticketType);
		else
			return new ModelAndView("PMS-TKT-02", "ticketType", new TicketTypeBean());
	}

	// call PMS-TKT-03 page
	@RequestMapping(value = "setupTicketTypeUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupTicketTypeUpdate(@RequestParam("id") String id, @RequestParam("name") String name,
			@RequestParam("price") String price, @RequestParam("validTo") String to,
			@RequestParam("validFrom") String from, @RequestParam("status") String status) {
		TicketTypeBean ticketType = new TicketTypeBean();
		ticketType.setTicketTypeId(id);
		ticketType.setTicketTypeName(name);
		ticketType.setPrice(price);
		ticketType.setValidFrom(from);
		ticketType.setValidTo(to);
		ticketType.setTicketTypeStatusId(status);

		return new ModelAndView("PMS-TKT-03", "ticketTypeDetail", ticketType);
	}

	// go ticket type update page
	@RequestMapping(value = "confirmTicketTypeUpdate", method = RequestMethod.POST)
	public ModelAndView confirmTicketTypeUpdate(@ModelAttribute("ticketType") @Validated TicketTypeBean ticketType,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-TKT-03", "ticketType", ticketType);
		} else {
			ticketType.setTicketTypeStatusName(
					ticketTypeIdSearchService.getStatusName(Integer.valueOf(ticketType.getTicketTypeStatusId())));
			return new ModelAndView("PMS-TKT-93", "ticketTypeDetail", ticketType);
		}
	}

	// update ticket type
	@RequestMapping(value = { "ticketTypeUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateTicketType(@ModelAttribute("ticketType") TicketTypeBean ticketType, Model model) {

		TicketTypeRequestDao reqDao = new TicketTypeRequestDao();
		reqDao.setTicketTypeId(ticketType.getTicketTypeId());
		reqDao.setTicketTypeName(ticketType.getTicketTypeName());
		reqDao.setPrice(Double.valueOf(ticketType.getPrice()));
		reqDao.setValidFrom(Date.valueOf(ticketType.getValidFrom()));
		reqDao.setValidTo(Date.valueOf(ticketType.getValidTo()));
		reqDao.setTicketTypeStatusId(ticketType.getTicketTypeStatusId());

		boolean status = false;
		try {
			status = ticketTypeUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "Ticket Type  has been Update!");

		}
		return new ModelAndView("redirect:setupTicketTypeSearch", "ticketType", ticketType);
	}

	// delete ticket type
	@RequestMapping(value = "deleteTicketType", method = RequestMethod.POST)
	public String deleteTicketType(@ModelAttribute("ticketType") @Validated TicketTypeBean ticketType, ModelMap model) {
		TicketTypeRequestDao reqDao = new TicketTypeRequestDao();

		reqDao.setTicketTypeId(ticketType.getTicketTypeId());

		try {
			boolean status = ticketTypeDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupTicketTypeSearch";
	}
}
