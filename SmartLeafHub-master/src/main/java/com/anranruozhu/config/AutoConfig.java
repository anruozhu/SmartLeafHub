package com.anranruozhu.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author anranruozhu
 * @ClassName AutoConfig
 * @Description 自动化配置
 * @create 2024/4/15 上午10:44
 **/
@Component
@ConfigurationProperties(prefix ="autoconfig")
@Data
public class AutoConfig
{
    private boolean FenAuto=false;
    private boolean PumpAuto=false;
    private boolean LightAuto=false;
}
