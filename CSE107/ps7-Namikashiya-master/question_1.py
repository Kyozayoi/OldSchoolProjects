import math
import json
import sys, os, itertools

sys.path.append(os.path.abspath(os.path.join('..')))
from crypto.tools import *

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
1. [50 points] Let p >= 3 be a prime and g \in Z_p^* be a generator of Z_p^*.
(These are public quantities, known to all parties including the adversary.)
We will define variables p and g for you. Consider the key-generation and
encryption algorithms below:
"""

def K():
    x = random_Z_N_star(p-1)
    X = exp(g,x,p)
    return (X,x)

def E(X, M):
    """
    :param X: The public key used to encrypt the message
    :param M: The plaintext to be encrypted, must be in Z_p^*
    :return: return the encryption of plaintext M
    """
    if not in_Z_N_star(M,p):
        return None
    y = random_Z_N(p-1)
    Y = exp(g,y,p)
    Z = exp(X,y,p)
    W = (Y*M) % p
    return (Z,W)

"""
The message M must be in Z_p^*, meaning only elements of Z_p^* are allowed as
messages. We let k be the bit-length of p.

Specify an O(k^3)-time decryption algorithm D such that AE=(K,E,D) is an
asymmetric encryption scheme satisfying the correct decryption property.
Prove that the correction decryption property holds.
"""

def D(x, C):
    """
    This is the decryption algorithm that the problem is asking for.

    :param x: The secret key used to decrypt the message
    :param C: The ciphertext to be decrypted
    :return: return the decryption on the ciphertext C
    """
    X = MOD_EXP(g,x,p)
    Z,W = C
    
    """
    solve for y by reversing the modulo in Z, and somehow isolating it from g^x
    """
    Y = MOD_EXP(g,y,p)
    
    """
    solve for M ideally by reversing modulo again in W this time 
    and then dividing by the Y we solved for earlier
    """
    
    pass
    
    """
    So my decryption algorithm doesn't actually work because I didn't implement one
    I actually have no idea how to implement the idea I had in my head both 
    mathematically or practically. My plan was to isolate y from Z, use that to find Y, 
    then calculate M through W, but even with all the variables and functions that 
    were already given, I had no idea how to either reverse or cancel out the modulo 
    function that both Z and W used to calculate their values, so I was just stuck 
    for hours upon days trying to figure how to do that.
    I tried using MOD_INV to cancel out any values into 1, but that didn't work in 
    theory or even in practice either. I considered using EXT_GCD, but I don't know
    how the information returned from that function would even help me for this 
    problem. I was working with 2 other people for this assignment and none of us were
    even capable of 
    I'm writing the proof for what I was planning to do for my algorithm and going to
    room 4244 first thing tomorrow morning to figure this out because I honestly have
    no clue :(
    
    """

if __name__ == '__main__':

    s="%s%s%s%s%s" % (
        "179769313486231590770839156793787453197860296048756011706444423684197",
        "180216158519368947833795864925541502180565485980503646440548199239100",
        "050792877003355816639229553136239076508735759914822574862575007425302",
        "077447712589550957937778424442426617334727629299387668709205606050270",
        "810842907692932019128194467627007")
    p=int(s)
    g=22
    f = open("student_info.json", 'r')
    student_info = json.loads(f.read())
    f.close()
    for a in student_info:
        print "%s: %s" % (a, student_info[a])
    works = True
    for j in range(100):
        (pk,sk) = K()
        M = random_Z_N_star(p)
        C = E(pk, M)
        if M != D(sk, C):
            works = False
    if works:
        print "\nYour decryption function appears to be correct."
    else:
        print "\nYour decryption function is incorrect."
