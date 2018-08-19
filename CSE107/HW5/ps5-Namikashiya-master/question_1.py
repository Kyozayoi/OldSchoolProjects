import sys, os, itertools

sys.path.append(os.path.abspath(os.path.join('..')))

from crypto.tools import *
from crypto.ideal.block_cipher import *

"""
Problem 1 [50 points]
Let E: {0, 1}^k x {0, 1)^n -> {0, 1)^n be a block cipher.
Let D = { M \in {0, 1}* : 0 < |M| < n * 2^n and |M| mod n = 0}.
Let T: {0, 1}^k x D -> {0, 1}^n be defined as follows:
"""

def T(K, M):
    if len(M) <= 0 or len(M)*8 > n*(2**n) or len(M) % n_bytes != 0:
        return None

    M = split(M, n_bytes)
    m = len(M)

    M = [None] + M + [int_to_string(m, n_bytes)]
    C = ["\x00" * n_bytes]

    for i in xrange(1, m + 2):
        C += [E(K, xor_strings(C[i - 1], M[i]))]

    return C[m + 1]

"""
Show that T is an insecure MAC by presenting a O(n + T_E) time adversary
A making at most 3 queries to its tag oracle and achieving Adv^uf-cma_T(A) = 1,
where T_E is the time for a computation of E. 
"""

def A(tag):
    x1 = "\x00" * n_bytes
    x2 = "\xFF" * n_bytes
    
    T1 = tag(x1)
    T2 = tag(x2)
    
    x3 = x1 + int_to_string(1, n_bytes) + T1
    T3 = tag(x3)
    
    x4 = x2 + int_to_string(1, n_bytes) + T2
  
    return (x4, T3)
    

from crypto.games.game_ufcma import GameUFCMA
from crypto.simulator.ufcma_sim import UFCMASim

def V(K, M, t):
    if T(K, M) == t:
        return 1
    else:
        return 0

if __name__ == '__main__':
    k = 256
    n = 128
    k_bytes = k/8
    n_bytes = n/8
    E = BlockCipher(k_bytes, n_bytes).encrypt
    g = GameUFCMA(T, V, k_bytes)
    s = UFCMASim(g, A)

    print "The advantage of your adversary is ~" + str(s.compute_advantage())
