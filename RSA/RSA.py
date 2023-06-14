import math
# def computeGCD(x, y):
 
#     if x > y:
#         small = y
#     else:
#         small = x
#     for i in range(1, small + 1):
#         if((x % i == 0) and (y % i == 0)):
#             gcd = i
             
#     return gcd



def computeGCD(x, y):
    while(y):
       x, y = y, x % y
    return abs(x)



p=656692050181897513638241554199181923922955921760928836766304161790553989228223793461834703506872747071705167995972707253940099469869516422893633357693
q=204616454475328391399619135615615385636808455963116802820729927402260635621645177248364272093977747839601125961863785073671961509749189348777945177811
n=p*q
phiN=(p-1)*(q-1)
message=12345
print("Actual message: ",message)
cnt=0
e_list=[]
for i in range((phiN-1),1000000,-1):
    if computeGCD(i,phiN) ==1:
        cnt+=1
        e_list.append(i)
    if cnt>10:
        break
# e=i
# print("value of e: ",e)
print(e_list)




for e in e_list:
    for k in range(1,10000000):
        x=1+k*phiN
        if(x%e == 0):
            d=x//e
            break







print("value of decryption key ="+str(d))
ciphertext=pow(message,e,n)

print("encryptedText= "+str(ciphertext))
plaintext=pow(ciphertext,d,n)


print("decryptedText= "+ str(plaintext))

