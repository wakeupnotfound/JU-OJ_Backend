package com.junie.juojbackenduserservice.controller.inner;

import com.junie.juojbackendmodel.model.entity.User;
import com.junie.juojbackendserviceclient.service.UserFeignClient;
import com.junie.juojbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 用户服务内部调用
 * @Author: juheng
 * @Create: 2026-04-24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {

    @Resource
    private UserService userService;

    /**
     * 根据 id 获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/get/id")
    @Override
    public User getById(@RequestParam("userId") Long userId) {
        return userService.getById(userId);
    }

    /**
     * 根据 id 获取用户列表
     * @param idList
     * @return
     */
    @GetMapping("/get/ids")
    @Override
    public List<User> listByIds(@RequestParam("idList") Collection<Long> idList) {
        return userService.listByIds(idList);
    }
}
