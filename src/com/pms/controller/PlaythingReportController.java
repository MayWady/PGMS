package com.pms.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pms.bean.PlaythingReportBean;
import com.pms.persistant.dao.PlaythingCategoriesResponseDao;
import com.pms.persistant.dao.PlaythingReportRequestDao;
import com.pms.persistant.dao.PlaythingReportResponseDao;
import com.pms.persistant.dao.PlaythingResponseDao;
import com.pms.persistant.service.PlaythingCategoriesSearchService;
import com.pms.persistant.service.PlaythingReportSearchService;
import com.pms.persistant.service.PlaythingSelectService;

@Controller
public class PlaythingReportController {

	@Autowired
	PlaythingReportSearchService playthingReportSearchService;
	@Autowired
	PlaythingCategoriesSearchService playthingCategoriesSearchService;
	@Autowired
	PlaythingSelectService playthingSelectService;
	
	// plaything status list
		@ModelAttribute("playthingCategoriesList")
		public List<PlaythingCategoriesResponseDao> getPlaythingCategoriesList() {
			try {
				return playthingCategoriesSearchService.doService(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
		@ModelAttribute("playthingList")
		public List<PlaythingResponseDao> getPlaythingList() {
			try {
				return playthingSelectService.doService(null);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	// call PMS-MBS-01
	@RequestMapping(value = { "setupPlaythingReportSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayplaythingReportSearch() {
		return new ModelAndView("PMS-PRP-02", "playthingReportSearch", new PlaythingReportBean());
	}

	@RequestMapping(value = { "playthingReportSearch" }, method = { RequestMethod.GET, RequestMethod.POST })

	public ModelAndView displayplaythingReport(@ModelAttribute("playthingReportSearch") PlaythingReportBean playthingReport) {
		ModelAndView model;
		model = new ModelAndView("PMS-PRP-02", "playthingReportSearch", playthingReport);
		PlaythingReportRequestDao reqDao = new PlaythingReportRequestDao();

		if (playthingReport != null) {
			if (!playthingReport.getPlaything().equals(""))
				reqDao.setPlaything(playthingReport.getPlaything());
			if (!playthingReport.getPlaythingCategory().equals(""))
				reqDao.setPlaythingCategory(playthingReport.getPlaythingCategory());
			if (!playthingReport.getValidFrom().equals(""))
				reqDao.setValidFrom(Date.valueOf(playthingReport.getValidFrom()));
			if (!playthingReport.getValidTo().equals(""))
				reqDao.setValidTo(Date.valueOf(playthingReport.getValidTo()));
		}

			try

			{
				List<PlaythingReportResponseDao> list = playthingReportSearchService.doService(reqDao);
				System.out.println(list.size() + "size");
			
				model.addObject("playthingReportSearchList", list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		return model;
	}

	
}
