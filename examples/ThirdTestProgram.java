using System;

class ExampleProgramThree
{
    public interface Speaker
    {
        void Speak();
    }

    public abstract class Animal : Speaker
    {
        public String Name;
        public Animal(String name)
        {
            this.Name = name;
        }

        public virtual int WalkingSpeed()
        {
            return 5;
        }

        public abstract void Speak();
    }

    public class Dog : Animal
    {
        public Dog() : base("dog")
        {
        }

        public override void Speak()
        {
            Console.WriteLine("Woof!");
        }
    }

    public class Cat : Animal
    {
        public Cat() : base("cat")
        {
        }

        public override void Speak()
        {
            Console.WriteLine("Meow!");
        }

        public override int WalkingSpeed()
        {
            return base.WalkingSpeed() * 2;
        }
    }

    public class Replicator : Speaker
    {
        public String Sound;
        public Replicator(String sound)
        {
            this.Sound = sound;
        }

        public void Speak()
        {
            Console.WriteLine(this.Sound);
        }
    }

    public static void Main(String[] args)
    {
        Dog dog = new Dog();
        dog.Speak();
        Animal animal = dog;
        animal.Speak();
        Console.WriteLine("The current animal has a walking speed of: " + animal.WalkingSpeed());
        animal = new Cat();
        animal.Speak();
        Console.WriteLine("The current animal has a walking speed of: " + animal.WalkingSpeed());
        Speaker speaker = new Replicator("I AM A CAT. MEOW!");
        speaker.Speak();
    }
}
