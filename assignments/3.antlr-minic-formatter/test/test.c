           int max=500; int mac[500]; void main(int a, int b, int c) {
             int i; int j; int k;
             int rem; int sum;

             i = 2;
             while(i <= max) {
                 sum = 0;
                 k = i / 2;
                 j = i;
                 while ( j <= k) {
                         rem = i % j;
                         if (rem == 0) {
                                sum = sum + j;
                         ++j;}
                 }
                 if  (i == sum) write(i);
                 ++i;
             }
}