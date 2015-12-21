package com.nwt.facade;

import com.nwt.auth.entities.VerificationToken;
import com.nwt.auth.entities.VerificationTokens;
import com.nwt.entities.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by glasshark on 29-Mar-15.
 */
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
        query.setParameter("deleted",false);
        return new Users(query.getResultList());
    }

    @Override
    public Users getAllDeletedUsers() {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_ALL, User.class);
        query.setParameter("deleted",true);
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
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_USERNAME, User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
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
    public Message createMessage(Message message) {
        em.persist(message);
        assertNotNull(message.getId());
        return message;
    }

    @Override
    public Message updateMessage(Message message) {
        em.merge(message);
        assertNotNull(message.getId());
        return message;
    }

    @Override
    public Messages getUserMessage(Integer userId) {
        TypedQuery<Message> query = em.createNamedQuery(Message.USER_MESSAGES, Message.class);
        query.setParameter(Message.USER_MESSAGE_RECEIVER_PARAM, userId);
        return new Messages(query.getResultList());
    }

    @Override
    public Messages getUserSentMessage(Integer userId) {
        TypedQuery<Message> query = em.createNamedQuery(Message.USER_SENT_MESSAGES, Message.class);
        query.setParameter(Message.USER_MESSAGE_SENDER_PARAM, userId);
        return new Messages(query.getResultList());
    }

    @Override
    public Messages getUnreadMessages(Integer userId) {
        TypedQuery<Message> query = em.createNamedQuery(Message.USER_MESSAGES_UNREAD, Message.class);
        query.setParameter(Message.USER_MESSAGE_RECEIVER_PARAM, userId);
        return new Messages(query.getResultList());
    }


    @Override
    public Users searchUsers(String text,boolean deleted)
    {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_TEXT, User.class);
        query.setParameter("text","%"+text+"%");
        query.setParameter("deleted",deleted);
        //CONCAT(u.firstName,' ' , u.lastName ,' ', u.userPrincipal.username)
        return new Users(query.getResultList());
    }

    @Override
    public ConfigProperties searchConfigs(String text)
    {
        TypedQuery<ConfigProperty> query = em.createNamedQuery(ConfigProperty.FIND_BY_TEXT, ConfigProperty.class);
        query.setParameter("text","%"+text+"%");
        //CONCAT(u.firstName,' ' , u.lastName ,' ', u.userPrincipal.username)
        return new ConfigProperties(query.getResultList());
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
        TypedQuery<Project> query = em.createNamedQuery(Project.FIND_BY_TEXT, Project.class);
        query.setParameter("text","%"+text+"%");
        return new Projects(query.getResultList());
    }

    @Override
    public Project getUserProjectById(Integer userId, Integer projectId)
    {
        ProjectUserId projectUserId = new ProjectUserId(projectId, userId);
        ProjectUser projectUser = em.find(ProjectUser.class, projectUserId);
        Project project = null;
        if (projectUser != null)
        {
            project = projectUser.getProject();
        }
        return project;
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
    public Tasks getProjectTasks(Integer project_id) {
        TypedQuery<Task> query = em.createNamedQuery(Task.PROJECT_TASKS, Task.class);
        query.setParameter("project_id", project_id);
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

//    @Override
//    public Task getUserTaskById(Integer userId, Integer taskId)
//    {
//        TypedQuery<Task> query = em.createNamedQuery(Task.FIND_USER_TASK_BY_ID, Task.class);
//        query.setParameter("id", taskId);
//        return query.getSingleResult();
//    }


    @Override
    public VerificationTokens getAllTokens() {
        TypedQuery<VerificationToken> query = em.createNamedQuery(VerificationToken.FIND_ALL, VerificationToken.class);
        return new VerificationTokens(query.getResultList());
    }

    @Override
    public VerificationToken getToken(String id) {
        VerificationToken token = em.find(VerificationToken.class, id);
        return token;
    }

    @Override
    public VerificationToken updateToken(VerificationToken token) {
        em.merge(token);
        assertNotNull(token.getId());
        return token;
    }

    @Override
    public VerificationToken createToken(VerificationToken token) {
        em.persist(token);
        assertNotNull(token.getId());
        return token;
    }

    @Override
    public void deleteToken(VerificationToken token) {
        em.remove(token);
    }

    @Override
    public ConfigProperties getConfigProperties() {
        TypedQuery<ConfigProperty> query = em.createNamedQuery(ConfigProperty.FIND_ALL, ConfigProperty.class);
        return new ConfigProperties(query.getResultList());
    }

    @Override
    public ConfigProperties updateProperties(ConfigProperties properties) {
       for(ConfigProperty property:properties)
       {
           em.merge(property);
           assertNotNull(property.getId());
       }

        return properties;
    }

    @Override
    public ConfigProperty getConfigProperty(String id)
    {
        return em.find(ConfigProperty.class,id);
    }


    @Override
    public Users getProjectUsers(Project project)
    {
        TypedQuery<User> query = em.createNamedQuery(User.FIND_BY_PROJECT_ID, User.class);
        query.setParameter("projectId",project.getId());
        return new Users(query.getResultList());
    }

    @Override
    public Users getFreeUsers(Project project) {
        TypedQuery<User> query = em.createNamedQuery(User.UNLONKED_USERS, User.class);
        query.setParameter("projectId",project.getId());
        return new Users(query.getResultList());
    }

    @Override
    public void removeUserFromProject(ProjectUser projectUser)
    {
        Query query = em.createNamedQuery(User.DELETE_FROM_PROJECT);
        query.setParameter("projectId",projectUser.getProjectId());
        query.setParameter("userId",projectUser.getUserId());
        query.executeUpdate();
    }

}
