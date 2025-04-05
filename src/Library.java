//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// You have to implement a library using Java Class "Library"
// Methods: addBook, issueBook, returnBook, showAvailableBooks
// Properties: Array to store the available books,
// Array to store the issued books


class Library {
    String booksAvailable [];
    String issuedBooks [];
    private String name;
    int l1;
    int l2;

    public Library(String name){
        this.name=name;
        booksAvailable = new String[]{"To Kill a Mockingbird", "1984", "Pride and Prejudice","The Great Gatsby","Moby-Dick","War and Peace","The Catcher in the Rye","The Lord of the Rings","Harry Potter and the Sorcerer's Stone","The Hobbit","Crime and Punishment","Jane Eyre","Brave New World","The Adventures of Huckleberry Finn","The Book Thief","The Chronicles of Narnia","Fahrenheit 451","The Alchemist","Gone with the Wind", null, null};
        issuedBooks=new String[] {"The Catch-22","The Handmaid's Tale","The Picture of Dorian Gray","Wuthering Heights","A Tale of Two Cities"};
         l1 =booksAvailable.length;
         l2 =issuedBooks.length;
    }

    public String addBook(String bookname){
        for(int i =0; i<l1; i++){
            if(booksAvailable[i]==(null)){//if we want to check equality with null we have to use == otherwise .equals()for strings
                booksAvailable[i]=bookname;
                return("Book added");
            }
        }
        return ("No space available");
    }
//
    public String issueBook(String bookname){
        for (int i =0;i<l1;i++) {
            if (booksAvailable[i].equals(bookname)) {
                booksAvailable[i] = null;
                return (name + " Thanks for borrowing the book: " + bookname + " return 7 days later");
            }
        }
        return("Not Available");
    }
//
    public String returnBook(String bookname){
        for(int i =0; i<l1; i++){
            if(booksAvailable[i]==(null)){//if we want to check equality with null we have to use == otherwise .equals()for strings
                booksAvailable[i]=bookname;
                return("Thanks for returning");
            }
        }
        return("No space availble, please keep the book with yourself until we notify you");
    }

     void showAvailableBooks(){
        System.out.println();
         System.out.println("The available books are:");
        for(int i = 0; i < l1 ; i++){
            if(booksAvailable[i]==null) {
                continue;
            }
            System.out.println(booksAvailable[i]);
        }
        System.out.println();
    }
}

 class Main{
    public static void main(String[] args) {
        Library person1 = new Library("Souhardya Bhowal");
        Library person2 = new Library("Suchana Bhowal");

        System.out.println(person1.addBook("To Kill a Mockingbird"));
        person1.showAvailableBooks();
        System.out.println(person1.issueBook("1984"));
        person1.showAvailableBooks();
        System.out.println(person1.returnBook("1984"));
                //
    }
}