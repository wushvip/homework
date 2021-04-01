package com.rpc.akka

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import javafx.concurrent.Worker

/**
  * @Author: wushuai
  * @Date: 2019/9/3 17:29
  * @Description:
  */
class Worker(val masterHost: String,masterPort: Int,workerHost: String, workerPort: Int) extends Actor{
  var masterRef: ActorSelection = _

  override def preStart(): Unit = {
    println("worker prestart beginning!")

    var master: ActorSelection = context.actorSelection(s"akka.tcp://Master@$masterHost:$masterPort/user/master");
    masterRef = master
    //  ！ 发送异步消息
    master ! "send"
    println("send has out to master!")
  }

  override def receive: Receive = {
    case "reply"=> {
      println("client has recieve mes!")
    }

  }

}
object Worker{
  def main(args: Array[String]): Unit = {
    var masterHost = args(0)
    var masterPort = args(1).toInt
    var workerHost = args(2)
    var workerPort = args(3).toInt

    var config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=$workerHost
         |akka.remote.netty.tcp.port=$workerPort
       """.stripMargin)

    var actorSystem = ActorSystem("WorkSystem",config);
    var actor = actorSystem.actorOf(Props(new Worker(masterHost,masterPort,workerHost,workerPort)),"client");
    actorSystem.awaitTermination();
  }

}
