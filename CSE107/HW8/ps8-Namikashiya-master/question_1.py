import math
import json
import sys, os, itertools
from fractions import gcd

sys.path.append(os.path.abspath(os.path.join('..')))
from crypto.tools import *

sys.path.append(os.path.abspath(os.path.join('ps8game')))
from game_ow import GameOW
from ow_sim import OWSim

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
Problem 1 [50 points] Let p, q >= 5 be primes such that p-1 = 2q. The 
one-wayness game OW_H associated to a family of functions 
H: Z_p^* x Z_p^* -> Z_p^* is defined in game_ow.py. The one-wayness advantage
of an adversary A is defined by Adv_H^ow(A) = Pr[OW_H^A => true].
For all e, x \in Z_p^*, define H: Z_p^* x Z_p^* -> Z_p^* as follows.
"""

def H(e, x):
    y = MOD_EXP(x, e, p)
    return y


"""
1. [40 points]
Present in pseudocode an O(k^3)-time adversary A such that Adv_H^ow(A) >= 2/5,
where k is the bit-length of p.
"""
def A((e, y)):
    """
    This is the adversary the problem is asking for.

    :param e: first input to H
    :param y: output of H on inputs e and x
    :return: guess of w such that H(e, w) = y
    """
    q = (p-1) / 2
    ordN = (p-1)*(q-1)
    d = MOD_INV(e, ordN)
    if(d == None):
        d = MOD_INV(e, p)
    return MOD_EXP(y, d, p)

"""
2. [10 points]
Give a justification to show that the A_H^ow(A) >= 2/5.

We cannot reasonably calculate mod_inv for every possible value, so at the very least we know that A_H^ow(A) is less than 1.


WRITE YOUR ANSWER HERE


"""

if __name__ == '__main__':

    s="%s%s%s%s%s" % (
        "179769313486231590770839156793787453197860296048756011706444423684197",
        "180216158519368947833795864925541502180565485980503646440548199239100",
        "050792877003355816639229553136239076508735759914822574862575007425302",
        "077447712589550957937778424442426617334727629299387668709205606050270",
        "810842907692932019128194467627007")
    p=int(s)
    f = open("student_info.json", 'r')
    student_info = json.loads(f.read())
    f.close()
    for a in student_info:
        print "%s: %s" % (a, student_info[a])

    g = GameOW(H, p)
    s = OWSim(g, A)
    print "The advantage of adversary A is ~" + str(s.compute_advantage())
