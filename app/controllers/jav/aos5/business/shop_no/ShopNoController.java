package controllers.jav.aos5.business.shop_no;

import common.nacos.client.NacosClient;
import play.Logger;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static common.nacos.severName.ServerName.BUSINESS_SERVER;

/**
 * @author qingyun
 * @date 2021/5/11 1:47 下午
 */
public class ShopNoController extends Controller {

  private Logger.ALogger logger = Logger.of(ShopNoController.class);

  public final NacosClient client;

  @Inject
  public ShopNoController(NacosClient client) {
    this.client = client;
  }

  public Result shopNo() throws ExecutionException, InterruptedException {
    WSRequest execute = client.execute(BUSINESS_SERVER + "/seat/shopNoMerchantNo/" + 66);
    CompletionStage<WSResponse> wsResponseCompletionStage = execute.get();
    WSResponse response = wsResponseCompletionStage.toCompletableFuture().get();
    logger.info(response.getBody());
    return ok(response.getBody());
  }
}
