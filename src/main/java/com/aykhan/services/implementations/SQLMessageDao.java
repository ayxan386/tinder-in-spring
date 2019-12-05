package com.aykhan.services.implementations;

import com.aykhan.db.SQLMessagesQueries;
import com.aykhan.entities.Message;
import com.aykhan.services.interfaces.DAO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SQLMessageDao implements DAO<Message> {

  private final SQLMessagesQueries sqlMessagesQueries = new SQLMessagesQueries();

  @Override
  public void add(Message data) {
    sqlMessagesQueries.add(data);
  }

  @Deprecated
  @Override
  public Optional<Message> get(int id) {
    throw new IllegalArgumentException("Not supported");
  }

  @Deprecated
  @Override
  public int update(Message data) {
    throw new IllegalArgumentException("Not supported");
  }

  @Override
  public List<Message> getThese(int sender, int receiver) {
    return sqlMessagesQueries.getBetween(sender, receiver).orElse(Collections.emptyList());
  }

  @Deprecated
  @Override
  public Optional<List<Message>> getContaining(int id) {
    throw new IllegalArgumentException("Not supported");
  }

}
