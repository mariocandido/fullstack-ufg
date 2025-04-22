package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/calcular")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        try {
            Double num1 = Double.valueOf(request.getParameter("num1"));
            Double num2 = Double.valueOf(request.getParameter("num2"));
            String operacao = request.getParameter("operacao");
            Double resultado;

            switch (operacao) {
                case "soma":
                    resultado = num1 + num2;
                    break;
                case "subtracao":
                    resultado = num1 - num2;
                    break;
                case "multiplicacao":
                    resultado = num1 * num2;
                    break;
                case "divisao":
                    resultado = num1 / num2;
                    break;
                default:
                    response.getWriter().println("Verifique se a oeração está correta! São aceitos os valores 'soma', 'subtracao', 'multiplicacao' e 'divisao'");
                    return;
            }
            response.getWriter().println( "O resultado dessa " + operacao + " é igual a: " + resultado);
        } catch (Exception e) {
            response.getWriter().println( "Verifique se os parâmetros de entrada estão corretos!");
        }
    }

}
