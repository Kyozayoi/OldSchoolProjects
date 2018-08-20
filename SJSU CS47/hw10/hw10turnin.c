/*
By Anthony Shih Secret#82
12/10/2014
CS49c Section 2
Homework 10
 */

#include <stdio.h>
#include <stdlib.h>

enum player { //Initializing the player enum
    X, O
};

struct config { //Initializing the struct config
    char grid[3][3];
    enum player toplay;
    int playsLeft;
};

void printGrid(struct config *cp) { //Printing the grid
    printf("+---+-%c-+---+\n", '-');
    printf("| %c | %c | %c |\n", cp->grid[0][0], cp->grid[0][1], cp->grid[0][2]);
    printf("+---+-%c-+---+\n", '-');
    printf("| %c | %c | %c |\n", cp->grid[1][0], cp->grid[1][1], cp->grid[1][2]);
    printf("+---+-%c-+---+\n", '-');
    printf("| %c | %c | %c |\n", cp->grid[2][0], cp->grid[2][1], cp->grid[2][2]);
    printf("+---+-%c-+---+\n", '-');

}

void Xmove(struct config *cp) {
    int row, col;
    switch (cp->playsLeft) {
        case 9:
            cp->grid[1][1] = 'X';
            break;
        case 7:
            if (cp->grid[2][1] == 'O') {
                cp->grid[0][1] = 'X';
            } else if (cp->grid[1][0] == 'O') {
                cp->grid[1][2] = 'X';
            } else if (cp->grid[1][2] == 'O') {
                cp->grid[1][0] = 'X';
            } else if (cp->grid[0][1] == 'O') {
                cp->grid[2][1] = 'X';
            } else {
                cp->grid[0][1] = 'X';
            }
            break;
         case 5:
            if (cp->grid[0][1] == 'X') {
                if (cp->grid[0][0] == 'O' && cp->grid[2][0] == 'O') {
                    cp->grid[1][2] = 'X';
                } else if (cp->grid[1][0] == 'O') {
                    cp->grid[1][2] = 'X';
                } else {
                    cp->grid[1][0] = 'X';
                }
            } else if (cp->grid[2][1] == 'X') {
                if (cp->grid[0][2] == 'O' && cp->grid[2][2] == 'O') {
                    cp->grid[1][0] = 'X';
                } else if (cp->grid[1][2] == 'O') {
                    cp->grid[1][0] = 'X';
                } else {
                    cp->grid[1][2] = 'X';
                }
            } else if (cp->grid[1][0] == 'X') {
                if (cp->grid[2][0] == 'O' && cp->grid[2][2] == 'O') {
                    cp->grid[0][1] = 'X';
                } else if (cp->grid[2][1] == 'O') {
                    cp->grid[0][1] = 'X';
                } else {
                    cp->grid[2][1] = 'X';
                }
            } else if (cp->grid[1][2] == 'X') {
                if (cp->grid[0][0] == 'O' && cp->grid[0][2] == 'O') {
                    cp->grid[2][1] = 'X';
                } else if (cp->grid[0][1] == 'O') {
                    cp->grid[2][1] = 'X';
                } else {
                    cp->grid[0][1] = 'X';
                }
            }
            break;
       case 3:
            if (cp->grid[1][2] == 'X' && cp->grid[2][1] == 'X') {
                if (cp->grid[0][2] == ' ') {
                    cp->grid[0][2] = 'X';
                } else if (cp->grid[2][0] == ' ') {
                    cp->grid[2][0] = 'X';
                } else if (cp->grid[0][0] == 'O') {
                    cp->grid[2][2] = 'X';
                } else if (cp->grid[2][2] == 'O') {
                    cp->grid[O][O] = 'X';
                } else if (cp->grid[0][1] == ' ') {
                    cp->grid[0][1] = 'X';
                } else if (cp->grid[1][0] == ' ') {
                    cp->grid[1][0] = 'X';
                }
            } else if (cp->grid[1][0] == 'X' && cp->grid[2][1] == 'X') {
                if (cp->grid[0][0] == ' ') {
                    cp->grid[0][0] = 'X';
                } else if (cp->grid[2][2] == ' ') {
                    cp->grid[2][2] = 'X';
                } else if (cp->grid[0][2] == 'O') {
                    cp->grid[2][0] = 'X';
                } else if (cp->grid[2][0] == 'O') {
                    cp->grid[0][2] = 'X';
                } else if (cp->grid[0][1] == ' ') {
                    cp->grid[0][1] = 'X';
                } else if (cp->grid[1][2] == ' ') {
                    cp->grid[1][2] = 'X';
                }
            } else if (cp->grid[1][2] == 'X' && cp->grid[0][1] == 'X') {
                if (cp->grid[0][0] == ' ') {
                    cp->grid[0][0] = 'X';
                } else if (cp->grid[2][2] == ' ') {
                    cp->grid[2][2] = 'X';
                } else if (cp->grid[2][0] == 'O') {
                    cp->grid[0][2] = 'X';
                } else if (cp->grid[0][2] == 'O') {
                    cp->grid[2][0] = 'X';
                } else if (cp->grid[2][1] == ' ') {
                    cp->grid[2][1] = 'X';
                } else if (cp->grid[1][0] == ' ') {
                    cp->grid[1][0] = 'X';
                }
            } else if (cp->grid[0][1] == 'X' && cp->grid[1][0] == 'X') {
                if (cp->grid[0][2] == ' ') {
                    cp->grid[0][2] = 'X';
                } else if (cp->grid[2][0] == ' ') {
                    cp->grid[2][0] = 'X';
                } else if (cp->grid[2][2] == 'O') {
                    cp->grid[0][0] = 'X';
                } else if (cp->grid[0][0] == 'O') {
                    cp->grid[2][2] = 'X';
                } else if (cp->grid[2][1] == ' ') {
                    cp->grid[2][1] = 'X';
                } else if (cp->grid[1][2] == ' ') {
                    cp->grid[1][2] = 'X';
                }
            }
            break;
        case 1:
            row = rand() % 3;
            col = rand() % 3;
            while (cp->grid[row][col] != ' ') {
                col++;
                if (col == 3) {
                    col = 0;
                    row = (row + 1) % 3;
                }
            }
            cp->grid[row][col] = 'X';

            break;
    }
}

