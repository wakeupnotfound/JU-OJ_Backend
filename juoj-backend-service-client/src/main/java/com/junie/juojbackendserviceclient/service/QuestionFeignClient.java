package com.junie.juojbackendserviceclient.service;

import com.junie.juojbackendmodel.model.entity.Question;
import com.junie.juojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author juheng
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2026-04-13 14:45:00
*/
@FeignClient(name = "juoj-backend-question-service", path = "/api/question/inner")
public interface QuestionFeignClient {

    /**
     * 根据 id 获取题目信息
     * @param questionId
     * @return
     */
    @GetMapping("/get/id")
    Question getQuestionById(@RequestParam("questionId") Long questionId);

    /**
     * 根据 id 获取题目提交信息
     * @param questionSubmitId
     * @return
     */
    @GetMapping("/question_submit/get/id")
    QuestionSubmit getQuestionSubmitById(@RequestParam("questionSubmitId") Long questionSubmitId);

    /**
     * 根据 id 更新题目提交信息
     * @param questionSubmit
     * @return
     */
    @PostMapping("/question_submit/update")
    boolean updateQuestionSubmitById(@RequestBody QuestionSubmit questionSubmit);
}
