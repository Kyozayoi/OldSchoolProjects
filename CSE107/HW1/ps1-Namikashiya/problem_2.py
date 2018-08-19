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
Problem 2 [40 points]
Let E1:{0, 1}^k x {0, 1}^n --> {0, 1}^n and
E2:{0, 1}^k x {0, 1}^n --> {0, 1}^n be blockciphers.
Let E1_I and E2_I be their inverses.
Define E: {0, 1}^k x {0, 1}^n --> {0, 1}^n as shown below.

Notes:
Sizes in comments are bits, sizes in code are in bytes (bits / 8).
In the code K\in{0,1}^k and M\in{0,1}^n.
"""

# Arbitrary choices of k, n.
k = 128
n = 128
k_bytes = k/8
n_bytes = n/8

def E(K, M):
    """
    Blockcipher E constructed from blockciphers E1 and E2.
    
    :param K: This is the key for the block cipher.
    :param M: Message for the block cipher.
    :return: Ciphertext.
    """

    C1 = E1(K,M)
    C2 = E2(K,C1)
    return C2

"""
(a) [10 points] Prove that E is a blockcipher.
--&--
E's range and domain is based on the result of E1's and E2's range and domain, which in this case are equal -> {0,1}^n
The next step is to prove that there's an inverse for E, E_I, which we can create from looking at E.
***********************
def E_I(K, C):
    C1 = E2_I(K, C)
    M = E1_I(K, C1)
    
    return M
***********************
The message is essentially being encrypted twice, so the inverse of E would be trying to decypt the message twice using the inverse of E1 and E2, which we have been told exist.
                      E_I(E(x))
                    = E_I(E2(E1(x))
                    = E1_I(E2_I(E2(E1(x))))
                    = E1_I(E1(x))
                    = x
Because we can prove that E's range and domain are the same and that there exists an inverse of E, E is a blockcipher.
"""

"""
(b) [30 points] Let E1, E1_I be given. Specify in code the following:
                
                A blockcipher E2:{0, 1}^k x {0, 1}^n --> {0, 1}^n
                of your choice, allowed to depend on E1
                and its inverse E1_I:{0, 1}^k x {0, 1}^n --> {0, 1}^n
                such that the time to compute E2 is O(T_E1+k+n).
                
                A 1-query adversary A having running time O(T_E+k+n) and 
                achieving advantage Adv^kr_E(A)=1 against E, where E is defined as
                above based on the given E1 and your E2.
"""

def E2(K, M):
    """
    You must fill in this method. This is the E2 that the problem is
    asking for.
    
    :param K: This is the key for the blockcipher.
    :param M: Message for the blockcipher.
    :return: Ciphertext.
    """
    K1, K2 = split(K)
    Ka, Kb = split(K1)
    Kc, Kd = split(K2)
    KX = Kc + Ka + Kd + Kb
    MX = E1(K,M) 
    C = xor_strings(MX, KX)
    return C

def E2_I(K, C):
    """
    You must fill in this method. This is the E2_I that the problem is
    asking for.
    
    :param K: This is the key for the blockcipher.
    :param C: Ciphertext for the blockcipher.
    :return: Message.
    """
    K1, K2 = split(K)
    Ka, Kb = split(K1)
    Kc, Kd = split(K2)
    KX = Kc + Ka + Kd + Kb
    MX = xor_strings(C, KX)
    M = E1_I(K,MX)
    return M

def A(fn):
    """
    You must fill in this method. This is the adversary that the problem is
    asking for.

    :param fn: This is the oracle supplied by GameKR, you can call this
    oracle to get an "encryption" of the data you pass into it.
    :return: return the a string that represents a key guess.
    """
    M = '\x00' * n_bytes
    C = fn(M)
    KX = xor_strings(C, M)
    K1, K2 = split(KX)
    Kc, Ka = split(K1)
    Kd, Kb = split(K2)
    K = Ka + Kb + Kc + Kd
    return K

from crypto.games.game_kr import GameKR
from crypto.games.game_tkr import GameTKR
from crypto.simulator.kr_sim import KRSim

if __name__ == '__main__':
    k = 128
    n = 128
    k_bytes = k/8
    n_bytes = n/8
    EE = BlockCipher(k_bytes, n_bytes)
    E1 = EE.encrypt
    E1_I = EE.decrypt

    worked = True
    for j in range(100):
        K = random_string(k_bytes)
        M = random_string(n_bytes)
        C = E2(K, M)
        if M != E2_I(K, C):
            worked = False
            print "Your E2_I is incorrect."
            break
    if worked:
        print "Your E2_I appears correct."
    
    
    g = GameKR(E, k_bytes, n_bytes)
    s = KRSim(g, A)

    print "The advantage of your adversary A is ~" + str(s.compute_advantage(20))
