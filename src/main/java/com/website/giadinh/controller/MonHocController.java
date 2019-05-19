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

import com.website.giadinh.entity.MonHoc;
import com.website.giadinh.service.GetListService;
import com.website.giadinh.service.MonHocService;
import com.website.giadinh.validator.MonHocValidator;

@Controller
@RequestMapping(value = "/admin")
public class MonHocController extends PageController<MonHoc> {
	@Autowired
	private MonHocService monHocService;

	@Autowired
	private GetListService getListService;

	@Autowired
	private MonHocValidator monHocValidator;

	@InitBinder
	protected void InitBinder(WebDataBinder binder) {
		binder.addValidators(monHocValidator);
	}

	@Override
	public void pagedListHolder(HttpServletRequest request, List<MonHoc> list, Integer p) {
		PagedListHolder<MonHoc> pagedListHolder = new PagedListHolder<MonHoc>(list);
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
		return "mon-hoc?p=" + p;
	}

	@Override
	public String getReturnPage(String k, Integer p) {
		return "mon-hoc?k=" + k + "&p=" + p;
	}

	@RequestMapping(value = "/mon-hoc", method = RequestMethod.GET)
	public String getMonHocList(HttpServletRequest request, @RequestParam(required = false) Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("pageURL", "mon-hoc");
		if (k == null) {
			List<MonHoc> list = monHocService.findAll();
			pagedListHolder(request, list, p);
			map.addAttribute("result", monHocService.countList());
			return "monHoc";
		}
		List<MonHoc> list = monHocService.search(k);
		pagedListHolder(request, list, p);
		map.addAttribute("search", true);
		map.addAttribute("k", k);
		map.addAttribute("result", monHocService.countSearchResult(k));
		return "monHoc";
	}

	@RequestMapping(value = "/searchAuto-mon-hoc", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchAutoMonHoc(HttpServletRequest request) {
		return monHocService.searchAuto(request.getParameter("term"));
	}

	@RequestMapping(value = "/add-mon-hoc", method = RequestMethod.GET)
	public String addMonHoc(@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("monHoc", new MonHoc());
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}
		return "monHocForm";
	}

	@RequestMapping(value = "/add-mon-hoc", method = RequestMethod.POST)
	public String saveMonHoc(@ModelAttribute("monHoc") @Valid MonHoc monHoc, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("add", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (result.hasErrors()) {
			return "monHocForm";
		}

		monHocService.add(monHoc);
		map.addAttribute("success", true);
		map.addAttribute("tenMH", monHoc.getTenMH());
		return "monHocForm";
	}

	@RequestMapping(value = { "/edit-mon-hoc", "/delete-mon-hoc" }, method = RequestMethod.GET)
	public String getMonHoc(@RequestParam String maMH, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map, HttpServletRequest request) {
		String servletPath = request.getServletPath().substring(7).toString();
		map.addAttribute("monHoc", monHocService.findById(maMH));
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (servletPath.equalsIgnoreCase("edit-mon-hoc")) {
			map.addAttribute("edit", true);
			map.addAttribute("mode", "edit");
		} else {
//			if (monHocService.isExistReference(maMH)) {
//				map.addAttribute("announceReference", true);
//				return "monHocForm";
//			}

			map.addAttribute("remove", true);
			map.addAttribute("announceRemove", true);
		}
		return "monHocForm";
	}

	@RequestMapping(value = "/edit-mon-hoc", method = RequestMethod.POST)
	public String updateMonHoc(@ModelAttribute("monHoc") @Valid MonHoc monHoc, BindingResult result,
			@RequestParam Integer p, @RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("edit", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		if (result.hasErrors()) {
			return "monHocForm";
		}

		monHocService.update(monHoc);
		map.addAttribute("success", true);
		map.addAttribute("tenMH", monHoc.getTenMH());
		return "monHocForm";
	}

	@RequestMapping(value = "/delete-mon-hoc", method = RequestMethod.POST)
	public String deleteMonHoc(@ModelAttribute("monHoc") MonHoc monHoc, @RequestParam Integer p,
			@RequestParam(required = false) String k, ModelMap map) {
		map.addAttribute("remove", true);
		map.addAttribute("pageURL", getReturnPage(p));
		if (k != null) {
			map.addAttribute("pageURL", getReturnPage(k, p));
		}

		monHocService.delete(monHoc);
		map.addAttribute("success", true);
		map.addAttribute("tenMH", monHoc.getTenMH());
		return "monHocForm";
	}

	@ModelAttribute("soTinChiList")
	private Map<Integer, String> getSoTinChiList() {
		return getListService.getSoTinChiList();
	}

	@ModelAttribute("soTietHocList")
	private Map<Integer, String> getSoTietHocList() {
		return getListService.getSoTietHocList();
	}
}