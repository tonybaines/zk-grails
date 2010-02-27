class BootStrap {

     def init = { servletContext ->
         def author1 = new zk.Author(name: "Tony Baines")
         new zk.Author(name: "Tony Barnes").save()
         author1.save()
         40.times { i ->
             new zk.Book(title: "Volume $i", author: author1).save()
         }
     }
     def destroy = {
     }
} 