plaintext=input("The plaintext: ")
key=input("The cipherKey: ")
ciphertext=""
if(len(plaintext)!=len(key)):
    print("length of plaintext and key not matched")
    a=input()
cnt=0
for i in plaintext:
    if(i==key[cnt]):
        ciphertext+="0"
    else:
        ciphertext+="1"
    cnt+=1
print("The cipher text is: ",ciphertext)

print("The decryption prcoess")
cnt=0
decrypted=""
for i in ciphertext:
    if(i==key[cnt]):
        decrypted+="0"
    else:
        decrypted+="1"
    cnt+=1
print("The decrypted plain text is: ",decrypted)