void Omove(struct config *cp) { //Similar idea with O, react to opponent
    int row, col;
    switch (cp->playsLeft) { //Going for a corner tactic, take all
        case 8: //the corners
            if (cp->grid[1][1] != 'X') {
                cp->grid[1][1] = 'O';
            } else {
                cp->grid[0][0] = 'O';
            }
            break;
        case 6: //If we got the middle spot, then GG
            if (cp->grid[1][1] == 'O') {
                row = rand() % 3;
                col = rand() % 3;
                while (cp->grid[row][col] != ' ') {
                    col++;
                    if (col == 3) {
                        col = 0;
                        row = (row + 1) % 3;
                    }
                }
                cp->grid[row][col] = 'O';
            } else if (cp->grid[0][1] == 'X' || cp->grid[2][1] == 'X' || cp->grid[1][0] == 'X' || cp->grid[1][2] == 'X') {
                cp->grid[2][0] = 'O';
            } else if (cp->grid[0][2] == 'X') {
                cp->grid[0][1] = 'O';
            } else if (cp->grid[2][0] == 'X') {
                cp->grid[2][1] = 'O';
            } else if (cp->grid[2][2] == 'X') {
                cp->grid[1][2] = 'O';
            }
            break; //More or less reacting to opponent's moves
        case 4: //and hoping he makes a mistake
            if (cp->grid[1][1] == 'O') {
                row = rand() % 3;
                col = rand() % 3;
                while (cp->grid[row][col] != ' ') {
                    col++;
                    if (col == 3) {
                        col = 0;
                        row = (row + 1) % 3;
                    }
                }
                cp->grid[row][col] = 'O';
            } else if (cp->grid[0][0] == 'O' && cp->grid[2][0] == 'O') {
                if (cp->grid[1][2] == 'X' && cp->grid[2][1] == 'X') {
                    cp->grid[0][2] = 'O';
                } else if (cp->grid[1][0] == 'X' && cp->grid[0][1] == 'X') {
                    cp->grid[0][2] = 'O';
                } else if (cp->grid[0][1] == 'X' && cp->grid[1][2] == 'X') {
                    cp->grid[2][2] = 'O';
                } else if (cp->grid[1][0] == 'X' && cp->grid[2][1] == 'X') {
                    cp->grid[2][2] = 'O';
                }
            } else if (cp->grid[0][0] == 'O' && cp->grid[0][1] == 'O') {
                if (cp->grid[2][1] == ' ') {
                    cp->grid[2][1] = 'O';
                } else if (cp->grid[1][2] == ' ') {
                    cp->grid[1][2] = 'O';
                }
            } else if (cp->grid[0][0] == 'O' && cp->grid[0][0] == 'O') {
                if (cp->grid[0][1] == ' ') {
                    cp->grid[0][1] = 'O';
                } else if (cp->grid[1][0] == ' ') {
                    cp->grid[1][0] = 'O';
                }
            } else if (cp->grid[0][0] == 'O' && cp->grid[2][2] == 'O') {
                if (cp->grid[1][0] == ' ') {
                    cp->grid[1][0] = 'O';
                } else if (cp->grid[2][1] == ' ') {
                    cp->grid[2][1] = 'O';
                }
            }
            break; //There are various cases here, most of which
        case 2: //are safe to win unless opponent doesn't mess up
            if (cp->grid[1][1] == 'O') {
                row = rand() % 3;
                col = rand() % 3;
                while (cp->grid[row][col] != ' ') {
                    col++;
                    if (col == 3) {
                        col = 0;
                        row = (row + 1) % 3;
                    }
                }
                cp->grid[row][col] = 'O';
            } else if (cp->grid[0][2] == 'O') {
                if (cp->grid[2][2] == ' ') {
                    cp->grid[2][2] = 'O';
                } else {
                    row = rand() % 3;
                    col = rand() % 3;
                    while (cp->grid[row][col] != ' ') {
                        col++;
                        if (col == 3) {
                            col = 0;
                            row = (row + 1) % 3;
                        }
                    }
                    cp->grid[row][col] = 'O';
                }
            } else if (cp->grid[2][2] == 'O') {
                if (cp->grid[0][2] == ' ') {
                    cp->grid[0][2] = 'O';
                } else {
                    row = rand() % 3;
                    col = rand() % 3;
                    while (cp->grid[row][col] != ' ') {
                        col++;
                        if (col == 3) {
                            col = 0;
                            row = (row + 1) % 3;
                        }
                    }
                    cp->grid[row][col] = 'O';
                }
            } else if (cp->grid[1][0] == 'O' && cp->grid[0][1] == 'O' && cp->grid[2][1] == 'O') {
                if (cp->grid[2][2] == ' ') {
                    cp->grid[2][2] = 'O';
                } else if (cp->grid[1][2] == ' ') {
                    cp->grid[1][2] = 'O';
                }
            } else if (cp->grid[2][1] == 'O' && cp->grid[0][0] == 'O' && cp->grid[1][2] == 'O') {
                if (cp->grid[0][1] == ' ') {
                    cp->grid[0][1] = 'O';
                }
            } else if (cp->grid[1][2] == 'O' && cp->grid[0][1] == 'O' && cp->grid[0][0] == 'O') {
                if (cp->grid[2][2] == ' ') {
                    cp->grid[2][2] = 'O';
                } else if(cp->grid[1][0] == ' '){
                    cp->grid[1][0] = 'O';
                } else if(cp->grid[2][1] == ' '){
                    cp->grid[2][1] = 'O';
                }
            } else {
                row = rand() % 3;
                col = rand() % 3;
                while (cp->grid[row][col] != ' ') {
                    col++;
                    if (col == 3) {

                        col = 0;
                        row = (row + 1) % 3;
                    }
                }
                cp->grid[row][col] = 'O';
            }
            break;
    }
}
 

