#include<bits/stdc++.h>
using namespace std;

int main()
{
    int a,b;
    // we have to check  whether a is primitive root of b or not
    cin>>a>>b;
    //array to check whether all modulus are distinct are not
    int arr[b-1]={};
    // for i=1 
    int prev= a % b;
    arr[prev-1]=1;
    int x;

    for(int i =2;i<b-1;i++){
        x= (prev * a) % b;
        cout<<x<<endl;
        
        if(arr[x-1]==0)
        {   
            arr[x-1]=1;
            prev=x;
        }
        else{
            cout<< a<<" is not primitive root of "<<b<<endl;
            return 0;
        }

    }
    cout<< a <<" is a primitive root of " << b <<endl;
}