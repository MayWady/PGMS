package com.pms.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.pms.bean.PlaythingBean;
import com.pms.persistant.dao.PlaythingCategoriesResponseDao;
import com.pms.persistant.dao.PlaythingRequestDao;
import com.pms.persistant.dao.PlaythingResponseDao;
import com.pms.persistant.dao.PlaythingStatusResponseDao;
import com.pms.persistant.service.PlaythingCategoriesSearchService;
import com.pms.persistant.service.PlaythingDeleteService;
import com.pms.persistant.service.PlaythingIdSearchService;
import com.pms.persistant.service.PlaythingRegisterService;
import com.pms.persistant.service.PlaythingSearchService;
import com.pms.persistant.service.PlaythingStatusSearchService;
import com.pms.persistant.service.PlaythingUpdateService;

@Controller
public class PlaythingController {
	@Autowired
	PlaythingRegisterService playthingRegisterService;
	@Autowired
	PlaythingSearchService playthingSearchService;
	@Autowired
	PlaythingStatusSearchService playthingStatusSearchService;
	@Autowired
	PlaythingCategoriesSearchService playthingCategoriesSearchService;
	@Autowired
	PlaythingUpdateService playthingUpdateService;
	@Autowired
	PlaythingDeleteService playthingDeleteService;
	@Autowired
	PlaythingIdSearchService playthingIdSearchService;

