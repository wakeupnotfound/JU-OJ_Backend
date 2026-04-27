package com.junie.juojbackendjudgeservice.controller;

import com.junie.juojbackendjudgeservice.judge.JudgeService;
import com.junie.juojbackendmodel.model.entity.QuestionSubmit;
import com.junie.juojbackendserviceclient.service.JudgeFeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 判题服务内部调用
 * @Author: juheng
 * @Create: 2026-04-24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/inner")
public class JudgeInnerController implements JudgeFeignClient {

    @Resource
    private JudgeService judgeService;

    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    @PostMapping("/do")
    @Override
    public QuestionSubmit doJudge(@RequestParam("questionSubmitId") long questionSubmitId) {
        return judgeService.doJudge(questionSubmitId);
    }
}
