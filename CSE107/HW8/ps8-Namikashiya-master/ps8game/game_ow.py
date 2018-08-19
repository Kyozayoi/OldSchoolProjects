import sys
import os
sys.path.append(os.path.abspath(os.path.join('..')))

from crypto.primitives import random_string
from crypto.games import game
from crypto.tools import *


class GameOW(game.Game):
    """
    This game is used to test the one-wayness of a family of functions
    H: Z_p^* x Z_p^* -> Z_p^*. Adversaries playing this game do not have access
    to any oracles. They have access to the TODO
    """

    def __init__(self, H, p):
        """
        :param H: This is the function that the adversary is playing against. 
                  It must take two parameters that are in Z_p^*.
        :param p: This is a prime such that p-1 = 2q, where q >= 5 is a prime.
        """
        super(self.__class__, self).__init__()
        self.H = H
        self.p = p

    def initialize(self):
        """
        Generates the inputs to the function and the output. Called by simulator
        class.

        :return: The first parameter to the function and the output.
        """
        self.e = random_Z_N_star(self.p)
        self.x = random_Z_N_star(self.p)
        self.y = self.H(self.e, self.x)
        return self.e, self.y

    def finalize(self, w):
        """
        This method is called by the simulator class to determine whether or not
        the adversary produced the correct output.

        :param w: Suspect input to H.
        :return: True if correct, false otherwise.
        """
        if not in_Z_N_star(w, self.p):
            return False
        return (self.H(self.e, w) == self.y)
