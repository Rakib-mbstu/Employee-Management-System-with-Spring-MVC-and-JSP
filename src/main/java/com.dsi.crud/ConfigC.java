package com.dsi.crud;

import com.dsi.dao.EmpDaoImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

@Controller
@ComponentScan({"com.dsi.crud"})
public class ConfigC {
    @Bean
    public EmpDaoImp EmpBean()
    {
        EmpDaoImp imp = new EmpDaoImp();
        return imp;
    }
}
