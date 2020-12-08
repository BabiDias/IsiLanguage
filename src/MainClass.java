import java.util.Scanner;

public class MainClass { 
public static void main(String args[]) {
 Scanner _key = new Scanner(System.in);
double  a;
double  b;
String  t1;
a=Double.parseDouble(_key.nextLine());
b=Double.parseDouble(_key.nextLine());
t1=_key.nextLine();
a = 1+2*10/b;
if (a>b) {
System.out.println(a);}else {
System.out.println(b);}

System.out.println(10);
System.out.println(t1);
System.out.println("ola");
while (a>b) {
System.out.println(a);a = a-1;}
_key.close();
  }}