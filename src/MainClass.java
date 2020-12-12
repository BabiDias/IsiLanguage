import java.util.Scanner;

public class MainClass { 
public static void main(String args[]) {
 Scanner _key = new Scanner(System.in);
boolean b2;
double  a;
double  b;
double  i;
String  t1;
String  t2;
String  t3;
boolean b1;
System.out.println("Digite um numero:");
a=Double.parseDouble(_key.nextLine());
System.out.println("Digite um numero:");
b=Double.parseDouble(_key.nextLine());
System.out.println("Digite um texto:");
t1=_key.nextLine();
a = ((1+2*10)+2*3)/((20/2)-b);
t2 = "ola mundo";
t3 = t2+"!";
b1 = a<b||b<10;
b2 = false;
if (b1||b2||!(b2 && b1)) {
System.out.println("Passou na condicao booleana");
}else {
System.out.println(b);
}

System.out.println(b+10);
System.out.println(t1);
System.out.println(t2);
System.out.println(t3);
while (a>b) {
System.out.println("a = "+a);
a = a-1;
}
while (a<=b) {
System.out.println(a);
a = a+1;
}
for (i = 0; i<=10; i+=2) {
System.out.println(i);
}
_key.close();
  }}
