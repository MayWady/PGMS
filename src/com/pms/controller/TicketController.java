package com.pms.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scripting.support.RefreshableScriptTargetSource;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.pms.bean.PlaythingBean;
import com.pms.bean.TicketBean;
import com.pms.persistant.dao.PlaythingResponseDao;
import com.pms.persistant.dao.TicketRequestDao;
import com.pms.persistant.dao.TicketResponseDao;
import com.pms.persistant.dao.TicketStatusResponseDao;
import com.pms.persistant.dao.TicketTypeRequestDao;
import com.pms.persistant.dao.TicketTypeResponseDao;
import com.pms.persistant.dao.TopupAmountRequestDao;
import com.pms.persistant.service.TicketDeleteService;
import com.pms.persistant.service.TicketIdSearchService;
import com.pms.persistant.service.TicketMemberDiscountSearchService;
import com.pms.persistant.service.TicketPriceSearchService;
import com.pms.persistant.service.TicketRegisterService;
import com.pms.persistant.service.TicketSearchService;
import com.pms.persistant.service.TicketStatusSearchService;
import com.pms.persistant.service.TicketTopupRegisterService;
import com.pms.persistant.service.TicketTypeSearchService;
import com.pms.persistant.service.TicketUpdateService;

@Controller
public class TicketController {
	@Autowired
	TicketRegisterService ticketRegisterService;
	@Autowired
	TicketSearchService ticketSearchService;
	@Autowired
	TicketUpdateService ticketUpdateService;
	@Autowired
	TicketDeleteService ticketDeleteService;
	@Autowired
	TicketStatusSearchService ticketStatusSearchService;
	@Autowired
	TicketTypeSearchService ticketTypeSearchService;
	@Autowired
	TicketPriceSearchService ticketPriceSearchService;
	@Autowired
	TicketMemberDiscountSearchService ticketMemberDiscountSearchService;
	@Autowired
	TicketIdSearchService ticketIdSearchService;
	@Autowired
	TicketTopupRegisterService ticketTopupRegisterService;

