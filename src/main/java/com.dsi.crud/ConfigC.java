package com.dsi.crud;

import com.dsi.dao.EmpDaoImp;
import com.dsi.dao.EmployeeDesignationDao;
import com.dsi.dao.EmployeeDesignationDaoImp;
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
    @Bean
    public EmployeeDesignationDaoImp EmpDesImp(){
        return new EmployeeDesignationDaoImp();
    }
}
