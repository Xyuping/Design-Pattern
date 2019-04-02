package domain;

import java.util.Date;
import java.util.Map;

public class Cache {
  private State state;
  private static Cache cache = new Cache();
  private Date data;
  private Cache(){
    state = InitState.getInstance();
  }
  public static Cache getInstance(){
    return cache;
  }
  public void handleEvent(Event event){
    state.handleEvent(event);
  }

  public Date loaddb(){
    Date date = new Date();
//    System.out.println("cache "+date);

    try {
      Thread.sleep(3000);
//      System.out.println(state.getClass().getName()+":loaddb");
    }catch (InterruptedException ex){

    }
    return date;
  }

  public void update(Object data){
//    System.out.println(state.getClass().getName()+":update");
    this.data = (Date)data;
  }


  public void setState(State state){
    this.state = state;
  }
  public State getState(){
    return state;
  }
  public Date getData(){
   return data;
  }

}
