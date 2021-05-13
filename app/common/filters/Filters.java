package common.filters;

import play.filters.cors.CORSFilter;
import play.filters.gzip.GzipFilter;
import play.http.DefaultHttpFilters;

import javax.inject.Inject;

/** Created by questiny on 17-3-21. */
public class Filters extends DefaultHttpFilters {
  @Inject
  public Filters(
      CORSFilter corsFilter,
      GzipFilter gzip,
      LoggingFilter logging,
      RoutedLoggingFilter routedLoggingFilter) {
    super(gzip, corsFilter, logging, routedLoggingFilter, corsFilter.asJava());
  }
}
