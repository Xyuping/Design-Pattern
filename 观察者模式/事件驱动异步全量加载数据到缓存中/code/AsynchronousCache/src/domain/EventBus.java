package domain;

import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventBus {

  BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>();
  private static EventBus bus = new EventBus();

  private EventBus(){}
  public static EventBus getInstance(){
    return bus;
  }
  public void attachEvent(Event event){
      eventQueue.add(event);
  }
  public void notifyController(){

    Event event = null;
    try {
      event = eventQueue.take();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Controller.getInstance().handleEvent(event);
  }
  public void init(){
    while(true){
      notifyController();
    }
  }

}
