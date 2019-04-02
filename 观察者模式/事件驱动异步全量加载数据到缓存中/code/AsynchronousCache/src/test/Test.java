package test;

import java.util.Date;

import domain.Cache;
import domain.Event;
import domain.EventBus;
import domain.EventName;
import domain.ThreadPool;


public class Test {
  public static void main(String[] args)throws Exception{
    ThreadPool.getPool().submit(new Runnable() {
      @Override
      public void run() {
        EventBus.getInstance().init();
      }
    });

    Thread.sleep(1000);//确保时间总线先跑起来
    EventBus.getInstance().attachEvent(new Event(EventName.INIT));
    int i = 0;
    Date data = null;
    while(true){
      data = Cache.getInstance().getData();
      System.out.println("第"+(i++)+"次读取数据："+data);
      Thread.sleep(3500);
    }



  /*  Connection con = JDBCUtils.getConnectionByProperties();
    PreparedStatement ps = con.prepareStatement("SELECT sname FROM sort");
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
      System.out.println(rs.getString("sname"));
    }
    JDBCUtils.close(con, ps, rs);*/

  }



}
