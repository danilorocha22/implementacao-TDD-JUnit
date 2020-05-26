package com.manoelcampos.tdd;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Inclui métodos estáticos para fazer cálculos de propósito geral,
 * como o <a href="https://pt.wikipedia.org/wiki/Máximo_divisor_comum">MDC</a>.
 * 
 * <p>A implementação do cálculo do MDC seguiu as 
 * <a href="https://pt.wikipedia.org/wiki/Máximo_divisor_comum#Propriedades">propriedades matemáticas</a> 
 * de tal método e o processo de Test Driven Development (TDD).
 * Veja a classe MathUtilMdcTest.</p>
 * 
 * @author Manoel Campos da Silva Filho <http://github.com/manoelcampos>
 */
public class MathUtil {
    /**
     * Construtor privado para evitar que a classe seja instanciada.
     * Uma vez que ela só possui métodos estáticos e não
     * tem atributos, é totalmente desnecessário criar instâncias dela.
     */
    private MathUtil(){}
    
    /**
     * Calcula o MDC de dois números.
     * @param a 1º valor pra calcular o MDC
     * @param b 2º valor pra calcular o MDC
     * @return o MDC calculado
     *
     * @see <a href="https://pt.wikipedia.org/wiki/Máximo_divisor_comum#Propriedades">Propriedades MDC</a>
     */
    public static int mdc(int a, int b){
        /*
        Propriedade 7
        ------------------------------------------------------------------------------------------------
        */
        a = Math.abs(a);
        b = Math.abs(b);
        
        /*
        Propriedade 6
        ------------------------------------------------------------------------------------------------
        Ordena os valores de forma descrescente,
        o que causa efeito colateral em outras propriedades,
        como indicado abaixo.
        Ordenando decrescentemente,
        */
        final int min = Math.min(a, b);
        a = Math.max(a, b);        
        b = min;

        /*
        Propriedade 1
        ------------------------------------------------------------------------------------------------
        A lógica para atender à situação da Propriedade 3 (antes de atender à 7),
        quando a é negativo, seria
            if(a % b == 0)
                return Math.abs(b);
                
        Para a = -6 e b = 0, como ordenamos os valores no início, será calculado mdc(0,-6).
        Ao chegar neste if 0 % -6 == 0, o que retornaria -6 e não 6,
        como indica a Propriedade 3.
        
        No entanto, depois de resolvida a Propriedade 7, a lógica fica exatamente
        como definida na Propriedade 1.
        
        Esta condição também vale como caso base para quando a função
        for chamada recursivamente (ela chamando ela mesma, como 
        ocorre com o caso geral mostrado no final da função).
        O caso base (que indica que a função deve parar de ser chamada recursivamente)
        é quando a == b. No entanto, quando os dois valores forem iguais,
        isto também indica que a é divisível por b (que é a condição abaixo).
        Sem identificar inicialmente qual o caso base de uma função recursiva,
        a mesma ficará em loop infinito.
        */
        if(b > 0 && a % b == 0){
            return b;
        }
                
        /*
        Propriedade 3
        ------------------------------------------------------------------------------------------------
        Lógica modificada para atender à situação
        quando a é negativo, depois da ordenação dos dados para atender à Propriedade 6.
        Pela lógica da Propriedade 3, este código bastaria ser
            if(b == 0) 
                return Math.abs(a);
        Mas como ordenados os dados para atender à Propriedade 6, esta lógica precisou
        ser alterada também.
        */
        if(b == 0 || a == 0){
            return Math.max(Math.abs(a), Math.abs(b));
        }

        /*Este é o Caso Geral para cálculo do MDC.
        Resolvendo-se o caso geral, é mais fácil resolver 
        casos específicos que sejam apenas uma variação do caso geral.
        Existem diferentes maneiras de resolver o caso geral,
        com o Algoritmo de Euclides (https://pt.wikipedia.org/wiki/Algoritmo_de_Euclides)
        ou os métodos indicados em https://pt.wikipedia.org/wiki/Máximo_divisor_comum#Determinação_do_máximo_divisor_comum.
        Neste caso, estamos usando o método do item 3 da link acima.
        */
        return mdc(a - b, b);
    }
    
    public static int mdc(int ...valores){
        if(valores.length == 0){
            throw new IllegalArgumentException("Informe ao menos um valor para calcular o MDC");
        }
                
        int a = valores[0];
        for (int b : valores) {
            a = mdc(a, b);
        }
        
        return a;
    }

    /**
     * Verifica se um número é primo.
     * Um primo é um número natual maior que 1, que não pode ser formado pela multiplicação
     * de dois números naturais menores.
     *
     * @param numero número a ser testado.
     * @return true se o número for primo, false caso contrário
     *
     * @see <a href="https://pt.wikipedia.org/wiki/Número_primo">Número Primo</a>
     * @see <a href="https://en.wikipedia.org/wiki/Prime_number">Prime Number</a>
     */
    public static boolean isPrimo(final long numero){
        if(numero <= 1){
            return false;
        }

        for(long i = 2; i < numero; i++){
            if(numero%i==0){
                return false;
            }
        }

        return true;
    }
}