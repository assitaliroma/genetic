/**
 *PROGRAMA DESARROLLADO POR:
Antonio Rivero 	CI.	17976025
Evander Palacios CI.	18816770

 * Clase Competencia del AG
 * es donde los nadadores van a participar en grupos de 4
 */
public class Competencia {
int idC;//Numero de competencia
double tmax;//Tiempo maximo que se debe cumplir, establecido por la Federacion
double sumNad;//Suma de tiempos de los 4 nadadores que estan actualmente seleccionados para esta competencia
double resta;//Resta entre tmax y sumNad
boolean cumplida;//Permite saber si los 4 nadadores seleccionados cumplen con tmax
Nadador participantes[]=new Nadador[4];//Arreglo de participantes en la competencia

    public Competencia(int id,double tm) {
    	idC=id;
    	tmax=tm;
    	sumNad=tmax+100;
    	resta=-100;
    	cumplida=false;
    	participantes[0]=new Nadador(-1,-1,-1,-1,-1);
    	participantes[1]=new Nadador(-1,-1,-1,-1,-1);
    	participantes[2]=new Nadador(-1,-1,-1,-1,-1);
    	participantes[3]=new Nadador(-1,-1,-1,-1,-1);
    }
}