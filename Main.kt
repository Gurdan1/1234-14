import java.io.File

fun main() {
    val file = File("lines.txt")
    file.writeText("")

    val lines = mutableListOf<String>()
    while (true) {
        println("Хотите добавить строку в файл? (да/нет)")
        val answer = readLine()?.trim()?.lowercase() ?: "нет"
        if (answer != "да" && answer != "д") break

        print("Введите строку: ")
        val line = readLine()?.trim() ?: ""
        lines.add(line)
    }
    if (lines.isNotEmpty()) {
        file.writeText(lines.joinToString("\n"))
    } else {
        file.writeText("")
    }

    if (lines.isEmpty()) {
        println("Файл пуст.")
        println("Количество строк: 0")
    } else {
        val wordCounts = lines.map { it.split(Regex("\\s+")).filter { it.isNotEmpty() }.size }
        val totalLines = lines.size
        val minWords = wordCounts.minOrNull() ?: 0
        val maxWords = wordCounts.maxOrNull() ?: 0

        println("\nСодержимое файла:")
        lines.forEach { println(it) }

        println("\nИнформация:")
        println("Количество строк: $totalLines")
        println("Минимальное количество слов в строке: $minWords")
        println("Максимальное количество слов в строке: $maxWords")
    }

    println("\nХотите заменить строку? (да/нет)")
    val replaceAnswer = readLine()?.trim()?.lowercase() ?: "нет"
    if (replaceAnswer == "да" || replaceAnswer == "д") {
        if (lines.isEmpty()) {
            println("Невозможно заменить строку — файл пуст.")
        } else {
            print("Введите номер строки для замены (от 1 до ${lines.size}): ")
            val lineNumInput = readLine()?.trim()
            val lineNum = lineNumInput?.toIntOrNull()

            if (lineNum == null || lineNum < 1 || lineNum > lines.size) {
                println("Неверный номер строки.")
            } else {
                print("Введите новую строку: ")
                val newLine = readLine()?.trim() ?: ""
                lines[lineNum - 1] = newLine
                file.writeText(lines.joinToString("\n"))

                println("\nОбновлённое содержимое файла:")
                lines.forEachIndexed { index, line ->
                    val words = line.split(Regex("\\s+")).filter { it.isNotEmpty() }.size
                    println("${index + 1}: $line (слов: $words)")
                }
            }
        }
    }
}