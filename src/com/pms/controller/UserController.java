package com.pms.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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

import com.pms.bean.PlaythingBean;
import com.pms.bean.UserBean;
import com.pms.persistant.dao.PlaythingResponseDao;
import com.pms.persistant.dao.UserRequestDao;
import com.pms.persistant.dao.UserResponseDao;
import com.pms.persistant.dao.UserStatusResponseDao;
import com.pms.persistant.service.UserDeleteService;
import com.pms.persistant.service.UserSearch;
import com.pms.persistant.service.UserRegisterService;
import com.pms.persistant.service.UserSearchService;
import com.pms.persistant.service.UserStatusSearchService;
import com.pms.persistant.service.UserUpdateService;

@Controller
public class UserController {
	@Autowired
	UserRegisterService userRegisterService;
	@Autowired
	UserSearchService userSearchService;
	@Autowired
	UserUpdateService userUpdateService;
	@Autowired
	UserDeleteService userDeleteService;
	@Autowired
	UserStatusSearchService userStatusSearchService;
	@Autowired
	UserSearch userSearch;

	// call PMS-UST-01
	@RequestMapping(value = { "setupUserAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayUser(@ModelAttribute("user") UserBean user) {

		UserBean bean = new UserBean();
		bean.setUserId(getId());

		return new ModelAndView("PMS-USR-01", "user", bean);

	}

	public String getId() {
		String localdate1 = LocalDate.now().toString();
		String localdate2 = localdate1.substring(2, 4);
		localdate2 += localdate1.substring(5, 7);
		String id = userSearch.getId();
		String lastdate = id.substring(3, 7);
		String lastId = id.substring(7);
		String ans = "";
		if (localdate2.equals(lastdate)) {
			int i = Integer.valueOf(lastId);
			i++;
			lastId = String.valueOf(i);
			if (lastId.length() == 1) {
				ans = ("USR" + lastdate + "00" + lastId);
			} else if (lastId.length() == 2) {
				ans = ("USR" + lastdate + "0" + lastId);
			} else {
				ans = ("USR" + lastdate + lastId);
			}

		} else {
			ans = ("USR" + localdate2 + "001");
		}
		return ans;
	}

	// go user add confirm page
	@RequestMapping(value = "confirmUserAdd", method = RequestMethod.POST)
	public ModelAndView confirmUserAdd(@ModelAttribute("user") @Validated UserBean user, BindingResult bindingResult) {
		ModelAndView model;

		UserRequestDao reqDao = new UserRequestDao();
		reqDao.setPassword(user.getPassword());
		reqDao.setConfirmPassword(user.getConfirmPassword());

		if (bindingResult.hasErrors()) {
			model = new ModelAndView("PMS-USR-01", "user", user);
			try {
				List<UserResponseDao> list = userSearchService.doService(null);
				model.addObject("userList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (reqDao.getPassword().equals(reqDao.getConfirmPassword())) {
			model = new ModelAndView("PMS-USR-91", "user", user);
		} else {
			model = new ModelAndView("PMS-USR-01", "user", user);
		}

		return model;

	}

	// call UserStatus
	@ModelAttribute("statusList")
	public List<UserStatusResponseDao> getClassList() {
		try {
			return userStatusSearchService.doService(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// add user
	@RequestMapping(value = { "addUser" }, method = RequestMethod.POST)
	public ModelAndView addUserStatus(@ModelAttribute("user") UserBean user, Model model) {
		UserRequestDao reqDao = new UserRequestDao();
		reqDao.setUserId(user.getUserId());
		reqDao.setUserName(user.getUserName());
		reqDao.setPassword(user.getPassword());
		reqDao.setNrc(user.getNrc());
		reqDao.setAddress(user.getAddress());
		reqDao.setPhone(user.getPhone());
		reqDao.setEmail(user.getEmail());
		reqDao.setRole(user.getRole());
		reqDao.setUserStatus(user.getUserStatus());

		boolean status;
		try {
			status = userRegisterService.doService(reqDao);
			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "User has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupUserAdd", "user", user);
	}

	// call PMS-USR-02
	@RequestMapping(value = { "setupUserSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayUserSearch(@ModelAttribute("user") UserBean user, HttpServletRequest request) {
		ServletContext app = request.getServletContext();
		List<UserResponseDao> list = new ArrayList();
		app.setAttribute("userList", list);
		return new ModelAndView("PMS-USR-02", "user", new UserBean());
	}

	@RequestMapping(value = { "userSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayUser(@ModelAttribute("user") UserBean user, HttpServletRequest request) {
		ModelAndView model;
		model = new ModelAndView("PMS-USR-02", "user", user);
		UserRequestDao reqDao = new UserRequestDao();

		if (user != null) {
			if (!user.getUserId().equals(""))
				reqDao.setUserId(user.getUserId());
			if (!user.getUserName().equals(""))
				reqDao.setUserName(user.getUserName());
			if (!user.getNrc().equals(""))
				reqDao.setNrc(user.getNrc());
			reqDao.setRole(user.getRole());
			reqDao.setUserStatus(user.getUserStatus());
		}
		try

		{
			List<UserResponseDao> list = userSearchService.doService(reqDao);
			System.out.println(list.size() + "size");
			ServletContext app = request.getServletContext();
			app.setAttribute("userList", list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	// for detail page
	@RequestMapping(value = { "backUserSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView backPlaythingSearch(@ModelAttribute("user") UserBean user) {
		if(user!=null)
			return new ModelAndView("PMS-USR-02", "user", user);
		else
		return new ModelAndView("PMS-USR-02", "user", new UserBean());
	}
	
	// call PMS-USR-03 page
	@RequestMapping(value = "setupUserUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupUserUpdate(@RequestParam("userId") String userId,
			@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("nrc") String nrc, @RequestParam("address") String address,
			@RequestParam("phone") String phone, @RequestParam("email") String email, @RequestParam("role") int role,
			@RequestParam("userStatus") int userStatus) {
		UserBean user = new UserBean();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword("000000");
		user.setConfirmPassword("000000");
		user.setNrc(nrc);
		user.setAddress(address);
		user.setPhone(phone);
		user.setEmail(email);
		user.setRole(role);
		user.setUserStatus(userStatus);
		return new ModelAndView("PMS-USR-03", "userDetail", user);
	}

	// go user update page
	@RequestMapping(value = "confirmUserUpdate", method = RequestMethod.POST)
	public ModelAndView confirmUserUpdate(@ModelAttribute("user") @Validated UserBean user,
			BindingResult bindingResult) {

		ModelAndView model;

		if (bindingResult.hasErrors()) {
			model = new ModelAndView("PMS-USR-03", "user", user);
			System.out.println(bindingResult.getAllErrors());
			return model;
		} else {
			model = new ModelAndView("PMS-USR-93", "user", user);
		}
		return model;
	}

	// update user status
	@RequestMapping(value = { "userUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("user") UserBean user, Model model) {

		UserRequestDao reqDao = new UserRequestDao();
		reqDao.setUserId(user.getUserId());
		reqDao.setUserName(user.getUserName());
		reqDao.setPassword(user.getPassword());
		reqDao.setNrc(user.getNrc());
		reqDao.setAddress(user.getAddress());
		reqDao.setPhone(user.getPhone());
		reqDao.setEmail(user.getEmail());
		reqDao.setRole(user.getRole());
		reqDao.setUserStatus(user.getUserStatus());

		boolean status = false;
		try {
			status = userUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "User   has been Update!");

		}
		return new ModelAndView("redirect:setupUserSearch", "user", user);
	}

	// delete user status
	@RequestMapping(value = "deleteUser", method = RequestMethod.POST)
	public String deleteUser(@ModelAttribute("user") UserBean user, ModelMap model) {
		UserRequestDao reqDao = new UserRequestDao();
		reqDao.setUserId(user.getUserId());
		reqDao.setUserName(user.getUserName());
		reqDao.setNrc(user.getNrc());
		reqDao.setPassword(user.getPassword());
		reqDao.setAddress(user.getAddress());
		reqDao.setEmail(user.getEmail());
		reqDao.setPhone(user.getPhone());
		reqDao.setUserStatus(user.getUserStatus());
		reqDao.setRole(user.getRole());

		try {
			boolean status = userDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupUserAdd";
	}

}
