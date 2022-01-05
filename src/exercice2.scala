// Imports
import scala.io.StdIn.readLine
import scala.io.StdIn.readInt
import scala.collection.mutable.ArrayBuffer

object exercice2 {

  def main(args: Array[String]): Unit = {
    // Texts

    // Variables

    // Print menu
      menu()
  }

  /*class Ewallet (idclient: String, pinclient: Int, soldeclient: BigDecimal, decouvertclient: Int = 500, fraisclient: Int = 3) {
    // Variables
      var clientid: String
      var clientpin: Int
      var clientsolde: BigDecimal
      var clientdecouvert: Int
      var clientfrais: Int
    // Initialisation
      clientid = idclient
      clientpin = pinclient
      clientsolde = soldeclient
      clientdecouvert = decouvertclient
      clientfrais = fraisclient
    // Functions

  }*/

/*  def lireEwallet(): ArrayBuffer[Ewallet] = {
    //
  }

  def sauverEwallet(listeEwallet: ArrayBuffer[Ewallet]): Unit = {
    //
  }*/

  def menu(): Unit = {
    // Texts
      val menuTitle: String = "Menu"
      val menuCreate: String = "1. Créer un porte-monnaie"
      val menuAccess: String = "2. Accéder à son porte-monnaie"
      val menuQuit: String = "3. Quitter le programme"
      val menuPrompt: String = "Entrez votre choix:"
    // Variables
      var nextPage: Int = 0
    // Print menu
      println(menuTitle)
      println(menuCreate)
      println(menuAccess)
      println(menuQuit)
      println("")
      println(menuPrompt)
      nextPage = readInt()
    // Next menu
      if (nextPage == 1) {
        // Create E-Wallet
        createWallet()
      } else if (nextPage == 2) {
        // Access E-Wallet
        accessWallet()
      } else if (nextPage == 3) {
        // Exit
        exit()
      }
  }

  def createWallet(): String = {
    // Texts
      val promptClientID: String = "Saisissez votre ID client:"
    // Variables
      var newWallet: String = ""
      var clientID: String = ""
    // Prompts
      println("")
      println(promptClientID)
      clientID = readLine()
    // Check if clientID already exists
    return newWallet
  }

  def accessWallet() = {
    // Texts
      val promptClientID: String = "Saisissez votre ID client:"
      val promptClientPIN: String = "Saisissez votre code PIN:"
    // Variables
      var newWallet: String = ""
      var clientID: String = ""
      var clientPIN: Int = 0
    // Code
      println("")
      println(promptClientID)
      clientID = readLine()
      println("")
      println(promptClientPIN)
      clientPIN = readInt()
  }

  def exit() = {
    // Texts
    val menuTitle: String = "Menu"
    // Variables
    var newWallet: String = ""
  }

}