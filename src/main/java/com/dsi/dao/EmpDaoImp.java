package com.dsi.dao;

import com.dsi.entity.EmployeeDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmpDaoImp implements EmpDao{

    EntityManager entityManager;

    public void init()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa");
        this.entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
    }
    @Override
    public void save(EmployeeDetails details) {
        init();
        entityManager.persist(details);
        entityManager.getTransaction().commit();
    }

    @Override
    public EmployeeDetails getDetailsById(int id) {
        init();
        EmployeeDetails employeeDetails = entityManager.find(EmployeeDetails.class,id);
        entityManager.getTransaction().commit();
        return employeeDetails;
    }

    @Override
    public List<EmployeeDetails> getAllEmp() {
        init();
        List<EmployeeDetails> detailsList = entityManager.createQuery("from EmployeeDetails").getResultList();
        entityManager.getTransaction().commit();
        return detailsList;
    }

    @Override
    public void update(EmployeeDetails details) {
        init();
        entityManager.merge(details);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        init();
        entityManager.remove(entityManager.find(EmployeeDetails.class,id));
        entityManager.getTransaction().commit();

    }
}
