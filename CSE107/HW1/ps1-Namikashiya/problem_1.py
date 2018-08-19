import sys, os, itertools

sys.path.append(os.path.abspath(os.path.join('..')))
from crypto.primitives import *
from crypto.tools import *
from crypto.ideal.block_cipher import *

"""
Worked with Truman Tse on this assignment
  "student_id": "A12852055",
  "first_name": "Truman",
  "last_name": "Tse",
"""

"""
Problem 1 [60 points]
Let E be a blockcipher  E:{0, 1}^k x {0, 1}^n --> {0, 1}^n
and E_I be its inverse.
Define F: {0, 1}^k+n x {0, 1}^n --> {0, 1}^n as shown below.

Notes:
Sizes in comments are bits, sizes in code are in bytes (bits / 8).
In the code K1\in{0,1}^k and K2,M\in{0,1}^n
"""

# Arbitrary choices of k, n.
k = 128
n = 64

# Block & key size in bytes.
k_bytes = k/8
n_bytes = n/8
 
def F(K, M):
    """
    Blockcipher F constructed from blockcipher E.

    :param K: blockcipher key
    :param M: plaintext message
    :return: ciphertext
    """
    K1 = K[:k_bytes]
    K2 = K[k_bytes:]

    C = E(K1, xor_strings(M,K2))

    return C

"""
(a) [8 points] Is F a blockcipher? Answer YES or NO and prove your answer correct.
--&--
YES F is a blockcipher.
F's range and domain are based off of E's range and domain, which are the same values -> {0, 1}^n
We can also create the inverse of F, F_I, using E_I which we know exists from the statements above.
*******************************
def F_I(K, C):
    K1 = K[:k_bytes]
    K2 = K[k_bytes:]
    
    Mx = E_I(K1, C)
    M = xor_strings(Mx, K2)
    
    return M
*******************************
We don't know exactly how E_I works, but given the format of E, we can assume that E_I returns the Message with the Cipher as an input.
In order to show that F_I is the inverse of F, we take    F_I(F(x)) 
                                                        = F_I(E(K1, x^K2))
                                                        = E_I(K1, E(K1, x^K2)) ^ K2
                                                        = x ^ K2 ^ K2
                                                        = x
Because we can prove that F's range and domain are the same and that there exists an inverse of F, F is a blockcipher.
"""

"""
(b) [8 points] How much time is taken by a 3-query exhaustive key-search
               attack on F? Your answer should be a function of T_E, k, n.
--&--
O(2^k * (T_E + n) )
"""

"""
(c) [18 points] Give a 1-query adversary A1 that has advantage
                Adv^kr_F(A1) = 1 and running time O(T_E + k + n).
"""

def A1(fn):
    """
    You must fill in this method. This is the adversary that the problem is
    asking for.

    :param fn: This is the oracle supplied by GameKR, you can call this
    oracle to get an "encryption" of the data you pass into it.
    :return: return the a string that represents a key guess.
    """
    M = '\x00' * n_bytes
    C = fn(M)
    Kb = xor_strings(C, M)
    Ka = xor_strings(C, Kb)
    Kz = Ka + Kb
    return Kz

"""
(d) [26 points] Give a 3-query adversary A3 that has advantage Adv^kr_F(A3) = 1
                and running time O(2^k * (T_E + k + n)).
"""

# Smaller choices of k, n.
k = 16
n = 64
k_bytes = k/8
n_bytes = n/8

def A3(fn):
    """
    You must fill in this method. This is the adversary that the problem is
    asking for.

    :param fn: This is the oracle supplied by GameKR, you can call this
    oracle to get an "encryption" of the data you pass into it.
    :return: return the a string that represents a key guess.
    """
    M = []
    C = []
    for x in range(0, 3):
        M.append(int_to_string(x, n_bytes))
        C.append(fn(M[-1]))
                 
    for y in range(0, 2**k):
        A = int_to_string(y, k_bytes)
        correct = True
        for z in range(0, 3):
            if E(A, M[z]) != C[z]:
                correct = False
        if correct:
            return A

from crypto.games.game_kr import GameKR
from crypto.simulator.kr_sim import KRSim

if __name__ == '__main__':
    k = 128
    n = 64
    k_bytes = k/8
    n_bytes = n/8
    EE = BlockCipher(k_bytes, n_bytes)
    E = EE.encrypt
    E_I = EE.decrypt
    g1 = GameKR(F, k_bytes+n_bytes, n_bytes)
    s1 = KRSim(g1, A1)

    print "The advantage of your adversary A1 is ~" + str(s1.compute_advantage(20))

    k = 8
    n = 64
    k_bytes = k/8
    n_bytes = n/8
    EE = BlockCipher(k_bytes, n_bytes)
    E = EE.encrypt
    E_I = EE.decrypt
    g3 = GameKR(F, k_bytes+n_bytes, n_bytes)
    s3 = KRSim(g3, A3)
    print "The advantage of your adversary A3 is ~" + str(s3.compute_advantage(20))
