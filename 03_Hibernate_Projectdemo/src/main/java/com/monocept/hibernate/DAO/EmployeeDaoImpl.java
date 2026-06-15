package com.monocept.hibernate.DAO;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.monocept.hibernate.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private EntityManager entityManager;

    // INSERT
    @Override
    public Employee saveEmployee(Employee employee) {

        entityManager.persist(employee);

        return employee;
    }

    // READ ALL
    @Override
    public List<Employee> getAllEmployees() {

        String query = "from Employee";

        TypedQuery<Employee> q =
                entityManager.createQuery(query, Employee.class);

        return q.getResultList();
    }

    // READ BY ID
    @Override
    public Employee getEmployeeById(int id) {

        return entityManager.find(Employee.class, id);
    }

    // READ BY NAME
    @Override
    public List<Employee> getEmployeeByName(String name) {

        String query =
                "from Employee where empName=:name";

        TypedQuery<Employee> q =
                entityManager.createQuery(query, Employee.class);

        q.setParameter("name", name);

        return q.getResultList();
    }

    // READ BY AGE
    @Override
    public List<Employee> getEmployeeByAge(int age) {

        String query =
                "from Employee where empAge=:age";

        TypedQuery<Employee> q =
                entityManager.createQuery(query, Employee.class);

        q.setParameter("age", age);

        return q.getResultList();
    }

    // READ BY CITY CODE
    @Override
    public List<Employee> getEmployeeByCityCode(String cityCode) {

        String query =
                "from Employee where cityCode=:code";

        TypedQuery<Employee> q =
                entityManager.createQuery(query, Employee.class);

        q.setParameter("code", cityCode);

        return q.getResultList();
    }

    // SALARY > 10000
    @Override
    public List<Employee> getEmployeeSalaryGreaterThan(double salary) {

        String query =
                "from Employee where empSalary > :salary";

        TypedQuery<Employee> q =
                entityManager.createQuery(query, Employee.class);

        q.setParameter("salary", salary);

        return q.getResultList();
    }

    // UPDATE
    @Override
    public Employee updateEmployee(Employee employee) {

        return entityManager.merge(employee);
    }

    // DELETE
    @Override
    public void deleteEmployee(int id) {

        Employee employee =
                entityManager.find(Employee.class, id);

        entityManager.remove(employee);
    }
}