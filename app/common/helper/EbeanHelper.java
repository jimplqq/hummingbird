package common.helper;

import io.ebean.DtoQuery;
import io.ebean.Ebean;
import io.ebean.Query;

/**
 * @author qingyun
 * @date 2021/5/20 3:39 下午
 */
@SuppressWarnings("unchecked")
public class EbeanHelper {
  public static <T> Query<T> query(Class<T> clazz) {
    return Ebean.find(clazz);
  }

  public static <T> DtoQuery<T> queryDto(Class<T> clazz, String sql) {
    return Ebean.findDto(clazz, sql);
  }

  public static <T> T queryById(Class<T> clazz, Object id) {
    return Ebean.find(clazz, id);
  }
}
