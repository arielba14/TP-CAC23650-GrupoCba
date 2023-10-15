package com.cac.tpcacfinal.entities;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testCreateUser(){
        User user = new User();
        Assert.assertNotNull(user);
    }
    @Test
    public void testCreateUserWithUsername(){
        User user = new User(1L, "arielba", "123", "Ariel");
        Assert.assertEquals(user.getUsername(), "arielba");
    }
    @Test
    public void testCreateUserWithName(){
        User user = new User(1L, "arielba", "123", "Ariel");
        Assert.assertEquals(user.getName(), "Ariel");
    }
    @Test
    public void testCreateUserWithPassword(){
        User user = new User(1L, "arielba", "123", "Ariel");
        Assert.assertEquals(user.getPassword(), "123");
    }


}
