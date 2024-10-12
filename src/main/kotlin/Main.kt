import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val alphabet = listOf(
        'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П',
        'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'ъ', 'ы', 'ь', 'Э', 'Ю', 'Я'
    )

    val alphabetNumbers = listOf(
        21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5, 30,
        27, 8, 18, 10, 33, 31, 32, 19, 7, 17
    )

    println("Выберите действие:")
    println("1. Шифровка")
    println("2. Дешифровка")
    val choice = scanner.nextLine()

    println("Введите текст:")
    val text = scanner.nextLine().uppercase()

    println("Введите ключевое слово:")
    val keyword = scanner.nextLine().uppercase()

    when (choice) {
        "1" -> {
            val encryptedText = encrypt(text, keyword, alphabet, alphabetNumbers)
            println("Зашифрованный текст: $encryptedText")
        }
        "2" -> {
            val decryptedText = decrypt(text, keyword, alphabet, alphabetNumbers)
            println("Расшифрованный текст: $decryptedText")
        }
        else -> {
            println("Неверный выбор. Выберите 1 или 2.")
        }
    }
}

fun encrypt(text: String, keyword: String, alphabet: List<Char>, alphabetNumbers: List<Int>): String {
    val encrypted = StringBuilder()
    val keywordRepeated = keyword.repeat((text.length / keyword.length) + 1).take(text.length)

    for (i in text.indices) {
        val textCharIndex = alphabet.indexOf(text[i])
        val keyCharIndex = alphabet.indexOf(keywordRepeated[i])
        val shift = alphabetNumbers[keyCharIndex]
        val textCharShiftIndex = alphabetNumbers[textCharIndex]
        val newIndex = (textCharShiftIndex + shift - 1) % alphabet.size
        val alphabetIndexForNewChar = alphabetNumbers.indexOf(newIndex + 1)
        encrypted.append(alphabet[alphabetIndexForNewChar])
    }

    return encrypted.toString()
}

fun decrypt(encryptedText: String, keyword: String, alphabet: List<Char>, alphabetNumbers: List<Int>): String {
    val decrypted = StringBuilder()
    val keywordRepeated = keyword.repeat((encryptedText.length / keyword.length) + 1).take(encryptedText.length)

    for (i in encryptedText.indices) {
        val encryptedCharIndex = alphabet.indexOf(encryptedText[i])
        val keyCharIndex = alphabet.indexOf(keywordRepeated[i])
        val shift = alphabetNumbers[keyCharIndex]
        val encryptedCharShiftIndex = alphabetNumbers[encryptedCharIndex]

        val newIndex = (encryptedCharShiftIndex - shift + alphabet.size) % alphabet.size
        val alphabetIndexForOriginalChar = alphabetNumbers.indexOf(newIndex + 1)
        decrypted.append(alphabet[alphabetIndexForOriginalChar])
    }

    return decrypted.toString()
}
