package common.filters;

import akka.stream.Materializer;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * @author qingyun
 * @date 2020/11/24 2:05 下午
 */
public class LoggingFilter extends Filter {
  private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

  @Inject
  public LoggingFilter(Materializer mat) {
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
              long endTime = System.currentTimeMillis();
              long requestTime = endTime - startTime;

              log.info(
                  "{} {} took {}ms and returned {}",
                  requestHeader.method(),
                  requestHeader.uri(),
                  requestTime,
                  result.status());
              Source<ByteString, ?> source = result.body().dataStream();
              final Sink<ByteString, CompletionStage<ByteString>> sink =
                  Sink.fold(ByteString.empty(), ByteString::concat);
              final CompletionStage<ByteString> sum = source.runWith(sink, materializer);
              try {
                ByteString byteString = sum.toCompletableFuture().get();
                String response = new String(byteString.toArray());
                log.info("响应response = {}", response);
              } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
              }
              return result.withHeader("Request-Time", "" + requestTime);
            });
  }
}
