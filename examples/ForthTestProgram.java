public class ForthTestProgram {

    public static int DayOfWeek = 0;

    public static void main(String[] args) {

        try {
            

            var wayOfKings = new Book.Builder("5433421342")
                                    .withAuthor("Brandon", "Sanderson")
                                    .withTitle("The Way Of Kings")
                                    .publishedIn(2011)
                                    .inLanguage(Language.ENGLISH)
                                    .build();
            var eastOfEden = new Book.Builder("6623332321")
                                      .withAuthor("John", "Steinbeck")
                                      .withTitle("East Of Eden")
                                      .publishedIn(1954)
                                      .inLanguage(Language.ENGLISH)
                                      .build();
            var lePetitPrince = new Book.Builder("5123252333")
                                        .withAuthor("Antoine", "de Saint-Exupéry")
                                        .withTitle("Le Petit Prince")
                                        .inLanguage(Language.FRENCH)
                                        .build();

            var abuela = new Book.Builder("1231245411")
                                 .withAuthor("Arthor", "Dorros")
                                 .withTitle("Abuela")
                                 .inLanguage(Language.SPANISH)
                                 .build();
            var mathmatica = new Book.Builder("1939019334")
                                     .withAuthor("Isaac", "Newton")
                                     .withTitle("Philosophiæ Naturalis Principia Mathematica")
                                     .inLanguage(Language.UNKNOWN)
                                     .publishedIn(1687)
                                     .build();

            SaleSlot[] slots = {
                new SaleSlot(wayOfKings, 12, 0),
                new SaleSlot(eastOfEden, 7, 3),
                new SaleSlot(lePetitPrince, 5, 2),
                new SaleSlot(abuela, 4, 5),
                new SaleSlot(mathmatica, 20, 8)
            };

            var bookshop = new Bookshop(slots);

            Customer[] customers = {
                new Customer("John", "Smith", new LanguageSpecific(Language.FRENCH)),
                new Customer("Herold", "McCheep", new OnlyOnWeekend(new LowestCost())),
                new Customer("Dyno", "Flower", new AntiqueCollector(1800)),
                new Customer("Xenon", "Futureman", new AntiqueCollector(3000)),
                new Customer("Richard", "Banks", new LanguageSpecific(Language.SPANISH))
            };

            while(DayOfWeek < 30) {
                System.out.println("---------");
                System.out.println("Start of day " + DayOfWeek);



                if (DayOfWeek % 3 == 0) {
                    System.out.println("Recieved Shipment of Books!");
                    bookshop.recieveShipment();
                }

                bookshop.printInventory();
                System.out.println();
                for (Customer customer : customers) {
                    customer.shop(bookshop);
                }

                DayOfWeek++;
            }

        } catch (InvalidBookException e) {
            System.out.println(e);
            return;
        }
    }

    public static class Customer {
        private ShoppingBehavior behavior;

        private String firstName;
        private String lastName;

        public Customer(String firstName, String lastName, ShoppingBehavior behavior) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.behavior = behavior;
        }

        public void shop(Bookshop bookshop) {
            System.out.print(this.firstName + " " + this.lastName + ": ");
            this.behavior.shop(bookshop);
        }
    }

    public static interface ShoppingBehavior {
        void shop(Bookshop bookshop);
    }

    public static class AntiqueCollector implements ShoppingBehavior {
        private int maximumPublishYear;
        public AntiqueCollector(int maximumPublishYear) {
            this.maximumPublishYear = maximumPublishYear;
        }


        public void shop(Bookshop bookshop) {
            var inventory = bookshop.getInventory();

            var index = -1;

            for (var i = 0; i < inventory.length; i++) {
                if (inventory[i].inStock() && inventory[i].getBook().getPublishingYear() <= this.maximumPublishYear) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Dang! The bookstore is all out of books made before " + this.maximumPublishYear + "!");
                return;
            }

            var slot = inventory[index];

            bookshop.buy(index, 1);
            System.out.println("I bought 1 copies of ISBN " + slot.getBook().getISBN() + " for only $" + bookshop.totalCost(index, 1) + ". What a deal!");
        }
    }

    public static class LanguageSpecific implements ShoppingBehavior {
        private Language perferedLanguage;

        public LanguageSpecific(Language perferedLanguage) {
            this.perferedLanguage = perferedLanguage;
        }

        public void shop(Bookshop bookshop) {

            var inventory = bookshop.getInventory();

            var index = -1;

            for (var i = 0; i < inventory.length; i++) {
                if (inventory[i].inStock() && inventory[i].getBook().getLanguage() == this.perferedLanguage) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Dang! The bookstore is out of all " + this.perferedLanguage.toString() + " books!");
                return;
            }

            var slot = inventory[index];

            bookshop.buy(index, 1);
            System.out.println("I bought 1 copies of ISBN " + slot.getBook().getISBN() + " for only $" + bookshop.totalCost(index, 1) + ". What a deal!");

        }
    }    

    public static class LowestCost implements ShoppingBehavior {

        public void shop(Bookshop bookshop) {
            var inventory = bookshop.getInventory();

            var lowestCost = inventory[0].costPerUnit();
            var lowestCostIndex = 0;

            for (var i = 0; i < inventory.length; i++) {
                var cost = inventory[i].costPerUnit();
                if (cost < lowestCost && inventory[i].inStock()) {
                    lowestCost = cost;
                    lowestCostIndex = i;
                }
            }

            var slot = inventory[lowestCostIndex];

            if (!slot.inStock()) {
                System.out.println("There was nothing for me to buy!");
                return;
            }


            bookshop.buy(lowestCostIndex, 1);
            System.out.println("I bought a copy of ISBN " + slot.getBook().getISBN() + " for only $" + slot.costPerUnit() + ". What a deal!");
        }
    }

    public static class OnlyOnWeekend implements ShoppingBehavior {
        private ShoppingBehavior baseBehavior;

        public OnlyOnWeekend(ShoppingBehavior baseBehavior) {
            this.baseBehavior = baseBehavior;
        }
        
        public void shop(Bookshop bookshop) {
            if (DayOfWeek % 7 == 0 || DayOfWeek % 7 == 6) {
                System.out.println("Its the weekend! Time to shop at the bookstore!");
                this.baseBehavior.shop(bookshop);
            } else {
                System.out.println("Its a weekday! I can't wait to pickup some books this weekend!");
            }
        }
    }

    public static class Bookshop {
        private SaleSlot[] slots;

        public Bookshop(SaleSlot[] slots) {
            this.slots = slots;
        }

        public void recieveShipment() {
            for (var slot : this.slots) {
                if (slot.inStock()) {
                    slot.restock((slot.unitsRemaining() / 2) + 1);
                } else {
                    slot.restock(7);
                }
            }
        }

        public int buy(int index, int amount) {
            return this.slots[index].buy(amount);
        }

        public double totalCost(int index, int amount) {
            return this.slots[index].costPerUnit() * amount;
        }

        public SaleSlot[] getInventory() {
            return this.slots;
        }

        public void printInventory() {
            for (var slot : this.getInventory()) {
                var book = slot.getBook();
                System.out.println(slot.unitsRemaining() + " units of " + book.getTitle() + " (" + book.getISBN() + ") " + "remaining at $" + slot.costPerUnit());
            }
        }
    }

    public static class SaleSlot {
        private static final int MAX_UNIT_CAPACITY = 20;
        private static final double SCARCITY_MULTIPLIER = 1.1;
        private static final double ABUNDANCE_MULTIPLIER = 0.9;
        private static final int SCARCITY_MIN_UNITS = 7;
        private static final int ABUNDANCE_MIN_UNITS = 13;

        private Book book;
        private int baseCost;
        private int units;

        public SaleSlot(Book book, int baseCost, int initialStock) {
            this.book = book;
            this.baseCost = baseCost;
            this.restock(initialStock);
        }

        public boolean inStock() {
            return this.units > 0;
        }

        public double costPerUnit() {
            if (this.units <= SCARCITY_MIN_UNITS) {
                return this.baseCost * SCARCITY_MULTIPLIER;
            } else if (this.units >= ABUNDANCE_MIN_UNITS) {
                return this.baseCost * ABUNDANCE_MULTIPLIER;
            } else {
                return this.baseCost;
            }
        }

        public void restock(int amount) {
            this.units += amount;

            if (this.units > MAX_UNIT_CAPACITY) {
                this.units = MAX_UNIT_CAPACITY;
            }
        }

        public Book getBook() {
            return this.book;
        }

        public int unitsRemaining() {
            return this.units;
        }

        public int buy(int amount) {
            if (amount > this.units) {
                amount = this.units;
            }

            this.units -= amount;
            return amount;
        }
    }

    public static class Book {
        private ISBN isbn;

        private Author author;

        private String title;
        private int publishingYear;
        private Language language;

        private Book(ISBN isbn) {
            this.isbn = isbn;
        }

        public ISBN getISBN() {
            return this.isbn;
        }

        public String getTitle() {
            return this.title;
        }

        public int getPublishingYear() {
            return this.publishingYear;
        }

        public Language getLanguage() {
            return this.language;
        }

        public static class Builder {
            private Book book;

            public Builder(String isbn) {
                this.book = new Book(new ISBN(isbn));
            }
            
            public Builder withAuthor(String firstName, String lastName) {
                this.book.author = new Author(firstName, lastName);
                return this;
            }

            public Builder withTitle(String title) {
                this.book.title = title;
                return this;
            }

            public Builder publishedIn(int year) {
                this.book.publishingYear = year;
                return this;
            }

            public Builder inLanguage(Language language) {
                this.book.language = language;
                return this;
            }

            public Book build() throws InvalidBookException {
                if (!this.book.isbn.valid()) {
                    throw new InvalidBookException(this.book.isbn, "isbn is not valid");
                }

                if (!this.book.author.valid()) {
                    throw new InvalidBookException(this.book.isbn, "author is not valid");
                }

                if (this.book.title == null || this.book.title == "") {
                    throw new InvalidBookException(this.book.isbn, "title is not valid");

                }

                if (this.book.language == null) {
                    this.book.language = Language.UNKNOWN;
                }

                return this.book;
            }
        }
    }


    public static class Author {
        private String firstName;
        private String lastName;

        public Author(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public boolean valid() {
                // We only care that an author has a first name set.
                return this.firstName != null && this.firstName != "";
        }

        public String toString() {
            return this.firstName + " " + this.lastName;
        }
    }

    public static class ISBN {
        private String value;

        public ISBN(String value) {
            this.value = value;
        }

        public boolean valid() {
            // The rules are more complicated than this, 
            // but for our purposes it will do.
            return this.value != null && this.value != "";
        }


        public String toString() {
            return this.value;
        }
    }

    public enum Language {
        ENGLISH,
        FRENCH,
        SPANISH,
        UNKNOWN,
    }

    public static class InvalidBookException extends Exception {
        public InvalidBookException(ISBN isbn, String reason) {
            super("book isbn " + isbn + " is not valid: " + reason);
        }
    }
}
