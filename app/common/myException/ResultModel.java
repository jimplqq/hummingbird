package common.myException;

import com.fasterxml.jackson.annotation.JsonInclude;
import play.mvc.Http;

/**
 * @author qingyun
 * @date 2021/5/10 4:43 下午
 */
@SuppressWarnings("unchecked")
public class ResultModel<T> {

  private int code;

  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T object;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  /**
   * Gets message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets message.
   *
   * @param message the message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  public T getObject() {
    return object;
  }

  public void setObject(T object) {
    this.object = object;
  }

  public static ResultModel resultModelFalse(String msg, Object o) {
    return builtResult(Http.Status.INTERNAL_SERVER_ERROR, msg, o);
  }

  public static ResultModel resultModelFalse(String msg, int code, Object o) {
    return builtResult(code, msg, o);
  }

  public static ResultModel resultModelTrue(Object o) {
    return builtResult(Http.Status.OK, ResultModel.SUCCESS, o);
  }

  /**
   * Built result result model.
   *
   * @param code the status
   * @param message the message
   * @param object the object
   * @return the result model
   */
  public static ResultModel builtResult(int code, String message, Object object) {
    ResultModel resultModel = new ResultModel();
    resultModel.setCode(code);
    resultModel.setMessage(message);
    resultModel.setObject(object);
    return resultModel;
  }

  /** The constant SUCCESS. */
  public static final String SUCCESS = "成功";

  public static final String ERROR_PARAM = "参数异常";
  public static final String DUPLICATE_DATA = "存在重复的数据";
  public static final String NO_OBJECT_FOUNT = "未找到对象";
  public static final String OBJECT_USED = "对象被占用";
  public static final String UN_USER_STATUS = "不可使用状态";
}
