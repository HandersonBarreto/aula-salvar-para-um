# Salvar Entidades com Relacionamento Para-Um (`@ManyToOne`)

Este projeto demonstra como salvar uma entidade com relacionamento `@ManyToOne` no Spring Boot usando duas abordagens diferentes com DTOs.

## Diagrama de Relacionamento

O sistema é composto por duas entidades:

- `Person` (Pessoa)
- `Department` (Departamento)

Uma pessoa está associada a **um único departamento**, enquanto um departamento pode ter **várias pessoas**:

![image](https://github.com/user-attachments/assets/683bc9bf-9847-47bd-9206-9902b734a21c)


## Objetivo

Explorar duas maneiras de representar a associação entre entidades no momento de persistência via JSON, simulando um cenário de cadastro de pessoas com departamento.

## Estruturas JSON utilizadas

### 1. DTO com objeto aninhado

```json
POST http://localhost:8080/people
{
  "name": "Nova Pessoa",
  "salary": 8000.0,
  "department": {
    "id": 1
  }
}
```
#### Quando Usar
- Quando a estrutura da entidade associada `Department` é mais complexa ou precisa ser expandida futuramente (ex: incluir `name, location, etc.`).
- Quando você já tem uma camada de DTO aninhada (ex: `PersonDepartmentDTO`) e quer manter a clareza do relacionamento.

 ### 2. DTO com `departmentId` direto (estrutura simplificada)
```json
 POST http://localhost:8080/people
{
  "name": "Nova Pessoa",
  "salary": 8000.0,
  "departmentId": 1
}
```
#### Quando Usar
- Quando o foco é simplicidade ou performance no front-end.
- Quando o ID é suficiente para estabelecer o vínculo (e.g. em sistemas onde os dados do `Department` já estão em cache).
- Ideal em *requisições simples*, como formulários com apenas os IDs selecionados.

## Tecnologias utilizadas
- Java 17+
- Spring Boot
- JPA / Hibernate
- H2 Database (em memória)

| Nome da Branch         | Descrição                                 |
|------------------------|-------------------------------------------|
| `main`                 | Estrutura base do projeto                 |
| `exercicio/dto-aninhado` | Abordagem usando DTO com objeto aninhado |
| `exercicio/dto-nao-aninhado`   | Abordagem usando DTO com departmentId    |

