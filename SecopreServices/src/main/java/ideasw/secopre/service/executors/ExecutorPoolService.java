package ideasw.secopre.service.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorPoolService {
	public static final int maxPoolSize = 5;
	private static ExecutorService threadPool = null;

	public static void initPool() {
		threadPool = Executors.newFixedThreadPool(maxPoolSize,Executors.defaultThreadFactory());
	}

	public static ExecutorService getService() {
		if (threadPool == null) {
			initPool();
		}
		return threadPool;
	}

}
