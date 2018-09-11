package primegeneratordemo;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A test bed for the PrimeGenerator implementation.
 * CSC 3102 Programming Project # 0
 * @author Duncan, Jordan Morris
 * @since 9-1-2018
 * @see PrimeGeneratorAPI , PrimeGenerator
 */

public class PrimeGeneratorDemo
{
    public static void main(String[] args)
    {

        Scanner cin = new Scanner(System.in);
        System.out.print("Enter a positive integer -> ");
        int n = cin.nextInt();
        PrimeGenerator primeSeq = new PrimeGenerator(n,'e');
        System.out.printf("Is %d a prime number? %b%n",n,primeSeq.isPrime(n));
        System.out.printf("Prime numbers in [1,%d] are %s.%n",n,primeSeq);
        System.out.printf("The largest prime number in [1,%d] is %d.%n",n,primeSeq.getMax());
        ArrayList<Integer> primes = primeSeq.generate(n);
        System.out.printf("The number of prime numbers in [1,%d] is %d.%n",n,primes.size());
        Random generator = new Random(System.currentTimeMillis());
        int randInt = generator.nextInt(primes.size());
        System.out.printf("A random prime number in [2,%d] is %d%n%n", n, primeSeq.getPrime(randInt));

        System.out.println("Empirical Analysis of Brute-force vs Sieve of Eratosthenes");
        System.out.println("Prime Sequence Generation Algorithms");
        System.out.println("===================================================================================");
        System.out.printf("%-10s %-20s %-20s %-10s %-20s%n", "n", "Eratosthenes(ns)", "max Prime in [1,n]",
                "Naive(ns)", "max Prime in [1,n]");
        System.out.println("-----------------------------------------------------------------------------------");

        int maxE, maxN;
        long startE, startN, elapsedE, elapsedN;
        for(int i = 400000; i <= 8000000; i+=400000) {
            startE = System.nanoTime();
            PrimeGenerator primeSeqE = new PrimeGenerator(i, 'e');
            elapsedE = System.nanoTime() - startE;

            maxE = primeSeqE.getMax();

            startN = System.nanoTime();
            PrimeGenerator primeSeqN = new PrimeGenerator(i, 'n');
            elapsedN = System.nanoTime() - startN;

            maxN = primeSeqN.getMax();

            System.out.printf("%-10s %-20s %-20s %-10s %-20s%n", i, elapsedE, maxE, elapsedN, maxN);
        }

    }

}

