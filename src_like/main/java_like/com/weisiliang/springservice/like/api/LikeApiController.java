package com.weisiliang.springservice.like.api;


import com.weisiliang.springservice.api.response.base.ApiStatusResponse;
import com.weisiliang.springservice.api.response.exception.ExceptionResponse;


import com.weisiliang.springservice.core.userservice.UserService;
import com.weisiliang.springservice.exception.*;
import com.weisiliang.springservice.like.config.LikeConfig;
import com.weisiliang.springservice.like.entity.Like;
import com.weisiliang.springservice.like.resp.GetLikePageResponse;
import com.weisiliang.springservice.like.service.LikeService;

import com.weisiliang.springservice.like.service.LikeUserService;
import com.weisiliang.springservice.like.style.LikeParamStyle;
import com.weisiliang.springservice.like.style.LikeTargetStyle;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@EnableConfigurationProperties({LikeConfig.class})
@Controller
//@EnableAutoConfiguration
@RequestMapping("/api/like")
@Api(value="用户",description = "用户相关接口")
public class LikeApiController {




    @Autowired
    LikeService likeService;

    @Autowired
    LikeUserService likeUserService;

    @Autowired
    HttpServletRequest request;


    @Autowired
    LikeConfig likeConfig;
//    private static Logger logger = Logger.getLogger(LikeApiController.class);
    @RequestMapping(value = "/get_like_page",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取单个用户",notes = "根据用户id 获取用户详情")
    @ApiResponses({@ApiResponse(code=1,message="成功",response = com.weisiliang.springservice.api.response.base.ApiResponse.class)
                ,@ApiResponse(code=201,message="没有找到这个用户",response = ExceptionResponse.class)})
    GetLikePageResponse getUser(
//            @ApiParam(value = "targetStyle")@RequestParam LikeTargetStyle targetStyle,
            @ApiParam(value = "targetType")@RequestParam Integer targetType,
            @ApiParam(value = "targetId")@RequestParam String targetId,
            @ApiParam(value = "pageNo")@RequestParam Integer pageNo,
            @ApiParam(value = "pageSize")@RequestParam Integer pageSize,
            @ApiParam(value = "likeParam")@RequestParam(defaultValue = "") Set<LikeParamStyle> likeParam
    )  throws  ApiNotTokenException, ApiNotFoundException {

//        LikeTargetStyle[] t=likeConfig.getTargetStyles();
        Page<Like> page= likeService.findAllByTargetId(targetType,targetId, PageRequest.of(pageNo,pageSize,new Sort(Sort.Direction.DESC,"createTime")),likeParam);

        GetLikePageResponse response=new GetLikePageResponse();
        response.setLikeList(page.getContent());
        response.setJpaPage(page);

        return response;
    }


    @RequestMapping(value = "/add_like",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "点赞",notes = "点赞")
    @ApiResponses({@ApiResponse(code=1,message="成功",response = com.weisiliang.springservice.api.response.base.ApiResponse.class)
            ,@ApiResponse(code=201,message="没有找到这个用户",response = ExceptionResponse.class)})
    ApiStatusResponse addLike(
            @ApiParam(value = "targetType")@RequestParam Integer targetType,
            @ApiParam(value = "targetId")@RequestParam String targetId
    )  throws  ApiNotTokenException, ApiNotFoundException {


        String userId=likeUserService.getCurrentUserId();

        if(userId.equals("")||userId.equals("0")){
            return null;
        }
        likeService.addLike(userId,targetType,targetId);
        ApiStatusResponse response=new ApiStatusResponse();
        return response;
    }

    @RequestMapping(value = "/delete_like",method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation(value = "取消点赞",notes = "取消点赞")
    @ApiResponses({@ApiResponse(code=1,message="成功",response = com.weisiliang.springservice.api.response.base.ApiResponse.class)
            ,@ApiResponse(code=201,message="没有找到这个用户",response = ExceptionResponse.class)})
    ApiStatusResponse deleteLike(
            @ApiParam(value = "targetStyle")@RequestParam Integer targetType,
            @ApiParam(value = "targetId")@RequestParam String targetId
    )  throws  ApiNotTokenException, ApiNotFoundException {


        String userId=likeUserService.getCurrentUserId();

        if(userId.equals("")||userId.equals("0")){
            return null;
        }
        likeService.delete(targetType,targetId,userId);
        ApiStatusResponse response=new ApiStatusResponse();
        return response;
    }

}
