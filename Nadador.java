/**
 *PROGRAMA DESARROLLADO POR:
Antonio Rivero 	CI.	17976025
Evander Palacios CI.	18816770

 *Clase Nadador de el AG
 *son las personas que compiten en las N competencias
 */
public class Nadador {
int idN;//Nombre del nadador
int comp;//Cantidad de competencias en la que participa actualmente
int comp1;//Numero de la competencia donde participa de primero
int comp2;//Numero de competencia donde participa por segunda vez
double tiempo;//7Tiempo del nadador en el estilo seleccionado
double tiempos[]=new double [4];//Tiempos del nadador en los 4 estilos

    public Nadador(int numero,double t1,double t2,double t3,double t4) {
    	idN=numero;
    	comp=0;
    	comp1=-1;
    	comp2=-1;
    	tiempos[0]=t1;
    	tiempos[1]=t2;
    	tiempos[2]=t3;
    	tiempos[3]=t4;
    }
}