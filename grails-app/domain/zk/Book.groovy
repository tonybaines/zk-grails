package zk

class Book {
    String title
    Author author
    
    static belongsTo = Author

    static constraints = {
    }

    static def findByAuthorId(id) {
        def author = findAuthorById(id)
        findAllByAuthor(author)
    }
}
