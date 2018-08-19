import sys, os, itertools

sys.path.append(os.path.abspath(os.path.join('..')))
from crypto.games.game_cr import GameCR
from crypto.simulator.cr_sim import CRSim
from crypto.ideal.block_cipher import BlockCipher
from crypto.primitives import *
from crypto.tools import *

"""
Let E: {0,1}^k x {0,1}^l -> {0,1}^l be a block cipher (with inverse E_I) and let
T_E denote the time to compute E or E_I. Let D be the set of all strings whose
length is a positive multiple of l. Define the hash function
H: {0,1}^k x D -> {0,1}^l as follows:
"""

def H(K, M):
    """
    Hash function.

    :param K: Key used by the hash function, must be of size k_bytes
    :param M: Message hashed by the function, must be of length n * l_bytes
    """
    if len(M) % l_bytes != 0:
        raise ValueError("Input length is outside of parameters.")

    W = []
    C = ["\x00" * l_bytes]
    M = split(M, l_bytes)

    for B in M:
        W += [E(K, xor_strings(C[-1], B))]
        C += [E(K, xor_strings(W[-1], B))]

    return C[-1]

"""
[30 points] Show that H is not collision resistant by presenting an O(T_E + l)
time adversary A with Adv^cr_H(A)=1.
"""

def A(K):
    """
    You must fill in this method. We will define variables k, l, k_bytes,
    l_bytes, E, and E_I for you.

    :param K: This is the key used as the seed to the provided hash function
    :return: Return 2 messages, M1 and M2, that your adversary believes collide
    """
    Z = "\x00" * l_bytes 
    O = "\xFF" * l_bytes 
    E0 = E(K, "\x00" * l_bytes)
    E1 = E(K, "\xFF" * l_bytes)
    EI0 = E_I(K, Z)
    EI1 = E_I(K, O)
    M1 = EI0
    M2 = EI0 + EI0
    
    return (M1,M2)


if __name__ == '__main__':
    # Case 1: k = l = 128
    k = 128
    l = 128
    k_bytes = k/8
    l_bytes = l/8
    EE = BlockCipher(k_bytes, l_bytes)
    E = EE.encrypt
    E_I = EE.decrypt

    g = GameCR(H, k_bytes)
    s = CRSim(g, A)

    print "When k=128, n=128:"
    print "The advantage of your adversary is ~" + str(s.compute_advantage())


    # Case 2: k = 64, l = 16
    k = 64
    l = 16
    k_bytes = k/8
    l_bytes = l/8
    EE = BlockCipher(k_bytes, l_bytes)
    E = EE.encrypt
    E_I = EE.decrypt

    g = GameCR(H, k_bytes)
    s = CRSim(g, A)

    print "\nWhen k=64, n=16:"
    print "The advantage of your adversary is ~" + str(s.compute_advantage())
