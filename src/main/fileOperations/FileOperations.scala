package main.fileOperations

import main.States
import java.io._

import scala.annotation.tailrec
import scala.io.Source
import scala.io.StdIn.readLine

object FileOperations {
  def saveStates(states: States) = {
    val out = new ObjectOutputStream(new FileOutputStream(new File(s"${states.profileName}.prof")))
    out.writeObject(states)
  }

  def loadStates(fileName: String): Option[States] = {
    try {
      val in = new ObjectInputStream(new FileInputStream(new File(s"${fileName}.prof")))
      val states = in.readObject().asInstanceOf[States]
      Some(states)
    } catch {
      case _: Throwable => None
    }
  }

  def processLines[A](lines: List[String], convert: String => A): Map[String, A] = {
    @tailrec
    def loop(lines: List[String], map: Map[String, A]): Map[String, A] = {
      lines match {
        case ::(head, next) => {
          val line = head.split(":")
          loop(next, map + (line(0) -> convert(line(1))))
        }
        case Nil => map
      }
    }

    loop(lines, Map())
  }

  def loadCaloriesMap[A](name: String, convert: String => A): Map[String, A] = {
    val file = Source.fromFile(name)
    val map = processLines(file.getLines().toList, convert)
    file.close()
    map
  }

  def printLoadError = println("There is no saved profile. Create a new One")

  def getUsername(): String ={
    println("Please type your username")
    val username = readLine()
    username
  }
}
