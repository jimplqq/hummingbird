package common.util.ebean;

import io.ebean.DtoQuery;
import io.ebean.Ebean;
import io.ebean.Query;

/**
 * @author qingyun
 * @date 2021/5/11 10:35 上午
 */
@SuppressWarnings("unchecked")
public class EbeanUtil {


  public static Query query(Class clazz) {
    return Ebean.find(clazz);
  }

  public static DtoQuery queryDto(Class clazz, String sql) {
    return Ebean.findDto(clazz, sql);
  }

  public static <T> T queryById(Class<T> clazz, Object id) {
    return Ebean.find(clazz, id);
  }
}
