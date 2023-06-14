package lk.ijse.petcarecenter.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T,S> extends SuperDAO{
      List<T> getAll() throws SQLException;
      T search(S id) throws SQLException;
      S getNextId() throws SQLException;
      S splitId(S id);
      boolean save(T dto) throws SQLException;
      boolean update(T dto) throws SQLException;
      boolean delete(S id) throws SQLException;
      boolean seachID(S id) throws SQLException;
}
