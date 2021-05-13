package common.module;

import com.google.inject.AbstractModule;
import play.libs.akka.AkkaGuiceSupport;

/**
 * @author qingyun
 * @date 2021/5/8 4:13 下午
 */
public class MyModule extends AbstractModule implements AkkaGuiceSupport {
	@Override
	protected void configure() {
	}
}
