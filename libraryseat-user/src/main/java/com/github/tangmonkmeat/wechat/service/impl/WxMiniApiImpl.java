package com.github.tangmonkmeat.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.tangmonkmeat.wechat.util.WeChatUtil;
import com.github.tangmonkmeat.wechat.service.WxMiniApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Description:
 * 微信小程序Api接口实现类
 * @author zwl
 * @version 1.0
 * @date 2020/8/30 22:17
 */
@Service
public class WxMiniApiImpl implements WxMiniApi {

    @Value("${wxmini.appId}")
    private String appId;
    @Value("${wxmini.secret}")
    private String secret;

    private static final Logger LOGGER = LoggerFactory.getLogger(WxMiniApiImpl.class);

    /**
     * auth.code2Session
     * https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
     * 请求参数   属性	     类型	   默认值	必填	 说明
     * @param   appId	     string		         是	   小程序 appId
     * @param   secret	     string		         是	   小程序 appSecret
     * @param   jsCode	     string		         是	   登录时获取的 code
     *          grantType	 string		         是	   授权类型，此处只需填写 authorization_code
     * 返回值
     * @return  JSON 数据包
     *           属性	     类型	   说明
     *          openid	     string	  用户唯一标识
     *          session_key	 string	  会话密钥
     *          unionid	     string	  用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
     *          errcode	     number	  错误码
     *          errmsg	     string	  错误信息
     *
     *          errcode 的合法值
     *
     *          值	         说明	                     最低版本
     *          -1	         系统繁忙，此时请开发者稍候再试
     *          0	         请求成功
     *          40029	     code 无效
     *          45011	     频率限制，每个用户每分钟100次
     */
    @Override
    public JSONObject authCode2Session(String jsCode) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String str = WeChatUtil.httpRequest(url, "GET", null);
        LOGGER.info("api/wx-mini/getSessionKey:" + str);
        if ("".equals(str)) {
            return null;
        } else {
            return JSONObject.parseObject(str);
        }
    }
}
