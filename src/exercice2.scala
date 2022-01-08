// Imports
import scala.io.StdIn.readLine
import scala.io.StdIn.readInt
import java.io.File
import java.io.PrintWriter
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object exercice2 {

  class Ewallet (idclient: String, pinclient: Int = 0, soldeclient: Double, decouvertclient: Int = 500, fraisclient: Int = 3) {
    // Initialisation
    if (pinclient == 0) {
      // Random number
      val random = scala.util.Random
      pinclient = random.nextInt(99900) + 100
    }
    // Functions
    // Check client and pin
    def verifieClient(idclient_verif: String, codepin_verif: Int) : Boolean = {
      idclient == idclient_verif && pinclient == codepin_verif
    }
    // Operation debit
    def operationDebit(montantadebiter: Double) : Double = {
      // Total amount of operation
      val fees: Double = (montantadebiter * fraisclient) / 100
      var total: Double = montantadebiter + fees
      // Check if amount enough and do
      if (soldeclient + decouvertclient >= total) {
        soldeclient -= total
        return soldeclient
      } else {
        return Double.NaN
      }
    }
    // Operation credit
    def operationCredit(montantacrediter: Double) : Double = {
      val fees: Double = (montantacrediter * fraisclient) / 100
      var total: Double = montantacrediter - fees
      soldeclient += montantacrediter
      return soldeclient
    }
    // Show informations
    def afficheVerif() : Unit = {
      println("Votre e-wallet :")
      println("")
      println("Votre identifiant :")
      println(idclient)
      println("Votre code pin :")
      println(pinclient)
      println("Solde du compte :")
      println(soldeclient)
    }
  }

  def main(args: Array[String]): Unit = {
    // Array
    val ewallets = new Array[Ewallet](50)
    var counter: Int = 0
    // Texts

    // Variables

    // Print menu
      menu()
  }

  def lireEwallet(): Array[Ewallet] = {
    //
    val bufferedSource = io.Source.fromFile("/ewallets.txt")
    for (line <- bufferedSource.getLines) {
      val cols = line.split(",").map(_.trim)
      // do whatever you want with the columns here
      println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}")
    }
    bufferedSource.close
    return bufferedSource
  }

  def sauverEwallet(listeEwallet: Array[Ewallet]): Unit = {
    //

    return
  }

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

}*/