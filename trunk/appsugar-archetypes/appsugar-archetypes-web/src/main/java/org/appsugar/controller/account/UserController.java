package org.appsugar.controller.account;

import java.util.List;

import org.appsugar.condition.account.UserCondition;
import org.appsugar.controller.account.dto.UserDto;
import org.appsugar.repository.extend.PageAdapter;
import org.appsugar.service.account.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户controller
 * @author NewYoung
 * 2016年2月29日上午11:32:33
 */
@Controller
@RequestMapping("/account/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping({ "list", "" })
	public String list(Model model, UserCondition condition, PageAdapter adapter) {
		logger.debug("list all user");
		model.addAttribute("page", userService.getByCondition(condition, adapter.toPageable()));
		model.addAttribute("condition", condition);
		return "/account/user/list";
	}

	@RequestMapping("listUser")
	@ResponseBody
	public List<UserDto> listUser() {
		return UserDto.transfer(userService.getAll());
	}

}
