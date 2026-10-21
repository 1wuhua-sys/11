/*#include<stdio.h>
#include<string.h>
int main()
{
    char ch[1000];
    scanf("%s",&ch);
    int N[10]={0};
    for(int i=0;i<strlen(ch);i++)
    {
        N[ch[i]-'0']++;
    }
    for(int i=0;i<10;i++)
    {
        if(N[i]!=0)
        {
            printf("%d:%d\n",i,N[i]);
        }
    }
}
#include<stdio.h>
int main(){
    int a,b,max=0,min=0,mid=0,c;
    scanf("%d %d %d",&a,&b,&c);
    mid=(int)(a+b+c)/3;
    if(a>mid){
        max=a;
            if(b<mid){
                min=b;
                mid=c;
            }
        else{
            min=c;
            mid=b;
        }
    }
    else{
        min=a;
            if(b>mid){
                max=b;
                mid=c;
            }
        else{
            max=c;
            mid=b;
        }
    }
    printf("%d->%d->%d",min,mid,max);
}
#include<bits/stdc++.h>
using namespace std;
int main(){
	int a[7]={1,2,3,6,7,9,0};
	sort(a+0,a+7);
	int i;
	for(i=0;i<7;i++){
		printf("%d",a[i]); 
	}
	return 0;
}
*/
#include<bits/stdc++.h>
#include<algorithm>
//#include<vector>
using namespace std;
int main(){
	int a[]={};
	for(int i=0;i<6;i++){
		cin>>a[i];
	}
	int *max=max_element(a+0,a+7);
	int *min=min_element(a+0,a+7);
	cout<<*max<<endl;
	cout<<*min<<endl;
	return 0;
}


