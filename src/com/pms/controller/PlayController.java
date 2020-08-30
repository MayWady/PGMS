package com.pms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pms.bean.PlayBean;
import com.pms.bean.PlaythingBean;
import com.pms.bean.TicketBean;
import com.pms.bean.TicketTypeBean;
import com.pms.persistant.dao.PlayRequestDao;
import com.pms.persistant.dao.PlayResponseDao;
import com.pms.persistant.dao.PlaythingRequestDao;
import com.pms.persistant.dao.TicketRequestDao;
import com.pms.persistant.dao.TicketResponseDao;
import com.pms.persistant.dao.TicketTypePlaythingRequestDao;
import com.pms.persistant.service.PlayAddService;
import com.pms.persistant.service.PlaySearchService;
import com.pms.persistant.service.PlayUpdateService;
import com.pms.persistant.service.PlaythingRunService;
import com.pms.persistant.service.PlaythingTicketTypeIdSearchService;
import com.pms.persistant.service.TicketBalanceSearchService;
import com.pms.persistant.service.TopupAmountTicketUpdateService;

@Controller
public class PlayController {

	@Autowired
	PlayAddService playAddService;
	@Autowired
	PlaythingTicketTypeIdSearchService playthingTicketTypeIdSearchService;
	@Autowired
	PlaySearchService playSearchService;
	@Autowired
	TicketBalanceSearchService ticketBalanceSearchService;
	@Autowired
	TopupAmountTicketUpdateService topupAmountTicketUpdateService;
	@Autowired
	PlayUpdateService playUpdateService;
	@Autowired
	PlaythingRunService playthingRunService;

	@RequestMapping(value = { "setupPlayAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayPaly(@ModelAttribute("play") PlaythingBean plaything, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("bean", plaything);
		String path = request.getServletContext().getRealPath("/");

		File dir = new File(path + File.separator + "resources/tmpFiles");
		if (!dir.exists())
			dir.mkdirs();
		File serverFile = new File(dir + File.separator + plaything.getPlaythingId() + ".png");
		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(serverFile));
		/* bout.write(plaything.getB()); */
		bout.flush();
		bout.close();
		// plaything.setImage("resources/tmpFiles/" + plaything.getPlaythingId() +
		// ".png");
		PlayBean play = new PlayBean();
		play.setPlaythingId(plaything.getPlaythingId());
		play.setPrice(plaything.getPrice());
		play.setDescription(plaything.getDescription());
		play.setImage("resources/tmpFiles/" + plaything.getPlaythingId() + ".png");
		ModelAndView model;
		model = new ModelAndView("PMS-PLY-01", "play", play);

		return model;
	}

	// add play
	@RequestMapping(value = { "addPlay" }, method = RequestMethod.POST)
	public ModelAndView addPlay(@ModelAttribute("play") PlayBean play, Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		TicketTypePlaythingRequestDao reqDao = new TicketTypePlaythingRequestDao();
		PlaythingBean bean = (PlaythingBean) session.getAttribute("bean");
		reqDao.setPlaythingId(bean.getPlaythingId());
		reqDao.setTicketId(play.getTicketId());
		String s;
		PlayRequestDao dao = new PlayRequestDao();
		try {
			s = playthingTicketTypeIdSearchService.doService(reqDao);
			if (s.equals("0")) {
				TicketRequestDao req = new TicketRequestDao();
				req.setTicketId(play.getTicketId());

				List<TicketResponseDao> list;

				list = ticketBalanceSearchService.doService(req);
				if (list.size() != 0) {

					play.setBalance(String.valueOf(list.get(0).getBalance()));
					double playthingprice = Double.valueOf(bean.getPrice());
					double ticketBalance = Double.valueOf(play.getBalance());
					System.out.println(ticketBalance + "ticketBalance");
					if (playthingprice > ticketBalance) {
						model.addAttribute("error", "Sorry your balance is not enough!");
						return new ModelAndView("redirect:afterPlayAdd", "play", play);

					}
					double total = ticketBalance - playthingprice;
					req.setBalance(total);
					System.out.println(play.getBalance() + "total");
					topupAmountTicketUpdateService.doService(req);
				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		dao.setTicketId(play.getTicketId());
		dao.setPlaythingId(reqDao.getPlaythingId());
		dao.setDate(Date.valueOf(LocalDate.now()));
		Calendar cal = Calendar.getInstance();
		dao.setTime(new SimpleDateFormat("HH:mm:ss").format(cal.getTime()));
		dao.setStatus("0");
		boolean status;
		try {
			status = playAddService.doService(dao);

			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Ticket has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:afterPlayAdd", "play", play);
	}

	@RequestMapping(value = { "afterPlayAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView afterPlayAdd(HttpServletRequest request) {
		HttpSession session = request.getSession();
		PlaythingBean plaything = (PlaythingBean) session.getAttribute("bean");
		PlayBean play = new PlayBean();
		play.setPlaythingId(plaything.getPlaythingId());
		play.setPrice(plaything.getPrice());
		play.setDescription(plaything.getDescription());
		play.setB(plaything.getB());
		PlayRequestDao dao = new PlayRequestDao();
		dao.setPlaythingId(plaything.getPlaythingId());
		ModelAndView model;
		model = new ModelAndView("PMS-PLY-01", "play", play);
		try {
			List<PlayResponseDao> list = playSearchService.doService(dao);
			model.addObject("playList", list);
		} catch (SQLException e) {

		}

		return model;
	}

	@RequestMapping(value = { "run" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView run(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		PlaythingBean plaything = (PlaythingBean) session.getAttribute("bean");
		PlayBean play = new PlayBean();
		play.setPlaythingId(plaything.getPlaythingId());
		play.setPrice(plaything.getPrice());
		play.setDescription(plaything.getDescription());
		play.setPlaythingName(plaything.getPlaythingName());
		play.setB(plaything.getB());
		PlayRequestDao dao = new PlayRequestDao();
		dao.setStatus("1");
		dao.setPlaythingId(play.getPlaythingId());
		PlaythingRequestDao pdao = new PlaythingRequestDao();
		pdao.setPlaythingId(dao.getPlaythingId());
		try {
			List<PlayResponseDao> list = playSearchService.doService(dao);
			pdao.setPlayCount(list.size());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		boolean status, status1;
		try {
			status1 = playthingRunService.doService(pdao);
			status = playUpdateService.doService(dao);
		} catch (SQLException e) {
			status = false;
		}
		if (status == true) {
			
			model.addAttribute("msg", "This Plaything is run");
		} else {
			model.addAttribute("error", "Something wrong.");
		}
		return new ModelAndView("redirect:setupPlayAdd", "play", play);
	}

}
