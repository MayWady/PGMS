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

import com.pms.bean.PlaythingStatusBean;
import com.pms.persistant.dao.PlaythingStatusRequestDao;
import com.pms.persistant.dao.PlaythingStatusResponseDao;
import com.pms.persistant.service.PlaythingStatusDeleteService;
import com.pms.persistant.service.PlaythingStatusRegisterService;
import com.pms.persistant.service.PlaythingStatusSearchService;
import com.pms.persistant.service.PlaythingStatusUpdateService;



@Controller
public class PlaythingStatusController {
	@Autowired
	PlaythingStatusRegisterService playthingStautsRegisterService;
	@Autowired
	PlaythingStatusSearchService playthingStatusSearchService;
	@Autowired
	PlaythingStatusUpdateService playthingStatusUpdateService;
	@Autowired
	PlaythingStatusDeleteService playthingStatusDeleteService;
	
	//call PMS-PTS-01
	@RequestMapping(value = { "setupPlaythingStatusAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayUserStatus(@ModelAttribute("playthingStatus") PlaythingStatusBean playthingStatus) {
		ModelAndView model;
		model = new ModelAndView("PMS-PTS-01", "playthingStatus", playthingStatus);
		try {
			List<PlaythingStatusResponseDao> list = playthingStatusSearchService.doService(null);
			model.addObject("playthingStatusList", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	//go plaything status add confirm page
	@RequestMapping(value = "confirmPlaythingStatusAdd", method = RequestMethod.POST)
	public ModelAndView confirmPlaythingStatusAdd(@ModelAttribute("playthingStatus") @Validated PlaythingStatusBean playthingStatus,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("PMS-PTS-01", "playthingStatus", playthingStatus);
			try {
				List<PlaythingStatusResponseDao> list = playthingStatusSearchService.doService(null);
				model.addObject("playthingStatusList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			model = new ModelAndView("PMS-PTS-91", "playthingStatus", playthingStatus);
		}

		return model;

	}
	//add plaything status
		@RequestMapping(value = { "addPlaythingStatus" }, method = RequestMethod.POST)
		public ModelAndView addUserStatus(@ModelAttribute("playthingStatus") PlaythingStatusBean playthingStatus, Model model) {
			PlaythingStatusRequestDao reqDao = new PlaythingStatusRequestDao();
			reqDao.setId(playthingStatus.getId());
			reqDao.setName(playthingStatus.getName());

			boolean status;
			try {
				status = playthingStautsRegisterService.doService(reqDao);
				if (!status) {
					model.addAttribute("error", "Data entry failed!");
				} else {
					model.addAttribute("msg", "Plaything Status has been added!");
				}
			} catch (SQLException e) {
				model.addAttribute("error", "Database error!");
				e.printStackTrace();
			}
			return new ModelAndView("redirect:setupPlaythingStatusAdd", "playthingStatus", playthingStatus);
		}
		
	
		//call PMS-PST-03 page
		@RequestMapping(value = "setupPlaythingStatusUpdate", method = { RequestMethod.GET, RequestMethod.POST })
		public ModelAndView setupPlaythingStatusUpdate(@RequestParam("id") int id, @RequestParam("name") String name) {
			PlaythingStatusBean playthingStatus = new PlaythingStatusBean();
			playthingStatus.setId(id);
			playthingStatus.setName(name);
			return new ModelAndView("PMS-PTS-03", "playthingStatus", playthingStatus);
		}

		//go plaything status update page
		@RequestMapping(value = "confirmPlaythingStatusUpdate", method = RequestMethod.POST)
		public ModelAndView confirmPlaythingStatusUpdate(@ModelAttribute("playthingStatus") @Validated PlaythingStatusBean playthingStatus,
				BindingResult bindingResult) {

			if (bindingResult.hasErrors()) {
				return new ModelAndView("PMS-PTS-03", "playthingStatus", playthingStatus);
			} else
				return new ModelAndView("PMS-PTS-93", "playthingStatus", playthingStatus);
		}


		//update plaything status
		@RequestMapping(value = { "playthingStatusUpdate" }, method = RequestMethod.POST)
		public ModelAndView updatePlaything(@ModelAttribute("playthingStatus") PlaythingStatusBean playthingStatus, Model model) {

			PlaythingStatusRequestDao reqDao = new PlaythingStatusRequestDao();
			reqDao.setId(playthingStatus.getId());
			reqDao.setName(playthingStatus.getName());

			boolean status = false;
			try { 
				status = playthingStatusUpdateService.doService(reqDao);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!status) {
				model.addAttribute("error", "Data Update failed!");
			} else {
				model.addAttribute("msg", "Plaything Status  has been Update!");

			}
			return new ModelAndView("redirect:setupPlaythingStatusAdd", "playthingStatus", playthingStatus);
		}
		//delete plaything status
		@RequestMapping(value = "deletePlaythingStatus", method = RequestMethod.POST)
		public String deletePlaythingStatus(@ModelAttribute("playthingStatus") @Validated PlaythingStatusBean playthingStatus, ModelMap model) {
			PlaythingStatusRequestDao reqDao = new PlaythingStatusRequestDao();
			reqDao.setId(playthingStatus.getId());

			try {
				boolean status = playthingStatusDeleteService.doService(reqDao);
				if (status) {
					model.addAttribute("msg", "Data delete successful");
				} else {
					model.addAttribute("error", "Data delete Fail and this data is using other!");
				}
			} catch (SQLException e) { // TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:setupPlaythingStatusAdd";
		}


	
}
