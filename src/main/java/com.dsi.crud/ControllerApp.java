package com.dsi.crud;

import com.dsi.dao.EmpDao;
import com.dsi.dao.EmployeeDesignationDao;
import com.dsi.entity.DesignationDetails;
import com.dsi.entity.EmployeeDetails;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControllerApp {
    private final EmpDao empDao;
    private final EmployeeDesignationDao designationDao;

    public ControllerApp(EmpDao empDao, EmployeeDesignationDao dao) {
        this.empDao = empDao;
        this.designationDao = dao;
    }

    @RequestMapping("/home")
    public String home(Model model) {
        List<EmployeeDetails> detailsList = empDao.getAllEmp();
        model.addAttribute("detailsList", detailsList);
        return "/WEB-INF/view/home.jsp";
    }

    @RequestMapping("/add_Emp")
    public String add_Emp(Model model) {
        List<DesignationDetails> list = designationDao.getAllDetails();
        model.addAttribute("desiDetails", list);
        return "/WEB-INF/view/Add_Emp.jsp";
    }

    @RequestMapping("/front_view")
    public String front_page()
    {
        return "index.jsp";
    }

    @RequestMapping(value = "/searchByDept", method = RequestMethod.POST)
    public String byDept(@RequestParam("department") String department, Model model) {
        List<EmployeeDetails> detailsList = empDao.getAllEmpByDept(department);
        model.addAttribute("detailsList", detailsList);
        return "/WEB-INF/view/home.jsp";
    }

    @RequestMapping("/Edit_Emp/{id}")
    public String edit_emp(@PathVariable("id") int id, Model model) {
        EmployeeDetails emp = empDao.getDetailsById(id);
        model.addAttribute("emp", emp);
        List<DesignationDetails> list = designationDao.getAllDetails();
        model.addAttribute("designationLists",list);
        return "/WEB-INF/view/Edit_Emp.jsp";
    }

    @RequestMapping(value = "/updateEmp", method = RequestMethod.POST)
    public String updateEmp(@RequestParam("d_Id") int id,@ModelAttribute EmployeeDetails employeeDetails, HttpSession session) {
        employeeDetails.setDesignationDetails(designationDao.getDesignationById(id));
        empDao.update(employeeDetails);
        session.setAttribute("msg", "Success");
        return "redirect:/home";
    }

    @RequestMapping(value = "/registerEmployee", method = RequestMethod.POST)
    public String register(@RequestParam("d_Id") int id,@ModelAttribute EmployeeDetails employeeDetails, HttpSession session) {
        System.out.println(id);
        employeeDetails.setDesignationDetails(designationDao.getDesignationById(id));
        empDao.save(employeeDetails);
        session.setAttribute("msg", "Registration Successful");
        return "redirect:/add_Emp";
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String delete(@PathVariable("id") int id, HttpSession session) {
        empDao.delete(id);
        session.setAttribute("msg", "deleted");
        return "redirect:/home";
    }

}
