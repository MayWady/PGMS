package com.pms.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pms.bean.CustomerReportBean;
import com.pms.bean.MembershipBean;
import com.pms.bean.MembershipStatusBean;
import com.pms.bean.TicketTypeBean;
import com.pms.bean.UserBean;
import com.pms.persistant.dao.CustomerReportRequestDao;
import com.pms.persistant.dao.CustomerReportResponseDao;
import com.pms.persistant.dao.MembershipStatusResponseDao;
import com.pms.persistant.dao.TicketTypeRequestDao;
import com.pms.persistant.dao.TicketTypeResponseDao;
import com.pms.persistant.service.CustomerReportSearchService;

@Controller
public class CustomerReportController {

	@Autowired
	CustomerReportSearchService customerReportSearchService;

	// call PMS-MBS-01
	@RequestMapping(value = { "setupCustomerReportSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayCustomerReportSearch() {
		return new ModelAndView("PMS-CRP-02", "customerReportSearch", new CustomerReportBean());
	}

	@RequestMapping(value = { "CustomerReportSearch" }, method = { RequestMethod.GET, RequestMethod.POST })

	public ModelAndView displayCustomerReport(@ModelAttribute("customerReport") CustomerReportBean customerReport) {
		ModelAndView model;
		model = new ModelAndView("PMS-CRP-02", "customerReport", customerReport);
		CustomerReportRequestDao reqDao = new CustomerReportRequestDao();

		if (customerReport != null) {
			if (!customerReport.getTicketId().equals(""))
				reqDao.setTicketId(customerReport.getTicketId());
			if (!customerReport.getCustomerName().equals(""))
				reqDao.setCustomerName(customerReport.getCustomerName());
			if (!customerReport.getValidFrom().equals(""))
				reqDao.setValidFrom(Date.valueOf(customerReport.getValidFrom()));
			if (!customerReport.getValidTo().equals(""))
				reqDao.setValidFrom(Date.valueOf(customerReport.getValidTo()));
			try

			{
				List<CustomerReportResponseDao> list = customerReportSearchService.doService(reqDao);
				System.out.println(list.size() + "size");
				model.addObject("customerReportSearchList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try

			{
				List<CustomerReportResponseDao> list = customerReportSearchService.doService(null);
				System.out.println(list.size() + "size");
				model.addObject("customerReportSearchList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return model;
	}

}
