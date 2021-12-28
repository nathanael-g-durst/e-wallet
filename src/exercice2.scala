// Imports
import scala.io.StdIn.readLine
import scala.io.StdIn.readInt
import scala.collection.mutable.ArrayBuffer

object exercice2 {

  def main(args: Array[String]): Unit = {
    // Texts
      // Create E-Wallet
      val promptClientID: String = "Saisissez votre ID client:"
    // Variables
      var nextPage: Int = 0
      var clientID: Int = 0
    // Print menu
      nextPage = menu()
    // Next
      if (nextPage == 1) {
        // Create E-Wallet
          println("")
          println(promptClientID)
          clientID = readInt()
          // Check if clientID already exists
      } else if (nextPage == 2) {
        // Access E-Wallet

      } else if (nextPage == 3) {
        // Exit

      }
  }

  def menu(): Int = {
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
    return nextPage
  }
}