package domain;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoadingState implements State {
  private static LoadingState loadingState = new LoadingState();

  private  LoadingState(){}
  public static LoadingState getInstance(){
    return loadingState;
  }

  @Override
  public void handleEvent(Event event) {
    EventName name = event.getName();
    switch (name){
      case LAOD_SUCCESS:
        Object data = event.getInfo();
        update(data);
        break;
    }
  }
  private void update(Object data){
    Cache.getInstance().update(data);
    timer(5000);
    Cache.getInstance().setState(RunningState.getInstance());
//    System.out.println("状态切换 RunningState ："+Cache.getInstance().getState().getClass().getName());
  }
  private void timer(int time){

    ScheduledExecutorService ses= Executors.newSingleThreadScheduledExecutor();
    ses.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run()
      {
        System.out.println("time_out");
        EventBus.getInstance().attachEvent(new Event(EventName.RELOAD));
      }
    }, 0, time, TimeUnit.MILLISECONDS);
  }
}
