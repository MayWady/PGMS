package com.pms.controller;

import java.sql.Date;
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

import com.pms.bean.MembershipBean;
import com.pms.bean.PlaythingBean;
import com.pms.persistant.dao.MemberTypeRequestDao;
import com.pms.persistant.dao.MemberTypeResponseDao;
import com.pms.persistant.dao.MembershipRequestDao;
import com.pms.persistant.dao.MembershipResponseDao;
import com.pms.persistant.dao.MembershipStatusResponseDao;
import com.pms.persistant.dao.PlaythingResponseDao;
import com.pms.persistant.service.MemberIdSearch;
import com.pms.persistant.service.MemberTypeSearchService;
import com.pms.persistant.service.MemberTypeSelectOne;
import com.pms.persistant.service.MemberTypeSelectService;
import com.pms.persistant.service.MembershipDeleteService;
import com.pms.persistant.service.MembershipRegisterService;
import com.pms.persistant.service.MembershipSearchService;
import com.pms.persistant.service.MembershipStatusSearchService;
import com.pms.persistant.service.MembershipUpdateService;

@Controller
public class MembershipController {

	@Autowired
	MembershipRegisterService membershipRegisterService;
	@Autowired
	MembershipStatusSearchService membershipStatusSearchService;
	@Autowired
	MembershipSearchService membershipSearchService;
	@Autowired
	MembershipUpdateService membershipUpdateService;
	@Autowired
	MembershipDeleteService membershipDeleteService;
	@Autowired
	MemberIdSearch memberIdSearch;
	@Autowired
	MemberTypeSelectOne memberTypeSelectOne;
	@Autowired
	MemberTypeSelectService memberTypeSelectService;