	// Call PMS-TCK-01
	@RequestMapping(value = { "setupTicketAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayTicket() {
		TicketBean bean = new TicketBean();
		bean.setTicketId(getId());
		return new ModelAndView("PMS-TCK-01", "ticket", bean);
	}

	public String getId() {

		String ans = "";
		String id = "";
		id = ticketIdSearchService.getId();
		String localdate1 = LocalDate.now().toString();
		String localdate2 = localdate1.substring(2, 4);
		localdate2 += localdate1.substring(5, 7);

		if (id.equals("")) {
			ans = "TCK" + localdate2 + "001";
		} else {
			String lastdate = id.substring(3, 7);
			String lastId = id.substring(7);
			if (localdate2.equals(lastdate)) {
				int i = Integer.valueOf(lastId);
				i++;
				lastId = String.valueOf(i);
				if (lastId.length() == 1) {
					ans = ("TCK" + lastdate + "00" + lastId);
				} else if (lastId.length() == 2) {
					ans = ("TCK" + lastdate + "0" + lastId);
				} else {
					ans = ("TCK" + lastdate + lastId);
				}

			} else {
				ans = ("TCK" + localdate2 + "001");
			}
		}
		return ans;
	}

	@ModelAttribute("ticketTypeNameList")
	public List<TicketTypeResponseDao> getTicketTypeList() {
		try {
			return ticketTypeSearchService.doService(null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@ModelAttribute("statusList")
	public List<TicketStatusResponseDao> getTicketStatusList() {
		try {
			return ticketStatusSearchService.doService(null);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Go ticket add confirm page
	@RequestMapping(value = "confirmTicketAdd", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView confirmTicketAdd(@ModelAttribute("ticket") @Validated TicketBean ticket,
			BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-TCK-01", "ticket", ticket);
		} else {
			ticket.setDate(LocalDate.now().toString());

			ticket.setTicketStatusName(ticketIdSearchService.getStatusName(Integer.valueOf(ticket.getTicketStatus())));

			ticket.setTicketTypeName(ticketIdSearchService.getTicketTypeName(ticket.getTicketType()));

			TicketTypeRequestDao reqDao = new TicketTypeRequestDao();
			reqDao.setTicketTypeId(ticket.getTicketType());

			List<TicketTypeResponseDao> list;
			try {
				list = ticketPriceSearchService.doService(reqDao);
				if (list.size() != 0) {

					ticket.setTicketPrice(String.valueOf(list.get(0).getPrice()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {

				ticket.setDiscount(ticketMemberDiscountSearchService.doService(ticket.getMemberId()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (ticket.getCustomerName().equals("")) {
				ticket.setCustomerName("Unknown Customer");
			}

			double discount = Double.valueOf(ticket.getDiscount());
			double price = Double.valueOf(ticket.getTicketPrice());
			int quantity = Integer.valueOf(ticket.getQuantity());
			double topup = Double.valueOf(ticket.getTopupAmount());
			price = price * quantity;
			String balance = String.valueOf((price - (price * (discount / 100))) + topup);
			ticket.setBalance(balance);
			ModelAndView model = new ModelAndView("PMS-TCK-91", "ticket", ticket);
			QRCodeWriter qr = new QRCodeWriter();
			try {
				BitMatrix bm = qr.encode(ticket.getTicketId(), BarcodeFormat.QR_CODE, 200, 200);
				String s = session.getServletContext().getRealPath("/");
				File dir = new File(s + File.separator + "resources/tmpFiles");
				if (!dir.exists())
					dir.mkdir();
				String serverPath = dir + File.separator + ticket.getTicketId() + ".png";
				Path path = FileSystems.getDefault().getPath(serverPath);
				System.out.println(path + "s");
				MatrixToImageWriter.writeToPath(bm, "PNG", path);
				model.addObject("img", "resources/tmpFiles/" + ticket.getTicketId() + ".png");

			} catch (WriterException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return model;

		}
	}

	// Add ticket
	@RequestMapping(value = { "addTicket" }, method = RequestMethod.POST)
	public ModelAndView addTicket(@ModelAttribute("ticket") TicketBean ticket, Model model) {
		TicketRequestDao reqDao = new TicketRequestDao();
		reqDao.setTicketId(ticket.getTicketId());
		reqDao.setCustomerName(ticket.getCustomerName());
		reqDao.setTicketType(ticket.getTicketType());
		reqDao.setTicketPrice(Double.valueOf(ticket.getTicketPrice()));
		reqDao.setQuantity(Integer.valueOf(ticket.getQuantity()));
		reqDao.setTopupAmount(Double.valueOf(ticket.getTopupAmount()));
		reqDao.setMemberId(ticket.getMemberId());
		reqDao.setDiscount(Double.valueOf(ticket.getDiscount()));
		reqDao.setDate(Date.valueOf(ticket.getDate()));
		reqDao.setBalance(Double.valueOf(ticket.getBalance()));
		reqDao.setValidFrom(Date.valueOf(ticket.getValidFrom()));
		reqDao.setValidTo(Date.valueOf(ticket.getValidTo()));
		reqDao.setTicketStatus(ticket.getTicketStatus());

		TopupAmountRequestDao req = new TopupAmountRequestDao();
		req.setTicketId(ticket.getTicketId());

		req.setDate(Date.valueOf(ticket.getDate()));
		Calendar cal = Calendar.getInstance();
		req.setTime(new SimpleDateFormat("HH:mm:ss").format(cal.getTime()));
		req.setTime(req.getTime());

		req.setTopupAmount(Double.valueOf(ticket.getTopupAmount()));

		if (reqDao.getBalance() == req.getTopupAmount()) {
			req.setBalance(Double.valueOf(0.0));
		} else {
			req.setBalance(Double.valueOf(reqDao.getBalance()));

		}
		double balance = Double.valueOf(req.getBalance());
		double topup = Double.valueOf(req.getTopupAmount());
		// double totalBalance = balance + topup;
		req.setTotalBalance(Double.valueOf(topup));

		boolean status, statusTopup;
		try {

			status = ticketRegisterService.doService(reqDao);
			statusTopup = ticketTopupRegisterService.doService(req);
			if (!status || !statusTopup) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Ticket has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupTicketAdd", "ticket", model);
	}

	// Call PMS-TCK-02

	@RequestMapping(value = { "setupTicketSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayTicketSearch(@ModelAttribute("ticket") TicketBean ticket, HttpServletRequest request) {
		ServletContext app = request.getServletContext();
		List<TicketResponseDao> list = new ArrayList();
		app.setAttribute("ticketList", list);
		return new ModelAndView("PMS-TCK-02", "ticket", new TicketBean());
	}

	@RequestMapping(value = { "ticketSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayTicketStatus(@ModelAttribute("ticket") TicketBean ticket, HttpServletRequest request) {
		ModelAndView model = new ModelAndView("PMS-TCK-02", "ticket", ticket);
		TicketRequestDao dao = new TicketRequestDao();
		if (ticket != null) {
			if (!ticket.getTicketId().equals(""))
				dao.setTicketId(ticket.getTicketId());
			dao.setTicketStatus(ticket.getTicketStatus());
			/* dao.setTicketType(ticket.getTicketType()); */
			if (!ticket.getMemberId().equals(""))
				dao.setMemberId(ticket.getMemberId());
			if (!ticket.getCustomerName().equals(""))
				dao.setCustomerName(ticket.getCustomerName());
			if (!ticket.getDate().equals(""))
				dao.setDate(Date.valueOf(ticket.getDate()));
			if (!ticket.getValidFrom().equals(""))
				dao.setValidFrom(Date.valueOf(ticket.getValidFrom()));
			if (!ticket.getValidTo().equals(""))
				dao.setValidTo(Date.valueOf(ticket.getValidTo()));

			try {
				List<TicketResponseDao> list = ticketSearchService.doService(dao);
				ServletContext app = request.getServletContext();
				if (list.size() == 0) {
					model.addObject("msg", "Data not found!");
					app.setAttribute("ticketList", new ArrayList());
				} else {
					app.setAttribute("ticketList", list);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return model;
	}

	@RequestMapping(value = { "backTicketSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView backTicketSearch(@ModelAttribute("ticket") TicketBean ticket) {
		if (ticket != null)
			return new ModelAndView("PMS-TCK-02", "ticket", ticket);
		else
			return new ModelAndView("PMS-TCK-02", "ticket", new TicketBean());
	}

	// Call PMS-TCK-03
	@RequestMapping(value = "setupTicketUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupTicketUpdate(@RequestParam("ticketId") String ticketId,
			@RequestParam("ticketPrice") String ticketPrice, @RequestParam("ticketType") String ticketType,
			@RequestParam("quantity") String quantity, @RequestParam("customerName") String customerName,
			@RequestParam("topupAmount") String topupAmount, @RequestParam("memberId") String memberId,
			@RequestParam("discount") String discount, @RequestParam("date") String date,
			@RequestParam("balance") String balance, @RequestParam("validFrom") String validFrom,
			@RequestParam("validTo") String validTo, @RequestParam("ticketStatus") String ticketStatus) {
		TicketBean ticket = new TicketBean();
		ticket.setTicketId(ticketId);
		ticket.setTicketPrice(ticketPrice);
		ticket.setTicketType(ticketType);
		ticket.setQuantity(quantity);
		ticket.setCustomerName(customerName);
		ticket.setTopupAmount(topupAmount);
		ticket.setMemberId(memberId);
		ticket.setDiscount(discount);
		ticket.setDate(date);
		ticket.setBalance(balance);
		ticket.setValidFrom(validFrom);
		ticket.setValidTo(validTo);
		ticket.setTicketStatus(ticketStatus);

		TicketTypeRequestDao reqDao = new TicketTypeRequestDao();
		reqDao.setTicketTypeId(ticket.getTicketType());

		List<TicketTypeResponseDao> list;
		try {
			list = ticketPriceSearchService.doService(reqDao);
			if (list.size() != 0) {

				ticket.setTicketPrice(String.valueOf(list.get(0).getPrice()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {

			ticket.setDiscount(ticketMemberDiscountSearchService.doService(ticket.getMemberId()));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (ticket.getCustomerName().equals("")) {
			ticket.setCustomerName("Unknown Customer");
		}

		double dis = Double.valueOf(ticket.getDiscount());
		double price = Double.valueOf(ticket.getTicketPrice());
		int quan = Integer.valueOf(ticket.getQuantity());
		double topup = Double.valueOf(ticket.getTopupAmount());
		price = price * quan;
		String bal = String.valueOf((price - (price * (dis / 100))) + topup);

		ticket.setTopupAmount(String.valueOf(topup));
		ticket.setBalance(bal);

		return new ModelAndView("PMS-TCK-03", "ticketDetail", ticket);
	}

	// Go ticket update confirm page
	@RequestMapping(value = "confirmTicketUpdate", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView confirmTicketUpdate(@ModelAttribute("ticket") @Validated TicketBean ticket,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-TCK-03", "ticket", ticket);
		} else {
			ticket.setTicketStatusName(ticketIdSearchService.getStatusName(Integer.valueOf(ticket.getTicketStatus())));
			ticket.setTicketTypeName(ticketIdSearchService.getTicketTypeName(ticket.getTicketType()));
			TicketTypeRequestDao reqDao = new TicketTypeRequestDao();
			reqDao.setTicketTypeId(ticket.getTicketType());

			List<TicketTypeResponseDao> list;
			try {
				list = ticketPriceSearchService.doService(reqDao);
				if (list.size() != 0) {

					ticket.setTicketPrice(String.valueOf(list.get(0).getPrice()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {

				ticket.setDiscount(ticketMemberDiscountSearchService.doService(ticket.getMemberId()));

			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (ticket.getCustomerName().equals("")) {
				ticket.setCustomerName("Unknown Customer");
			}

			double dis = Double.valueOf(ticket.getDiscount());
			double price = Double.valueOf(ticket.getTicketPrice());
			int quan = Integer.valueOf(ticket.getQuantity());
			double topup = Double.valueOf(ticket.getTopupAmount());
			price = price * quan;
			String bal = String.valueOf((price - (price * (dis / 100))) + topup);

			ticket.setTopupAmount(String.valueOf(topup));
			ticket.setBalance(bal);
			return new ModelAndView("PMS-TCK-93", "ticket", ticket);
		}
	}

	// Update ticket
	@RequestMapping(value = { "ticketUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateTicket(@ModelAttribute("ticket") TicketBean ticket, Model model) {

		TicketRequestDao reqDao = new TicketRequestDao();
		reqDao.setTicketId(ticket.getTicketId());
		reqDao.setTicketPrice(Double.valueOf(ticket.getTicketPrice()));
		reqDao.setTicketType(ticket.getTicketType());
		reqDao.setQuantity(Integer.valueOf(ticket.getQuantity()));
		reqDao.setCustomerName(ticket.getCustomerName());
		reqDao.setTopupAmount(Double.valueOf(ticket.getTopupAmount()));
		reqDao.setMemberId(ticket.getMemberId());
		reqDao.setDiscount(Double.valueOf(ticket.getDiscount()));
		reqDao.setDate(Date.valueOf(ticket.getDate()));
		reqDao.setBalance(Double.valueOf(ticket.getBalance()));
		reqDao.setValidFrom(Date.valueOf(ticket.getValidFrom()));
		reqDao.setValidTo(Date.valueOf(ticket.getValidTo()));
		reqDao.setTicketStatus(ticket.getTicketStatus());
		boolean status = false;
		try {
			status = ticketUpdateService.doService(reqDao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "Ticket has been Update!");

		}
		return new ModelAndView("redirect:setupTicketSearch", "ticket", ticket);
	}

	// Delete ticket
	@RequestMapping(value = "deleteTicket", method = RequestMethod.POST)
	public String deleteTicket(@ModelAttribute("ticket") @Validated TicketBean ticket, ModelMap model) {
		TicketRequestDao reqDao = new TicketRequestDao();
		reqDao.setTicketId(ticket.getTicketId());
		reqDao.setTicketStatus(ticket.getTicketStatus());

		try {
			boolean status = ticketDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:setupTicketSearch";
	}

}
