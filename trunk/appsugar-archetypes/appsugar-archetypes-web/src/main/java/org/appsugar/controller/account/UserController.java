package org.appsugar.controller.account;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.appsugar.condition.account.UserCondition;
import org.appsugar.controller.account.dto.UserDto;
import org.appsugar.entity.account.User;
import org.appsugar.repository.extend.PageAdapter;
import org.appsugar.service.account.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequiresPermissions("user:view")
	@RequestMapping({ "list", "" })
	public String list(Model model, UserCondition condition, PageAdapter adapter) {
		model.addAttribute("page", userService.getByCondition(condition, adapter.toPageable()));
		model.addAttribute("condition", condition);
		return "/account/user/list";
	}

	@RequiresPermissions("user:view")
	@RequestMapping("listUser")
	@ResponseBody
	public List<UserDto> listUser() {
		return UserDto.transfer(userService.getAll());
	}

	@RequiresPermissions("user:view")
	@RequestMapping({ "create", "update" })
	public String form(@ModelAttribute User user, Model model) {
		logger.debug("user is {}", user);
		model.addAttribute("user", user);
		return "/account/user/form";
	}

	@ModelAttribute
	public User modelAttribute(Long id) {
		if (id == null) {
			return new User();
		}
		User user = userService.get(id);
		return user == null ? new User() : user;
	}

}