enum player eval(struct config *cp) { //I just check if X is in all possible winning locations
    if (cp->grid[0][0] == 'X' && cp->grid[1][0] == 'X' && cp->grid[1][1] == 'X' && cp->grid[2][1] == 'X') {
        return X;
    } else if (cp->grid[0][0] == 'X' && cp->grid[0][1] == 'X' && cp->grid[1][1] == 'X' && cp->grid[1][2] == 'X') {
        return X;
    } else if (cp->grid[0][1] == 'X' && cp->grid[0][2] == 'X' && cp->grid[1][0] == 'X' && cp->grid[1][1] == 'X') {
        return X;
    } else if (cp->grid[0][1] == 'X' && cp->grid[1][0] == 'X' && cp->grid[1][1] == 'X' && cp->grid[2][0] == 'X') {
        return X;
    } else if (cp->grid[0][1] == 'X' && cp->grid[1][1] == 'X' && cp->grid[1][2] == 'X' && cp->grid[2][2] == 'X') {
        return X;
    } else if (cp->grid[0][2] == 'X' && cp->grid[1][1] == 'X' && cp->grid[1][2] == 'X' && cp->grid[2][1] == 'X') {
        return X;
    } else if (cp->grid[1][0] == 'X' && cp->grid[1][1] == 'X' && cp->grid[2][1] == 'X' && cp->grid[2][2] == 'X') {
        return X;
    } else if (cp->grid[1][1] == 'X' && cp->grid[1][2] == 'X' && cp->grid[2][0] == 'X' && cp->grid[2][1] == 'X') {
        return X;
    } else {
        return O;
    }
}