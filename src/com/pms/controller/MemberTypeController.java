package com.pms.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pms.bean.MemberTypeBean;
import com.pms.persistant.dao.MemberTypeRequestDao;
import com.pms.persistant.dao.MemberTypeResponseDao;
import com.pms.persistant.service.MemberTypeDeleteService;
import com.pms.persistant.service.MemberTypeRegisterService;
import com.pms.persistant.service.MemberTypeSearchService;
import com.pms.persistant.service.MemberTypeUpdateService;

@Controller
public class MemberTypeController {
	@Autowired
	MemberTypeRegisterService memberTypeRegisterService;
	@Autowired
	MemberTypeSearchService memberTypeSearchService;
	@Autowired
	MemberTypeUpdateService memberTypeUpdateService;
	@Autowired
	MemberTypeDeleteService memberTypeDeleteService;

	// call PMS-MBT-01
	@RequestMapping(value = { "setupMemberTypeAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayMemberType() {
		return new ModelAndView("PMS-MBT-01", "membertype", new MemberTypeBean());
	}

	// go Member Type add confirm page
	@RequestMapping(value = "confirmMemberTypeAdd", method = RequestMethod.POST)
	public ModelAndView confirmMemberTypeAdd(@ModelAttribute("membertype") @Validated MemberTypeBean membertype,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			// System.out.println(bindingResult.getAllErrors());
			return new ModelAndView("PMS-MBT-01", "membertype", membertype);
		}
		return new ModelAndView("PMS-MBT-91", "membertype", membertype);
	}

	// add member type
	@RequestMapping(value = { "addMemberType" }, method = RequestMethod.POST)
	public ModelAndView addMemberType(@ModelAttribute("membertype") MemberTypeBean membertype, Model model) {
		MemberTypeRequestDao reqDao = new MemberTypeRequestDao();
		reqDao.setMemberTypeid(membertype.getMemberTypeid());
		reqDao.setMemberTypename(membertype.getMemberTypename());
		reqDao.setDescription(membertype.getDescription());
		reqDao.setPrice(Double.valueOf(membertype.getPrice()));
		reqDao.setPerdiscount(Double.valueOf(membertype.getPerdiscount()));
		boolean status;
		try {
			status = memberTypeRegisterService.doService(reqDao);
			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Member Type has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupMemberTypeAdd", "membertype", membertype);
	}

	// call PMS-MBT-02
	@RequestMapping(value = { "setupMemberTypeSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayMemberTypeSearch(@ModelAttribute("membertype") MemberTypeBean membertype,HttpServletRequest request) {
		ServletContext app = request.getServletContext();
		List<MemberTypeResponseDao> list = new ArrayList();
		app.setAttribute("membertypeList", list);
		return new ModelAndView("PMS-MBT-02", "membertype", new MemberTypeBean());
	}

	@RequestMapping(value = { "memberTypeSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayMemberType(@ModelAttribute("membertype") MemberTypeBean membertype,HttpServletRequest request) {
		ModelAndView model;
		model = new ModelAndView("PMS-MBT-02", "membertype", membertype);
		MemberTypeRequestDao reqDao = new MemberTypeRequestDao();

		if (membertype != null) {
			if (!membertype.getMemberTypename().equals(""))
				System.out.println("reach");
			reqDao.setMemberTypename(membertype.getMemberTypename());
			try

			{
				List<MemberTypeResponseDao> list = memberTypeSearchService.doService(reqDao);
				ServletContext app = request.getServletContext();
				if (list.size() == 0) {
					model.addObject("msg", "Data not found!");
					app.setAttribute("membertypeList", new ArrayList());
				} else {				
					app.setAttribute("membertypeList", list);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return model;
	}
	
	@RequestMapping(value = { "backMemberTypeSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView backmembertypeSearch(@ModelAttribute("membertype") MemberTypeBean membertype) {
		if(membertype != null)
			return new ModelAndView("PMS-MBT-02", "membertype",membertype);
		else
		return new ModelAndView("PMS-MBT-02", "membertype", new MemberTypeBean());
	}

	// call PMS-MBT-03 page
	@RequestMapping(value = "setupMemberTypeUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupMemberTypeUpdate(@RequestParam("membertypeId") String membertypeId,
			@RequestParam("membertypeName") String membertypeName, @RequestParam("description") String description,
			@RequestParam("price") String price, @RequestParam("perdiscount") String perdiscount) {
		MemberTypeBean membertype = new MemberTypeBean();

		membertype.setMemberTypeid(membertypeId);
		membertype.setMemberTypename(membertypeName);
		membertype.setDescription(description);
		membertype.setPrice(price);
		membertype.setPerdiscount(perdiscount);

		return new ModelAndView("PMS-MBT-03", "membertypeDetail", membertype);
	}

	// go member type update page
	@RequestMapping(value = "confirmMemberTypeUpdate", method = RequestMethod.POST)
	public ModelAndView confirmMemberTypeUpdate(@ModelAttribute("membertype") @Validated MemberTypeBean membertype,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-MBT-03", "membertype", membertype);
		} else
			return new ModelAndView("PMS-MBT-93", "membertype", membertype);
	}

	// update Member type
	@RequestMapping(value = { "memberTypeUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateMemberType(@ModelAttribute("membertype") MemberTypeBean membertype, Model model) {

		MemberTypeRequestDao reqDao = new MemberTypeRequestDao();
		reqDao.setMemberTypeid(membertype.getMemberTypeid());
		reqDao.setMemberTypename(membertype.getMemberTypename());
		reqDao.setDescription(membertype.getDescription());
		reqDao.setPrice(Double.valueOf(membertype.getPrice()));
		reqDao.setPerdiscount(Double.valueOf(membertype.getPerdiscount()));
		boolean status = false;
		try {
			status = memberTypeUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "Member Type  has been Update!");

		}
		return new ModelAndView("redirect:setupMemberTypeSearch", "membertype", membertype);
	}

	// delete ticket type
	@RequestMapping(value = "deleteMemberType", method = RequestMethod.POST)
	public String deleteMemberType(@ModelAttribute("membertype") MemberTypeBean membertype, ModelMap model) {
		MemberTypeRequestDao reqDao = new MemberTypeRequestDao();

		reqDao.setMemberTypeid(membertype.getMemberTypeid());

		try {
			boolean status = memberTypeDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail and this Data is using other!");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupMemberTypeSearch";
	}

}