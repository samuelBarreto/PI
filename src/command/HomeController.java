package command;

import java.util.Date;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import model.Contato;
import model.DiasUteis;

public class HomeController implements Command{
	
	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String datapreenchida = request.getParameter("dataInicial");
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date datainicial = null;
		try {
			datainicial = (Date)formatter.parse(datapreenchida);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int diasinformados = Integer.parseInt(request.getParameter("diasUteis"));
		
        //Cria variável contador de dias uteis passados...            
        int contagemdias = 0;
        int diasferiado = 0;

        Calendar datafinal = Calendar.getInstance();
        datafinal.setTime(datainicial);
        int dayOfWeek = datafinal.get(Calendar.DAY_OF_WEEK);
        
        //laço de repetição pra todos os dias, no caso 3
        while (contagemdias < diasinformados || (dayOfWeek == 7 || dayOfWeek == 1))
        {
            //Verifica qual é o dia da semana da variável date
            switch (dayOfWeek)
            {
                //se for sábado, pula 2 dias, ou seja, para segunda e continua a contagem do while.
            	case 7:
            		datafinal.add(Calendar.DATE, 2);
                    break;
                //se for domingo, pula 1 dia, ou seja, para segunda e continua a contagem do while.
                case 1:
                	datafinal.add(Calendar.DATE, 1);
                    break;
                //caso seja qualquer dia diferente de sabado e domingo,  pula 1 dia e adiciona 1 na variável i que representa os dias úteis pulados                   
                default:
                	datafinal.add(Calendar.DATE, 1);
                    contagemdias++;
                    break;
            }
        }
        
        
        //System.out.println("#####################" + datafinal.toString());
        System.out.println("Time is "+datafinal.getTime());
       // RequestDispatcher view = null;
       // HttpSession session = request.getSession();
        
        DiasUteis ResultadoDias = new DiasUteis();
        ResultadoDias.setResultadoDias(datafinal.toString());
        PrintWriter out = response.getWriter();
        
       
        out.println("<html><head><title>Pais Cadastrado</title></head><body>");
		out.println(	"id: "+datafinal.getTime()+"<br>");
		
	    out.println("</body></html>");
		//session.setAttribute("ResultadoDias", ResultadoDias );	
		////view = request.getRequestDispatcher("ResultadoDias.jsp");
		
       // response.sendRedirect("ResultadoDias.jsp");	

        // retorna data 
    }
}


