package com.pms.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.pms.bean.TicketBean;
import com.pms.bean.TopupAmountBean;
import com.pms.bean.UserStatusBean;
import com.pms.persistant.dao.TicketRequestDao;
import com.pms.persistant.dao.TicketResponseDao;
import com.pms.persistant.dao.TopupAmountRequestDao;
import com.pms.persistant.dao.TopupAmountResponseDao;
import com.pms.persistant.dao.UserStatusRequestDao;
import com.pms.persistant.service.TicketBalanceSearchService;
import com.pms.persistant.service.TicketIdSearchService;
import com.pms.persistant.service.TicketSearchService;
import com.pms.persistant.service.TopupAmountDeleteService;
import com.pms.persistant.service.TopupAmountGetTicketIdSearchService;
import com.pms.persistant.service.TopupAmountRegisterService;
import com.pms.persistant.service.TopupAmountSearchService;
import com.pms.persistant.service.TopupAmountTicketUpdateService;
import com.pms.persistant.service.TopupAmountUpdateService;

@Controller
public class TopupAmountController {
	@Autowired
	TopupAmountRegisterService topupAmountRegisterService;
	@Autowired
	TopupAmountSearchService topupAmountSearchService;
	@Autowired
	TopupAmountUpdateService topupAmountUpdateService;
	@Autowired
	TopupAmountDeleteService topupAmountDeleteService;
	@Autowired
	TicketSearchService ticketSearchService;
	@Autowired
	TicketBalanceSearchService ticketBalanceSearchService;
	@Autowired
	TopupAmountTicketUpdateService topupAmountTicketUpdateService;
	@Autowired
	TicketIdSearchService ticketIdSearchService;
	@Autowired
	TopupAmountGetTicketIdSearchService topupAmountGetTicketIdSearchService;

