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
import com.pms.bean.UserStatusBean;
import com.pms.persistant.dao.UserStatusRequestDao;
import com.pms.persistant.dao.UserStatusResponseDao;
import com.pms.persistant.service.UserStatusDeleteService;
import com.pms.persistant.service.UserStatusRegisterService;
import com.pms.persistant.service.UserStatusSearchService;
import com.pms.persistant.service.UserStatusUpdateService;

@Controller
public class UserStatusController {
	@Autowired
	UserStatusRegisterService userStautsRegisterService;
	@Autowired
	UserStatusSearchService userStatusSearchService;
	@Autowired
	UserStatusUpdateService userStatusUpdateService;
	@Autowired
	UserStatusDeleteService userStatusDeleteService;
	
	//call PMS-UST-01
	@RequestMapping(value = { "setupUserStatusAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayUserStatus(@ModelAttribute("userStatus") UserStatusBean userStatus) {
		ModelAndView model;
		model = new ModelAndView("PMS-UST-01", "userStatus", userStatus);
		try {
			List<UserStatusResponseDao> list = userStatusSearchService.doService(null);
			model.addObject("userStatusList", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	//go user status add confirm page
	@RequestMapping(value = "confirmUserStatusAdd", method = RequestMethod.POST)
	public ModelAndView confirmUserStatusAdd(@ModelAttribute("userStatus") @Validated UserStatusBean userStatus,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("PMS-UST-01", "userStatus", userStatus);
			try {
				List<UserStatusResponseDao> list = userStatusSearchService.doService(null);
				model.addObject("userStatusList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			model = new ModelAndView("PMS-UST-91", "userStatus", userStatus);
		}

		return model;

	}

	//add user status
	@RequestMapping(value = { "addUserStatus" }, method = RequestMethod.POST)
	public ModelAndView addUserStatus(@ModelAttribute("userStatus") UserStatusBean userStatus, Model model) {
		UserStatusRequestDao reqDao = new UserStatusRequestDao();
		reqDao.setId(userStatus.getId());
		reqDao.setName(userStatus.getName());

		boolean status;
		try {
			status = userStautsRegisterService.doService(reqDao);
			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "User Status has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupUserStatusAdd", "userStatus", userStatus);
	}
	
	//call PMS-UST-03 page
	@RequestMapping(value = "setupUserStatusUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupUserStatusUpdate(@RequestParam("id") int id, @RequestParam("name") String name) {
		UserStatusBean userStatus = new UserStatusBean();
		userStatus.setId(id);
		userStatus.setName(name);
		return new ModelAndView("PMS-UST-03", "userStatus", userStatus);
	}

	//go user status update page
	@RequestMapping(value = "confirmUserStatusUpdate", method = RequestMethod.POST)
	public ModelAndView confirmUserStatusUpdate(@ModelAttribute("userStatus") @Validated UserStatusBean userStatus,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-UST-03", "userStatus", userStatus);
		} else
			return new ModelAndView("PMS-UST-93", "userStatus", userStatus);
	}

	//update user status
	@RequestMapping(value = { "userStatusUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("userStatus") UserStatusBean userStatus, Model model) {

		UserStatusRequestDao reqDao = new UserStatusRequestDao();
		reqDao.setId(userStatus.getId());
		reqDao.setName(userStatus.getName());

		boolean status = false;
		try {
			status = userStatusUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "User Status  has been Update!");

		}
		return new ModelAndView("redirect:setupUserStatusAdd", "userStatus", userStatus);
	}

	//delete user status
	@RequestMapping(value = "deleteUserStatus", method = RequestMethod.POST)
	public String deleteUserStatus(@ModelAttribute("userStatus") @Validated UserStatusBean userStatus, ModelMap model) {
		UserStatusRequestDao reqDao = new UserStatusRequestDao();
		reqDao.setId(userStatus.getId());

		try {
			boolean status = userStatusDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail and this data is using other");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupUserStatusAdd";
	}

}
