import json
import sys, os, itertools

sys.path.append(os.path.abspath(os.path.join('..')))
from crypto.tools import *
from crypto.ideal.digital_signature import DigitalSignature
from crypto.ideal.hash_function import HashFunction

def ADD(a,b):
    return a+b
def MULT(a,b):
    return a*b
def INT_DIV(a,N):
    return (a/N, a%N)
def MOD(a,N):
    return a%N
def EXT_GCD(a,N):
    return egcd(a,N)
def MOD_INV(a,N):
    return modinv(a,N)
def MOD_EXP(a,n,N):
    return exp(a,n,N)

"""
1. [50 points] Let DS = (K,S,V) be a uf-cma secure digital signature scheme for
signing messages m /in Z_N^*, where N >= 1000 is an integer whose bit-length
we denote by k. Alice wants to sign messages M \in D where
        D = {(M[1],...,M[n]) : n >= 1 and M[1],...,M[n] \in Z_N^*}.
Letting h : Z_N^* -> Z_N^* be some hash function, she defines digital signature
scheme DS_bar = (K,S_bar,V_bar) through the following function H : D -> Z_N^*,
signing algorithm S_bar and verification algorithm V_bar:
"""

def H(M):
    """
    :param M: A list of integers such that each integer is in Z_N^*
    :return: An integer in Z_N^*
    """
    n = len(M)
    M = [None] + M
    Y = 1
    for i in xrange(1, n+1):
        W = MOD(Y * M[i], N)
        Y = h(W)
    return Y

def S_bar(sk, M):
    for m in M:
        if not in_Z_N_star(m, N):
            return None
    m = H(M)
    return S(sk, m)

def V_bar(pk, M, S):
    for m in M:
        if not in_Z_N_star(m, N):
            return 0
    m = H(M)
    return V(pk, m, S)


"""
Present in pseudocode an O(T_h + k^2 + s)-time adversary A making one query to
Sign and achieving Adv^{uf-cma}_{DS_bar}(A) = 1, where T_h is the time to compute
h and s is the length of a signature returned by S.
"""

def A(Sign):
    """
    You must fill in this method. This is the adversary that the problem is
    asking for. Returns a (message, signature) pair.

    :param Sign: This is an oracle supplied by GameUFCMASign. You can call this
    oracle to get a "signature" for the data you pass into it.
    """
    
    reverseHash = MOD_INV(h(1), N)
    
    m1 = [1] + ([reverseHash]*24)
    t = Sign(m1)
    m2 = m1 + [reverseHash]
    
    return (m2, t)


from crypto.games.game_ufcma_sign import GameUFCMASign
from crypto.simulator.ufcma_sign_sim import UFCMASignSim

if __name__ == '__main__':
    f = open("student_info.json", 'r')
    student_info = json.loads(f.read())
    f.close()
    for a in student_info:
        print "%s: %s" % (a, student_info[a])

    N = 1000
    k = 128
    n = 128
    k_bytes = k/8
    n_bytes = n/8
    hash_fn = HashFunction(N = N)
    h = hash_fn.hash_int
    ds = DigitalSignature(k_bytes, n_bytes)
    S = ds.sign
    V = ds.verify

    g = GameUFCMASign(S_bar, V_bar, k_bytes, key_gen=ds.key_gen)
    s = UFCMASignSim(g, A)

    print "The advantage of your adversary is ~" + str(s.compute_advantage())
