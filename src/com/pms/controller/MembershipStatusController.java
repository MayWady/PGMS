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

import com.pms.bean.MembershipStatusBean;
import com.pms.bean.UserStatusBean;
import com.pms.persistant.dao.MembershipStatusRequestDao;
import com.pms.persistant.dao.MembershipStatusResponseDao;
import com.pms.persistant.service.MembershipStatusDeleteService;
import com.pms.persistant.service.MembershipStatusRegisterService;
import com.pms.persistant.service.MembershipStatusSearchService;
import com.pms.persistant.service.MembershipStatusUpdateService;

@Controller
public class MembershipStatusController {
	@Autowired
	 MembershipStatusRegisterService  membershipStautsRegisterService;
	@Autowired
	 MembershipStatusSearchService  membershipStatusSearchService;
	@Autowired
	 MembershipStatusUpdateService  membershipStatusUpdateService;
	@Autowired
	 MembershipStatusDeleteService  membershipStatusDeleteService;
	
	//call PMS-MST-01
	@RequestMapping(value = { "setupMembershipStatusAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayMembershipStatus(@ModelAttribute("membershipStatus") MembershipStatusBean membershipStatus) {
		ModelAndView model;
		model = new ModelAndView("PMS-MST-01", "membershipStatus", membershipStatus);
		try {
			List<MembershipStatusResponseDao> list = membershipStatusSearchService.doService(null);
			model.addObject("membershipStatusList", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	//go Membership status add confirm page
	@RequestMapping(value = "confirmMembershipStatusAdd", method = RequestMethod.POST)
	public ModelAndView confirmMembershipStatusAdd(@ModelAttribute("membershipStatus") @Validated MembershipStatusBean membershipStatus,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			model = new ModelAndView("PMS-MST-01", "membershipStatus", membershipStatus);
			try {
				List<MembershipStatusResponseDao> list = membershipStatusSearchService.doService(null);
				model.addObject("membershipStatusList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			model = new ModelAndView("PMS-MST-91", "membershipStatus", membershipStatus);
		}

		return model;

	}

	//add membership status
	@RequestMapping(value = { "addMembershipStatus" }, method = RequestMethod.POST)
	public ModelAndView addMembershipStatus(@ModelAttribute("membershipStatus") MembershipStatusBean membershipStatus, Model model) {
		MembershipStatusRequestDao reqDao = new MembershipStatusRequestDao();
		reqDao.setId(membershipStatus.getId());
		reqDao.setName(membershipStatus.getName());

		boolean status;
		try {
			status = membershipStautsRegisterService.doService(reqDao);
			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Membership Status has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupMembershipStatusAdd", "membershipStatus", membershipStatus);
	}
	
	//call PMS-MST-03 page
	@RequestMapping(value = "setupMembershipStatusUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupMembershipStatusUpdate(@RequestParam("id") int id, @RequestParam("name") String name) {
		MembershipStatusBean membershipStatus = new MembershipStatusBean();
		membershipStatus.setId(id);
		membershipStatus.setName(name);
		return new ModelAndView("PMS-MST-03", "membershipStatus", membershipStatus);
	}

	//go membership status update page
	@RequestMapping(value = "confirmMembershipStatusUpdate", method = RequestMethod.POST)
	public ModelAndView confirmMembershipStatusUpdate(@ModelAttribute("membershipStatus") @Validated MembershipStatusBean membershipStatus,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-MST-03", "membershipStatus", membershipStatus);
		} else
			return new ModelAndView("PMS-MST-93", "membershipStatus", membershipStatus);
	}

	//update membership status
	@RequestMapping(value = { "membershipStatusUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateMembership(@ModelAttribute("membershipStatus") MembershipStatusBean membershipStatus, Model model) {

		MembershipStatusRequestDao reqDao = new MembershipStatusRequestDao();
		reqDao.setId(membershipStatus.getId());
		reqDao.setName(membershipStatus.getName());

		boolean status = false;
		try {
			status = membershipStatusUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "Membership Status  has been Update!");

		}
		return new ModelAndView("redirect:setupMembershipStatusAdd", "membershipStatus", membershipStatus);
	}

	//delete membership status
	@RequestMapping(value = "deleteMembershipStatus", method = RequestMethod.POST)
	public String deleteMembershipStatus(@ModelAttribute("membershipStatus") @Validated MembershipStatusBean membershipStatus, ModelMap model) {
		MembershipStatusRequestDao reqDao = new MembershipStatusRequestDao();
		reqDao.setId(membershipStatus.getId());

		try {
			boolean status = membershipStatusDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete Successful");
			} else {
				model.addAttribute("error", "Data delete Fail and this data is using other");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupMembershipStatusAdd";
	}

}
