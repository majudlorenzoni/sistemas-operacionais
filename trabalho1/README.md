# Escalonador de Tarefas
Este projeto implementa um simulador de escalonamento de um conjunto de tarefas conhecidas, utilizando políticas de escalonamento SJF (Shortest Job First) e LJF (Longest Job First). O projeto é para a disciplina de Sistemas Operacionais.

O programa recebe um arquivo contendo informações sobre as tarefas que serão escalonadas e a quantidade de processadores disponíveis. Cada linha do arquivo de entrada contém o nome da tarefa (identificação única) e um número inteiro que representa o tempo de execução em segundos.

Na branch **main** estão os arquivos prontos para serem compilados. 
Na branch **trabalho** estão os arquivos executáveis e os arquivos com os resultados que obtive no escalonamento.

Forma de executar o programa:

```sh
javac *.java
```
```
java Scheduler <tasks_file> <num_processors>
```

Exemplo: 
```
java Scheduler tarefas.txt 2
```
