package common.provider;

import play.data.format.Formatters;
import play.i18n.MessagesApi;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qingyun
 * @date 2021/5/10 4:03 下午
 */
@Singleton
public class FormattersProvider implements Provider<Formatters> {
  private final MessagesApi messagesApi;

  @Inject
  public FormattersProvider(MessagesApi messagesApi) {
    this.messagesApi = messagesApi;
  }

  private static final Pattern TIME_PATTERN =
      Pattern.compile("([012]?\\d)(?:[\\s:\\._\\-]+([0-5]\\d))?");

  @Override
  public Formatters get() {
    Formatters formatters = new Formatters(messagesApi);

    formatters.register(
        LocalTime.class,
        new Formatters.SimpleFormatter<LocalTime>() {

          @Override
          public LocalTime parse(String input, Locale l) throws ParseException {
            Matcher m = TIME_PATTERN.matcher(input);
            if (!m.find()) throw new ParseException("No valid Input", 0);
            int hour = Integer.parseInt(m.group(1));
            int min = m.group(2) == null ? 0 : Integer.parseInt(m.group(2));
            return LocalTime.of(hour, min);
          }

          @Override
          public String print(LocalTime localTime, Locale l) {
            return localTime.format(DateTimeFormatter.ofPattern("HH:mm"));
          }
        });

    return formatters;
  }
}
