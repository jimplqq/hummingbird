package common.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author qingyun
 * @date 2020/9/15 3:07 下午
 */
public class BeanCopierUtil {

  /**
   * 单个对象属性拷贝
   *
   * @param source 源对象
   * @param clazz 目标对象Class
   * @param <T> 目标对象类型
   * @param <M> 源对象类型
   * @return 目标对象
   */
  public static <T, M> T copyProperties(M source, Class<T> clazz) {
    return copyProperties(source, clazz, null);
  }

  /**
   * 列表对象拷贝
   *
   * @param sources 源列表
   * @param clazz 源列表对象Class
   * @param <T> 目标列表对象类型
   * @param <M> 源列表对象类型
   * @return 目标列表
   */
  public static <T, M> List<T> copyObjects(List<M> sources, Class<T> clazz) {
    if (sources.size() == 0) {
      return new ArrayList<>();
    }
    BeanCopier copier = BeanCopier.create(sources.get(0).getClass(), clazz, false);
    return Optional.of(sources).orElse(new ArrayList<>()).stream()
        .map(m -> copyProperties(m, clazz, copier))
        .collect(Collectors.toList());
  }

  /**
   * 单个对象属性拷贝
   *
   * @param source 源对象
   * @param clazz 目标对象Class
   * @param copier copier
   * @param <T> 目标对象类型
   * @param <M> 源对象类型
   * @return 目标对象
   */
  private static <T, M> T copyProperties(M source, Class<T> clazz, BeanCopier copier) {
    if (source == null) {
      try {
        return clazz.newInstance();
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }
    if (null == copier) {
      copier = BeanCopier.create(source.getClass(), clazz, false);
    }
    T t = null;
    try {
      t = clazz.newInstance();
      copier.copy(source, t, null);
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return t;
  }
}
