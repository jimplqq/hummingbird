package common.myException.global;

/**
 * @author qingyun
 * @date 2020/11/13 2:53 下午
 */
public class ToUserException extends RuntimeException {

  public ToUserException() {}

  public ToUserException(String message) {
    super(message);
  }
}
