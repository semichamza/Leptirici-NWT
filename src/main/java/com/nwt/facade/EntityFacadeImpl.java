package com.nwt.facade;

import com.nwt.entities.*;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by glasshark on 29-Mar-15.
 */
@Named ("entityFacade")
@Stateless
public class EntityFacadeImpl implements EntityFacade
{
    @PersistenceContext (unitName = "NWTPersistenceUnit")
    private EntityManager em;

    /**
     * USERS
     */
    @Override
    public Users getAllUsers()
    {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_ALL, User.class);
        return new Users(query.getResultList());
    }

    @Override
    public User getUserById(Integer id)
    {
        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByUsername(String username)
    {
        //TODO - pretraga sa fiksiranim queryjem!
        User user = em.find(User.class, username);
        return user;
    }

    @Override
    public User createUser(User user)
    {
        em.persist(user);
        assertNotNull(user.getId());
        return user;
    }

    @Override
    public User updateUser(User user)
    {
        em.merge(user);
        assertNotNull(user.getId());
        return user;
    }

    @Override
    public void deleteUser(User user)
    {
        em.remove(user);
    }

    @Override
    public Users searchUsers(String text)
    {
        //Query query = em.createNamedQuery("findWithParam").setParameter("username", "admin");//.setMaxResults(3);
        //TODO: Implement!
        return null;
    }

    /**
     * PROJECTS
     */
    @Override
    public Projects getAllProjects()
    {
        TypedQuery<Project> query = em.createNamedQuery(Project.FIND_ALL, Project.class);
        return new Projects(query.getResultList());
    }

    @Override
    public Project getProjectById(Integer id)
    {
        Project project = em.find(Project.class, id);
        return project;
    }

    @Override
    public Project createProject(Project project)
    {
        em.persist(project);
        assertNotNull(project.getId());
        return project;
    }

    @Override
    public Project updateProject(Project project)
    {
        em.merge(project);
        assertNotNull(project.getId());
        return project;
    }

    @Override
    public void deleteProject(Project project)
    {
        em.remove(project);
    }

    @Override
    public Projects searchProjects(String text) {
        //TODO: Implement!
        return null;
    }

    /**
     * TASKS
     */
    @Override
    public Tasks getAllTasks() {
        TypedQuery<Task> query = em.createNamedQuery(Task.FIND_ALL, Task.class);
        return new Tasks(query.getResultList());
    }

    @Override
    public Task getTaskById(Integer id) {
        Task task = em.find(Task.class, id);
        return task;
    }

    @Override
    public Task createTask(Task task) {
        em.persist(task);
        assertNotNull(task.getId());
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        em.merge(task);
        assertNotNull(task.getId());
        return task;
    }

    @Override
    public void deleteTask(Task task) {
        em.remove(task);
    }

    @Override
    public Tasks searchTasks(String text)
    {
        //TODO: Implement!
        return null;
    }
}