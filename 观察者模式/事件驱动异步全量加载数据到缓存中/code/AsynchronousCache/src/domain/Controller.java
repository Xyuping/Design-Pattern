package domain;

public class Controller {
  private static Controller controller = new Controller();

  private Controller(){

  }

  public static Controller getInstance(){
    return controller;
  }
  public void handleEvent(Event event){
    EventName name =  event.getName();
    switch (name){
      case INIT:;
      case LAOD_SUCCESS:;
      case RELOAD:
        Cache.getInstance().handleEvent(event);
        break;
    }

  }
}
