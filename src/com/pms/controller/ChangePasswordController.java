package com.pms.controller;

import java.sql.SQLException;

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
import org.springframework.web.servlet.ModelAndView;
import com.pms.bean.ChangePasswordBean;
import com.pms.bean.PlayBean;
import com.pms.bean.PlaythingBean;
import com.pms.bean.UserBean;
import com.pms.persistant.dao.TicketTypePlaythingRequestDao;
import com.pms.persistant.dao.UserRequestDao;
import com.pms.persistant.dao.UserResponseDao;
import com.pms.persistant.service.ChangePasswordUpdateService;

@Controller

public class ChangePasswordController {
	@Autowired
	ChangePasswordUpdateService changePasswordUpdateService;

	// Call PMS-LGN-02
	@RequestMapping(value = { "setupChangePassword" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayChangePassword(@ModelAttribute("changePassword") UserBean user,
			HttpServletRequest request) {

		ChangePasswordBean changePassword = new ChangePasswordBean();

		ModelAndView model = new ModelAndView("PMS-LGN-02", "changePassword", changePassword);

		return model;
	}

	// Change Password
	@RequestMapping(value = { "change" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView updateTicket(@ModelAttribute("changePassword") @Validated ChangePasswordBean changePassword,
			BindingResult bindingResult, HttpServletRequest request, Model model) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-LGN-02", "changePassword", changePassword);
		}
		HttpSession session = request.getSession();
		UserRequestDao req = new UserRequestDao();
		UserResponseDao sbean = (UserResponseDao) session.getAttribute("SessionUser");
		req.setUserId(sbean.getUserId());
		req.setPassword(sbean.getPassword());
		String page = "PMS-LGN-02";
		if (!req.getPassword().equals(changePassword.getOldPassword())) {
			model.addAttribute("olderror", "Password is incorrect.");
			page = "PMS-LGN-02";
		} else {
			if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
				model.addAttribute("error", "Password and confirm password are not match.");
				page = "PMS-LGN-02";
			} else {
				UserRequestDao reqDao = new UserRequestDao();
				reqDao.setUserId(req.getUserId());
				System.out.println(reqDao.getUserId() + "id");
				reqDao.setPassword(changePassword.getNewPassword());

				boolean status = false;
				try {
					status = changePasswordUpdateService.doService(reqDao);
					if (!status) {
						model.addAttribute("error", "Data Update failed!");
					} else {
						model.addAttribute("msg", "Change Password has been Update!");
					}
				} catch (SQLException e) {

				}
			}
		}
		return new ModelAndView( "redirect:/logout", "changePassword", changePassword);

	}

}
