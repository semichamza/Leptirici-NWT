package com.nwt.rest;

import com.nwt.entities.User;
import com.nwt.entities.UserPrincipal;
import com.nwt.enums.UserRoleEnum;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.*;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.RuntimeDelegate;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by glasshark on 26-Apr-15.
 */
public class UserRestServiceIT extends JerseyTest
{
    private static final String PATH = "http://localhost:18686/PMS-NWT/rest/users/";
    private static final String TESTING_USER_1 = "testingUser1";
    private static final String TESTING_USER_2 = "testingUser2";
    private static final String TESTING_USER_3 = "testingUser3";
    private static HttpServer server;
    private static URI uri = UriBuilder.fromUri("http://localhost/").port(8383).build();
    private static Client client = ClientBuilder.newClient();
    private static WebTarget target = client.target(PATH);
    private User user1 = new User(new UserPrincipal(TESTING_USER_1, "test", UserRoleEnum.NORMAL),
            "user1", "testing", true, "user1@testing.com");
    private User user2 = new User(new UserPrincipal(TESTING_USER_2, "test", UserRoleEnum.NORMAL),
            "user2", "testing", true, "user2@testing.com");
    private User user3 = new User(new UserPrincipal(TESTING_USER_3, "test", UserRoleEnum.NORMAL),
            "user3", "testing", true, "user3@testing.com");

    @BeforeClass
    public static void init() throws Exception
    {
        // create a new server listening at default port
        server = HttpServer.create(new InetSocketAddress(uri.getPort()), 0);
        // create a handler wrapping the JAX-RS application
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new TestApplication(), HttpHandler.class);
        // map JAX-RS handler to the server root
        server.createContext(uri.getPath(), handler);
        // start the server
        server.start();
    }

    @AfterClass
    public static void stop() throws Exception
    {
        server.stop(0);
    }

    private static User findUser(Set<User> users, String username)
    {
        for (User user : users)
        {
            if (user.getUserPrincipal().getUsername().equals(username))
                return user;
        }

        return null;
    }

    @Override
    public Application configure()
    {
        return new TestApplication();
    }

    @Override
    @Before
    public void setUp() throws Exception
    {
        target.request().post(Entity.entity(user1, MediaType.APPLICATION_JSON));
        target.request().post(Entity.entity(user2, MediaType.APPLICATION_JSON));

        flush();
    }

    private void flush()
    {
        Response response = client.target("http://localhost:18686/PMS-NWT/rest/tests/flush/").request().get();
        assertEquals(200, response.getStatus());//200-OK
    }

    @Override
    @After
    public void tearDown() throws Exception
    {
        User user1 = target.path(TESTING_USER_1).request().get(User.class);
        target.path(user1.getId().toString()).request().delete();

        User user2 = target.path(TESTING_USER_2).request().get(User.class);
        target.path(user2.getId().toString()).request().delete();
        flush();
    }

    @Test
    public void testGetAllUsers() throws Exception
    {
        Response response = target.request().get();
        List<User> persistedUsers = response.readEntity(new GenericType<List<User>>()
        {
        });
        assertNotNull(findUser(new HashSet<User>(persistedUsers), TESTING_USER_1));
        assertNotNull(findUser(new HashSet<User>(persistedUsers), TESTING_USER_2));
    }

    @Test
    public void testGetUserById() throws Exception
    {
        //Id generated automatically
        User user = target.path(TESTING_USER_1).request().get(User.class);
        Response response = target.path(user.getId().toString()).request().get();
        assertEquals(200, response.getStatus());//200-OK

        testUserResponse(user1, response);
    }

    @Test
    public void testGetUserByUsername() throws Exception
    {
        Response response = target.path(TESTING_USER_1).request().get();
        assertEquals(200, response.getStatus());//200-OK

        testUserResponse(user1, response);
    }

    private void testUserResponse(User user, Response response)
    {
        User persistedUser = response.readEntity(User.class);
        assertNotNull(persistedUser);
        assertNotNull(persistedUser.getId());

        UserPrincipal principal = persistedUser.getUserPrincipal();
        assertNotNull(persistedUser);

        assertEquals(user.getUserPrincipal().getUsername(), principal.getUsername());
        assertEquals(user.getFirstName(), persistedUser.getFirstName());
        assertEquals(user.getLastName(), persistedUser.getLastName());
        assertEquals(user.getEmail(), persistedUser.getEmail());
    }

    @Test (expected = BadRequestException.class)
    public void testCreateAndDeleteUser() throws Exception
    {
        Response response = target.request().post(Entity.entity(user3, MediaType.APPLICATION_JSON));
        assertEquals(201, response.getStatus());//201-Created
        flush();

        User user = target.path(TESTING_USER_3).request().get(User.class);
        assertEquals(PATH + user.getId(), response.getLocation().toString());

        response = target.path(user.getId().toString()).request().delete();
        assertEquals(204, response.getStatus());//204-No Content
        flush();

        target.request().post(Entity.entity(null, MediaType.APPLICATION_JSON));
    }

    @Test
    public void testUpdateUser() throws Exception
    {
        user1.setFirstName("juzerJedan");
        Response response = target.request().put(Entity.entity(user1, MediaType.APPLICATION_JSON));
        flush();
        assertEquals(200, response.getStatus());

        user1.setFirstName("user1");
        response = target.request().put(Entity.entity(user1, MediaType.APPLICATION_JSON));
        flush();
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testSearchUsers() throws Exception
    {
        //TODO: Implement!
    }

    @Test
    public void testGetAllUserProjects() throws Exception
    {
        //TODO: Implement!
    }

    @Test
    public void testGetUserProjectById() throws Exception
    {
        //TODO: Implement!
    }

    @Test
    public void testGetAllUserTasks() throws Exception
    {
        //TODO: Implement!
    }

    @Test
    public void testGetUserTaskById() throws Exception
    {
        //TODO: Implement!
    }

    @Test
    public void testGetAllUserComments() throws Exception
    {
        //TODO: Implement!
    }

    @Test
    public void testGetUserCommentById() throws Exception
    {
        //TODO: Implement!
    }
}