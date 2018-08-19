import sys, os, itertools

sys.path.append(os.path.abspath(os.path.join('..')))
from crypto.primitives import *
from crypto.tools import *
from crypto.ideal.function_family import *

"""
Problem 1 [50 points]
Let G: {0, 1}^k x {0, 1}^l --> {0, 1}^l be a family of functions and let r>= 1 be an
integer. The r-round Feistel cipher associated to G is the family of functions
Gr: {0, 1}^k x {0, 1}^2l --> {0, 1}^2l, defined as shown below.

If E is a family of functions, then T_E denotes the time to compute it.
All times are worst case.
"""
 
def Gr(K,x):
    x0, x1 = split(x)

    L, R = [x0], [x1]

    for i in range(1, r + 1):
        L.append(R[i-1])
        R.append(xor_strings(G(K, R[i-1]), L[i-1]))

    return L[r] + R[r]

"""
1. [20 points] Show that Gr is not a secure PRF when r=1 by presenting in code
an O(T_G+k+l)-time adversary A1 making one query to its Fn oracle and achieving 
Adv^prf_Gr(A1) = 1 - 2^-l.
"""


def A1(fn):
    """
    You must fill in this method. We will define variables r, k, l, k_bytes, l_bytes,
    and G for you.

    :param fn: This is the oracle supplied by GamePRF.
    :return: return 1 to indicate your adversary believes it is the real world
    and return 0 to indicate that your adversary believes it is in the random
    world.
    """
    M = '\x00'*2*l_bytes
    encrypted = fn(M)
    
    if(encrypted[:l_bytes] == '\x00'*l_bytes):
        return 1
    
    return 0


"""
2. [30 points] Show that Gr is not a secure PRF when r=2 by presenting in code
an O(T_G+k+l)-time adversary A2 making two queries to its Fn orcale and achieving 
Adv^prf_Gr(A2) = 1 - 2^-l.
"""

def A2(fn):
    """
    You must fill in this method. We will define variables r, k, l, k_bytes, l_bytes,
    and G for you.

    :param fn: This is the oracle supplied by GamePRF.
    :return: return 1 to indicate your adversary believes it is the real world
    and return 0 to indicate that your adversary believes it is in the random
    world.
    """
    encrypt0 = fn('0'*2*l_bytes)

    encrypt1 = fn(('1'*l_bytes) + ('0'*l_bytes))

    encryptX = xor_strings(encrypt0, encrypt1)

    if(encryptX[:l_bytes] == '1'*l_bytes):
        return 1
    
    return 0


from crypto.games.game_prf import GamePRF
from crypto.simulator.world_sim import WorldSim


if __name__ == '__main__':

    r=1
    k=128
    l=128
    k_bytes = k/8
    l_bytes = l/8
    G = FunctionFamily(k_bytes, l_bytes,l_bytes).evaluate
    g1 = GamePRF(Gr, k_bytes, 2*l_bytes)
    s1 = WorldSim(g1, A1)
    adv1 = s1.compute_advantage(1000)
    r=2
    g2 = GamePRF(Gr, k_bytes, 2*l_bytes)
    s2 = WorldSim(g2, A2)
    adv2 = s2.compute_advantage(1000)

    print "When k=128, l=128:"
    print "The advantage of your adversary A1 is ~" + str(adv1)
    print "The advantage of your adversary A2 is ~" + str(adv2)
    
    r=1
    k=128
    l=8
    k_bytes = k/8
    l_bytes = l/8
    G = FunctionFamily(k_bytes, l_bytes,l_bytes).evaluate
    g1 = GamePRF(Gr, k_bytes, 2*l_bytes)
    s1 = WorldSim(g1, A1)
    adv1 = s1.compute_advantage(1000)
    r=2
    g2 = GamePRF(Gr, k_bytes, 2*l_bytes)
    s2 = WorldSim(g2, A2)
    adv2 = s2.compute_advantage(1000)

    print "When k=128, l=8:"
    print "The advantage of your adversary A1 is ~" + str(adv1)
    print "The advantage of your adversary A2 is ~" + str(adv2)

