package byexample.functions

fun printAll(vararg messages: String) {
  for (m in messages) println(m)
}

fun printAllWithPrefix(vararg messages: String, prefix: String) {
  for (m in messages) println(prefix + m)
}

fun main() {
  printAll("Hello", "Hallo", "Salut", "Hola", "你好")
  println()
  printAllWithPrefix(
    "Hello", "Hallo", "Salut", "Hola", "你好",
    prefix = "Greeting: "
  )
}
