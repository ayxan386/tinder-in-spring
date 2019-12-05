package com.aykhan.services.interfaces;


import com.aykhan.entities.User;

import java.util.Optional;

public interface Auth {
  Optional<User> login(String name, String pass);

  boolean register(User user);
}
