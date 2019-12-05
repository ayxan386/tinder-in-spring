package com.aykhan.Controller;

import com.aykhan.entities.LikedUser;
import com.aykhan.entities.Message;
import com.aykhan.entities.User;
import com.aykhan.services.implementations.SQLAuth;
import com.aykhan.services.implementations.SQLLikedDao;
import com.aykhan.services.implementations.SQLMessageDao;
import com.aykhan.services.implementations.SQLUserDao;
import com.aykhan.services.interfaces.Auth;
import com.aykhan.services.interfaces.DAO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MyRestController {

  private final Auth auth = new SQLAuth();
  private final DAO<User> userDAO = new SQLUserDao();
  private final DAO<LikedUser> likedUserDAO = new SQLLikedDao();
  private final DAO<Message> messageDAO = new SQLMessageDao();

  @GetMapping("/static/css/{file}")
  @ResponseBody
  String css_loader(@PathVariable String file) {
    try {
      file += ".css";
      return Files.lines(Paths.get("./src/main/resources/static/css/", file)).collect(Collectors.joining());
    } catch (IOException e) {
    }
    return "";
  }

  @PostMapping("/login")
  void post_login(HttpServletResponse res, @RequestParam("email") String email, @RequestParam("password") String pass) {
    Optional<User> login = auth.login(email, pass);
    login.ifPresent(user -> {
      Cookie cookie = new Cookie("%s.id%", String.valueOf(user.getId()));
      res.addCookie(cookie);
      try {
        res.sendRedirect("/users");
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  @PostMapping("/users")
  void post_users(HttpServletResponse res, @RequestParam("id") int liked_id,
                  @RequestParam("liked") boolean liked,
                  @CookieValue("%s.id%") int logged_id) throws IOException {

    if (liked) {
      Optional<List<LikedUser>> list_of_liked = likedUserDAO.getContaining(logged_id);
      list_of_liked.ifPresent(list -> {
            List<Integer> intList = list.stream()
                .map(LikedUser::getWhom)
                .collect(Collectors.toList());
            if (!intList.contains(liked_id)) {
              likedUserDAO.add(new LikedUser(-1, logged_id, liked_id));
            }
          }
      );
    }
    res.sendRedirect("/users");
  }

  @PostMapping("/messages/*")
  void post_messages(HttpServletResponse res,
                     @CookieValue("%s.id%") int sender,
                     @RequestParam("receiver") int receiver,
                     @RequestParam("message") String text) throws IOException {
    messageDAO.add(new Message(text, sender, receiver));
    res.sendRedirect(String.format("/messages/%s", receiver));
  }
}
