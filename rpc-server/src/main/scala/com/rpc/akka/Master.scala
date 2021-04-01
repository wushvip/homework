import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

/**
  * @Author: wushuai
  * @Date: 2019/9/3 13:30
  * @Description:
  */
class Master extends Actor{


  override def preStart(): Unit = {
    println("master prestart() invoked!")
  }
//  override preStart()
  //用于接收消息
  override def receive: Receive = {
    case "send" => {
      println("server has receive success!")
      sender ! "reply"
    }
    case "hello" => print("hello _____");
  }

}

object Master{
  def main(args: Array[String]): Unit = {
    var host = args(0)
    var port = args(1).toInt
    var config = ConfigFactory.parseString(
      s"""|akka.actor.provider="akka.remote.RemoteActorRefProvider"
          |akka.remote.netty.tcp.hostname=$host
          |akka.remote.netty.tcp.port=$port
      """.stripMargin)
    var actorSystem = ActorSystem("Master",config);
    var actor = actorSystem.actorOf(Props[Master],"master")
//    actor !"hello"
//    sender().forward("123");

    actorSystem.awaitTermination()
  }
}
