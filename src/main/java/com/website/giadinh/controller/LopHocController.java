package com.website.giadinh.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.website.giadinh.entity.LopHoc;
import com.website.giadinh.service.GetListService;
import com.website.giadinh.service.LopHocService;
import com.website.giadinh.validator.LopHocValidator;

@Controller
@RequestMapping(value = "/admin")
public class LopHocController extends PageController<LopHoc> {
	@Autowired
	private LopHocService lopHocService;
	
	@Autowired
	private LopHocValidator lopHocValidator;
	
	@Autowired
	private GetListService getListService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(lopHocValidator);
	}

	@Override
	public void pagedListHolder(HttpServletRequest request, List<LopHoc> list, Integer p) {
		PagedListHolder<LopHoc> pagedListHolder = new PagedListHolder<LopHoc>(list);
		pagedListHolder.setMaxLinkedPages(5);
		request.getSession().setAttribute("pagedListHolder", pagedListHolder);
		if (p == null) {
			pagedListHolder.setPage(0);
		} else {
			pagedListHolder.setPage(p);
		}
	}

	@Override
	public String getReturnPage(Integer p) {
		return "lop-hoc?p=" + p;
	}

	@Override
	public String getReturnPage(String k, Integer p) {
		return "lop-hoc?k=" + k + "&p=" + p;
	}

	@RequestMapping(value = "/lop-hoc", method = RequestMethod.GET)
	public String getLopHocList(HttpServletRequest request, @RequestParam(required = false) Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("pageURL", "lop-hoc");
		if (k == null) {
			List<LopHoc> list = lopHocService.findAll();
			pagedListHolder(request, list, p);
			map.addAttribute("result", lopHocService.countList());
			return "lopHoc";
		}
		List<LopHoc> list = lopHocService.search(k);
		pagedListHolder(request, list, p);
		map.addAttribute("search", true);
		map.addAttribute("k", k);
		map.addAttribute("result", lopHocService.countSearchResult(k));
		return "lopHoc";
	}

	@RequestMapping(value = "/searchAuto-lop-hoc", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchAutoLopHoc(HttpServletRequest request) {
		return lopHocService.searchAuto(request.getParameter("term"));
	}

	@RequestMapping(value = "/add-lop-hoc", method = RequestMethod.GET)
	public String addLopHoc(@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("lopHoc", new LopHoc());
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}
		return "lopHocForm";
	}

	@RequestMapping(value = "/add-lop-hoc", method = RequestMethod.POST)
	public String saveLopHoc(@ModelAttribute("lopHoc") @Valid LopHoc lopHoc, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (lopHocService.isExistKey(lopHoc.getMaLop())) {
			map.addAttribute("existKey", true);
			return "lopHocForm";
		}

		if (result.hasErrors()) {
			return "lopHocForm";
		}

		lopHocService.add(lopHoc);
		map.addAttribute("success", true);
		map.addAttribute("maLop", lopHoc.getMaLop());
		return "lopHocForm";
	}
	
	@RequestMapping(value = { "/edit-lop-hoc", "/delete-lop-hoc" }, method = RequestMethod.GET)
	public String getLopHoc(@RequestParam String maLop, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map, HttpServletRequest request) {
		String servletPath = request.getServletPath().substring(7).toString();
		map.addAttribute("lopHoc", lopHocService.findById(maLop));
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (servletPath.equalsIgnoreCase("edit-lop-hoc")) {
			map.addAttribute("edit", true);
		} else {
//			if (lopHocService.isExistReference(maLop)) {
//				map.addAttribute("announceReference", true);
//				return "lopHocForm";
//			}

			map.addAttribute("remove", true);
			map.addAttribute("announceRemove", true);
		}
		return "lopHocForm";
	}

	@RequestMapping(value = "/edit-lop-hoc", method = RequestMethod.POST)
	public String updateLopHoc(@ModelAttribute("lopHoc") @Valid LopHoc lopHoc, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("edit", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (result.hasErrors()) {
			return "lopHocForm";
		}

		lopHocService.update(lopHoc);
		map.addAttribute("success", true);
		map.addAttribute("maLop", lopHoc.getMaLop());
		return "lopHocForm";
	}

	@RequestMapping(value = "/delete-lop-hoc", method = RequestMethod.POST)
	public String deleteLopHoc(@ModelAttribute("lopHoc") LopHoc lopHoc, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		lopHocService.delete(lopHoc);
		map.addAttribute("success", true);
		map.addAttribute("remove", true);
		map.addAttribute("maLop", lopHoc.getMaLop());
		return "lopHocForm";
	}
	
	@ModelAttribute("nganhHocList")
	protected Map<String, String> getNganhHocList() {
		return getListService.getNganhHocList();
	}
}