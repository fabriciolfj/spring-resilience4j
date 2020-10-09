### Spring Resilience4j

Alguns conceitos relacionados ao resilience4j:
- Rate Limiter: garante que um serviço só aceite determinado número de requisições durante uma janela de tempo, garantindo que os recursos sejam utilizados de acordo com os limites desejados e que não sejam utilizados até a sua exaustão.

- Retry: permite que uma aplicação trate falhas momentâneas quando fizerem chamadas para serviços externos, garantindo que retentativas sejam feitas por um certo número de vezes. Caso não obtenham sucesso após todas as retentativas, a chamada ao método falha e a resposta deve ser tratada normalmente pela aplicação.
O retry permite que a API retente executar uma chamada externa que falhou diversas vezes, até atingir o valor máximo configurado. Se for bem sucedido, o contador irá zerar (contador para ver se vai abrir ou não o circuit breaker). 

- Bulkhead: garante que a falha em uma parte do sistema não cause o falha no sistema todo. Ele controla o número de chamadas concorrentes que um componente pode ter. Dessa maneira, o número de recursos esperando resposta do componente é limitado. Há dois tipos de implementações do bulkhead:
 - O Isolamento por semáforo: limita o número de chamadas concorrentes ao serviço, rejeitando imediatamente outras chamadas assim que o limite é alcançado;
 - O isolamento por thread pool: utiliza um thread pool para separar o serviço dos consumidores e limita cada consumidor a um subgrupo dos recursos do sistema.
 
 O abordagem por thread pool também provê uma fila de espera, rejeitando requisições apenas quando o pool e a fila estão cheias. O gerenciamento da thread pool adiciona um pouco desobrecarga, o que diminui um pouco a performance quando comparado ao uso de semáforos, mas permite que threads fiquem suspensas até expirar, caso não sejam executadas.
