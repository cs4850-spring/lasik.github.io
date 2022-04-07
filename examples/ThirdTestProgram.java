class ExampleProgramThree {

    public static interface Speaker {
        public void speak();
    }

    public abstract static class Animal implements Speaker {
        public String name;

        public Animal(String name) {
            this.name = name;
        }

        public int walkingSpeed() {
            return 5;
        }

        public abstract void speak();
    }


    public static class Dog extends Animal {

        public Dog() {
            super("dog");
        }

        public void speak() {
            System.out.println("Woof!");
        }
    }

    public static class Cat extends Animal {

        public Cat() {
            super("cat");
        }

        public int walkingSpeed() {
            return super.walkingSpeed() * 2;
        }

        public void speak() {
            System.out.println("Meow!");
        }
    }

    public static class Replicator implements Speaker {
        public String sound;

        public Replicator(String sound) {
            this.sound = sound;
        }

        public void speak() {
            System.out.println(this.sound);
        }
    }


    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.speak();

        Animal animal = dog;
        animal.speak();

        System.out.println("The current animal has a walking speed of: " + animal.walkingSpeed());

        animal = new Cat();
        animal.speak();

        System.out.println("The current animal has a walking speed of: " + animal.walkingSpeed());


        Speaker speaker = new Replicator("I AM A CAT. MEOW!");
        speaker.speak();
    }
}
