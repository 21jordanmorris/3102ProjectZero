package primegeneratordemo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A test bed for the PrimeGenerator implementation.  
 * @author Duncan, Jordan Morris
 * @since 8-21-2018
 * @see PrimeGeneratorAPI, PrimeGenerator
 */

public class PrimeGeneratorDemo 
{
    public static void main(String[] args) 
    {
        
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter a positive integer -> ");
        int n = cin.nextInt();
        PrimeGenerator primeSeq = new PrimeGenerator(n,'n');
        System.out.printf("Is %d a prime number? %b%n",n,primeSeq.isPrime(n));
        System.out.printf("Prime numbers in [1,%d] are %s.%n",n,primeSeq);
        System.out.printf("The largest prime number in [1,%d] is %d.%n",n,primeSeq.getMax());
        ArrayList<Integer> primes = primeSeq.generate(n);
        System.out.printf("The number of prime numbers in [1,%d] is %d.%n",n,primes.size());        
        //Add code to generate a random prime in [2,n] and the table shown on handout:

		
		
    }
    
}
