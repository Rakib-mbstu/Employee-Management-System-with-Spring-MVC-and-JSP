package com.dsi.crud;

import com.dsi.dao.EmpDao;
import com.dsi.dao.EmpDaoImp;
import com.dsi.dao.EmployeeDesignationDao;
import com.dsi.entity.DesignationDetails;
import com.dsi.entity.EmployeeDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControllerApp {
    private  EmpDao empDao;
    private EmployeeDesignationDao designationDao;

    public ControllerApp(EmpDao empDao,EmployeeDesignationDao dao) {
        this.empDao = empDao;
        this.designationDao = dao;
    }

    @RequestMapping("/home")
    public String home(Model model)
    {
        List<EmployeeDetails> detailsList = empDao.getAllEmp();
        model.addAttribute("detailsList",detailsList);
        return "/WEB-INF/view/home.jsp";
    }
    @RequestMapping("/Add_Emp")
    public String add_Emp(Model model)
    {
        List<DesignationDetails> list = designationDao.getAllDetails();
        model.addAttribute("desiDetails",list);
        return "/WEB-INF/view/Add_Emp.jsp";
    }
    @RequestMapping("/front_view")
    public String front_page()
    {
        return "index.jsp";
    }
    @RequestMapping(value = "/searchByDept",method = RequestMethod.POST)
    public String byDept(@RequestParam("department") String department, Model model)
    {
        List<EmployeeDetails> detailsList = empDao.getAllEmpByDept(department);
        model.addAttribute("detailsList",detailsList);
        return "/WEB-INF/view/home.jsp";
    }
    @RequestMapping("/Edit_Emp/{id}")
    public String edit_emp(@PathVariable("id") int id,Model model)
    {
        EmployeeDetails emp = empDao.getDetailsById(id);
        model.addAttribute("emp",emp);
        return "/WEB-INF/view/Edit_Emp.jsp";
    }
    @RequestMapping(value = "/updateEmp",method = RequestMethod.POST)
    public String updateEmp(@ModelAttribute EmployeeDetails employeeDetails,HttpSession session)
    {
        empDao.update(employeeDetails);
        session.setAttribute("msg","Success");
        return "redirect:/home";
    }
    @RequestMapping(value = "/registerEmployee",method = RequestMethod.POST)
    public String register(@ModelAttribute("desi") String str,@ModelAttribute EmployeeDetails employeeDetails, HttpSession session)
    {
        System.out.println("bhai ashe" + str);
        System.out.println(employeeDetails.toString());
        empDao.save(employeeDetails);
        session.setAttribute("msg","Registration Successful");
        return "redirect:/Add_Emp";
    }
    @RequestMapping("/deleteEmployee/{id}")
    public String delete(@PathVariable("id") int id,HttpSession session)
    {
        empDao.delete(id);
        session.setAttribute("msg", "deleted");
        return "redirect:/home";
    }

}
