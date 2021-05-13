package common.myException.global;

/**
 * @author qingyun
 * @date 2020/11/13 2:53 下午
 */
public class MyException extends RuntimeException {

  public MyException() {}

  public MyException(String message) {
    super(message);
  }
}
