package com.dsi.dao;

import com.dsi.entity.DesignationDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeeDesignationDaoImp implements EmployeeDesignationDao{
    EntityManager entityManager;
    public void init()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa");
        entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
    }
    @Override
    public void save(DesignationDetails designation) {
        entityManager.persist(designation);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<DesignationDetails> getAllDetails() {
        List<DesignationDetails> list = entityManager.createQuery("from DesignationDetails").getResultList();
        entityManager.getTransaction().commit();
        return list;
    }

    @Override
    public List<DesignationDetails> getDetailsByDesignation(String designationName) {
        List<DesignationDetails> list = entityManager
                .createQuery("from DesignationDetails where=?1")
                .setParameter(1,designationName)
                .getResultList();
        entityManager.getTransaction().commit();
        return list;
    }

    @Override
    public void editDesignation(DesignationDetails designationDetails) {
        entityManager.merge(designationDetails);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteDesignation(String designationName) {
        entityManager.remove(entityManager.find(DesignationDetails.class,designationName));
        entityManager.getTransaction().commit();
    }
}
