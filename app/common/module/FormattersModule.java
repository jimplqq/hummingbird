package common.module;

import com.google.inject.AbstractModule;
import common.provider.FormattersProvider;
import play.data.format.Formatters;

/**
 * @author qingyun
 * @date 2021/5/10 4:02 下午
 */
public class FormattersModule extends AbstractModule {
	@Override
	protected void configure() {

		bind(Formatters.class).toProvider(FormattersProvider.class);
	}
}
