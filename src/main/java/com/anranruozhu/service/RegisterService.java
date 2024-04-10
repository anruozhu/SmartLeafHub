package com.anranruozhu.service;

import com.anranruozhu.common.Result;
import com.anranruozhu.mapper.UserMapper;
import com.anranruozhu.utils.MD5;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anranruozhu
 * @ClassName RegisterService
 * @Description 注册服务类
 * @create 2024/4/7 10:29
 **/

public interface RegisterService {
    Result register(String phone, String password);
}
