import java.util.Scanner;

public class MainClass { 
public static void main(String args[]) {
 Scanner _key = new Scanner(System.in);
double  a;
double  b;
double  c;
double  d;
String  t1;
String  t2;
String  t3;
System.out.println("Digite um numero:");
a=Double.parseDouble(_key.nextLine());
System.out.println("Digite um numero:");
b=Double.parseDouble(_key.nextLine());
System.out.println("Digite um texto:");
t1=_key.nextLine();
c = 1;
a = 1.5+2*10/b;
t2 = "ola mundo";
t3 = t2+"!";
if (b<10) {
System.out.println(b+" eh menor que 10");
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
_key.close();
  }}