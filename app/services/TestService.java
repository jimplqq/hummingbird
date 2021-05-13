package services;

import javaBeans.vo.response.TestResponse;

import java.util.List;

/**
 * @author qingyun
 * @date 2021/5/8 3:52 下午
 */
public interface TestService {
  List<TestResponse> testList();

}
