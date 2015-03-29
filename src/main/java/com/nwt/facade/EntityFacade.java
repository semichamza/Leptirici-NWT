package com.nwt.facade;

import com.nwt.entities.Project;
import com.nwt.entities.Projects;
import com.nwt.entities.User;
import com.nwt.entities.Users;

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

    public Project searchProjects(String text);
    //endregion
}
