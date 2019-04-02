package domain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool  {
  private static ExecutorService es = Executors.newFixedThreadPool(10);
  private ThreadPool(){};
  public static ExecutorService getPool(){
    return es;
  }
}
