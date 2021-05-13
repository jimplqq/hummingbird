package common.ssl;

import play.server.ApplicationProvider;
import play.server.SSLEngineProvider;

import javax.inject.Inject;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.security.NoSuchAlgorithmException;

/**
 * @author qingyun
 * @date 2021/5/13 2:16 下午
 */
public class CustomSSLEngineProvider implements SSLEngineProvider {
	private final ApplicationProvider applicationProvider;

	@Inject
	public CustomSSLEngineProvider(ApplicationProvider applicationProvider) {
		this.applicationProvider = applicationProvider;
	}

	@Override
	public SSLEngine createSSLEngine() {
		return sslContext().createSSLEngine();
	}

	@Override
	public SSLContext sslContext() {
		try {
			// Change it to your custom implementation, possibly using ApplicationProvider.
			return SSLContext.getDefault();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
