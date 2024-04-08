package com.anranruozhu.service.impl;



import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.anranruozhu.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Map;

/**
 * @author anranruozhu
 * @ClassName SmsServiceImpl
 * @Description 短信服务实现类
 * @create 2024/4/8 上午9:56
 **/
@Service
public class SmsServiceImpl implements SmsService {
        /**
         * 发送验证码
         * @param param     验证码
         * @param phone     手机号
         * @return
         */
        @Override
        public boolean send(Map<String, Object> param, String phone) {
            if(StringUtils.isEmpty(phone)) return false;

            //default 地域节点，默认就好  后面是 阿里云的 id和秘钥（这里记得去阿里云复制自己的id和秘钥哦）
            DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI5t7v9FU86XVJ5R7t9dv4", "ChRP42FkZ6r7REWMCHzC6Z5Fp6cdYt");
            IAcsClient client = new DefaultAcsClient(profile);

            //这里不能修改
            CommonRequest request = new CommonRequest();
            //request.setProtocol(ProtocolType.HTTPS);
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");

            request.putQueryParameter("PhoneNumbers", phone);   //手机号
            request.putQueryParameter("SignName", "中软国际第24组实训项目");    //申请阿里云 签名名称（暂时用阿里云测试的，自己还不能注册签名）
            request.putQueryParameter("TemplateCode", "SMS_465433541"); //申请阿里云 模板code（用的也是阿里云测试的）
            request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
                return response.getHttpResponse().isSuccess();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    }


