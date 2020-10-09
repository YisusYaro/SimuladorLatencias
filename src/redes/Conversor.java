/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redes;
import java.math.*;
/**
 *
 * @author JesusAlejandro
 */
public class Conversor {
    
    static float metrosAKilometros(int metros){
        return metros/1000;
    }
    
    static float gigabytesABytes(float gigabytes){
        //return (float) (gigabytes*(Math.pow(10,9) ));
        return gigabytes*1024*1024*1024;
    }
    
    static BigDecimal bytesAMegabits(int bytes){
        BigDecimal bytess = new BigDecimal(bytes);
        BigDecimal divisor = new BigDecimal(125000);
        BigDecimal megabits= bytess.divide(divisor,MathContext.DECIMAL128);
        return megabits;
    }
    
    static void mostrarHorasMinutosSegundos(BigDecimal segundos){
        int tsegundos=segundos.intValue();
        
        int horas = (tsegundos / 3600);
        
        int minutos = ((tsegundos-horas*3600)/60);
        
        int segundoss = tsegundos-(horas*3600+minutos*60);
        
        System.out.println("Tiempo: "+horas + ":" + minutos+ ":" + segundoss);
        
    }
    
}
