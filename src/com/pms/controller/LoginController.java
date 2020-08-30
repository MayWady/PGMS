package com.pms.controller;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.pms.bean.LoginBean;
import com.pms.persistant.dao.UserRequestDao;
import com.pms.persistant.dao.UserResponseDao;
import com.pms.persistant.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	// Display Home Page
	@RequestMapping(value = { "home" }, method = RequestMethod.GET)
	public String displayHome() {
		return "PMS-HOM-01";
	}

	// Display Login Page
	@RequestMapping(value = { "/", "displayLogin" }, method = RequestMethod.GET)
	public ModelAndView displayLogin() {
		return new ModelAndView("PMS-LGN-01", "loginUser", new LoginBean());
	}

	// Check Login Credentials
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("loginUser") @Validated LoginBean loginUser, BindingResult bindingResult,
			HttpServletRequest request, ModelMap model) {
		//System.out.println("reach" + loginUser.getUserId() + loginUser.getPassword());
		String page = "PMS-LGN-01";

		if (bindingResult.hasErrors()) {
			/*
			 * System.out.println(bindingResult.getAllErrors());
			 * System.out.println("validation")
			 */;
			return new ModelAndView(page, "loginUser", loginUser);
		}
		UserRequestDao reqDao = new UserRequestDao();
		reqDao.setUserId(loginUser.getUserId());
		reqDao.setPassword(loginUser.getPassword());

		List<UserResponseDao> list;

		try {
			list = loginService.doService(reqDao);
			if (list.size() == 0) {
				model.addAttribute("errorMsg", "Incorrect password. Try again.");
				page = "PMS-LGN-01";

			} else {
				if (!loginUser.getPassword().equals(list.get(0).getPassword())) {
					model.addAttribute("errorMsg", "Incorrect password. Try again.");
					page = "PMS-LGN-01";

				} else {
					page = "PMS-HOM-01";
					request.getSession().setAttribute("SessionUser", list.get(0));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ModelAndView(page, "loginUser", loginUser);
	}

	// Display Home Page
	@RequestMapping(value = { "logout" }, method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		session.invalidate();
		return "redirect:/";
	}

}
