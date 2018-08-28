package primegeneratordemo;

import java.util.ArrayList;

/**
 * Provides methods that perform the Sieve of Eratosthenes
 * and brute-force algorithm or assist in doing so.
 * CSC 3102 Programming Project # 0
 * @author Jordan Morris
 * @since 8-22-2018
 * @see PrimeGeneratorAPI
 */

public class PrimeGenerator implements PrimeGeneratorAPI {

    /**
     * A reference to a boolean array.
     */
    private boolean[] sequence;

    /**
     * An integer one less than the capacity of the array to account
     * for index 0 in the sequence array.
     */
    private int length;

    /**
     * Creates a Boolean sequence of length 100 using the Sieve of
     * Eratosthenes, where sequence[k] is true, when k is prime and
     * false when k is composite.
     */
    public PrimeGenerator() {
        sequence = new boolean[100];
        length = sequence.length;
        eratosthenesSieve(sequence);
    }

    /**
     * Generates a boolean sequence [0,...,length] that indicates
     * whether sequence[k] is prime or composite using the
     * specific algorithm
     * @param n an integer >= 1
     * @param alg 'E' or 'e' for the Sieve of Eratosthenes sieve
     * and 'n' or 'N' for the naive/brute-force algorithm
     * @throws IllegalArgumentException when n is less than 1 or
     * parameter alg is not 'n', 'N', 'E', or 'e'
     */
    public PrimeGenerator(int n, char alg) throws IllegalArgumentException {
        if(n < 1)
            throw new IllegalArgumentException("Must enter a positive non-zero integer.");
        else if(alg != 'n' && alg != 'N' && alg != 'e' && alg != 'E')
            throw new IllegalArgumentException("Parameter alg must be one of 'n', 'N', 'E', or 'e'");
        else {
            sequence = new boolean[n + 1];
            length = sequence.length;
            if (alg == 'n' || alg == 'N') {
                naiveSieve(sequence);
            } else {
                eratosthenesSieve(sequence);
            }
        }
    }

    /**
     * An auxiliary method that sets seq[k] to true if k is prime
     * and false if k is composite using the Eratosthenes Sieve
     * algorithm.
     * @param seq a boolean array that indicates whether or not
     * k is prime
     */
    private void eratosthenesSieve(boolean[] seq) {
        length = seq.length;
        seq[0] = false;
        seq[1] = false;
        if(length >= 2) {
            for(int i = 2; i < length; i++)
                seq[i] = true;
            for(int p = 2; p*p <= length; p++) {
                if(seq[p] == true) {
                    for(int i = p*2; i < length; i+= p)
                        seq[i] = false;
                }
            }
        }
    }

    /**
     * An auxiliary method that sets seq[k] to true if k is prime
     * and false if k is composite using the brute-force algorithm.
     * @param seq a boolean array that indicates whether or not
     * k is prime
     */
    private void naiveSieve(boolean[] seq) {
        length = seq.length;
        for(int i = 0; i < length; i++) {
            seq[i] = isPrime(i);
        }
    }

    public ArrayList<Integer> generate(int n) throws IllegalArgumentException {
        if(n > length)
            throw new IllegalArgumentException("Upper bound cannot exceed size of current sequence");
        ArrayList<Integer> upperBoundedArray = new ArrayList<Integer>();
        for(int i = 0; i <= n; i++) {
            if(sequence[i] == true)
                upperBoundedArray.add(i);
        }
        return upperBoundedArray;
    }

    public boolean isPrime(int n) {
        if(n < 2)
            return false;
        else if(n == 2 || n == 3 || n == 5)
            return true;
        else if(n%2 == 0)
            return false;
        else if(((n%6)-1 == 0) || ((n%6)-5 == 0)) {
            for(int i = 3; i <= Math.sqrt(n); i+=2) {
                if(n%i == 0)
                    return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    public int getPrime(int nth) throws IllegalArgumentException {
        if(nth > (size()/Math.log(size())) * (1 + 0.992/Math.log(size()))) {
            throw new IllegalArgumentException("Parameter 'nth' is invalid");
        }
        else {
            return nth;
        }
    }

    public int getMax() throws IllegalArgumentException {
        for(int i = sequence.length-1; i >= 0; i--) {
            if(sequence[i] == true)
                return i;
        }
        throw new IllegalArgumentException("There are no prime numbers in the given set of numbers");
    }

    public int size() {
        return length;
    }

    public String toString() {
        String pseq = "[";
        for(int i = 0; i < size(); i++) {
            if(sequence[i] == true)
                pseq += i + ", ";
        }
        if(pseq.length() == 1)
            return pseq + "]";
        return pseq.substring(0, pseq.length()-2) + "]";
    }
}