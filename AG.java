import java.util.*;
import java.io.*;
/**
 * Esta es la clase Principal de mi programa
 * aqui ya vienen cosas aun mas divertidas como el calculo de aptitud los cruces etc etc etc
 */

public class AG {
static int cantNad=0,cantComp1=0;
Cromosoma c;
static Cromosoma mejor;
static Random r= new Random();
static LinkedList<Cromosoma> aptos = new LinkedList<Cromosoma>();
static LinkedList<Cromosoma> lista = new LinkedList<Cromosoma>();
static LinkedList<Cromosoma> listaAUX = new LinkedList<Cromosoma>();
static double prob_cruce=0.7;
static double prob_mutar=0.01;
static double lim_sup=0,lim_inf=0;

public static double calcular_Funcion(Cromosoma x){
	double cont=0;
	for(int i=0;i<cantComp1;i++){
		cont=cont+x.compe[i].resta;
	}
	return(cont*0.4+x.cantComp*0.6);
	
}

public static void seleccion(){
	double acu=0;
	for(int j=0;j<lista.size();j++){
		lista.get(j).limi=acu;
		lista.get(j).lims=acu+lista.get(j).prob_cruce;
		acu=acu+lista.get(j).prob_cruce;
	}
	for(int k=0;k<lista.size();k++){
		
		double alfa=r.nextDouble();

		for(int p=0;p<lista.size();p++){
		if(alfa>=lista.get(p).limi&&alfa<lista.get(p).lims){
			aptos.add(lista.get(p));
		}		
		}	
	}
}
public static void cruzar(){
		for(int k=0;k<lista.size();k=k+2){
			double pb=r.nextDouble();
			if(pb<=prob_cruce){
				int tam=cantComp1*4;
				int pos=r.nextInt(tam);
				boolean seg_pas=false;
				for(int o=pos/4;o<cantComp1;o++){
					if(seg_pas){
					for(int r=0;r<4;r++){
						
						Nadador aux=lista.get(k).compe[o].participantes[r];
						lista.get(k).compe[o].participantes[r]=lista.get(k+1).compe[o].participantes[r];
						lista.get(k+1).compe[o].participantes[r]=aux;
						
						
					}	
					}else{
					for(int r=pos%4;r<4;r++){
					
						Nadador aux=lista.get(k).compe[o].participantes[r];
						lista.get(k).compe[o].participantes[r]=lista.get(k+1).compe[o].participantes[r];
						lista.get(k+1).compe[o].participantes[r]=aux;
						
					}	
					}
					
					seg_pas=true;
				}		
			}
		}	
}
public static void mutar(){
		for(int k=0;k<lista.size();k++){
			double pb=r.nextDouble();
			if(pb<=prob_mutar){
				int tam=cantComp1*4;
				int pos=r.nextInt(tam);
				int cambio=r.nextInt(cantNad);
			lista.get(k).compe[(pos/4)].participantes[(pos%4)]=lista.get(k).tipos[cambio];	
				}		
			}
			
}
public static Cromosoma mas_apto(){
	Cromosoma max=new Cromosoma(-1);
	max=lista.get(0);
	for(int i=0;i<lista.size();i++){
		if(lista.get(i).aptitud>=max.aptitud){
			max=lista.get(i);
		}
	
	}return(max);
}

public static int buscar(String a,String b){
	int cont=0;
	for(int i=0;i<b.length();i++){
		if(b.indexOf(a,i)>=0){
			cont++;
			i=b.indexOf(a,i)+1;
		}
	}
	return cont;
}

public static boolean cade_valida(String cade){
	String comp="";
	for(int i=0;i<cade.length();i++){
	if (buscar(cade.substring(i,i+1),cade)>2){
		return false;
	}
	}
	
	for(int k=0;k<cade.length();k=k+4){
		String cad1=cade.substring(k,k+4);
		for(int j=0;j<4;j++){
	if(buscar(cad1.substring(j,j+1),cad1)>1){
		return false;
	}
		
	}
	}
	
return true;
}
public static void validar(Cromosoma x){
	
	String cadRep="",cadM="";
	for (int i=0;i<lista.size();i++){//Entrar a cada cromosoma
		for(int j=0;j<cantComp1;j++){//Entrar a cada competencia
			for(int k=0;k<4;k++){
				cadRep+=lista.get(i).compe[j].participantes[k].idN;
			}
		}
	if(!(cade_valida(cadRep))){
		listaAUX.add(x);
	}
	else{
		listaAUX.add(lista.get(i));
	}
	cadRep="";
	cadM="";
}
}

public static void main(String args []){
	double total_Fitness=0;
	int i=0;
	try{
		Scanner arNad=new Scanner(new File ("Nadadores.txt"));
		Scanner arCom=new Scanner(new File ("Competencias.txt"));
	while(arNad.hasNextLine()){
    		arNad.next();arNad.nextDouble();arNad.nextDouble();arNad.nextDouble();arNad.nextDouble();
   			i++;
    	}
    	cantNad=i;
    	i=0;
    while(arCom.hasNextLine()){
    		arCom.next();arCom.nextDouble();
   			i++;
    	}
    	cantComp1=i;
    	i=0;
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("ERROR! Archivo imposible de procesar!!");
	}
    	for(int k=0;k<cantComp1*2;k++){//Creacion de Poblacion Inicial!
    	Cromosoma c=new Cromosoma(k);
  		lista.add(c);
  		
    	}
    	int cantIteraciones=1;
    	int tolerancia=20;//cantidad de iteraciones a realizar
    	while(cantIteraciones<=tolerancia){
    	total_Fitness=0;
    	for(int k=0;k<lista.size();k++){
    		lista.get(k).aptitud=calcular_Funcion(lista.get(k));//Calculo de la F(x) de cada cromosoma  
    		
    		total_Fitness+=lista.get(k).aptitud;//Sumatoria de las aptitudes
    	}
    	
    	for(int k=0;k<lista.size();k++){
    		lista.get(k).prob_cruce=lista.get(k).aptitud/total_Fitness;//Calculo de Probabilidad de cruce a cada cromosoma
			    	
    	}
    	seleccion();
    	for(int m=0;m<lista.size();m++){//Luego de cruzar debo actualizar los valores de las variables necesarias para los
    	aptos.get(m).vaciar();
    	lista.get(m).copiar();//Los cromosomas como son la resta, la cantidad de competiciones cumplidas etc
    	}
    
    
    	mejor=mas_apto();
    	mejor.vaciar();
    	Cromosoma imperdible=new Cromosoma(-1);
    	imperdible.copiar();
    	cruzar();
    	for(int m=0;m<lista.size();m++){//Luego de cruzar debo actualizar los valores de las variables necesarias para los
    	lista.get(m).Actualizar();//Los cromosomas como son la resta, la cantidad de competiciones cumplidas etc
    	}
    	for(int k=0;k<lista.size();k++){
    		lista.get(k).aptitud=calcular_Funcion(lista.get(k));//Calculo de la F(x) de cada cromosoma  
    		
    		total_Fitness+=lista.get(k).aptitud;//Sumatoria de las aptitudes
    	}
    
    	listaAUX=new LinkedList<Cromosoma>();
    	validar(imperdible);
    	for(int m=0;m<lista.size();m++){//Luego de cruzar debo actualizar los valores de las variables necesarias para los
    	listaAUX.get(m).vaciar();
    	lista.get(m).copiar();//Los cromosomas como son la resta, la cantidad de competiciones cumplidas etc
    	}
    	mutar();
    
    	for(int m=0;m<lista.size();m++){//Luego de cruzar debo actualizar los valores de las variables necesarias para los
    	lista.get(m).Actualizar();//Los cromosomas como son la resta, la cantidad de competiciones cumplidas etc
    	}
    	for(int k=0;k<lista.size();k++){
    		lista.get(k).aptitud=calcular_Funcion(lista.get(k));//Calculo de la F(x) de cada cromosoma  
    		
    		total_Fitness+=lista.get(k).aptitud;//Sumatoria de las aptitudes
    	}
    	mejor=mas_apto();
    	
    	listaAUX=new LinkedList<Cromosoma>();
    	validar(imperdible);
    	for(int m=0;m<lista.size();m++){//Luego de cruzar debo actualizar los valores de las variables necesarias para los
    	listaAUX.get(m).vaciar();
    	lista.get(m).copiar();//Los cromosomas como son la resta, la cantidad de competiciones cumplidas etc
    	}
    	for(int m=0;m<lista.size();m++){//Luego de cruzar debo actualizar los valores de las variables necesarias para los
    	lista.get(m).Actualizar();//Los cromosomas como son la resta, la cantidad de competiciones cumplidas etc
    	}
    for(int k=0;k<lista.size();k++){
    		lista.get(k).aptitud=calcular_Funcion(lista.get(k));//Calculo de la F(x) de cada cromosoma  
    		
    		total_Fitness+=lista.get(k).aptitud;//Sumatoria de las aptitudes
    	}
    	mejor=mas_apto();
    			
    	
    listaAUX.clear();
    aptos.clear();
    	
    	cantIteraciones++;
    	}
    	mejor=mas_apto();
    
    		System.out.println("Mejor Cromosoma "+mejor.idCro+" estructura: ");
    		for(int k=0;k<cantComp1;k++){
    			for(int j=0;j<4;j++){
    				System.out.print(mejor.compe[k].participantes[j].idN+" ");
    			}
    			System.out.println(" ");
    		}
    		System.out.println("Aptitud :"+mejor.aptitud);
    		System.out.println("\n");
    			for(int k=0;k<cantComp1;k++){
    				System.out.println("Competencia: "+k);
    			for(int j=0;j<4;j++){
    				System.out.println("Tiempo del Nadador "+mejor.compe[k].participantes[j].idN+" en el Estilo "+j+" es "+mejor.compe[k].participantes[j].tiempos[j]);
    			}
    		}  	
   
}
} 