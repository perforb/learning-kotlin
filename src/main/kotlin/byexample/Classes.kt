package byexample

class Customer

class Contact(val id: Int, var email: String) {
  override fun toString(): String {
    return "Contact(id=$id, email='$email')"
  }
}

fun main() {
  val customer = Customer()
  val contact = Contact(1, "mary@gmail.com")

  println(contact.id)
  contact.email = "jane@gmail.com"
  println(contact)
}