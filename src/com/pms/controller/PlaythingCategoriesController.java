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

import com.pms.bean.PlaythingCategoriesBean;
import com.pms.persistant.dao.PlaythingCategoriesRequestDao;
import com.pms.persistant.dao.PlaythingCategoriesResponseDao;
import com.pms.persistant.dao.UserStatusResponseDao;
import com.pms.persistant.service.PlaythingCategoriesDeleteService;
import com.pms.persistant.service.PlaythingCategoriesRegisterService;
import com.pms.persistant.service.PlaythingCategoriesSearchService;
import com.pms.persistant.service.PlaythingCategoriesUpdateService;

@Controller
public class PlaythingCategoriesController {

	@Autowired
	PlaythingCategoriesRegisterService playthingCategoriesRegisterService;
	@Autowired
	PlaythingCategoriesSearchService playthingCategoriesSearchService;
	@Autowired
	PlaythingCategoriesUpdateService playthingCategoriesUpdateService;
	@Autowired
	PlaythingCategoriesDeleteService playthingCategoriesDeleteService;

	// call playthingCategoryList
		@ModelAttribute("playthingCategoriesList")
		public List<PlaythingCategoriesResponseDao> getClassList() {
			try {
				return playthingCategoriesSearchService.doService(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	
	// call PMS-PTC-01

	@RequestMapping(value = { "setupPlaythingCategoriesAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayPalythingCategories(
			@ModelAttribute("playthingCategories") PlaythingCategoriesBean playthingCategories) {
		ModelAndView model;
		model = new ModelAndView("PMS-PTC-01", "playthingCategories", playthingCategories);
		try {
			List<PlaythingCategoriesResponseDao> list = playthingCategoriesSearchService.doService(null);
			model.addObject("playthingCategoriesList", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	// go plaything categories add confirm page
	@RequestMapping(value = "confirmPlaythingCategoriesAdd", method = RequestMethod.POST)
	public ModelAndView confirmPlaythingCategoriesAdd(
			@ModelAttribute("playthingCategories") @Validated PlaythingCategoriesBean playthingCategories,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("PMS-PTC-01", "playthingCategories", playthingCategories);
			try {
				List<PlaythingCategoriesResponseDao> list = playthingCategoriesSearchService.doService(null);
				model.addObject("playthingCategoriesList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			model = new ModelAndView("PMS-PTC-91", "playthingCategories", playthingCategories);
		}

		return model;

	}

	// add plaything categories
	@RequestMapping(value = { "addPlaythingCategories" }, method = RequestMethod.POST)
	public ModelAndView addPlaythingCategories(
			@ModelAttribute("playthingCategories") PlaythingCategoriesBean playthingCategories, Model model) {
		PlaythingCategoriesRequestDao reqDao = new PlaythingCategoriesRequestDao();
		reqDao.setId(playthingCategories.getId());
		reqDao.setName(playthingCategories.getName());

		boolean status;
		try {
			status = playthingCategoriesRegisterService.doService(reqDao);
			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Plaything Categories has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupPlaythingCategoriesAdd", "playthingCategories", playthingCategories);
	}

	// call PMS-PTC-03 page
	@RequestMapping(value = "setupPlaythingCategoriesUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupPlaythingCategoriesUpdate(@RequestParam("id") int id, @RequestParam("name") String name) {
		PlaythingCategoriesBean playthingCategories = new PlaythingCategoriesBean();
		playthingCategories.setId(id);
		playthingCategories.setName(name);
		return new ModelAndView("PMS-PTC-03", "playthingCategories", playthingCategories);
	}

	// go plaything categories update page
	@RequestMapping(value = "confirmPlaythingCategoriesUpdate", method = RequestMethod.POST)
	public ModelAndView confirmPlaytingCategoriesUpdate(
			@ModelAttribute("playthingCategories") @Validated PlaythingCategoriesBean playthingCategories,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-PTC-03", "playthingCategories", playthingCategories);
		} else
			return new ModelAndView("PMS-PTC-93", "playthingCategories", playthingCategories);
	}

	// update user status
	@RequestMapping(value = { "playthingCategoriesUpdate" }, method = RequestMethod.POST)
	public ModelAndView updatePlaythingCategories(
			@ModelAttribute("playthingCategories") PlaythingCategoriesBean playthingCategories, Model model) {

		PlaythingCategoriesRequestDao reqDao = new PlaythingCategoriesRequestDao();
		reqDao.setId(playthingCategories.getId());
		reqDao.setName(playthingCategories.getName());

		boolean status = false;
		try {
			status = playthingCategoriesUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "Plaything Categories  has been Update!");

		}
		return new ModelAndView("redirect:setupPlaythingCategoriesAdd", "playthingCategories", playthingCategories);
	}

	// delete user status
	@RequestMapping(value = "deletePlaythingCategories", method = RequestMethod.POST)
	public String deletePlaythingCategories(
			@ModelAttribute("playthingCategories") @Validated PlaythingCategoriesBean playthingCategories,
			ModelMap model) {
		PlaythingCategoriesRequestDao reqDao = new PlaythingCategoriesRequestDao();
		reqDao.setId(playthingCategories.getId());

		try {
			boolean status = playthingCategoriesDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail and this data is using other!");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupPlaythingCategoriesAdd";
	}

}
