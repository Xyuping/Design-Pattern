package domain;



public class Event {

  private EventName name;
  private Object info;
  public Event(EventName name){
    this.name = name;
  }
  public Event(EventName name,Object info){
    this.name = name;
    this.info = info;
  }

  public void setName(EventName name) {
    this.name = name;
  }

  public void setInfo(Event info) {
    this.info = info;
  }

  public Object getInfo() {
    return info;
  }

  public EventName getName() {
    return name;
  }
}