	// Call PMS-TCK-01
	@RequestMapping(value = { "setupTopupAmountAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayTo111pupAmount(@ModelAttribute("ticket") TicketBean ticketBean) {
		TopupAmountBean bean = new TopupAmountBean();
		bean.setTicketId(ticketBean.getTicketId());
		return new ModelAndView("PMS-TUA-01", "topupAmount", bean);
	}

	// Go topupAmount add confirm page
	@RequestMapping(value = "confirmTopupAmountAdd", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView confirmTopupAmountAdd(@ModelAttribute("topupAmount") @Validated TopupAmountBean topupAmount,
			BindingResult bindingResult, HttpSession session) {
		ModelAndView model = new ModelAndView("PMS-TUA-91", "topupAmount", topupAmount);
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-TUA-01", "topupAmount", topupAmount);
		} else {
			topupAmount.setDate(LocalDate.now().toString());
			Calendar cal = Calendar.getInstance();
			topupAmount.setTime(new SimpleDateFormat("HH:mm:ss").format(cal.getTime()));
			// topupAmount.setTime(LocalTime.now().toString());
			TicketRequestDao reqDao = new TicketRequestDao();
			TopupAmountRequestDao req = new TopupAmountRequestDao();
			reqDao.setTicketId(topupAmount.getTicketId());
			List<TicketResponseDao> list;
			try {
				list = ticketBalanceSearchService.doService(reqDao);
				if (list.size() != 0) {

					topupAmount.setBalance(String.valueOf(list.get(0).getBalance()));
					topupAmount.setTicketPrice(String.valueOf(list.get(0).getTicketPrice()));
					topupAmount.setDiscount(String.valueOf(list.get(0).getDiscount()));
					topupAmount.setQuantity(String.valueOf(list.get(0).getQuantity()));

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			req.setTicketId(topupAmount.getTicketId());
			double balance = Double.valueOf(topupAmount.getBalance());
			double topup = Double.valueOf(topupAmount.getTopupAmount());

			double quantity = Double.valueOf(topupAmount.getQuantity());
			double ticketPrice = Double.valueOf(topupAmount.getTicketPrice());
			ticketPrice = ticketPrice * quantity;
			double discount = Double.valueOf(topupAmount.getDiscount());
			discount = (ticketPrice) * (discount / 100);
			double remainBalance = balance - (ticketPrice - discount);
			List<TopupAmountResponseDao> ticketIdList;
			try {
				ticketIdList = topupAmountGetTicketIdSearchService.doService(req);
				if (ticketIdList.size() == 0) {
					topupAmount.setTotalBalance(String.valueOf(remainBalance));
					System.out.println("totalBalance" + topupAmount.getTotalBalance());
					model = new ModelAndView("PMS-TUA-91", "topupAmount", topupAmount);
				} else {
					double totalBalance = remainBalance + topup;
					topupAmount.setBalance(String.valueOf(remainBalance));
					topupAmount.setTotalBalance(String.valueOf(totalBalance));
					System.out.println("total" + topupAmount.getTotalBalance());
					model = new ModelAndView("PMS-TUA-91", "topupAmount", topupAmount);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return model;
	}

	// Add topupAmount
	@RequestMapping(value = { "addTopupAmount" }, method = RequestMethod.POST)
	public ModelAndView addTopupAmount(@ModelAttribute("topupAmount") TopupAmountBean topupAmount, Model model) {
		TopupAmountRequestDao reqDao = new TopupAmountRequestDao();

		reqDao.setTopupAmount(Double.valueOf(topupAmount.getTopupAmount()));
		reqDao.setDate(Date.valueOf(topupAmount.getDate()));
		reqDao.setTime(topupAmount.getTime().toString());
		reqDao.setTicketId(topupAmount.getTicketId());
		reqDao.setTotalBalance(Double.valueOf(topupAmount.getTotalBalance()));

		TicketRequestDao req = new TicketRequestDao();
		req.setTicketId(topupAmount.getTicketId());

		List<TicketResponseDao> list;
		try {
			list = ticketBalanceSearchService.doService(req);
			if (list.size() != 0) {

				topupAmount.setBalance(String.valueOf(list.get(0).getBalance()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		double balance = Double.valueOf(topupAmount.getTopupAmount());
		double ticketBalance = Double.valueOf(topupAmount.getBalance());
		System.out.println(ticketBalance + "ticketBalance");
		double total = balance + ticketBalance;
		System.out.println(total + "total");
		req.setBalance(Double.valueOf(total));
		System.out.println(req.getBalance() + "balance");
		boolean status, statusTicket;

		try {
			status = topupAmountRegisterService.doService(reqDao);
			statusTicket = topupAmountTicketUpdateService.doService(req);
			if (!status || !statusTicket) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Topup Amount has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupTicketSearch", "topupAmount", topupAmount);
	}

	// Call PMS-TUA-02

	@RequestMapping(value = { "setupTopupAmountSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayTopupAmountSearch(@ModelAttribute("topupAmount") TopupAmountBean topupAmount,
			HttpServletRequest request) {
		ServletContext app = request.getServletContext();
		List<TopupAmountResponseDao> list = new ArrayList();
		app.setAttribute("topupAmountList", list);
		return new ModelAndView("PMS-TUA-02", "topupAmount", new TopupAmountBean());
	}

	@RequestMapping(value = { "topupAmountSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayTopupAmountStatus(@ModelAttribute("topupAmount") TopupAmountBean topupAmount,
			HttpServletRequest request) {
		ModelAndView model;

		model = new ModelAndView("PMS-TUA-02", "topupAmount", topupAmount);

		TopupAmountRequestDao dao = new TopupAmountRequestDao();
		if (topupAmount != null) {
			if (!topupAmount.getTicketId().equals(""))
				dao.setTicketId(topupAmount.getTicketId());
			if (!topupAmount.getDate().equals(""))
				dao.setDate(Date.valueOf(topupAmount.getDate()));

			List<TopupAmountResponseDao> list;
			try {
				list = topupAmountSearchService.doService(dao);
				ServletContext app = request.getServletContext();
				if (list.size() == 0) {
					model.addObject("msg", "Data not found!");
					app.setAttribute("topupAmountList", new ArrayList());
				} else {
					app.setAttribute("topupAmountList", list);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return model;

	}

	/*
	 * // Call PMS-TUA-03
	 * 
	 * @RequestMapping(value = "setupTopupAmountUpdate", method = {
	 * RequestMethod.GET, RequestMethod.POST }) public ModelAndView
	 * setupTopupAmountUpdate(@RequestParam("topupAmount") String topupAmount,
	 * 
	 * @RequestParam("ticketId") String ticketId, @RequestParam("balance") String
	 * balance,
	 * 
	 * @RequestParam("totalBalance") String totalBalance) { TopupAmountBean
	 * topupBean = new TopupAmountBean(); topupBean.setTicketId(ticketId);
	 * topupBean.setTopupAmount(topupAmount); topupBean.setBalance(balance);
	 * topupBean.setTotalBalance(totalBalance);
	 * 
	 * TicketRequestDao reqDao = new TicketRequestDao();
	 * reqDao.setTicketId(topupBean.getTicketId()); List<TicketResponseDao> list;
	 * try { list = ticketBalanceSearchService.doService(reqDao); if (list.size() !=
	 * 0) {
	 * 
	 * topupBean.setBalance(String.valueOf(list.get(0).getBalance()));
	 * System.out.println("balance" + topupBean.getBalance()); } } catch
	 * (SQLException e) { e.printStackTrace(); }
	 * 
	 * double bal = Double.valueOf(topupBean.getBalance()); double topup =
	 * Double.valueOf(topupBean.getTopupAmount()); double total = bal + topup;
	 * 
	 * topupBean.setTotalBalance(String.valueOf(total));
	 * 
	 * return new ModelAndView("PMS-TUA-03", "topupAmount", topupBean); }
	 * 
	 * // Go ticket update confirm page
	 * 
	 * @RequestMapping(value = "confirmTopupAmountUpdate", method = {
	 * RequestMethod.POST, RequestMethod.GET }) public ModelAndView
	 * confirmTopupAmountUpdate(@ModelAttribute("topupAmount") @Validated
	 * TopupAmountBean topupAmount, BindingResult bindingResult) { if
	 * (bindingResult.hasErrors()) { return new ModelAndView("PMS-TUA-03",
	 * "topupAmount", topupAmount); } else
	 * 
	 * return new ModelAndView("PMS-TUA-93", "topupAmount", topupAmount); }
	 * 
	 * // Update topupAmount
	 * 
	 * @RequestMapping(value = { "topupAmountUpdate" }, method = RequestMethod.POST)
	 * public ModelAndView updateTopupAmount(@ModelAttribute("topupAmount")
	 * TopupAmountBean topupAmount, Model model) {
	 * 
	 * TopupAmountRequestDao reqDao = new TopupAmountRequestDao();
	 * reqDao.setTicketId(topupAmount.getTicketId());
	 * reqDao.setTopupAmount(Double.valueOf(topupAmount.getTopupAmount()));
	 * 
	 * reqDao.setDate(Date.valueOf(topupAmount.getDate()));
	 * reqDao.setTime(topupAmount.getTime());
	 * 
	 * reqDao.setTicketId(topupAmount.getTicketId());
	 * reqDao.setTotalBalance(Double.valueOf(topupAmount.getTotalBalance()));
	 * 
	 * boolean status = false; try { status =
	 * topupAmountUpdateService.doService(reqDao); } catch (SQLException e) {
	 * e.printStackTrace(); } if (!status) { model.addAttribute("error",
	 * "Data Update failed!"); } else { model.addAttribute("msg",
	 * "TopupAmount has been Update!");
	 * 
	 * } return new ModelAndView("redirect:setupTopupAmountSearch", "topupAmount",
	 * topupAmount); }
	 * 
	 * // Delete ticket
	 * 
	 * @RequestMapping(value = "deleteTopupAmount", method = RequestMethod.POST)
	 * public String deleteTopupAmount(@ModelAttribute("topupAmount") @Validated
	 * TopupAmountBean topupAmount, ModelMap model) { TopupAmountRequestDao reqDao =
	 * new TopupAmountRequestDao(); reqDao.setTicketId(topupAmount.getTicketId());
	 * 
	 * try { boolean status = topupAmountDeleteService.doService(reqDao); if
	 * (status) { model.addAttribute("msg", "Data delete successful"); } else {
	 * model.addAttribute("error", "Data delete Fail"); } } catch (SQLException e) {
	 * e.printStackTrace(); } return "redirect:setupTopupAmountSearch"; }
	 */

}
