import sys, os, itertools

sys.path.append(os.path.abspath(os.path.join('..')))

from crypto.primitives import random_string
from crypto.tools import *
from Crypto.Hash import HMAC, SHA

import random

def HMAC_SHA1(k, m):
    return HMAC.new(k, m, SHA).digest()

def K():
    return random_string(key_len)

def E_CTR(k, m):
    M = split(m, block_len)
    P = [] 
    C = ["\x00" * block_len]
    for i in range (len(M)):
        P = P + [HMAC_SHA1(k, (C[0] + int_to_string( (i+1), block_len) ) )]
        C += [xor_strings(P[i], M[i])]

    return join(C) 

def D_CTR(k, C):
    c_i = split(C, block_len)
    M = []
    P = []
    for i in range (len (c_i)-1):
        P = P + [HMAC_SHA1(k , (c_i[0] + int_to_string( (i+1), block_len) ) )]	
        M += [xor_strings(P[i], c_i[i + 1])] 

    return join(M)

"""
Problem 1 [50 points]

1. [40 points] Specify a (purely) SHA1-based (Purely SHA1 based means SHA1
is the only cryptographic primitive you may use, you may use HMAC-SHA1).
IND-CPA+INT-CTXT-secure symmetric encryption scheme SE = (K, E, D) by giving
Python code for the E and D (K is defined for you). You may invoke and use 
algorithms and results from the slides. Your scheme should be able to encrypt 
any message in D = {M \in {0,1}* : |M| mod 160 = 0 and 160 <= |M| < 2^64},
where |M| is in bits. The ciphertext should have length 320 + |M| bits, the key
should have length at most 320 bits and both encryption and decryption should be
efficient. Your scheme should have an 80-bit level of security.
"""

# Block and key size in bytes.
block_len = 20 # 160/8
key_len = 40 # 320/8


def E(k, m):
    """
    You must fill in this method. This is the encryption function you will
    define. 

    :param k: Key used by the encryption function, must be key_len bytes
    :param m: Message encrypted by the function, must be element of set D. 

    Returns a (ciphertext, tag) pair.
    """
    if ((((len(m) * 8) % 160 != 0)) or ((len(m) * 8) < 160) or ((len(m) * 8) >= 2**64)):
        return None, None

    c = E_CTR(k, m)
    t = HMAC_SHA1(k, c)

    return c, t

def D(k, (c, t)):
    """
    You must fill in this method. This is the decryption function you will
    define. 

    :param k: Key used by the decryption function, must be key_len bytes
    :param c: Ciphertext to decrypt.
    :param t: Tag to check. 

    Returns message corresponding to (c, t) or None.
    """
    m = D_CTR(k,c)

    if (t == HMAC_SHA1(k, c)):
        return m
    else:
        return None

"""
2. [10 points] Provide a convincing and **succinct** justification for the
security of your scheme that indicates what assumptions you make and on what
primitives you make them.
--&--
For this assignment, we want an Encrypt-then-MAC design, in order to be both 
IND-CPA secure and INT-CTXT secure. Having both our ciphertext and our tag be
relatively indistinguishable allows us to have unique encryptions that are 
difficult to collectively attack. We're limited by our HMAC_SHA1 as our primitive.
Because I'm using the CTR mode algorithm from the slides, we don't need any
inverses in order to decrypt our encryption, which makes it a lot easier for us.
"""

def A_1(lr):
    """
    Provided for experimentation (won't be graded).
    :param lr: This is the oracle supplied by GameLR, you can call this
    oracle to get an encryption of the data you pass into it.
    :return: return 1 to indicate your adversary believes it is the right world
    and return 0 to indicate that your adversary believes it is in the left
    world.
    """
    return None


def A_2(enc, dec):
    """
    Provided for experimentation (won't be graded).
    :param enc: This is an oracle supplied by GameINTCTXT, you can call this
    oracle to get an encryption of the data you pass into it.
    :param dec: This is an oracle supplied by GameINTCTXT, you can call this
    oracle to get a decryption of the data you pass into it.
    """
    return None

from crypto.games.game_lr import GameLR
from crypto.simulator.lr_sim import LRSim

from crypto.games.game_int_ctxt import GameINTCTXT
from crypto.simulator.ctxt_sim import CTXTSim

if __name__ == '__main__':
    key = K()

    for j in range(100):
        blocks = random.randrange(1,100)
        m = random_string(blocks*block_len)
        c, t = E(key, m)
        bad = False
        if m != D(key, (c, t)):
            print [len(m), len( E(key, m)[0]),  len(D(key, (c, t)))]
            bad = True
    if bad:
        print "Your Decryption function is incorrect"
    else:
        print "Your Decryption function is correct"

    g1 = GameLR(E, 16)
    s1 = LRSim(g1, A_1)

    g2 = GameINTCTXT(E, D, key_len)
    s2 = CTXTSim(g2, A_2)

    print "The advantage of A_1 adversary is ~" + str(s1.compute_advantage())
    print "The advantage of A_2 adversary is ~" + str(s2.compute_advantage(1000))

