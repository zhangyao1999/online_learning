package com.zy.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.commonutils.JwtUtils;
import com.zy.commonutils.R;
import com.zy.eduservice.client.UcenterClient;
import com.zy.eduservice.entity.EduComment;
import com.zy.eduservice.service.EduCommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author zy
 * @since 2022-04-30
 */
@RestController
@RequestMapping("/eduservice/comment")
@CrossOrigin
public class EduCommentController {
    @Autowired
    private UcenterClient ucenterClient;
    @Autowired
    private EduCommentService eduCommentService;
    @PostMapping("/addComment")
    public R addComment(HttpServletRequest httpServletRequest, @RequestBody EduComment comment){
        String memberId = JwtUtils.getMemberIdByJwtToken(httpServletRequest);
        if(StringUtils.isEmpty(memberId)) {
            return R.error().message("请登录");
        }
        Map<String, String> info = ucenterClient.getInfo(memberId);
        String nickname = info.get("nickname");
        String avatar = info.get("avatar");
        comment.setMemberId(memberId);
        comment.setNickname(nickname);
        comment.setAvatar(avatar);
        eduCommentService.save(comment);
        return R.ok();
    }

    //根据课程id查询评论列表
    @ApiOperation(value = "评论分页列表")
    @GetMapping("getComment")
    public R getComment(@RequestParam  String courseId) {

        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduComment> list = eduCommentService.list(wrapper);
        return R.ok().data("list",list);
    }

}

