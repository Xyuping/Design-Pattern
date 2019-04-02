package domain;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunningState implements State {
  private static RunningState runningState = new RunningState();

  private  RunningState(){}
  public static RunningState getInstance(){
    return runningState;
  }
  @Override
  public void handleEvent(Event event) {
    EventName name = event.getName();
    switch (name){
      case RELOAD:
//        System.out.println("Time事件");
        load();
        break;
    }
  }
  private void load(){
    ThreadPool.getPool().submit(new Runnable() {
      @Override
      public void run() {
        System.out.println("loading......");
        Date data = Cache.getInstance().loaddb();
        EventBus.getInstance().attachEvent(new Event(EventName.LAOD_SUCCESS,data));
        System.out.println("load_success");
      }
    });
    Cache.getInstance().setState(LoadingState.getInstance());
  }
}
