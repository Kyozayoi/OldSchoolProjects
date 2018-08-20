/*
 * hw10.h
 *
 *  Created on: Nov 25, 2012
 *      Author: Tom
 */

enum player {X, O};

struct config {
	char grid[3][3];
	enum player toplay;
	int playsLeft;
};

void printGrid(struct config *cp);
void Xmove(struct config *cp);
void Omove(struct config *cp);
enum player eval(struct config *cp);

