import java.util.*;
import java.io.*;
/**
 *PROGRAMA DESARROLLADO POR:
Antonio Rivero 	CI.	17976025
Evander Palacios CI.	18816770

 * Cromosoma de mi AG
 * aqui es lo divertido realmente el asunto!!
 */
public class Cromosoma {
  double aptitud,prob_cruce,limi,lims;//calculo del fitness
int cantMin;//Cantidad de veces que es el tiempo minimo entre su poblacion
int cantNad,cantCom;
int idCro;//Numero de cromosoma o individuo de mi poblacion
int cantComp;//Cantidad de competencias que se cumplen con el cromosoma actual
Competencia compe[];
Nadador tipos[];

    public Cromosoma(int lolo) {//Creacion de un cromosoma VALIDO YUPI!!
    int i=0;
	try{
		Scanner arNad=new Scanner(new File ("Nadadores.txt"));
		Scanner arNad1=new Scanner(new File ("Nadadores.txt"));
		Scanner arCom=new Scanner(new File ("Competencias.txt"));
		Scanner arCom1=new Scanner(new File ("Competencias.txt"));
		
    	while(arNad.hasNextLine()){
    		arNad.next();arNad.nextDouble();arNad.nextDouble();arNad.nextDouble();arNad.nextDouble();
   			i++;
   		
    	}
    	
    	cantNad=i;
    	tipos=new Nadador[i];
    	i=0;
    	while(arNad1.hasNextLine()){
    		arNad1.next();
    		tipos[i]=new Nadador(i,arNad1.nextDouble(),arNad1.nextDouble(),arNad1.nextDouble(),arNad1.nextDouble());
   			i++;
    	}
    	i=0;
    	while(arCom.hasNextLine()){
    		arCom.next();arCom.nextDouble();
   			i++;
    	}
    	compe=new Competencia[i];
    	cantCom=i;
    	i=0;
    	while(arCom1.hasNextLine()){
    		arCom1.next();
    		compe[i]=new Competencia (i,arCom1.nextDouble());
    		i++;
    	}
  
		
	}catch(Exception e){
		e.printStackTrace();
		System.out.println("ERROR! Archivo imposible de procesar!!");
	}
    idCro=lolo;
    
    	Random r= new Random();
    	for(int k=0;k<cantCom;k++){
    		double cont=0;
    		for(int j=0;j<4;j++){
    		
    	int id=r.nextInt(cantNad);//Numero aleatorio del id del nadador
    	while(!(tipos[id].comp<2&&tipos[id].comp1!=k&&tipos[id].comp2!=k)){//Las condiciones validad en ID
    	//No se puede competir mas de dos veces ni se puede competir en la misma carrera 2 veces
    		id=r.nextInt(cantNad);//Mientras No sea un ID valido sigo generando valores
    	}
    	
    	compe[k].participantes[j].idN=id;
    	if(tipos[id].comp1==-1){
    		compe[k].participantes[j].comp1=k;
    		compe[k].participantes[j].comp++;
    		compe[k].participantes[j].tiempos[j]=tipos[id].tiempos[j];
    		cont=cont+tipos[id].tiempos[j];
    		tipos[id].comp1=k;
    		tipos[id].comp++;
    		
    	}else{
    		compe[k].participantes[j].comp2=k;
    		compe[k].participantes[j].comp++;
    		compe[k].participantes[j].tiempos[j]=tipos[id].tiempos[j];
    		cont=cont+tipos[id].tiempos[j];//Contador de la suma de tiempos entre nadadores seleccionados
    		tipos[id].comp2=k;
    		tipos[id].comp++;
    		
    	}
    		}
    		compe[k].resta=compe[k].tmax/cont;//Calculo de la resta entre tmax y cont
    		if(compe[k].resta>=1){
    			compe[k].cumplida=true;
    			cantComp++;//Incremento de competencias cumplidas por el cromosoma
    		}
    		
    	}	
    }
    public void vaciar(){
    	try{PrintWriter ar=new PrintWriter(new File ("Mejor.txt"));
    	
    	ar.println(aptitud);
    	ar.println(prob_cruce);
    	ar.println(limi);
    	ar.println(lims);
    	ar.println(cantMin);
    	ar.println(cantNad);
    	ar.println(cantCom);
    	ar.println(idCro);
    	ar.println(cantComp);
    	for(int i=0;i<cantCom;i++){
    		
    		ar.println(compe[i].idC);ar.println(compe[i].tmax);ar.println(compe[i].sumNad);ar.println(compe[i].resta);
    		if(compe[i].cumplida){
    			
    			ar.println(1);
    		}else{
    			
    			ar.println(0);
    		}
    		for(int j=0;j<4;j++){
    			
    			ar.println(compe[i].participantes[j].idN);ar.println(compe[i].participantes[j].comp);ar.println(compe[i].participantes[j].comp1);
    			ar.println(compe[i].participantes[j].comp2);ar.println(compe[i].participantes[j].tiempo);
    			for(int k=0;k<4;k++){
    			
    				ar.println(compe[i].participantes[j].tiempos[k]);
    			}
    		}
    	}
    	for(int i=0;i<cantNad;i++){
    			ar.println(tipos[i].idN);ar.println(tipos[i].comp);ar.println(tipos[i].comp1);
    			ar.println(tipos[i].comp2);ar.println(tipos[i].tiempo);
    			for(int k=0;k<4;k++){
    				ar.println(tipos[i].tiempos[k]);
    			}
    	}
    
    	ar.flush();
    	ar.close();
    }catch(Exception e){
    }
    }
       public void copiar(){
    	try{
    	
    		int c=1;
    Scanner le=new Scanner (new File ("Mejor.txt"));
   
    
    	aptitud=Double.parseDouble(le.nextLine());
    
    	prob_cruce=Double.parseDouble(le.nextLine());
    
    	limi=Double.parseDouble(le.nextLine());
    
    	lims=Double.parseDouble(le.nextLine());
    
    	cantMin=le.nextInt();
    
    	cantNad=le.nextInt();
    
    	cantCom=le.nextInt();
    
    	idCro=le.nextInt();
    
    	cantComp=le.nextInt();
    
    	for(int i=0;i<cantCom;i++){
    	
    		compe[i].idC=le.nextInt();
    		compe[i].tmax=Double.parseDouble(le.next());
    		compe[i].sumNad=Double.parseDouble(le.next());compe[i].resta=Double.parseDouble(le.next());
    		
    		if(le.nextInt()==1){
    			compe[i].cumplida=true;
    		}else{
    			compe[i].cumplida=false;
    		}
    		
    			for(int j=0;j<4;j++){
    		
    			compe[i].participantes[j].idN=le.nextInt();compe[i].participantes[j].comp=le.nextInt();compe[i].participantes[j].comp1=le.nextInt();
    			compe[i].participantes[j].comp2=le.nextInt();compe[i].participantes[j].tiempo=Double.parseDouble(le.next());
    			for(int k=0;k<4;k++){
    					
    				compe[i].participantes[j].tiempos[k]=Double.parseDouble(le.next());
    			}
    		}
    	
    	}
    
    		for(int i=0;i<cantNad;i++){
    			tipos[i].idN=le.nextInt();tipos[i].comp=le.nextInt();tipos[i].comp1=le.nextInt();
    			tipos[i].comp2=le.nextInt();tipos[i].tiempo=Double.parseDouble(le.next());
    			for(int j=0;j<4;j++){
    				tipos[i].tiempos[j]=Double.parseDouble(le.next());
    			}
    		}
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    }
    public void Actualizar(){
    cantComp=0;
    	for(int k=0;k<cantCom;k++){
    		double cont=0;
    		for(int j=0;j<4;j++){
    			tipos[compe[k].participantes[j].idN].comp++;
    			if(tipos[compe[k].participantes[j].idN].comp==1){
    				tipos[compe[k].participantes[j].idN].comp1=k;
    				compe[k].participantes[j].comp1=k;
    			}else if(tipos[compe[k].participantes[j].idN].comp==2){
    				tipos[compe[k].participantes[j].idN].comp2=k;
    				compe[k].participantes[j].comp2=k;
    			}
    			cont=cont+tipos[compe[k].participantes[j].idN].tiempos[j];
    		}
    		compe[k].resta=compe[k].tmax/cont;//Calculo de la resta entre tmax y cont
    		if(compe[k].resta>=1){
    			compe[k].cumplida=true;
    			cantComp++;//Incremento de competencias cumplidas por el cromosoma
    		}
    	}
    }
}