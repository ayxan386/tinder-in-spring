package com.aykhan.Controller;

import com.aykhan.entities.LikedUser;
import com.aykhan.entities.Message;
import com.aykhan.entities.User;
import com.aykhan.services.implementations.RandomUsers;
import com.aykhan.services.implementations.SQLLikedDao;
import com.aykhan.services.implementations.SQLMessageDao;
import com.aykhan.services.implementations.SQLUserDao;
import com.aykhan.services.interfaces.DAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@EnableWebMvc
class ViewControl {
  private final DAO<User> userDAO = new SQLUserDao();
  private final DAO<LikedUser> likedUserDAO = new SQLLikedDao();
  private final DAO<Message> messageDAO = new SQLMessageDao();

  @GetMapping("/login")
  public String get_login(Model model) {
    return "login";
  }

  @GetMapping("/register")
  public String get_register(Model model) {
    return "registerPage";
  }

  @GetMapping("/users")
  public String get_users(Model model, @CookieValue("%s.id%") int id, HttpServletResponse resp) throws IOException {
    RandomUsers randomUsers = new RandomUsers(userDAO);

    List<Integer> alreadyLiked = likedUserDAO.getContaining(id)
        .orElse(Collections.emptyList())
        .stream()
        .map(user -> user.getWhom())
        .collect(Collectors.toList());
    User u = randomUsers.getRandom(id, alreadyLiked);

    if (u.isDefault()) {
      resp.sendRedirect("/liked");
    }
    HashMap<String, Object> data = new HashMap<>();
    data.put("notfound", "not found");
    data.put("userName", u.getName());
    data.put("photo_link", u.getLink());
    data.put("user_id", u.getId());

    model.addAllAttributes(data);
    return "like-page";
  }

  @GetMapping("/liked")
  public String get_liked(Model model, @CookieValue("%s.id%") int id, HttpServletResponse resp) {

    Optional<List<LikedUser>> likedUserO = likedUserDAO.getContaining(id);
    HashMap<String, Object> data = new HashMap<>();

    likedUserO.ifPresent(likedUsers -> {
      List<User> users = likedUsers.stream()
          .map(userLiked -> userDAO.get(userLiked.getWhom())
              .orElse(User.defaultUser()))
          .collect(Collectors.toList());
      data.put("users", users);
    });

    data.put("emptyList", Arrays.asList("You have not liked anybody yet:/"));
    data.put("def", -1);

    model.addAllAttributes(data);
    return "people-list";
  }

  @GetMapping("/messages/{other_id}")
  public String get_chat(Model model, @PathVariable("other_id") int other_id, @CookieValue("%s.id%") int my_id) {

    HashMap<String, Object> data = new HashMap<>();
    Optional<User> other_user = userDAO.get(other_id);
    other_user.ifPresent(user -> {
      List<Message> these = messageDAO.getThese(my_id, other_id);
      data.put("messages", these);
      data.put("other", user);
    });
    data.put("def", Arrays.asList(new Message("Start the conversation", my_id, other_id)));
    model.addAllAttributes(data);
    return "chat";
  }

}
