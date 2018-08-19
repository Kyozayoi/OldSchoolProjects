import sys, os, itertools

sys.path.append(os.path.abspath(os.path.join('..')))
from crypto.primitives import *
from crypto.tools import *
from crypto.ideal.block_cipher import *

"""
Problem 1 [50 points]
Let k,n>=4 be integers and let E:{0,1}^k x {0,1}^n --> {0,1}^n be a blockcipher
(with inverse E_I). Let Kg be the key-generation algorithm that returns a random 
128-bit string as the key. Let Enc be the following encryption algorithm whose
message space is the set of all strings whose length is a positive multiple of n,
meaning these are the allowed messages.

If E is a family of functions, then T_E denotes the time to compute it of its inverse.
All times are worst case.
"""

def Enc(K, M):
    """
    :param K: The key used to encrypt/decrypt the message
    :param M: The plaintext to be encrypted
    :return: return the encryption of plaintext M
    """
    M = split(M,n_bytes)
    M.insert(0,"")
    R = random_string(n_bytes)
    C = [R]
    W = [R]
    for i in range(1,len(M)):
        W.append(add_int_to_string(R, i, n_bytes))
        C.append(E(K, xor_strings(M[i], W[i])))
    return join(C)

"""
1. [10 points] Specify a decryption algorithm Dec such that SE = (Kg,Enc,Dec)
is a symmetric encryption scheme satisfying the correct decryption condition of Slide 3.
"""

def Dec(K, C):
    """
    You must fill in this method. This is the decryption algorithm that the
    problem is asking for. We will define variables k, n, k_bytes, n_bytes,
    E, and E_I for you.

    :param K: The key used to encrypt/decrypt the message
    :param C: The ciphertext to be decrypted
    :return: return the decryption on the ciphertext c
    """
    C = split(C,n_bytes)
    R = C[0]
    W = [R]
    M = []
    for i in range(1, len(C)):
        W.append(add_int_to_string(R, i, n_bytes))
        X = E_I(K, C[i])
        M.append(xor_strings(X, W[i]))
    return join(M)

"""
2. [40 points] Show that this scheme is not IND-CPA secure by presenting
a O(T_E+k+n)-time adversary A making one query to its LR oracle and
achieving Adv^ind-cpa_SE(A) = 1.
"""

def A(lr):
    """
    You must fill in this method. This is the adversary that the problem is
    asking for. We will define variables k, n, k_bytes, n_bytes,
    E, and E_I for you.

    :param lr: This is the oracle supplied by the game.
    :return: return 1 to indicate your adversary believes it is the right world
    and return 0 to indicate that your adversary believes it is in the left
    world.
    """
    hex0 = "\x00"
    M = hex0*n_bytes
    M = M + hex0*(n_bytes-1)
    M = M + "\x01"
    M = M*4
    """
    Message is 00000000 00000001 00000000 00000001 00000000 00000001 00000000 00000001 00000000 00000001
    In a theoretical sense.
    """
    C = lr("\xFF"*n, M)
    
    C = split(C, n_bytes)
    if C[2]==C[1] or C[2]==C[3]:
        return 1
    return 0

from crypto.games.game_lr import GameLR
from crypto.simulator.lr_sim import LRSim

def testDecryption():
    worked = True
    for j in range(100):
        K = random_string(k_bytes)
        num_blocks = random.randrange(100)
        M = random_string(num_blocks*n_bytes)
        C = Enc(K, M)
        if M != Dec(K, C):
            print "Your decryption function is incorrect."
            worked = False
            break
    if worked:
        print "Your decryption function appears correct."


if __name__ == '__main__':
    
    k = 128
    n = 128
    k_bytes = k/8
    n_bytes = n/8
    EE = BlockCipher(k_bytes, n_bytes)
    E = EE.encrypt
    E_I = EE.decrypt
    

    g = GameLR(Enc, k_bytes)
    s = LRSim(g, A)

    print "When k=128, n=128:"
    print "The advantage of your adversary is ~" + str(s.compute_advantage())
    testDecryption()

    k = 64
    n = 16
    k_bytes = k/8
    n_bytes = n/8
    EE = BlockCipher(k_bytes, n_bytes)
    E = EE.encrypt
    E_I = EE.decrypt
    g = GameLR(Enc, k_bytes)
    s = LRSim(g, A)

    print "When k=64, n=16:"
    print "The advantage of your adversary is ~" + str(s.compute_advantage())
    testDecryption()
