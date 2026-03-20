# 🌐 Sistema de Relógio Mundial com Sockets (Java)

Este projeto foi desenvolvido como atividade prática da disciplina de
**Sistemas Distribuídos**, com o objetivo de aplicar conceitos de
comunicação entre processos utilizando **Sockets em Java**.

A aplicação permite que um cliente solicite o horário atual de uma
determinada região geográfica, e o servidor retorna a data/hora
correspondente.

------------------------------------------------------------------------

## 🎯 Objetivo

Demonstrar na prática:

-   Comunicação via **UDP (sem conexão)**
-   Comunicação via **TCP (orientada à conexão)**
-   Servidor **concorrente com múltiplas threads**

------------------------------------------------------------------------

## ⚙️ Tecnologias utilizadas

-   Java
-   `java.net` (Sockets)
-   `java.time` (ZonedDateTime)

------------------------------------------------------------------------

## 📁 Estrutura do Projeto

    org.example
    │
    ├── udp_clock
    │   ├── ServidorUDP.java
    │   └── ClienteUDP.java
    │
    ├── tcp_clock_simple
    │   ├── ServidorTCP.java
    │   └── ClienteTCP.java
    │
    └── tcp_clock_multithread
        ├── ServidorTCP.java
        ├── ClienteTCP.java
        └── AtendeCliente.java

------------------------------------------------------------------------

## 🚀 Como executar

### 🔹 UDP

Servidor:

    java ServidorUDP

Cliente:

    java ClienteUDP

------------------------------------------------------------------------

### 🔹 TCP (Single Thread)

Servidor:

    java ServidorTCP

Cliente:

    java ClienteTCP

📌 Atende um cliente por vez.

------------------------------------------------------------------------

### 🔹 TCP Concorrente (Multithread)

Servidor:

    java ServidorTCP

Cliente:

    java ClienteTCP

📌 Atende múltiplos clientes simultaneamente usando threads.

------------------------------------------------------------------------

## 📌 Exemplos de entrada

    America/Sao_Paulo
    Europe/London
    Asia/Tokyo

------------------------------------------------------------------------

## 📌 Saída esperada

    Na região de America/Sao_Paulo são 14:32

ou

    Região invalida

------------------------------------------------------------------------

## 🧪 Conclusão

-   UDP é mais rápido, mas não garante entrega\
-   TCP é confiável, porém limitado sem concorrência\
-   TCP Multithread permite escalabilidade e melhor desempenho
