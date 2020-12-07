package com.github.tangmonkmeat.service;

import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.result.Result;
import com.github.tangmonkmeat.vo.IntergrityAndTimeVO;
import com.github.tangmonkmeat.vo.LoginUserVO;
import com.github.tangmonkmeat.vo.WxUserVO;

import java.util.Map;

/**
 * 用户业务逻辑
 *
 * @author zwl
 * @date 2020/12/3 22:09
 */
public interface UserService {

    /**
     * 用户登录业务
     *
     * @param user {@link User}
     * @param ip 用户ip
     * @return {@link Result},
     * <p>登录成功返回token和状态码信息，否则抛出异常</p>
     */
    Result<Map<String, Object>> login(LoginUserVO user,String ip);

    /**
     * 用户微信登录
     *
     * @param user {@link WxUserVO}
     * @param ip 用户ip
     * @return {@link Result}
     * <p>登录成功状态码信息，否则抛出异常</p>
     */
    Result<Object> wxLogin(WxUserVO user, String ip);

    /**
     * 获取 {@link IntergrityAndTimeVO}
     *          <p>user_integrity</p>
     *          <p>low_integrity</p>
     *          <p>today_time</p>
     *          <p>tom_time</p>
     * @return {@link IntergrityAndTimeVO}
     */
    IntergrityAndTimeVO getIntergrityAndTime();
}
