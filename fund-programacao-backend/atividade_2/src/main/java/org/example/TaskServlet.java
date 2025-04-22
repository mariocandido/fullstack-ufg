package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {

    private final ArrayList<String> taskList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        if (taskList.isEmpty()) {
            response.getWriter().println("Não há nenhuma tarefa disponível.");
            return;
        }
        for (int i = 0; i < taskList.size(); i++) {
            response.getWriter().println((i + 1) + ". " + taskList.get(i));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String task = request.getParameter("task").trim();
            if (task.isEmpty()) {
                response.getWriter().println("A tarefa não pode ser vazia.");
                return;
            }
            taskList.add(task);
            response.getWriter().println("Tarefa '" + task + "' adicionada com sucesso.");
        } catch (NullPointerException e) {
            response.getWriter().println("A tarefa não pode ser nula.");
        } catch (Exception e) {
            response.getWriter().println("Erro ao adicionar a tarefa. Verifique os valores fornecidos.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String task = request.getParameter("task").trim();
            if (task.isEmpty()) {
                response.getWriter().println("A tarefa não pode ser vazia.");
                return;
            }
            int index = Integer.parseInt(request.getParameter("index"));
            String oldTask = taskList.get(index - 1);
            taskList.set(index - 1, task);
            response.getWriter().println("Tarefa '" + oldTask + "' atualizada para '" + task + "'.");
        } catch (IndexOutOfBoundsException e) {
            response.getWriter().println("Índice de tarefa inválido.");
        } catch (NumberFormatException e) {
            response.getWriter().println("Valor do índice inválido.");
        } catch (NullPointerException e) {
            response.getWriter().println("A tarefa não pode ser nula.");
        } catch (Exception e) {
            response.getWriter().println("Erro ao atualizar a tarefa. Verifique os valores fornecidos.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int index = Integer.parseInt(request.getParameter("index"));
            String oldTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            response.getWriter().println("Tarefa '" + oldTask + "' removida com sucesso.");
        } catch (IndexOutOfBoundsException e) {
            response.getWriter().println("Índice de tarefa inválido.");
        } catch (NumberFormatException e) {
            response.getWriter().println("Valor do índice inválido.");
        } catch (Exception e) {
            response.getWriter().println("Erro ao remover a tarefa. Verifique os valores fornecidos.");
        }
    }
}
