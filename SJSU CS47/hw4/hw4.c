
int NROW = 100;
int NCOL = 20;
int hws[NROW][NCOL];
int totals[NROW];
double averages[NCOL];

int topscore()
{
    int i, j;
    int s = 0;
    for(i = 0; i < NROW; i++)
    {
        int t = 0;
        for (j = 0; j < NCOL; j++)
        {
            int t = t + hws[i][j];
        }
        totals[i] = t;
    }
    for(i = (NROW - 1); i >= 0; i--)
    {
        if(totals[i] > t)
        {
            t = totals[i];
            s = i;
        }
    }
    return s;
}
       
int toughesthomework()
{
    int i, j;
    int s = 0;
    for(i = 0; i < NCOL; i++)
    {
        int a = 0;
        for(j = 0; j < NROW; j++)
        {
            a = a + hws[i][j];
        }
        averages[i] = (a / 100);
    }
    for(i = (NCOL - 1); i >= 0; i--)
    {
        int a = averages[NCOL - 1];
        if(averages[i] < a)
        {
            a = averages[i];
            s = i;
        }
    }
    return s;
}

int numhighest()
{
    int i, j;
    int c = 0;
    int h = 0;
    for(i = 0; i < NROW; i++)
    {
        for(j = 0; j < NCOL; j++)
        {
            if(hws[i][j] > h)
            {
                h = hws[i][j];
            }
        }
    }
    for(i = 0; i < NROW; i++)
    {
        for(j = 0; j < NCOL; j++)
        {
            if(hws[i][j] == h)
            {
                c++;
            }
        }
    }
    return c;
}        