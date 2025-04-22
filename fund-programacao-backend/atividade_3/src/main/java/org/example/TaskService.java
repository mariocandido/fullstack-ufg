package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskService {

    EmailNotificacaoService emailNotificacaoService = new EmailNotificacaoService();
    SMSNotificacaoService smsNotificacaoService = new SMSNotificacaoService();
    private ArrayList<String> taskList = new ArrayList<>();
    public String list() throws FileNotFoundException {
        if (taskList.isEmpty())  throw new FileNotFoundException( "Não há nenhuma tarefa disponível!" );

        String result = "";
        for(int i = 0; i < taskList.size(); i++)
            result +=  (i+1) + ". " + taskList.get(i) + "\n" ;

        emailNotificacaoService.enviarNotificacao();
        smsNotificacaoService.enviarNotificacao();

        return result;
    }

    public String add(String task) throws Exception {
        try {
            if (task.isEmpty())  {
                throw new FileNotFoundException( "Tarefa não pode ser vazia!" );
            }

            emailNotificacaoService.enviarNotificacao();
            smsNotificacaoService.enviarNotificacao();

            taskList.add(task.trim());

            return "Tarefa '" + task + "' adicionada!";

        } catch (NullPointerException e){
             throw new NullPointerException("Tarefa não pode estar vazia");
        } catch (Exception e) {
            throw new Exception("Erro ao executar! Verifique se os valores estão válidos." +
                    "\nERRO: " + e.getMessage());
        }
    }

    public String update(int index, String task) throws Exception {
        try{
            if (task.isEmpty())  {
                throw new FileNotFoundException( "Tarefa não pode ser vazia!" );
            }

            String oldTask = taskList.get(index-1);

            emailNotificacaoService.enviarNotificacao();
            smsNotificacaoService.enviarNotificacao();

            taskList.set(index-1, task.trim());

            return "Tarefa '" + oldTask + "' modificada para '" + task + "'!";

        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Não existe tarefa para o index escolhido!");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor de index inválido!");
        } catch (NullPointerException e) {
            throw new NullPointerException("Tarefa não pode ser vazia!");
        } catch (Exception e) {
            throw new Exception("Erro ao executar! Verifique se os valores estão válidos. " +
                    "\nERRO: " + e.getMessage());
        }
    }

    public String remove(int index) throws Exception {
        try {
            String oldTask = taskList.get(index-1);

            emailNotificacaoService.enviarNotificacao();
            smsNotificacaoService.enviarNotificacao();

            taskList.remove(index-1);

            return "Tarefa '" + oldTask + "' removida!";

        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Não existe tarefa para o index escolhido!");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Valor de index inválido!");
        } catch (Exception e) {
            throw new Exception("Erro ao executar! Verifique se os valores estão válidos. " +
                    "\nERRO: " + e.getMessage());
        }
    }
}
