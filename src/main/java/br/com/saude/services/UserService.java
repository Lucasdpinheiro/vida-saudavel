package br.com.saude.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.saude.dao.UserDao;

@RequestScoped
public class UserService {

    @Inject
    UserDao userDao;


    
}
