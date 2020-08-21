import java.util.*
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList

class NumberMaster {
    var fourDigits: String = ""
    var attemptCounter: Int = 0
    val symbols = ArrayList<String>()
    val scString = Scanner(System.`in`)

    private fun randomNumberGenerator(): String {
        return ThreadLocalRandom.current().nextInt(0, 10).toString()
    }

    fun generate4DigitNumber(): String {
        var random4DigNum: String = ""

        while (random4DigNum.length != 4) {
            val allowedNumber = randomNumberGenerator()
            if (random4DigNum.contains(allowedNumber).not())
                random4DigNum = "$random4DigNum$allowedNumber"
        }
        return random4DigNum
    }

    fun guessingMechanics() {
        var textInputScanner3 = scString.nextLine()

        while (fourDigits.equals(textInputScanner3).not()) {
            for(i in 0 until fourDigits.length){
                val j: Int = i + 1

                val indexOfGuessedNum: Int = fourDigits.indexOf(textInputScanner3.substring(i, j))
                if (i == indexOfGuessedNum && indexOfGuessedNum >= 0) {
                    symbols.add("\u2713")
                } else if (i != indexOfGuessedNum && indexOfGuessedNum >= 0) {
                    symbols.add("\u25A2")
                } else {
                    symbols.add("X")
                }
            }
            symbols.sortDescending()
            println(symbols)
            attemptCounter++
            println("Incorrect! Make another 4-digit guess or type in 'defeat' to reveal the number")
            symbols.removeAll(symbols)

            val textInputScanner4 = scString.nextLine()
            textInputScanner3 = textInputScanner4

            if (textInputScanner3.equals("defeat")) {
                println("The number was: $fourDigits\n------YOU LOSE------")
                println("Try again? Restart the program")
                System.exit(0)
            }
        }
    }
}

fun main() {
    val nm = NumberMaster()

    println ("Start Number Master? Press 'y'!")
    val textInputScanner1 = nm.scString.nextLine()
    if (textInputScanner1.equals("y")) {
        nm.fourDigits = nm.generate4DigitNumber()
        //println(“four digits: “+ nm.fourDigits)

        println("Select '1' or '2'\n(1) Make your 4-digit guess\n(2) Reveal Number")
        val textInputScanner2 = nm.scString.nextLine()
        if (textInputScanner2.equals("1")) {
            println("Make your 4-digit guess:")
            nm.guessingMechanics()
            println ("Congrats! The number was: " + nm.fourDigits + "\nYou just needed " + nm.attemptCounter + " attempts.")
        } else if (textInputScanner2.equals("2")) {
            println("The number was: " + nm.fourDigits + "\n------YOU LOSE------")
        }
        println ("Try again? Restart the program!")
        System.exit (0)
    }
}