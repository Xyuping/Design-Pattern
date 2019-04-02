package domain;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InitState implements State{
  private static InitState initState = new InitState();

  private  InitState(){}
  public static InitState getInstance(){
    return initState;
  }


  @Override
  public void handleEvent(Event event) {
    EventName name = event.getName();
    switch (name){
      case INIT:
        load();
        break;
    }

  }
  public void load(){
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
