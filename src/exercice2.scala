// Imports
import scala.io.StdIn.readLine
import scala.io.StdIn.readInt
import java.io.File
import java.io.PrintWriter
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

object exercice2 {

  class Ewallet (
                  var idclient: String,
                  var pinclient: Int = 0,
                  var soldeclient: Double,
                  var decouvertclient: Int = 500,
                  var fraisclient: Int = 3
                ) {
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
      soldeclient += total
      return soldeclient
    }
    // Show informations
    def afficheVerif() : Unit = {
      println("Identifiant : " + idclient)
      println("Code PIN : " + pinclient)
      println("Solde du compte : " + soldeclient)
      println("")
    }
  }

  def lireEwallet(): ArrayBuffer[Ewallet] = {
    // Source
    val file = "ewallet.txt"
    // Check file existence
    if (!new File(file).exists) {
      new File(file).createNewFile()
      return ArrayBuffer[Ewallet]()
    } else {
      // Import wallets
      val wallets = ArrayBuffer[Ewallet]()
      val source = Source.fromFile(file)
      for (line <- source.getLines) {
        val newline = line.split(",")
        val newwallet = new Ewallet(newline(0), newline(1).toInt, newline(2).toDouble)
        wallets.append(newwallet)
      }
      // Close file
      source.close()
      return wallets
    }
  }

  def sauverEwallet(listeEwallet: ArrayBuffer[Ewallet]): Unit = {
    // Print in txt file
    val source = new PrintWriter(new File("ewallet.txt"))
    // Print line by line
    for (ewallets <- listeEwallet) {
      source.write(ewallets.idclient + "," + ewallets.pinclient + "," + ewallets.soldeclient + "\n")
    }
    source.close()
    return
  }

  def main(args: Array[String]): Unit = {
    // Wallets
    val ewallets = lireEwallet()
    // Print menu
    menu(ewallets)
  }

  def menu(allWallets: ArrayBuffer[Ewallet]): Unit = {
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
        createWallet(allWallets)
      } else if (nextPage == 2) {
        // Access E-Wallet
        accessWallet(allWallets)
      } else if (nextPage == 3) {
        // Exit
        exit(allWallets)
      }
  }

  def createWallet(allWallets: ArrayBuffer[Ewallet]): Unit = {
    // Texts
      val promptClientID: String = "Saisissez votre ID client:"
      val promptClientAmount: String = "Saisissez votre montant initial:"
      val errorExistant: String = "Erreur: un-e client-e ne peut avoir qu’un seul e-wallet à la fois"
    // Variables
      var clientID: String = ""
      var idCheck: Boolean = false
      var counter: Int = 0
      var initialAmount: Double = 0.0
    // Prompts
      println("")
      println(promptClientID)
      clientID = readLine()
    // Check if clientID already exists
      while (counter < allWallets.length && !idCheck) {
        if (allWallets(counter).idclient == clientID) {
          idCheck = true
        }
        counter += 1
      }
      if (idCheck) {
        println("")
        println(errorExistant)
        println("")
        menu(allWallets)
      } else {
        println("")
        println(promptClientAmount)
        initialAmount = readLine().toDouble
        val newWallet = new Ewallet(idclient = clientID, soldeclient = initialAmount)
        allWallets.append(newWallet)
        println("")
        println("Votre e-wallet :")
        println("")
        newWallet.afficheVerif()
        menu(allWallets)
      }
  }

  def accessWallet(allWallets: ArrayBuffer[Ewallet]): Unit = {
    // Texts
      val promptClientID: String = "Saisissez votre ID client:"
      val promptClientPIN: String = "Saisissez votre code PIN:"
      val errorID: String = "Accès refusé: votre identifiant est erroné"
      val errorPin: String = "Accès refusé: votre code PIN est erroné"
    // Variables
      var clientID: String = ""
      var clientPIN: Int = 0
      var walletCheck: Ewallet = null
      var pinCheck: Boolean = false
      var counter: Int = 0
    // Code
      println("")
      println(promptClientID)
      clientID = readLine()
      println("")
      println(promptClientPIN)
      clientPIN = readInt()
    // Check login
      while (walletCheck == null && counter < allWallets.length) {
        val ewallet = allWallets(counter)
        if (ewallet.idclient == clientID) {
          pinCheck = true
        }
        if (ewallet.verifieClient(clientID, clientPIN)) {
          walletCheck = ewallet
        }
        counter += 1
      }
      if (walletCheck == null) {
        if (pinCheck) {
          println("")
          println(errorPin)
          println("")
          menu(allWallets)
        } else {
          println("")
          println(errorID)
          println("")
          menu(allWallets)
        }
      } else {
        walletOperations(allWallets, walletCheck)
      }
  }

  def walletOperations(allWallets: ArrayBuffer[Ewallet], operationWallet: Ewallet): Unit = {
    // Texts
      val menuTitle: String = "Menu"
      val menuCredit: String = "1. Créditer votre porte-monnaie"
      val menuDebit: String = "2. Débiter votre porte-monnaie"
      val menuPrompt: String = "Entrez votre choix:"
      val creditPrompt: String = "Quel est le montant à créditer:"
      val debitPrompt: String = "Quel est le montant à débiter:"
      val errorBalance: String = "Solde insuffisant: la transaction n'a pas pu être effectué"
    // Variables
      var nextPage: Int = 0
      var amountOperation: Double = 0
      var amountWallet: Double = 0
    // Print menu
      println("")
      println(menuTitle)
      println(menuCredit)
      println(menuDebit)
      println("")
      println(menuPrompt)
      nextPage = readInt()
    // Next menu
      if (nextPage == 1) {
        // Credit
        println(creditPrompt)
        amountOperation = readLine().toDouble
        amountWallet = operationWallet.operationCredit(amountOperation)
        println("")
        println("Le solde de votre compte s'élève à : " + amountWallet)
        println("")
        menu(allWallets)
      } else if (nextPage == 2) {
        // Debit
        println(debitPrompt)
        amountOperation = readLine().toDouble
        amountWallet = operationWallet.operationDebit(amountOperation)
        println("")
        if (amountWallet.isNaN) {
          println(errorBalance)
        } else {
          println("Le solde de votre compte s'élève à : " + amountWallet)
        }
        println("")
        menu(allWallets)
      }
  }

  def exit(allWallets: ArrayBuffer[Ewallet]): Unit = {
    sauverEwallet(allWallets)
    println("")
    for (ewallet <- allWallets) {
      ewallet.afficheVerif()
    }
  }

}