package common.filters;

import akka.stream.Materializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;
import play.routing.HandlerDef;
import play.routing.Router;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

/**
 * @author qingyun
 * @date 2020/11/25 3:33 下午
 */
public class RoutedLoggingFilter extends Filter {
	private static final Logger log = LoggerFactory.getLogger(RoutedLoggingFilter.class);

	@Inject
	public RoutedLoggingFilter(Materializer mat) {
		super(mat);
	}

	@Override
	public CompletionStage<Result> apply(
			Function<Http.RequestHeader, CompletionStage<Result>> nextFilter,
			Http.RequestHeader requestHeader) {
		long startTime = System.currentTimeMillis();
		return nextFilter
				.apply(requestHeader)
				.thenApply(
						result -> {
							HandlerDef handlerDef = requestHeader.attrs().get(Router.Attrs.HANDLER_DEF);
							String actionMethod = handlerDef.controller() + "." + handlerDef.method();
							long endTime = System.currentTimeMillis();
							long requestTime = endTime - startTime;

							log.info("{} took {}ms and returned {}", actionMethod, requestTime, result.status());

							return result.withHeader("Request-Time", "" + requestTime);
						});
	}

}
