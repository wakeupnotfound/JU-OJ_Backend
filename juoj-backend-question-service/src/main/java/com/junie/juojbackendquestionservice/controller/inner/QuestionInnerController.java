package com.junie.juojbackendquestionservice.controller.inner;

import com.junie.juojbackendmodel.model.entity.Question;
import com.junie.juojbackendmodel.model.entity.QuestionSubmit;
import com.junie.juojbackendquestionservice.service.QuestionService;
import com.junie.juojbackendquestionservice.service.QuestionSubmitService;
import com.junie.juojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: 题目服务内部调用
 * @Author: juheng
 * @Create: 2026-04-24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/inner")
public class QuestionInnerController implements QuestionFeignClient {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @GetMapping("/get/id")
    @Override
    public Question getQuestionById(@RequestParam("questionId") Long questionId) {
        return questionService.getById(questionId);
    }

    @GetMapping("/question_submit/get/id")
    @Override
    public QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") Long questionSubmitId) {
        return questionSubmitService.getById(questionSubmitId);
    }

    @PostMapping("/question_submit/update")
    @Override
    public boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit) {
        return questionSubmitService.updateById(questionSubmit);
    }
}
