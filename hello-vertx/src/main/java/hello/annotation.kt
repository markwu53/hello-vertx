package hello

annotation class Comment(val value: String = "")

@Comment("""
 This is block comment
 """)
class MyHello {
}
