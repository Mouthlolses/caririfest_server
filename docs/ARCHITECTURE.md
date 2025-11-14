- Arquitetura do Sistema


- Repository Layer:

→ Faz a ligação entre a entity e o database

- Service Layer:

→ Regras, validações, processos e lógica de negócio / Se comunica com repository para fazer operações no database.
→ Exposto para o Controller

- Controller Layer:

→ Recebe requisição HTTP, Valida entrada básica, Chama o service e devolve o resultado para o usuário.


//

- 