fun main() {
    val alphabet = listOf(
        'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П',
        'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'ъ', 'ы', 'ь', 'Э', 'Ю', 'Я'
    )

    val alphabetNumbers = listOf(
        21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5, 30,
        27, 8, 18, 10, 33, 31, 32, 19, 7, 17
    )

    val text = "СООБЩЕНИЕ".toUpperCase()
    val keyword = "ПОЛЕ".toUpperCase()

    val encryptedText = encrypt(text, keyword, alphabet, alphabetNumbers)
    println("Зашифрованный текст: $encryptedText")
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