	// call PMS-MBS-01
	@RequestMapping(value = { "setupMembershipAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayMembership() {
		MembershipBean bean = new MembershipBean();
		bean.setMemberId(getId());
		return new ModelAndView("PMS-MBS-01", "membership", bean);
	}

	public String getId() {
		String localdate1 = LocalDate.now().toString();
		String localdate2 = localdate1.substring(2, 4);
		localdate2 += localdate1.substring(5, 7);
		String id = memberIdSearch.getId();
		String lastdate = id.substring(3, 7);
		String lastId = id.substring(7);
		String ans = "";
		if (localdate2.equals(lastdate)) {
			int i = Integer.valueOf(lastId);
			i++;
			lastId = String.valueOf(i);
			if (lastId.length() == 1) {
				ans = ("MBS" + lastdate + "00" + lastId);
			} else if (lastId.length() == 2) {
				ans = ("MBS" + lastdate + "0" + lastId);
			} else {
				ans = ("MBS" + lastdate + lastId);
			}

		} else {
			ans = ("MBS" + localdate2 + "001");
		}
		return ans;
	}

	// go membership status add confirm page
	@RequestMapping(value = "confirmMembershipAdd", method = RequestMethod.POST)
	public ModelAndView confirmMembershipAdd(@ModelAttribute("membership") @Validated MembershipBean membership,
			BindingResult bindingResult) {
		ModelAndView model;
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-MBS-01", "membership", membership);

		}
		MemberTypeRequestDao reqDao = new MemberTypeRequestDao();
		reqDao.setMemberTypeid(membership.getMemberType());
		try {
			MemberTypeResponseDao dao = memberTypeSelectOne.doService(reqDao);
			membership.setPrice(String.valueOf(dao.getPrice()));
			membership.setDiscount(String.valueOf(dao.getPerdiscount()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		membership.setMembershipStatusName(memberIdSearch.getStatus(membership.getMembershipStatusId()));
		membership.setMemberTypeName(memberIdSearch.getType(membership.getMemberType()));
		System.out.println("a"+membership.getMembershipStatusName()+membership.getMemberTypeName());
		membership.setDate(LocalDate.now().toString());

		return new ModelAndView("PMS-MBS-91", "membership", membership);
	}

	@ModelAttribute("statusList")
	public List<MembershipStatusResponseDao> getClassList() {
		try {
			return membershipStatusSearchService.doService(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@ModelAttribute("typeList")
	public List<MemberTypeResponseDao> getTypeList() {
		return memberTypeSelectService.getList();
	}

	// add membership status
	@RequestMapping(value = { "addMembership" }, method = RequestMethod.POST)
	public ModelAndView addMembership(@ModelAttribute("membership") MembershipBean membership, Model model) {
		MembershipRequestDao reqDao = new MembershipRequestDao();
		reqDao.setMemberId(membership.getMemberId());
		reqDao.setMemberName(membership.getMemberName());
		System.out.println(membership.getValidFrom()+ "");
		reqDao.setValidFrom(Date.valueOf(membership.getValidFrom()));
		reqDao.setValidTo(Date.valueOf(membership.getValidTo()));
		reqDao.setDate(Date.valueOf(membership.getDate()));
		reqDao.setAddress(membership.getAddress());
		reqDao.setMembershipStatusId(Integer.valueOf(membership.getMembershipStatusId()));
		reqDao.setMemberType(membership.getMemberType());
		reqDao.setPhone(membership.getPhone());
		reqDao.setNrc(membership.getNrc());

		boolean status;
		try {
			status = membershipRegisterService.doService(reqDao);

			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Membership has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupMembershipAdd", "membership", membership);
	}

	// call PMS-MBS-01
	@RequestMapping(value = { "setupMembershipSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayMembershipSearch(@ModelAttribute("membership") MembershipBean membership, HttpServletRequest request) {
		ServletContext app=request.getServletContext();
		List<MembershipResponseDao> list=new ArrayList();
		app.setAttribute("membershipList", list);
		return new ModelAndView("PMS-MBS-02", "membership", new MembershipBean());
		
		
		
	}

	@RequestMapping(value = { "membershipSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayMembership(@ModelAttribute("membership") MembershipBean membership,HttpServletRequest request) {
		ModelAndView model;
		model = new ModelAndView("PMS-MBS-02", "membership", membership);
		MembershipRequestDao reqDao = new MembershipRequestDao();

		if (membership != null) {
			/*if (!membership.getMemberId().equals(""))
				reqDao.setMemberId(membership.getMemberId());*/
			if (!membership.getMemberName().equals(""))
				reqDao.setMemberName(membership.getMemberName());
			if (!membership.getValidFrom().equals(""))
				reqDao.setValidFrom(Date.valueOf(membership.getValidFrom()));
			if (!membership.getValidTo().equals(""))
				reqDao.setValidTo(Date.valueOf(membership.getValidTo()));
			if (!membership.getAddress().equals(""))
				reqDao.setAddress(membership.getAddress());
			if (!membership.getMembershipStatusId().equals(""))
				reqDao.setMembershipStatusId(Integer.valueOf(membership.getMembershipStatusId()));
			if (!membership.getMemberType().equals(""))
				reqDao.setMemberType(membership.getMemberType());
			if (!membership.getNrc().equals(""))
				reqDao.setNrc(membership.getNrc());
			if (!membership.getPhone().equals(""))
				reqDao.setPhone(membership.getPhone());
		}
			try

			{
				List<MembershipResponseDao> list = membershipSearchService.doService(reqDao);
				
				System.out.println(list.size() + "size");
				ServletContext app=request.getServletContext();
				
				if(list.size()==0) {
					model.addObject("msg", "Data not found");
					app.setAttribute("membershipList", list);
				}else {
					app.setAttribute("membershipList", list);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		return model;
	}

	@RequestMapping(value = { "backmembershipSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView backmembershipSearch(@ModelAttribute("membership") MembershipBean membership) {
		if(membership!=null)
			return new ModelAndView("PMS-MBS-02", "membership", membership);
		else
		return new ModelAndView("PMS-MBS-02", "membership", new MembershipBean());
	}
	
	
	// call PMS-MBS-03 page
	@RequestMapping(value = "setupMembershipUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupMembershipUpdate(@RequestParam("memberId") String id, @RequestParam("memberName") String name,
		    @RequestParam("validTo") String validTo,@RequestParam("validFrom") String validFrom,
			@RequestParam("membershipStatusId") String membershipStatusId,
			@RequestParam("memberType") String memberType, @RequestParam("nrc") String nrc, 
			@RequestParam("phone") String phone,@RequestParam("address") String address, 
			@RequestParam("date") String date) {
		MembershipBean membership = new MembershipBean();
		membership.setMemberId(id);
		membership.setMemberName(name);
		membership.setValidFrom(validFrom);
		System.out.println("date"+validFrom);
		membership.setValidTo(validTo);
		membership.setMembershipStatusId(membershipStatusId);
		membership.setAddress(address);
		membership.setDate(date);
		membership.setMemberType(memberType);
		membership.setNrc(nrc);
		membership.setPhone(phone);
		return new ModelAndView("PMS-MBS-03", "membershipDetail", membership);
	}

	// go membership update page
	@RequestMapping(value = "confirmMembershipUpdate", method = RequestMethod.POST)
	public ModelAndView confirmMembershipUpdate(@ModelAttribute("membership") @Validated MembershipBean membership,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-MBS-03", "membership", membership);
		} else
			
	    membership.setMembershipStatusName(memberIdSearch.getStatus(membership.getMembershipStatusId()));
		membership.setMemberTypeName(memberIdSearch.getType(membership.getMemberType()));
		System.out.println("a"+membership.getMembershipStatusName()+membership.getMemberTypeName());
		membership.setDate(LocalDate.now().toString());
		MemberTypeRequestDao reqDao = new MemberTypeRequestDao();
		reqDao.setMemberTypeid(membership.getMemberType());
		MemberTypeResponseDao dao;
		try {
			dao = memberTypeSelectOne.doService(reqDao);
			membership.setPrice(String.valueOf(dao.getPrice()));
			membership.setDiscount(String.valueOf(dao.getPerdiscount()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			return new ModelAndView("PMS-MBS-93", "membership", membership);
	}

	// update membership
	@RequestMapping(value = { "membershipUpdate" }, method = RequestMethod.POST)
	public ModelAndView updateMembership(@ModelAttribute("membership") MembershipBean membership, Model model) {

		MembershipRequestDao reqDao = new MembershipRequestDao();
		reqDao.setMemberId(membership.getMemberId());
		reqDao.setMemberName(membership.getMemberName());
		reqDao.setAddress(membership.getAddress());		
		System.out.println(membership.getMembershipStatusId()+"id");
		reqDao.setMembershipStatusId(Integer.valueOf(membership.getMembershipStatusId()));
		reqDao.setMemberType(membership.getMemberType());
		reqDao.setNrc(membership.getNrc());
		reqDao.setPhone(membership.getPhone());
		System.out.println(reqDao.getNrc()+reqDao.getPhone());
		//reqDao.setDate(Date.valueOf(membership.getDate()));
		reqDao.setValidFrom(Date.valueOf(membership.getValidFrom()));
		reqDao.setValidTo(Date.valueOf(membership.getValidTo()));

		boolean status = false;
		try {
			status = membershipUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "Membership  has been Update!");

		}
		return new ModelAndView("redirect:setupMembershipSearch", "membership", membership);
	}

	// delete membership
	@RequestMapping(value = "deleteMembership", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteMembership(@ModelAttribute("membership") @Validated MembershipBean membership, ModelMap model) {
		MembershipRequestDao reqDao = new MembershipRequestDao();

		reqDao.setMemberId(membership.getMemberId());
		/*reqDao.setAddress(membership.getAddress());
		reqDao.setMemberName(membership.getMemberName());
		reqDao.setMembershipStatusId(Integer.valueOf(membership.getMembershipStatusId()));
		reqDao.setMemberType(membership.getMemberType());
		reqDao.setNrc(membership.getNrc());
		reqDao.setPhone(membership.getPhone());
		reqDao.setValidFrom(Date.valueOf(membership.getValidFrom()));
		reqDao.setValidTo(Date.valueOf(membership.getValidTo()));*/
		
		try {
			boolean status = membershipDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail and this Data is using");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupMembershipAdd";
	}
}
