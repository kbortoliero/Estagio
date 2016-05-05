package br.sceweb.manipuladatas;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Months;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.ISODateTimeFormat;

public class ManipulaDatas {
	
	public static void main(String[] args) {
		DateTime dateTime = new DateTime();
		// Imprimindo a data no formato YYYY-MM-dd
		System.out.println("dateTime.toString() = " + dateTime.toString("YYYY-MM-dd"));
		// Imprimindo a data no formato YYYY-MM-dd HH:mm:ss
		System.out.println("dateTime.toString() = " + dateTime.toString("YYYY-MM-dd HH:mm:ss"));
		// Imprimindo o mês
		System.out.println("dateTime.toString() = " + dateTime.monthOfYear().getAsText());
		// Imprimindo o mês
		System.out.println("dateTime.toString() = " + dateTime.monthOfYear().getAsShortText());
		// Imprimindo o mês em Inglês
		System.out.println("dateTime.toString() = " + dateTime.monthOfYear().getAsText(Locale.ENGLISH));
		
		//formatacao da data
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-YYYY");
		// Alternativa 1
		System.out.println(fmt.print(dateTime));
		// Alternativa 2
		System.out.println(dateTime.toString(fmt));
		// Efetuando parse da string no formato "dd-MM-YYYY"
		dateTime = fmt.parseDateTime("21-12-2012");
		System.out.println(dateTime.toString(fmt));
		// Imprimindo no formato ISO8601
		fmt = ISODateTimeFormat.dateTime();
		System.out.println(fmt.print(dateTime));
		
		// Configurando o ano para 2010
		System.out.println(dateTime.withYear(2010));
		// Somando 20 dias
		System.out.println(dateTime.withYear(2010).plusDays(20));
		// Verificando se o ano é bissexto
		System.out.println(dateTime.withYear(2010).plusDays(20).year().isLeap());
		fmt = new DateTimeFormatterBuilder().appendDayOfMonth(2).
		// 2 Digito (Valor mínimo) - Preenche com 0 se for menor que 10
		appendLiteral('-'). // Separador
		appendMonthOfYearText(). // Mes como Texto
		appendLiteral('-'). // Separador
		appendYear(2, 4). // Numero minimo para impressao (2) | Numero maximo para parse (4)
		toFormatter();
		// Imprime DD-Mes Por Extenso-Ano
		System.out.println(fmt.print(dateTime));
		
		DateTime dataFinal = new DateTime();
		System.out.println("data final - hoje = " + dataFinal.toString("dd-MM-YYYY"));
		DateTime dataInicio = new DateTime(2015, 1, 1, 0, 0);
		System.out.println("data inicio - inicio do ano = " + dataInicio.toString("dd-MM-YYYY"));
		Days d = Days.daysBetween(dataInicio, dataFinal);
		System.out.println("Diferença dias:" + d.getDays());
		Years y = Years.yearsBetween(dataInicio, dataFinal);
		System.out.println("Diferença anos:" + y.getYears());
		Hours h = Hours.hoursBetween(dataInicio, dataFinal);
		System.out.println("Diferença horas:" +h.getHours());
		Months m = Months.monthsBetween(dataInicio, dataFinal);
		System.out.println("Diferença meses:" + m.getMonths()); 
	}
}