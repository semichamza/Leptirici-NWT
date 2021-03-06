package com.nwt.facade;

import com.nwt.auth.entities.VerificationToken;
import com.nwt.auth.entities.VerificationTokens;
import com.nwt.entities.*;

/**
 * Created by glasshark on 29-Mar-15.
 */
public interface EntityFacade
{
    //region USERS
    Users getAllUsers();

    Users getAllDeletedUsers();

    User getUserById(Integer id);

    User getUserByUsername(String username);

    User createUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    Message createMessage(Message message);

    Message updateMessage(Message message);

    public Messages getUserMessage(Integer userId);

    public Messages getUserSentMessage(Integer userId);

    public Messages getUnreadMessages(Integer userId);


    Users searchUsers(String text, boolean deleted);

    ConfigProperties searchConfigs(String text);

    //region PROJECTS
    Projects getAllProjects();

    Projects getUserProjects(int userId);

    Project getProjectById(Integer id);

    Project createProject(Project project);

    Project updateProject(Project project);

    void deleteProject(Project project);

    Projects searchProjects(String text);

    Project getUserProjectById(Integer userId, Integer projectId);
    //endregion

    //region TASKS
    Tasks getAllTasks();

    Tasks getUserTasks(int userId,int projectId);

    Tasks getProjectTasks(Integer project_id);

    Task getTaskById(Integer id);

    Task createTask(Task task);

    Task updateTask(Task task);

    void deleteTask(Task task);

    Tasks searchTasks(String text);

//    Task getUserTaskById(Integer userId, Integer taskId);
    //endregion

    //TOKEN

    VerificationTokens getAllTokens();

    VerificationToken getToken(String id);

    VerificationToken updateToken(VerificationToken token);

    VerificationToken createToken(VerificationToken token);

    void deleteToken(VerificationToken token);

    ConfigProperties getConfigProperties();

    ConfigProperties updateProperties(ConfigProperties properties);

    ConfigProperty getConfigProperty(String id);

    Users getProjectUsers(Project project);

    Users getFreeUsers(Project project);

    void removeUserFromProject(ProjectUser projectUser);
}
