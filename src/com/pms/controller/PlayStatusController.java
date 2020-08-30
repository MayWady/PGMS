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

import com.pms.bean.PlayStatusBean;

import com.pms.persistant.dao.PlayStatusRequestDao;
import com.pms.persistant.dao.PlayStatusResponseDao;

import com.pms.persistant.service.PlayStatusDeleteService;
import com.pms.persistant.service.PlayStatusRegisterService;
import com.pms.persistant.service.PlayStatusSearchService;
import com.pms.persistant.service.PlayStatusUpdateService;

@Controller
public class PlayStatusController {

	@Autowired
	PlayStatusRegisterService playStautsRegisterService;
	@Autowired
	PlayStatusSearchService playStatusSearchService;
	@Autowired
	PlayStatusUpdateService playStatusUpdateService;
	@Autowired
	PlayStatusDeleteService playStatusDeleteService;

	// call PMS-PST-01
	@RequestMapping(value = { "setupPlayStatusAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayplayStatus(@ModelAttribute("playStatus") PlayStatusBean playStatus) {
		ModelAndView model;
		model = new ModelAndView("PMS-PST-01", "playStatus", playStatus);
		try {
			List<PlayStatusResponseDao> list = playStatusSearchService.doService(null);
			model.addObject("playStatusList", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	// go play status add confirm page
	@RequestMapping(value = "confirmPlayStatusAdd", method = RequestMethod.POST)
	public ModelAndView confirmPlayStatusAdd(@ModelAttribute("playStatus") @Validated PlayStatusBean playStatus,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("PMS-PST-01", "playStatus", playStatus);
			try {
				List<PlayStatusResponseDao> list = playStatusSearchService.doService(null);
				model.addObject("playStatusList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			model = new ModelAndView("PMS-PST-91", "playStatus", playStatus);
		}

		return model;

	}

	// add play status
	@RequestMapping(value = { "addPlaytStatus" }, method = RequestMethod.POST)
	public ModelAndView addPlayStatus(@ModelAttribute("playStatus") PlayStatusBean playStatus, Model model) {
		
		PlayStatusRequestDao reqDao = new PlayStatusRequestDao();
		reqDao.setId(playStatus.getId());
		reqDao.setName(playStatus.getName());
		
		boolean status;
		try {
			status = playStautsRegisterService.doService(reqDao);
			if (!status) {
				
				model.addAttribute("error", "Data entry failed!");
			} else {
				
				model.addAttribute("msg", "Play Status has been added!");
			}
		} catch (SQLException e) {
			
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupPlayStatusAdd", "playStatus", playStatus);
	}

	// call PMS-PST-03 page
	@RequestMapping(value = "setupPlayStatusUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupPlayStatusUpdate(@RequestParam("id") int id, @RequestParam("name") String name) {
		PlayStatusBean playStatus = new PlayStatusBean();
		playStatus.setId(id);
		playStatus.setName(name);
		return new ModelAndView("PMS-PST-03", "playStatus", playStatus);
	}

	// go play status update page
	@RequestMapping(value = "confirmPlayStatusUpdate", method = RequestMethod.POST)
	public ModelAndView confirmPlayStatusUpdate(@ModelAttribute("playStatus") @Validated PlayStatusBean playStatus,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-PST-03", "playStatus", playStatus);
		} else
			return new ModelAndView("PMS-PST-93", "playStatus", playStatus);
	}

	// update play status
	@RequestMapping(value = { "playStatusUpdate" }, method = RequestMethod.POST)
	public ModelAndView updatePlay(@ModelAttribute("playStatus") PlayStatusBean playStatus, Model model) {

		PlayStatusRequestDao reqDao = new PlayStatusRequestDao();
		reqDao.setId(playStatus.getId());
		reqDao.setName(playStatus.getName());

		boolean status = false;
		try {
			status = playStatusUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "Play Status  has been Update!");

		}
		return new ModelAndView("redirect:setupPlayStatusAdd", "playStatus", playStatus);
	}

	// delete play status
	@RequestMapping(value = "deletePlayStatus", method = RequestMethod.POST)
	public String deletePlayStatus(@ModelAttribute("playStatus") @Validated PlayStatusBean playStatus, ModelMap model) {
		PlayStatusRequestDao reqDao = new PlayStatusRequestDao();
		reqDao.setId(playStatus.getId());

		try {
			boolean status = playStatusDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupPlayStatusAdd";
	}

}
