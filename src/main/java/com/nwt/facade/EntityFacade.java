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
    public Users getAllUsers();

    public User getUserById(Integer id);

    public User getUserByUsername(String username);

    public User createUser(User user);

    public User updateUser(User user);

    public void deleteUser(User user);

    public Users searchUsers(String text);
    //endregion

    //region PROJECTS
    public Projects getAllProjects();

    public Project getProjectById(Integer id);

    public Project createProject(Project project);

    public Project updateProject(Project project);

    public void deleteProject(Project project);

    public Projects searchProjects(String text);
    //endregion

    //region TASKS
    public Tasks getAllTasks();

    public Task getTaskById(Integer id);

    public Task createTask(Task task);

    public Task updateTask(Task task);

    public void deleteTask(Task task);

    public Tasks searchTasks(String text);
    //endregion

    //TOKEN

    public VerificationTokens getAllTokens();

    public VerificationToken getToken(String id);

    public VerificationToken updateToken(VerificationToken token);

    public VerificationToken createToken(VerificationToken token);

    public void deleteToken(VerificationToken token);
}
