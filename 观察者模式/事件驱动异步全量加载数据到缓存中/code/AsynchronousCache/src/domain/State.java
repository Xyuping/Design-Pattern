package domain;

public interface State {
  public abstract void handleEvent(Event event);
}
