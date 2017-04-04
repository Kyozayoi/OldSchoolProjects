/*
Name: Anthony Shih
PID: A11295870
Login: cs12wei
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define BUFSIZE 100
#define TRUE 1

//struct tree_node can be reference simply as Node
typedef struct tree_node Node;

//struct definition of Node

struct tree_node {
    char* value;        //String Pointer
    tree_node* left;    //Left tree_node Pointer
    tree_node* right;   //Right tree_node Pointer
};

tree_node *root;        //Root pointer

/*-----------------------------------------------------------------------------
 * Function name:   print
 * Input:           pointer to a struct of type Node 
 * Output:          none
 * Result:          the string value of node is printed
 * Notes:           DO NOT EDIT THIS FUNCTION
 ----------------------------------------------------------------------------*/
void printNode(Node* node) {
    printf("%s\n", node->value);
}

/*-----------------------------------------------------------------------------
 * Function name:   compare
 * Input:           two char pointers 
 * Output:          0  if both strings are equal
 *                  <0 the first character that does not match has a 
                        lower value in ptr1 than in ptr2
                    >0 the first character that does not match has a 
                        greater value in ptr1 than in ptr2
 * Notes:           USE THIS TO COMPARE STRINGS WHEN INSERTING/FINDING
 ----------------------------------------------------------------------------*/
/*
 * Compare method that compares each individual letter of the string pointers
 * If letter at a is greater, return a positive number
 * If letter at b is greater, return a negative number
 * If string is equal, return 0
 * @author Anthony Shih
 * @version 1.0
 * @since 03-15-2016
 */
int compare(char* a, char* b) {
    while (*a && (*a == *b)) {
        a++;
        b++;
    }
    return *(unsigned char*) a - *(unsigned char*) b;
}

/*-----------------------------------------------------------------------------
 * Function name:   insert
 * Input:           char pointer
 * Output:          none
 * Result:          a node having input as value is inserted into it's
                    appropriate location in the tree
 ----------------------------------------------------------------------------*/
/*
 * Main insert method
 * Calls helper recursive method 
 * @author Anthony Shih
 * @version 1.0
 * @since 03-15-2016
 */
void insert(char* input) {
    root = insertHelper(root, input);
}

/*
 * Recursive insert helper method
 * Adds the node in place when we've reached a null node
 * Else, compare the node to the current location and adjust to the left/right
 * @author Anthony Shih
 * @version 1.0
 * @since 03-15-2016
 */
tree_node * insertHelper(tree_node* oldNode, char* input){
    if(oldNode==NULL){
        tree_node *temp;
        temp = (treeNode *)malloc(sizeof(tree_node));
        temp->value = input;
        temp->left = NULL;
        temp->right = NULL;
        return temp;
    }
    if(compare(input, oldNode->value) > 0){
        oldNode->right = insertHelper(oldNode->right, input);
    }
    else if(compare(input, oldNode->value < 0){
        oldNode->left = insertHelper(oldNode->left, input);
    }
    return oldNode;
}

/*-----------------------------------------------------------------------------
 * Function name:   find
 * Input:           char pointer
 * Output:          none
 * Result:          see write-up
 ----------------------------------------------------------------------------*/
/*
 * Main find method
 * Calls helper recursive method
 * @author Anthony Shih
 * @version 1.0
 * @since 03-15-2016
 */
void find(char* input) {
    tree_node* found = findHelper(root, input);
    if(found) == NULL){
        printf("NOT found: %s\n", input);
    }
    else{
        printf("Found: %s\n", found->value));
    }
}

/*
 * Recursive find helper method
 * Search for input through the binary tree recursively
 * Move to the left/right if compare returns negative/positive
 * @author Anthony Shih
 * @version 1.0
 * @since 03-15-2016
 */
tree_node * findHelper(tree_node* oldNode, char* input){
    if(oldNode == NULL){
        return NULL;
    }
    if(compare(input, oldNode->value) > 0){
        return findHelper(node->right, input);
    }
    else if(compare(input, oldNode->value) < 0){
        return findHelper(node->left, input);
    }
    else{
        return oldNode;
    }
}

/*-----------------------------------------------------------------------------
 * Function name:   traverse
 * Input:           none
 * Output:          none
 * Result:          the nodes in the tree are printed in order of increasing
 *                  value
 ----------------------------------------------------------------------------*/
/*
 * Main traverse method
 * Calls recursive helper method
 * @author Anthony Shih
 * @version 1.0
 * @since 03-15-2016
 */
void traverse() {
    if(root == NULL){
        return;
    }
    trverseHelper(root);
}

/*
 * Recursive traverse helper method
 * Prints inorder starting from the left side
 * @author Anthony Shih
 * @version 1.0
 * @since 03-15-2016
 */
void traverseHelper(tree_node* oldNode){
    if(oldNode == NULL){
        return;
    }
    traverseHelper(oldNode->left);
    printf("%s ", oldNode->value);
    traverseHelper(oldNode->right);
}

//DO NOT EDIT

void insertPrompt() {
    char * input = malloc(sizeof (char)*(BUFSIZE + 1));
    printf("Enter a string to insert (max length 100): ");
    scanf("%s", input);
    insert(input);
}

//DO NOT EDIT

void lookupPrompt() {
    char * input = malloc(sizeof (char)*(BUFSIZE + 1));
    printf("Enter a string to look up: ");
    scanf("%s", input);
    find(input);
}

//MUST MATCH STARTER CODE ON TURNIN 

int main() {

    char input[BUFSIZE];

    printf("Binary Search Tree!\n");

    while (!feof(stdin)) {

        printf("Select an Operation: (i)nsert, (l)ookup, (p)rint in order, (q)uit: ");
        scanf("%s", input);

        if (feof(stdin)) {
            break;
        }
        if (input[0] == 'i') {
            insertPrompt();
        } else if (input[0] == 'l') {
            lookupPrompt();
        } else if (input[0] == 'p') {
            traverse();
        } else if (input[0] == 'q') {
            break;
        } else {
            printf("Invalid option selected, try again\n");
        }
    }

    return 0;
}