	// plaything status list
	@ModelAttribute("statusList")
	public List<PlaythingStatusResponseDao> getStatusList() {
		try {
			return playthingStatusSearchService.doService(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

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

	// call PMS-PTC-01
	@RequestMapping(value = { "setupPlaythingAdd" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayPalything() {
		PlaythingBean bean = new PlaythingBean();
		bean.setPlaythingId(getId());
		return new ModelAndView("PMS-PLT-01", "plaything", bean);
	}

	public String getId() {
		String ans = "";
		String id = "";
		id = playthingIdSearchService.getId();
		String localdate1 = LocalDate.now().toString();
		String localdate2 = localdate1.substring(2, 4);
		localdate2 += localdate1.substring(5, 7);
		if (id.equals("")) {
			ans = "PLT" + localdate2 + "001";
		} else {

			String lastdate = id.substring(3, 7);
			String lastId = id.substring(7);

			if (localdate2.equals(lastdate)) {
				int i = Integer.valueOf(lastId);
				i++;
				lastId = String.valueOf(i);
				if (lastId.length() == 1) {
					ans = ("PLT" + lastdate + "00" + lastId);
				} else if (lastId.length() == 2) {
					ans = ("PLT" + lastdate + "0" + lastId);
				} else {
					ans = ("PLT" + lastdate + lastId);
				}

			} else {
				ans = ("PLT" + localdate2 + "001");
			}
		}
		return ans;
	}

	// go plaything add confirm page
	@RequestMapping(value = "confirmPlaythingAdd", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView confirmPlaythingAdd(@ModelAttribute("plaything") @Validated PlaythingBean plaything,
			BindingResult bindingResult, @RequestParam MultipartFile file, HttpServletRequest request)
			throws Exception {

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors());
			return new ModelAndView("PMS-PLT-01", "plaything", plaything);
		} else {
			// ModelAndView model=new ModelAndView("PMS-PLT-91", "plaything", plaything);
			String path = request.getServletContext().getRealPath("/");
			if (!file.isEmpty()) {

				byte barr[] = file.getBytes();
				plaything.setB(barr);
				// request.getSession().setAttribute("multipartfile", barr);
				File dir = new File(path + File.separator + "resources/tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(dir + File.separator + plaything.getPlaythingId() + ".png");
				BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(serverFile));
				bout.write(barr);
				bout.flush();
				bout.close();
				plaything.setImage("resources/tmpFiles/" + plaything.getPlaythingId() + ".png");

				// plaything.setBytearray(barr);
				// System.out.println(plaything.getBytearray());
			}
			plaything.setPlaythingCategoriesName(
					playthingIdSearchService.getPCName(Integer.valueOf(plaything.getPlaythingCategoriesId())));
			plaything.setPlaythingStatusName(
					playthingIdSearchService.getStatusName(Integer.valueOf(plaything.getPlaythingStatusId())));
			return new ModelAndView("PMS-PLT-91", "plaything", plaything);
		}

	}

	// add plaything
	@RequestMapping(value = { "addPlaything" }, method = RequestMethod.POST)
	public ModelAndView addPlaything(@ModelAttribute("plaything") PlaythingBean plaything, Model model,
			HttpServletRequest request) {
		PlaythingRequestDao reqDao = new PlaythingRequestDao();
		reqDao.setPlaythingId(plaything.getPlaythingId());
		reqDao.setPlaythingName(plaything.getPlaythingName());
		reqDao.setPrice(Double.valueOf(plaything.getPrice()));
		reqDao.setDescription(plaything.getDescription());
		reqDao.setCreatedDate(Date.valueOf(plaything.getCreatedDate()));
		reqDao.setPlayCount(0);
		reqDao.setRunningCount(0);
		reqDao.setServiceCount(plaything.getServiceCount());
		reqDao.setMaintenanceCount(0);
		reqDao.setPlaythingStatusId(plaything.getPlaythingStatusId());
		reqDao.setPlaythingCategoriesId(plaything.getPlaythingCategoriesId());
		// byte barr[] = (byte[]) request.getSession().getAttribute("multipartfile");
		System.out.println(plaything.getImage());
		reqDao.setB(plaything.getB());

		boolean status;
		try {
			status = playthingRegisterService.doService(reqDao);
			if (!status) {
				model.addAttribute("error", "Data entry failed!");
			} else {
				model.addAttribute("msg", "Plaything has been added!");
			}
		} catch (SQLException e) {
			model.addAttribute("error", "Database error!");
			e.printStackTrace();
		}
		return new ModelAndView("redirect:setupPlaythingAdd", "plaything", plaything);
	}

	// call PMS-PLT-02
	@RequestMapping(value = { "setupPlaythingSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayPlaythingSearch(@ModelAttribute("plaything") PlaythingBean plaything,
			HttpServletRequest request) {
		ServletContext app = request.getServletContext();
		List<PlaythingResponseDao> list = new ArrayList();
		app.setAttribute("playthingList", list);
		return new ModelAndView("PMS-PLT-02", "plaything", new PlaythingBean());
	}

	@RequestMapping(value = { "playthingSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView displayPlaything(@ModelAttribute("plaything") PlaythingBean plaything,
			HttpServletRequest request) {
		ModelAndView model;
		model = new ModelAndView("PMS-PLT-02", "plaything", plaything);
		PlaythingRequestDao reqDao = new PlaythingRequestDao();

		if (plaything != null) {
			if (!plaything.getPlaythingId().equals(""))
				reqDao.setPlaythingId(plaything.getPlaythingId());
			if (!plaything.getPlaythingName().equals(""))
				reqDao.setPlaythingName(plaything.getPlaythingName());
			if (!plaything.getCreatedDate().equals(""))

				reqDao.setCreatedDate(Date.valueOf(plaything.getCreatedDate()));
			if (plaything.getRunningCount() != 0)
				reqDao.setRunningCount(plaything.getRunningCount());
			if (!plaything.getPrice().equals(""))
				reqDao.setPrice(Double.valueOf(plaything.getPrice()));
			reqDao.setPlaythingStatusId(plaything.getPlaythingStatusId());
		}
		try

		{
			List<PlaythingResponseDao> list = playthingSearchService.doService(reqDao);
			System.out.println(list.size() + "size");
			ServletContext app = request.getServletContext();
			app.setAttribute("playthingList", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = { "backPlaythingSearch" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView backPlaythingSearch(@ModelAttribute("plaything") PlaythingBean plaything) {
		if (plaything != null)
			return new ModelAndView("PMS-PLT-02", "plaything", plaything);
		else
			return new ModelAndView("PMS-PLT-02", "plaything", new PlaythingBean());
	}

	// call PMS-PLT-03 page
	@RequestMapping(value = "setupPlaythingUpdate", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView setupPlaythingUpdate(@RequestParam("playthingId") String playthingId,
			@RequestParam("name") String name, @RequestParam("price") String price, @RequestParam("desc") String desc,
			@RequestParam("createddate") String date, @RequestParam("playcount") String playcount,
			@RequestParam("servicecount") String servicecount, @RequestParam("runningcount") String runningcount,
			@RequestParam("mcount") String mcount, @RequestParam("status") String status,
			@RequestParam("categories") String categories, @RequestParam("b") byte[] b) {
		PlaythingBean plaything = new PlaythingBean();
		plaything.setPlaythingId(playthingId);
		plaything.setPlaythingName(name);
		plaything.setPrice(price);
		plaything.setDescription(desc);
		plaything.setCreatedDate(date);
		plaything.setPlayCount(Integer.valueOf(playcount));
		plaything.setServiceCount(Integer.valueOf(servicecount));
		plaything.setRunningCount(Integer.valueOf(runningcount));
		plaything.setMaintenanceCount(Integer.valueOf(mcount));
		plaything.setPlaythingStatusId(status);
		plaything.setPlaythingCategoriesId(categories);
		plaything.setB(b);

		return new ModelAndView("PMS-PLT-03", "playthingDetail", plaything);
	}

	// go plaything update page
	@RequestMapping(value = "confirmPlaythingUpdate", method = RequestMethod.POST)
	public ModelAndView confirmPlaythingUpdate(@ModelAttribute("plaything") @Validated PlaythingBean plaything,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("PMS-PLT-03", "plaything", plaything);
		} else {
			plaything.setPlaythingCategoriesName(
					playthingIdSearchService.getPCName(Integer.valueOf(plaything.getPlaythingCategoriesId())));
			plaything.setPlaythingStatusName(
					playthingIdSearchService.getStatusName(Integer.valueOf(plaything.getPlaythingStatusId())));
			return new ModelAndView("PMS-PLT-93", "plaything", plaything);
		}
	}

	// update ticket type
	@RequestMapping(value = { "playthingUpdate" }, method = RequestMethod.POST)
	public ModelAndView updatePlaything(@ModelAttribute("plaything") PlaythingBean plaything, Model model) {

		PlaythingRequestDao reqDao = new PlaythingRequestDao();
		reqDao.setPlaythingId(plaything.getPlaythingId());
		reqDao.setPlaythingName(plaything.getPlaythingName());
		reqDao.setPrice(Double.valueOf(plaything.getPrice()));
		reqDao.setDescription(plaything.getDescription());
		reqDao.setPlayCount(Integer.valueOf(plaything.getPlayCount()));
		reqDao.setRunningCount(Integer.valueOf(plaything.getRunningCount()));
		reqDao.setServiceCount(plaything.getServiceCount());
		reqDao.setMaintenanceCount(plaything.getMaintenanceCount());
		reqDao.setPlaythingStatusId(plaything.getPlaythingStatusId());
		reqDao.setPlaythingCategoriesId(plaything.getPlaythingCategoriesId());
		// reqDao.setImage(plaything.getImage());

		boolean status = false;
		try {
			status = playthingUpdateService.doService(reqDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!status) {
			model.addAttribute("error", "Data Update failed!");
		} else {
			model.addAttribute("msg", "Plaything  has been Update!");

		}
		return new ModelAndView("redirect:setupPlaythingSearch", "plaything", plaything);
	}

	// delete ticket type
	@RequestMapping(value = "deletePlaything", method = RequestMethod.POST)
	public String deletePlaything(@ModelAttribute("plaything") @Validated PlaythingBean plaything, ModelMap model) {
		PlaythingRequestDao reqDao = new PlaythingRequestDao();

		reqDao.setPlaythingId(plaything.getPlaythingId());

		try {
			boolean status = playthingDeleteService.doService(reqDao);
			if (status) {
				model.addAttribute("msg", "Data delete successful");
			} else {
				model.addAttribute("error", "Data delete Fail");
			}
		} catch (SQLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:setupPlaythingSearch";
	}
}
