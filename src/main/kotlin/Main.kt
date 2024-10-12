fun main() {
    val alphabet = listOf(
        'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О',
        'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ы', 'Ъ', 'Э', 'Ю', 'Я'
    )
    val alphabetNumbers = listOf(
        21, 13, 4, 20, 22, 1, 25, 12, 24, 14, 2, 28, 9, 23, 3, 29, 6, 16, 15, 11, 26, 5, 30,
        27, 8, 18, 10, 33, 31, 32, 19, 7, 17
    )

    val alphabetMap = alphabet.zip(alphabetNumbers).toMap()
    val reverseAlphabetMap = alphabetNumbers.zip(alphabet).toMap()

    fun getShiftedChar(char: Char, shift: Int, encode: Boolean = true): Char {
        val upperChar = char.uppercaseChar()
        val charNumber = alphabetMap[upperChar] ?: return char

        val newNumber = if (encode) {
            (charNumber + shift - 1) % 33 + 1
        } else {
            (charNumber - shift - 1 + 33) % 33 + 1
        }

        return reverseAlphabetMap[newNumber] ?: char
    }

    print("Зашифровать (1) или расшифровать (2): ")
    val mode = readLine()?.toIntOrNull()

    print("Введите сообщение: ")
    val inputText = readLine() ?: return

    print("Введите ключевое слово: ")
    val key = readLine()?.uppercase() ?: return

    val result = StringBuilder()
    for (i in inputText.indices) {
        val currentChar = inputText[i]

        if (!currentChar.isLetter()) {
            result.append(currentChar)
            continue
        }

        val keyChar = key[i % key.length]
        val shift = alphabetMap[keyChar] ?: 0

        val shiftedChar = if (mode == 1) {
            getShiftedChar(currentChar, shift, encode = true)
        } else {
            getShiftedChar(currentChar, shift, encode = false)
        }

        result.append(if (currentChar.isLowerCase()) shiftedChar.lowercaseChar() else shiftedChar)
    }


    println("Результат: $result")
}